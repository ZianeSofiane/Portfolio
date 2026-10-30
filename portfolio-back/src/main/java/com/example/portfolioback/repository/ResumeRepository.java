package com.example.portfolioback.repository;

import com.example.portfolioback.entity.Resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    boolean existsByName(String name);
}
