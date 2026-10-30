package com.example.portfolioback.dto;

import java.util.List;
import java.util.Map;

public record ProjectDto (
    Long id,
    Map<String, String> title,
    Map<String, String> description,
    String imageUrl,
    String githubUrl,
    String demoUrl,
    String previewUrl,
    boolean featured,
    List<ProjectSkillDto> projectSkills
) {}
