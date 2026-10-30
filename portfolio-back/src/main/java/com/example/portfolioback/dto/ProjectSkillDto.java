package com.example.portfolioback.dto;

public record ProjectSkillDto(
        Long id,
        String version,
        SkillDto skill
) {}
