package com.example.portfolioback.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "project_skill")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ProjectSkill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "skill_id")
    private Skill skill;

    private String version;
}

