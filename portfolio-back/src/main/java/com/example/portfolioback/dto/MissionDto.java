package com.example.portfolioback.dto;

import java.util.List;

public record MissionDto(
        String id,
        String role,
        String company,
        String clientOrSector,
        String period,
        String duration,
        String location,
        String contractType,
        String teamSize,
        String methodology,
        String context,
        List<ResponsibilityDto> responsibilities,
        List<String> techStack,
        Boolean featured
) {}
