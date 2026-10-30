package com.example.portfolioback.dto;

import java.util.List;
import java.util.Map;

public record ResumeDto(
        String name,
        Map<String, String> role,
        Map<String, String> intro,
        ContactDto contact,
        List<SkillSectionDto> skills,
        List<EducationDto> education,
        List<InterestDto> interests,
        List<ExperienceDto> experiences
) {
    public record ContactDto(
            String phone,
            String email,
            String location,
            String license,
            String linkedin,
            String github
    ) {}

    public record SkillSectionDto(
            Map<String, String> category,
            String items
    ) {}

    public record EducationDto(
            Map<String, String> title,
            String school
    ) {}

    public record InterestDto(
            Map<String, String> category,
            Map<String, String> items
    ) {}

    public record ExperienceDto(
            Map<String, String> role,
            String company,
            String location,
            Map<String, String> clientOrSector,
            Map<String, String> period,
            Map<String, String> description,
            List<Map<String, String>> tasks,
            List<String> techStack
    ) {}
}