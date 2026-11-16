package com.example.portfolioback;

import com.example.portfolioback.repository.ResumeRepository;
import com.example.portfolioback.controller.ProjectController;
import com.example.portfolioback.dto.ProjectDto;
import com.example.portfolioback.entity.Project;
import com.example.portfolioback.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PortfolioBackendApplicationTests {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ProjectController projectController;

    @Autowired
    private ProjectRepository projectRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void resumeContactIncludesPortfolioUrl() {
        var resume = resumeRepository.findByName("Sofiane ZIANE").orElseThrow();
        assertEquals("https://zianesofiane-portfolio.duckdns.org/", resume.getContact().portfolio());
    }

    @Test
    void portfolioDetailsArePersistedAndExposedInBothLanguages() {
        var portfolio = projectController.getAllProjects().stream()
                .filter(project -> "https://github.com/ZianeSofiane/Portfolio".equals(project.githubUrl()))
                .findFirst().orElseThrow();
        assertTrue(portfolio.skillsPortfolioDescription().get("fr").contains("Caddy"));
        assertTrue(portfolio.skillsPortfolioDescription().get("en").contains("DuckDNS"));
        assertTrue(!portfolio.description().get("fr").contains("Caddy"));
    }

    @Test
    @Transactional
    void projectsAreDisplayedInSeededOrder() {
        var expected = List.of(
                "https://github.com/ZianeSofiane/Portfolio",
                "https://github.com/ZianeSofiane/ISN_Terminale",
                "https://github.com/ZianeSofiane/MiniJeuxJavaFx",
                "https://github.com/ZianeSofiane/Flutter_App"
        );
        assertEquals(expected, projectController.getAllProjects().stream().map(ProjectDto::githubUrl).toList());
        assertEquals(expected, projectController.getFeaturedProjects().stream().map(ProjectDto::githubUrl).toList());
        assertEquals(List.of(1, 2, 3, 4), projectRepository.findAllInDisplayOrder().stream()
                .map(Project::getPosition).toList());
    }

    @Test
    @Transactional
    void projectOrderUsesPositionThenIdAndPlacesUnpositionedProjectsLast() {
        var portfolio = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/Portfolio").orElseThrow();
        var geoquizz = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/ISN_Terminale").orElseThrow();
        var flutter = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/Flutter_App").orElseThrow();
        portfolio.setPosition(3);
        geoquizz.setPosition(3);
        flutter.setPosition(1);
        portfolio.setFeatured(false);

        var unpositioned = new Project();
        unpositioned.setTitle(Map.of("fr", "Autre projet", "en", "Other project"));
        unpositioned.setGithubUrl("https://example.com/other");
        unpositioned.setFeatured(true);
        projectRepository.saveAndFlush(unpositioned);

        var expected = List.of(
                flutter.getGithubUrl(),
                portfolio.getGithubUrl(),
                geoquizz.getGithubUrl(),
                "https://github.com/ZianeSofiane/MiniJeuxJavaFx",
                unpositioned.getGithubUrl()
        );
        assertEquals(expected, projectController.getAllProjects().stream().map(ProjectDto::githubUrl).toList());
        assertEquals(expected.stream().filter(url -> !url.equals(portfolio.getGithubUrl())).toList(),
                projectController.getFeaturedProjects().stream().map(ProjectDto::githubUrl).toList());
    }
}
