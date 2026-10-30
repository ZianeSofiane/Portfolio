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
                    EN, "A modern, high-performance portfolio built with Angular, Spring Boot 3+ and Tailwind CSS v4",
                    FR, "Un portfolio moderne et ultra-performant développé avec Angular, Spring Boot 3+ et Tailwind CSS v4"),
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
