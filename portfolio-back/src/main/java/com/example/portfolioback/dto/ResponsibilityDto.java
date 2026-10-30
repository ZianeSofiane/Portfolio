package com.example.portfolioback.dto;

import java.util.List;

public record ResponsibilityDto(
        String category,
        List<String> tasks
) {}
