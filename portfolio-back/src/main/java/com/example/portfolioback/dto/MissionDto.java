package com.example.portfolioback.dto;

import java.time.LocalDate;
import java.util.List;

public record MissionDto(
        String id,
        String role,
        String company,
        String clientOrSector,
        LocalDate startDate,
        LocalDate endDate,
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
