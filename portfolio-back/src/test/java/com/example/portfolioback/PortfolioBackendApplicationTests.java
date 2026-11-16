package com.example.portfolioback;

import com.example.portfolioback.repository.ResumeRepository;
import com.example.portfolioback.controller.ProjectController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class PortfolioBackendApplicationTests {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private ProjectController projectController;

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

}
