package com.example.portfolioback.repository;

import com.example.portfolioback.entity.Project;
import com.example.portfolioback.entity.ProjectSkill;
import com.example.portfolioback.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectSkillRepository extends JpaRepository<ProjectSkill, Long> {
    Optional<ProjectSkill> findByProjectAndSkill(Project project, Skill skill);
}
