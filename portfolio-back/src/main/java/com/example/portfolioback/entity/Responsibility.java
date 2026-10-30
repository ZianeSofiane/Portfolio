package com.example.portfolioback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "responsibilities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Responsibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stocké en JSON dans la BDD (ex: {"fr": "...", "en": "..."})
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, String> category;

    // Stocké en JSON dans la BDD (ex: [{"fr": "...", "en": "..."}, ...])
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private List<Map<String, String>> tasks;
}
