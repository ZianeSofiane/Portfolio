package com.example.portfolioback.service;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class TranslationService {

    public static final String EN = "en";
    public static final String FR = "fr";

    public static final String PROJECT_PORTFOLIO_TITLE = "project.portfolio.title";
    public static final String PROJECT_PORTFOLIO_DESCRIPTION = "project.portfolio.description";
    public static final String PROJECT_PORTFOLIO_SKILLS_DESCRIPTION = "project.portfolio.skillsDescription";
    public static final String PROJECT_ISN_TITLE = "project.isn.title";
    public static final String PROJECT_ISN_DESCRIPTION = "project.isn.description";
    public static final String PROJECT_JFX_TITLE = "project.jfx.title";
    public static final String PROJECT_JFX_DESCRIPTION = "project.jfx.description";
    public static final String PROJECT_FT_TITLE = "project.ft.title";
    public static final String PROJECT_FT_DESCRIPTION = "project.ft.description";

    private final Map<String, Map<String, String>> dictionary = Map.of(
            PROJECT_PORTFOLIO_TITLE, Map.of(
                    EN, "My Full-Stack Portfolio",
                    FR, "Mon Portfolio Full-Stack"),
            PROJECT_PORTFOLIO_DESCRIPTION, Map.of(
                    EN, "A modern, high-performance portfolio built with Angular (21), Java (25), Spring Boot (4) and Tailwind CSS v4, with GitHub Copilot, containerized with Docker and hosted on Oracle Cloud Infrastructure (OCI)",
                    FR, "Un portfolio moderne et ultra-performant développé avec Angular (21), Java (25), Spring Boot (4) et Tailwind CSS v4 avec GitHub Copilot, conteneurisé avec Docker et hébergé sur Oracle Cloud Infrastructure (OCI)"),
            PROJECT_PORTFOLIO_SKILLS_DESCRIPTION, Map.of(
                    EN, """
                            Context & objective: Design and development of a high-performance full-stack portfolio, containerized and independently hosted in the cloud.

                            Architecture & tooling:
                            - Development with IntelliJ IDEA Ultimate (Java 25 / Spring Boot 4+, Angular 21, Tailwind CSS).
                            - Containerization of services with Docker.
                            - Routing, traffic management and reverse proxy configured with Caddy on Oracle Cloud Infrastructure (OCI).
                            - Dynamic domain name resolution managed through DuckDNS.""",
                    FR, """
                            Contexte & objectif : Conception et développement d'un portfolio full-stack haute performance, conteneurisé et hébergé en autonomie complète sur le cloud.

                            Architecture & outillage :
                            - Développement sous IntelliJ IDEA Ultimate (Java 25 / Spring Boot 4+, Angular 21, Tailwind CSS).
                            - Conteneurisation des services via Docker.
                            - Routage, gestion du trafic et reverse proxy configurés avec Caddy sur Oracle Cloud Infrastructure (OCI).
                            - Résolution de nom de domaine dynamique gérée via DuckDNS."""),
            PROJECT_ISN_TITLE, Map.of(
                    EN, "GeoQuizz",
                    FR, "GeoQuizz"),
            PROJECT_ISN_DESCRIPTION, Map.of(
                    EN, "A geography mini-game built during my senior year in high school for the Computer Science and Digital Sciences curriculum",
                    FR, "Petit jeu de géographie développé en classe de Terminale Scientifique dans le cadre de la spécialité informatique et Sciences du Numérique"),
            PROJECT_JFX_TITLE, Map.of(
                    EN, "JavaFX mini-games",
                    FR, "Mini-jeux javaFx"),
            PROJECT_JFX_DESCRIPTION, Map.of(
                    EN, "Mini-games (Sudoku, Motus, Hangman, Crossword puzzles) developed using JavaFX during undergraduate studies",
                    FR, "Mini-jeux (Sudoku, Motus, Pendu, Mots fléchés) développés avec javaFx durant les études en licence"),
            PROJECT_FT_TITLE, Map.of(
                    EN, "Soap inventory management",
                    FR, "Gestion de stock de savons"),
            PROJECT_FT_DESCRIPTION, Map.of(
                    EN, "Soap inventory management application developed with Flutter",
                    FR, "Application de gestions de stocks de savon développée avec Flutter")
    );

    /** Returns all translations of a key, keyed by language code. */
    public Map<String, String> get(String key) {
        Map<String, String> translations = dictionary.get(key);
        if (translations == null) {
            throw new IllegalArgumentException("Unknown translation key: " + key);
        }
        return new LinkedHashMap<>(translations);
    }
}
