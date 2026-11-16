package com.example.portfolioback.repository;
import com.example.portfolioback.entity.Project;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("select p from Project p order by case when p.position is null then 1 else 0 end, p.position, p.id")
    List<Project> findAllInDisplayOrder();

    @Query("select p from Project p where p.featured = true order by case when p.position is null then 1 else 0 end, p.position, p.id")
    List<Project> findFeaturedInDisplayOrder();
    @EntityGraph(attributePaths = "projectSkills")
    Optional<Project> findByGithubUrl(String githubUrl);
}
