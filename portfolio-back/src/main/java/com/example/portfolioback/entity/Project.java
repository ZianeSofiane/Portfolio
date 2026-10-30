package com.example.portfolioback.entity;

import jakarta.persistence.*;
import com.example.portfolioback.converter.TranslationMapConverter;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // JSON: {"en": "...", "fr": "..."}
    @NotEmpty(message = "Le titre du projet est obligatoire")
    @Convert(converter = TranslationMapConverter.class)
    @Column(nullable = false, columnDefinition = "TEXT")
    private Map<String, String> title;

    // JSON: {"en": "...", "fr": "..."}
    @Convert(converter = TranslationMapConverter.class)
    @Column(columnDefinition = "TEXT")
    private Map<String, String> description;

    private String imageUrl;
    private String githubUrl;
    private String demoUrl;
    private String previewUrl;
    private boolean featured;

    @ManyToMany
    @JoinTable(
            name = "project_skills",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    @Builder.Default
    private Set<ProjectSkill> projectSkills = new HashSet<>();
}

