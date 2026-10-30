package com.example.portfolioback.controller;
import com.example.portfolioback.dto.ProjectDto;
import com.example.portfolioback.dto.ProjectSkillDto;
import com.example.portfolioback.dto.SkillDto;
import com.example.portfolioback.entity.Project;
import com.example.portfolioback.repository.ProjectRepository;
import com.example.portfolioback.service.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    private final ProjectService projectService;
    private final ProjectRepository projectRepository;

    public ProjectController(ProjectService projectService, ProjectRepository projectRepository) {
        this.projectService = projectService;
        this.projectRepository = projectRepository;
    }

    @GetMapping
    @Transactional(readOnly = true) // Sécurise le lazy loading si nécessaire
    public List<ProjectDto> getAllProjects() {
        return projectRepository.findAll().stream().map(this::mapProjectToDto).collect(Collectors.toList());
    }

    // Récupérer uniquement les projets mis en avant : GET http://localhost:8080/api/projects/featured
    @GetMapping("/featured")
    public List<ProjectDto> getFeaturedProjects() {
        return projectService.getFeaturedProjects().stream().map(this::mapProjectToDto).collect(Collectors.toList());
    }

    // Récupérer un projet par son ID : GET http://localhost:8080/api/projects/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProjectDto> getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id)
                .map(this::mapProjectToDto).map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    private ProjectDto mapProjectToDto(Project project) {
        // Mappe la liste des liaisons ProjectSkill -> ProjectSkillDto
        List<ProjectSkillDto> skillDtos = project.getProjectSkills().stream().map(ps -> {
            // Mappe le Skill de base
            SkillDto skillDto = new SkillDto(
                    ps.getSkill().getId(),
                    ps.getSkill().getName(),
                    ps.getSkill().getCategory()
            );
            // Crée le DTO de liaison avec la version
            return new ProjectSkillDto(
                    ps.getId(),
                    ps.getVersion(),
                    skillDto
            );
        }).collect(Collectors.toList());

        return new ProjectDto(
                project.getId(),
                project.getTitle(),
                project.getDescription(),
                project.getImageUrl(),
                project.getGithubUrl(),
                project.getDemoUrl(),
                project.getPreviewUrl(),
                project.isFeatured(),
                skillDtos
        );
    }
}
