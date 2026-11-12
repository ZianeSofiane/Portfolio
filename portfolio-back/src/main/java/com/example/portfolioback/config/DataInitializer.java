package com.example.portfolioback.config;
import com.example.portfolioback.dto.ResumeDto;
import com.example.portfolioback.entity.*;
import com.example.portfolioback.repository.*;
import com.example.portfolioback.service.TranslationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(ProjectRepository projectRepository, ProjectSkillRepository projectSkillRepository, SkillRepository skillRepository,
                               TranslationService translationService, MissionRepository missionRepository, ResumeRepository resumeRepository) {
        return args -> {
            initSkillsData(projectRepository, projectSkillRepository, skillRepository, translationService);
            initMissionsData(missionRepository);
            initResumeData(resumeRepository);
        };
    }

    private void initSkillsData(ProjectRepository projectRepository, ProjectSkillRepository projectSkillRepository, SkillRepository skillRepository,
                                TranslationService translationService){
        Skill java = createOrUpdateSkill(skillRepository, "Java", "Backend");
        Skill springBoot = createOrUpdateSkill(skillRepository, "Spring Boot", "Backend");
        Skill angular = createOrUpdateSkill(skillRepository, "Angular", "Frontend");
        Skill tailwind = createOrUpdateSkill(skillRepository, "Tailwind CSS", "Frontend");
        createOrUpdateSkill(skillRepository, "PrimeNg", "Frontend");
        createOrUpdateSkill(skillRepository, "Jira", "DevOps");
        createOrUpdateSkill(skillRepository, "Azure", "DevOps");
        createOrUpdateSkill(skillRepository, "Gitlab", "DevOps");
        createOrUpdateSkill(skillRepository, "Jenkins", "DevOps");
        createOrUpdateSkill(skillRepository, "SonarQube", "DevOps");
        createOrUpdateSkill(skillRepository, "PL/SQL", "Backend");
        createOrUpdateSkill(skillRepository, "PostgreSQL", "Database");
        createOrUpdateSkill(skillRepository, "H2 Database", "Database");
        createOrUpdateSkill(skillRepository, "PowerQuery", "Data");
        createOrUpdateSkill(skillRepository, "PowerBI", "Data");
        Skill docker = createOrUpdateSkill(skillRepository, "Docker", "DevOps");
        createOrUpdateSkill(skillRepository, "Git", "DevOps");
        createOrUpdateSkill(skillRepository, "Github", "DevOps");
        createOrUpdateSkill(skillRepository, "Typescript", "Frontend");
        Skill flutter = createOrUpdateSkill(skillRepository, "Flutter", "Frontend");
        Skill html = createOrUpdateSkill(skillRepository, "Html", null);
        Skill css = createOrUpdateSkill(skillRepository, "Css", null);
        Skill javascript = createOrUpdateSkill(skillRepository, "Javascript", null);
        Skill javaFx = createOrUpdateSkill(skillRepository, "JavaFx", null);
        createOrUpdateSkill(skillRepository, "Shell", null);
        createOrUpdateSkill(skillRepository, "Tomcat", null);

        // --- PORTFOLIO ---
        Project portfolio = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/Portfolio")
                .orElseGet(Project::new);
        portfolio.setTitle(translationService.get(TranslationService.PROJECT_PORTFOLIO_TITLE));
        portfolio.setDescription(translationService.get(TranslationService.PROJECT_PORTFOLIO_DESCRIPTION));
        portfolio.setFeatured(true);
        portfolio.setGithubUrl("https://github.com/ZianeSofiane/Portfolio");
        portfolio.setImageUrl("portfolio.png");
        portfolio = projectRepository.save(portfolio);

        ProjectSkill pfJava = createOrUpdateProjectSkill(projectSkillRepository, portfolio, java, "25");
        ProjectSkill pfSpring = createOrUpdateProjectSkill(projectSkillRepository, portfolio, springBoot, "4.1.1");
        ProjectSkill pfAngular = createOrUpdateProjectSkill(projectSkillRepository, portfolio, angular, "21");
        ProjectSkill pfTailwind = createOrUpdateProjectSkill(projectSkillRepository, portfolio, tailwind, "4.3");
        ProjectSkill pfDocker = createOrUpdateProjectSkill(projectSkillRepository, portfolio, docker, "29.1.3");
        addProjectSkills(portfolio, pfJava, pfSpring, pfAngular, pfTailwind, pfDocker);
        projectRepository.save(portfolio);

        // --- GEOQUIZZ ---
        Project geoquizz = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/ISN_Terminale")
                .orElseGet(Project::new);
        geoquizz.setTitle(translationService.get(TranslationService.PROJECT_ISN_TITLE));
        geoquizz.setDescription(translationService.get(TranslationService.PROJECT_ISN_DESCRIPTION));
        geoquizz.setFeatured(true);
        geoquizz.setGithubUrl("https://github.com/ZianeSofiane/ISN_Terminale");
        geoquizz.setImageUrl("geoquizz.png");
        geoquizz.setPreviewUrl("geoquizz.gif");
        geoquizz.setDemoUrl("https://cozy-sopapillas-7be157.netlify.app/");
        geoquizz = projectRepository.save(geoquizz); // Sauvegarde immédiate

        ProjectSkill gqHtml = createOrUpdateProjectSkill(projectSkillRepository, geoquizz, html, null);
        ProjectSkill gqCss = createOrUpdateProjectSkill(projectSkillRepository, geoquizz, css, null);
        ProjectSkill gqJs = createOrUpdateProjectSkill(projectSkillRepository, geoquizz, javascript, null);
        addProjectSkills(geoquizz, gqHtml, gqCss, gqJs);
        projectRepository.save(geoquizz);

        // --- JAVAFX ---
        Project javaFxProject = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/MiniJeuxJavaFx")
                .orElseGet(Project::new);
        javaFxProject.setTitle(translationService.get(TranslationService.PROJECT_JFX_TITLE));
        javaFxProject.setDescription(translationService.get(TranslationService.PROJECT_JFX_DESCRIPTION));
        javaFxProject.setFeatured(true);
        javaFxProject.setGithubUrl("https://github.com/ZianeSofiane/MiniJeuxJavaFx");
        javaFxProject.setImageUrl("javaFx.png");
        javaFxProject.setPreviewUrl("javaFx.gif");
        javaFxProject = projectRepository.save(javaFxProject);

        ProjectSkill fxJavaFx = createOrUpdateProjectSkill(projectSkillRepository, javaFxProject, javaFx, null);
        ProjectSkill fxCss = createOrUpdateProjectSkill(projectSkillRepository, javaFxProject, css, null);
        addProjectSkills(javaFxProject, fxJavaFx, fxCss);
        projectRepository.save(javaFxProject);

        // --- FLUTTER ---
        Project flutterProject = projectRepository.findByGithubUrl("https://github.com/ZianeSofiane/Flutter_App")
                .orElseGet(Project::new);
        flutterProject.setTitle(translationService.get(TranslationService.PROJECT_FT_TITLE));
        flutterProject.setDescription(translationService.get(TranslationService.PROJECT_FT_DESCRIPTION));
        flutterProject.setFeatured(true);
        flutterProject.setGithubUrl("https://github.com/ZianeSofiane/Flutter_App");
        flutterProject.setImageUrl("soap.png");
        flutterProject.setPreviewUrl("flutter.gif");
        flutterProject = projectRepository.save(flutterProject);

        ProjectSkill ftFlutter = createOrUpdateProjectSkill(projectSkillRepository, flutterProject, flutter, null);
        addProjectSkills(flutterProject, ftFlutter);
        projectRepository.save(flutterProject);
    }

    private Skill createOrUpdateSkill(SkillRepository skillRepository, String name, String category) {
        Skill skill = skillRepository.findByName(name).orElseGet(Skill::new);
        skill.setName(name);
        skill.setCategory(category);
        return skillRepository.save(skill);
    }

    private ProjectSkill createOrUpdateProjectSkill(ProjectSkillRepository projectSkillRepository, Project project, Skill skill, String version) {
        ProjectSkill projectSkill = projectSkillRepository.findByProjectAndSkill(project, skill)
                .orElseGet(ProjectSkill::new);
        projectSkill.setProject(project);
        projectSkill.setSkill(skill);
        projectSkill.setVersion(version);
        return projectSkillRepository.save(projectSkill);
    }

    private void addProjectSkills(Project project, ProjectSkill... projectSkills) {
        Set<ProjectSkill> existingSkills = project.getProjectSkills();
        if (existingSkills == null) {
            existingSkills = new HashSet<>();
        }
        existingSkills.addAll(List.of(projectSkills));
        project.setProjectSkills(existingSkills);
    }

    private void initMissionsData(MissionRepository missionRepository){
        Mission cgiTma = new Mission(
                "cgi-tma",
                Map.of(
                        "fr", "Ingénieur études et développement",
                        "en", "Software engineer / developer"
                ),
                "Sopra Steria",
                Map.of(
                        "fr", "CGI Finance — TMA Banque & Assurance",
                        "en", "CGI Finance — Banking & Insurance Maintenance & Evolution"
                ),
                LocalDate.of(2024, 2, 1),
                LocalDate.of(2025, 2, 1),
                Map.of(
                        "fr", "1 an",
                        "en", "1 year"
                ),
                "Marc-en-Barœul",
                Mission.ContractType.CDI,
                Map.of(
                        "fr", "12 personnes (6 fonctionnels CGI / 6 techniques Sopra Steria)",
                        "en", "12 members (6 CGI functional consultants / 6 Sopra Steria technical engineers)"
                ),
                Map.of(
                        "fr", "Agile SAFe — cycles de 8 semaines (7 runs + 1 run de flottement/refactoring), rituels Scrum, pilotage Jira & Kanban",
                        "en", "Agile SAFe — 8-week cycles (7 runs + 1 floating/refactoring run), Scrum ceremonies, Jira & Kanban management"
                ),
                Map.of(
                        "fr", "Intervention en régie au sein d'un pôle de Maintenance et d'Évolution (TMA) pour un logiciel d'automatisation de production documentaire (contrats bancaires et d'assurances). Gestion des flux documentaires à grande échelle, combinant automatisation nocturne (batchs scripts Shell) et interactions en temps réel avec les outils des gestionnaires bancaires.",
                        "en", "On-site consulting within a Maintenance and Evolution unit (TMA) for automated document generation software (banking and insurance contracts). High-volume document workflow management combining nightly batch automation (Shell scripts) and real-time integration with bank operations tools."
                ),
                List.of(
                        new Responsibility(
                                null,
                                Map.of(
                                        "fr", "Architecture & environnement technique",
                                        "en", "Architecture & technical environment"
                                ),
                                List.of(
                                        Map.of(
                                                "fr", "Backend & traitements : Java, PL/SQL (requêtage avancé et règles métiers complexes basées sur le profil client)",
                                                "en", "Backend & processing: Java, PL/SQL (advanced querying and complex business rules based on customer profiles)"
                                        ),
                                        Map.of(
                                                "fr", "Édition documentaire : KSL Studio (modèles de documents dynamiques via variables et structures JSON)",
                                                "en", "Document publishing: KSL Studio (dynamic document templates using variables and JSON payloads)"
                                        ),
                                        Map.of(
                                                "fr", "Serveurs, déploiement & DevOps : Apache Tomcat, Microsoft Azure (versioning), scripts Shell (batchs de nuit)",
                                                "en", "Servers, deployment & DevOps: Apache Tomcat, Microsoft Azure (versioning), Shell scripts (nightly batch jobs)"
                                        )
                                )
                        ),
                        new Responsibility(
                                null,
                                Map.of(
                                        "fr", "Études d’impact & chiffrage",
                                        "en", "Impact studies & estimation"
                                ),
                                List.of(
                                        Map.of(
                                                "fr", "Analyse des tickets Jira et des maquettes fonctionnelles fournis par l’équipe métier",
                                                "en", "Analysis of Jira tickets and functional mockups provided by the business team"
                                        ),
                                        Map.of(
                                                "fr", "Études de faisabilité technique, chiffrage des évolutions et identification des contraintes sur l’existant",
                                                "en", "Technical feasibility studies, feature estimation, and identification of constraints on legacy systems"
                                        ),
                                        Map.of(
                                                "fr", "Participation ponctuelle à des ateliers métiers pour cadrer les besoins complexes et présenter des solutions techniques adaptées",
                                                "en", "Occasional participation in business workshops to scope complex requirements and suggest technical solutions"
                                        )
                                )
                        ),
                        new Responsibility(
                                null,
                                Map.of(
                                        "fr", "Développement & qualité",
                                        "en", "Development & quality"
                                ),
                                List.of(
                                        Map.of(
                                                "fr", "Conception et développement des évolutions et corrections sur la stack Java / PL/SQL",
                                                "en", "Design and implementation of features and bug fixes on the Java / PL/SQL stack"
                                        ),
                                        Map.of(
                                                "fr", "Validation du code en environnement de développement et accompagnement de la recette fonctionnelle métier",
                                                "en", "Code validation in development environments and support during user acceptance testing (UAT)"
                                        )
                                )
                        ),
                        new Responsibility(
                                null,
                                Map.of(
                                        "fr", "Exploitation, Support & MEP",
                                        "en", "Operations, Support & Deployment"
                                ),
                                List.of(
                                        Map.of(
                                                "fr", "Mises en production nocturnes (astreinte) pour éviter toute interruption des flux diurnes",
                                                "en", "Nightly production deployments (on-call duty) to prevent daytime workflow disruption"
                                        ),
                                        Map.of(
                                                "fr", "Support applicatif et surveillance quotidienne : rotation journalière pour le contrôle du démarrage des moteurs Tomcat et le suivi des batchs de nuit (un millier par nuit)",
                                                "en", "Application support and daily monitoring: daily rotation to check Tomcat engine startups and track nightly batch runs (~1,000 runs per night)"
                                        ),
                                        Map.of(
                                                "fr", "Diagnostic et résolution d’anomalies de production complexes (erreurs de génération documentaire, support aux gestionnaires)",
                                                "en", "Diagnosis and resolution of complex production issues (document generation errors, operational user support)"
                                        )
                                )
                        )
                ),

                List.of("Java", "PL/SQL", "KSL Studio", "JSON", "Apache Tomcat", "Microsoft Azure", "Shell", "Jira"),

                true
        );

        Mission sncfGisement = new Mission(
                "sncf-gisement",
                Map.of("fr", "Développeur Full Stack Java Spring / Angular", "en", "Java Spring / Angular Full Stack developer"),
                "Sopra Steria",
                Map.of("fr", "SNCF (Direction du Matériel / Réseau) — Gisement de Données", "en", "SNCF (Equipment / Network Directorate) — Data Repository"),
                LocalDate.of(2022, 8, 1),
                LocalDate.of(2024, 2, 1),
                Map.of("fr", "1 an 7 mois", "en", "1 year 7 months"),
                "Saint-Denis (à distance depuis Lille)",
                Mission.ContractType.CDI,
                Map.of("fr", "12 personnes (6 développeurs / 6 fonctionnels) — Centre de Services", "en", "12 members (6 developers / 6 functional consultants) — Service Center"),
                Map.of("fr", "Hybride : Cycle en V (refonte globale) puis Agile (évolutions), pilotage Jira, rituels dev quotidiens, revue de code croisée", "en", "Hybrid: V-Model (global redesign) then Agile (evolutions), Jira management, daily dev rituals, cross-code review"),
                Map.of("fr", "Refonte complète et évolutions d'un gisement de données centralisé regroupant l'ensemble des incidents, défaillances et ressources de la SNCF. L'application sert de « Source Unique de Vérité » (Single Source of Truth) : toute modification validée sur un élément est propagée en temps réel via des APIs REST aux sous-applications de l'écosystème SNCF.", "en", "Complete redesign and enhancements of a centralized data repository aggregating all SNCF incidents, failures, and resources. The application serves as a 'Single Source of Truth': any validated modification on an item is propagated in real-time via REST APIs to sub-applications within the SNCF ecosystem."),
                List.of(
                        new Responsibility(null, Map.of("fr", "Architecture & stack technique", "en", "Architecture & technical stack"), List.of(
                                Map.of("fr", "Backend : Java 17, Spring Boot, Spring Data JPA (CrudRepository), PostgreSQL", "en", "Backend: Java 17, Spring Boot, Spring Data JPA (CrudRepository), PostgreSQL"),
                                Map.of("fr", "Frontend : Angular 15, TypeScript, PrimeNG (tableaux de données complexes et formulaires dynamiques)", "en", "Frontend: Angular 15, TypeScript, PrimeNG (complex data tables and dynamic forms)"),
                                Map.of("fr", "Qualité & DevOps : IntelliJ (SonarLint/SonarQube), GitLab CI, Jenkins (pipelines CI/CD d’automatisation des tests et déploiements)", "en", "Quality & DevOps: IntelliJ (SonarLint/SonarQube), GitLab CI, Jenkins (CI/CD test and deployment automation pipelines)")
                        )),
                        new Responsibility(null, Map.of("fr", "Refonte & développement full stack (binôme Front / Back)", "en", "Full stack redesign & development (Front/Back pair)"), List.of(
                                Map.of("fr", "Développement découplé et coordonné en équipe (alignement collaboratif des DTOs, des interfaces et des endpoints REST entre Front et Back)", "en", "Decoupled and coordinated team development (collaborative alignment of DTOs, interfaces, and REST endpoints between Front-end and Back-end)"),
                                Map.of("fr", "Implémentation d’un système complexe d’espace de travail (workflow de validation) : isolation des modifications d’incidents avant validation par des super-utilisateurs (gestion fine des droits et profils)", "en", "Implementation of a complex workspace system (validation workflow): isolation of incident modifications prior to validation by super-users (fine-grained management of rights and profiles)")
                        )),
                        new Responsibility(null, Map.of("fr", "Algorithmique & contraintes métiers (relations parent/enfant)", "en", "Algorithms & business constraints (parent/child relations)"), List.of(
                                Map.of("fr", "Conception du moteur de dépendances temporelles entre défaillances (causes) et incidents (conséquences)", "en", "Design of the temporal dependency engine between failures (causes) and incidents (consequences)"),
                                Map.of("fr", "Algorithmes d’ajustement automatique des fenêtres de dates (superposition forcée si chevauchement) et blocage préventif avec messages d’erreur explicites", "en", "Algorithms for automatic date window adjustment (forced overlap if overlapping) and preventive blocking with explicit error messages")
                        )),
                        new Responsibility(null, Map.of("fr", "Cadrage, négociation & gestion de crise métier", "en", "Scoping, Negotiation & business crisis management"), List.of(
                                Map.of("fr", "Rédaction des Spécifications Techniques Détaillées (STD), plans de tests et chiffrages associés", "en", "Drafting of Detailed Technical Specifications (DTS), test plans, and associated estimates"),
                                Map.of("fr", "Animation de réunions de cadrage technique pour résoudre les incohérences métier (contraintes temporelles contradictoires demandées par les utilisateurs)", "en", "Facilitation of technical scoping meetings to resolve business inconsistencies (contradictory time constraints requested by users)"),
                                Map.of("fr", "Arbitrage des périmètres, renégociation de faisabilité et compléments de chiffrage avec les équipes fonctionnelles", "en", "Scope arbitration, feasibility renegotiation, and additional estimations with functional teams")
                        )),
                        new Responsibility(null, Map.of("fr", "Qualité du code & collaboration", "en", "Code quality & collaboration"), List.of(
                                Map.of("fr", "Revue de code rigoureuse (MR GitLab) en pair-reviewing entre développeurs et validation du Lead Dev", "en", "Rigorous code review (GitLab MR) in peer-reviewing between developers and validation by the Lead Dev")
                        ))
                ),
                List.of("Java 17", "Spring Boot", "Spring Data JPA", "PostgreSQL", "Angular 15", "TypeScript", "PrimeNG", "SonarQube", "GitLab CI", "Jenkins"),
                true
        );

        Mission sncfJira = new Mission(
                "sncf-jira",
                Map.of("fr", "Ingénieur études et développement (Stage de fin d’études)", "en", "Software engineer (Master's internship)"),
                "Sopra Steria",
                Map.of("fr", "SNCF Réseau — Centre de Services", "en", "SNCF Réseau — Service Center"),
                LocalDate.of(2022, 3, 1),
                LocalDate.of(2022, 7, 1),
                Map.of("fr", "5 mois", "en", "5 months"),
                "Lille / Saint-Denis",
                Mission.ContractType.Stage,
                null,
                Map.of("fr", "Kanban / Agile, intervention multi-projets en centre de services", "en", "Kanban / Agile, multi-project intervention in a service center"),
                Map.of("fr", "Stage de fin d'études au sein du Centre de Services SNCF Réseau, axé sur l'automatisation des processus internes, la modernisation des stacks logicielles legacy, la refonte des infrastructures de build et le suivi décisionnel de la performance.", "en", "Master's internship within the SNCF Réseau Service Center, focusing on internal process automation, modernization of legacy software stacks, build infrastructure redesign, and decision-making performance tracking."),
                List.of(
                        new Responsibility(null, Map.of("fr", "Stack & outils", "en", "Stack & tools"), List.of(
                                Map.of("fr", "Langages & frameworks : Java, APIs REST Jira", "en", "Languages & frameworks: Java, Jira REST APIs"),
                                Map.of("fr", "DevOps & build : Artifactory, Sonatype Nexus, Jenkins (Jenkinsfile / pipelines CI-CD), Git", "en", "DevOps & build: Artifactory, Sonatype Nexus, Jenkins (Jenkinsfile / CI-CD pipelines), Git"),
                                Map.of("fr", "Data & BI : Power BI, Power Query (modélisation de données et rapports KPIs)", "en", "Data & BI: Power BI, Power Query (data modeling and KPI reports)")
                        )),
                        new Responsibility(null, Map.of("fr", "Infrastructure devOps & migration d’artefacts (Nexus => Artifactory)", "en", "DevOps infrastructure & artifact migration (Nexus => Artifactory)"), List.of(
                                Map.of("fr", "Migration de la gestion des dépôts d’artefacts logiciels pour plus de 30 projets du centre de services", "en", "Migration of software artifact repository management for over 30 service center projects"),
                                Map.of("fr", "Audit et mise à jour des scripts de builds (Jenkinsfile) pour rediriger la résolution de dépendances vers Artifactory", "en", "Audit and update of build scripts (Jenkinsfile) to redirect dependency resolution to Artifactory"),
                                Map.of("fr", "Montée de version des bibliothèques et validation de la non-régression via la réexécution des pipelines complets (Checkout, Build, Test, Archive, Deploy)", "en", "Library upgrades and non-regression validation through re-execution of full pipelines (Checkout, Build, Test, Archive, Deploy)"),
                                Map.of("fr", "Coordination : prise d’initiative sur le pilotage du sujet, puis délégation et répartition des charges avec 2 autres développeurs", "en", "Coordination: initiative on topic management, followed by delegation and workload distribution with 2 other developers")
                        )),
                        new Responsibility(null, Map.of("fr", "Refactoring & modernisation de code (projet Laplace — gestion de chantiers ferroviaires)", "en", "Code refactoring & modernization (Laplace project — rail construction management)"), List.of(
                                Map.of("fr", "Migration applicative de Java 7 vers Java 8", "en", "Application migration from Java 7 to Java 8"),
                                Map.of("fr", "Refactoring et optimisation des traitements complexes via l’API Streams et les Lambdas", "en", "Refactoring and optimization of complex processing via Streams API and Lambdas")
                        )),
                        new Responsibility(null, Map.of("fr", "Automatisation d’outillage interne (projet Pitaya — Java 17)", "en", "Internal tooling automation (Pitaya project — Java 17)"), List.of(
                                Map.of("fr", "Conception d'un service d'ingestion de données via l'API REST Jira pour extraire les KPIs des tickets, les stocker en base et alimenter les tableaux de bord Power BI", "en", "Design of a data ingestion service via the Jira REST API to extract ticket KPIs, store them in a database, and power Power BI dashboards"),
                                Map.of("fr", "Développement d’un service d’automatisation de génération de tickets Jira via les APIs REST de la suite Atlassian, conçu en Test Driven Development (TDD) pour garantir la robustesse du code.", "en", "Developed a Java automation service for generating Jira tickets via the Atlassian REST APIs, built using Test-Driven Development (TDD) to ensure code robustness."),
                                Map.of("fr", "Collaboration et qualité logicielle : mise en œuvre de sessions régulières de Pair Programming (via CodeWithMe) pour accélérer la conception et partager les bonnes pratiques.", "en", "Collaboration & software quality: Implemented regular Pair Programming sessions (via CodeWithMe) to accelerate design and share best practices."),
                                Map.of("fr", "Environnement de test sécurisé avec Mockoon (bouchonnage d’APIs REST) et Mockito (tests unitaires) pour valider les flux sans impacter l’instance Jira de production", "en", "Secure test environment with Mockoon (REST API stubbing) and Mockito (unit tests) to validate workflows without impacting the production Jira instance")
                        )),
                        new Responsibility(null, Map.of("fr", "Reporting & Suivi de performance (BI / KPIs)", "en", "Reporting & Performance Tracking (BI / KPIs)"), List.of(
                                Map.of("fr", "Modélisation et conception autonomes de tableaux de bord décisionnels avec Power BI et Power Query pour le suivi des indicateurs clés d’activité (KPIs)", "en", "Autonomous modeling and design of decision-making dashboards with Power BI and Power Query for KPI tracking")
                        ))
                ),
                List.of("Java", "Java 17", "REST", "Jira API", "Artifactory", "Nexus", "Jenkins", "Git", "Mockoon", "Mockito", "Power BI", "Power Query"),
                false
        );

        Mission prodival = new Mission(
                "prodival",
                Map.of("fr", "Développeur web", "en", "Web Developer"),
                "Prodival",
                Map.of("fr", "Bombardier, Arcelor Mittal, CFT", "en", "Bombardier, Arcelor Mittal, CFT"),
                LocalDate.of(2019, 4, 1),
                LocalDate.of(2019, 5, 1),
                Map.of("fr", "2 mois", "en", "2 months"),
                "Rosult",
                Mission.ContractType.Stage,
                null,
                null,
                Map.of("fr", "Refonte de l'aide en ligne du logiciel (outils pour le management de la qualité).", "en", "Redesign of the software's online help (quality management tools)."),
                List.of(
                        new Responsibility(null, Map.of("fr", "Développement", "en", "Development"), List.of(
                                Map.of("fr", "Codage en HTML, CSS, jQuery, C# (Visual Studio)", "en", "Coding in HTML, CSS, jQuery, C# (Visual Studio)")
                        ))
                ),
                List.of("HTML", "CSS", "jQuery", "C#"),
                false
        );

        missionRepository.saveAll(List.of(cgiTma, sncfGisement, sncfJira, prodival));
    }

    private void initResumeData(ResumeRepository resumeRepository){
        Resume resume = resumeRepository.findByName("Sofiane ZIANE").orElseGet(Resume::new);
        resume.setName("Sofiane ZIANE");

        resume.setRole(Map.of(
                "fr", "Développeur Full Stack Java Spring / Angular",
                "en", "Full Stack Java Spring / Angular Developer"
        ));

        resume.setIntro(Map.of(
                "fr", "Développeur full stack Java / Spring et Angular, spécialisé dans la conception et la maintenance d'applications web en environnements industriels et agiles (SAFe).",
                "en", "Full-stack Java / Spring and Angular developer focused on designing and maintaining web applications in industrial and agile (SAFe) environments."
        ));

        resume.setContact(new ResumeDto.ContactDto(
                "06.71.42.65.65",
                "zzianesofiane@gmail.com",
                "Valenciennes (59300)",
                "Permis B",
                "https://www.linkedin.com/in/sofiane-ziane-a52814225/",
                "https://github.com/ZianeSofiane"
        ));

        resume.setSkills(List.of(
                new ResumeDto.SkillSectionDto(
                        Map.of("fr", "Back-end", "en", "Back end"),
                        "Java, Spring Boot"
                ),
                new ResumeDto.SkillSectionDto(
                        Map.of("fr", "Front-end", "en", "Front end"),
                        "Angular, TypeScript, JavaScript, HTML, CSS"
                ),
                new ResumeDto.SkillSectionDto(
                        Map.of("fr", "Bases de données", "en", "Databases"),
                        "PostgreSQL, PL/SQL"
                ),
                new ResumeDto.SkillSectionDto(
                        Map.of("fr", "CI/CD & outils", "en", "CI/CD & tools"),
                        "GitLab CI, Jenkins, SonarQube, Jira, Apache Tomcat, Azure"
                )
        ));

        resume.setInterests(List.of(
                new ResumeDto.InterestDto(
                        Map.of("fr", "Langues", "en", "Languages"),
                        Map.of("fr", "Français (langue maternelle), anglais (B2)", "en", "French (native), English (B2)")
                )
        ));

        resume.setEducation(List.of(
                new ResumeDto.EducationDto(
                        Map.of("fr", "Master TNSI (Technologies Nouvelles et Systèmes d'Informations)", "en", "Master's Degree in New Technologies and Information Systems"),
                        "INSA, UPHF"
                ),
                new ResumeDto.EducationDto(
                        Map.of("fr", "Licence Informatique", "en", "Bachelor's Degree in Computer Science"),
                        "INSA, UPHF"
                ),
                new ResumeDto.EducationDto(
                        Map.of("fr", "Baccalauréat Scientifique (Spécialité ISN)", "en", "Scientific Baccalaureate (ISN Specialty)"),
                        null
                )
        ));

        resume.setExperiences(List.of(
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Ingénieur d'études et développement Java", "en", "Java Software Engineer"),
                        "Sopra Steria",
                        "Lille",
                        Map.of("fr", "CGI Finance — TMA Banque & Assurance", "en", "CGI Finance — AMS Banking & Insurance"),
                        Map.of("fr", "Février 2024 – Février 2025", "en", "February 2024 – February 2025"),
                        null,
                        List.of(
                                Map.of("fr", "Analyse et chiffrage des évolutions (via Jira) : études de faisabilité technique et conception des solutions pour la TMA", "en", "Analyzed and estimated enhancements using Jira, assessed technical feasibility, and designed solutions for application maintenance"),
                                Map.of("fr", "Maintenance corrective et évolutive d'applications critiques (stack Java / PL/SQL et KSL Studio) : garantie de la stabilité en production et gestion des évolutions techniques", "en", "Maintained and enhanced critical Java / PL/SQL and KSL Studio applications, ensuring production stability and managing technical changes"),
                                Map.of("fr", "Mises en production nocturnes et support applicatif critique dans le cadre d'astreintes", "en", "Supported overnight production releases and critical application support on call")
                        ),
                        List.of("Java", "PL/SQL", "KSL Studio", "Shell", "Azure", "Jira")
                ),
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Développeur Full Stack", "en", "Full Stack developer"),
                        "Sopra Steria",
                        "Saint-Denis (Distance / Lille)",
                        Map.of("fr", "SNCF Réseau — Gisement de données", "en", "SNCF Réseau — Data Repository"),
                        Map.of("fr", "Août 2022 – Février 2024", "en", "August 2022 – February 2024"),
                        Map.of("fr", "Refonte complète d'un référentiel centralisé de données sur les incidents et défaillances de SNCF Réseau.", "en", "Redesigned a centralized data repository for SNCF Réseau incidents and failures."),
                        List.of(
                                Map.of("fr", "Développement full stack (Spring Boot / Angular 15) du gisement de données centralisé : création de nouvelles fonctionnalités et interfaces utilisateur", "en", "Developed the centralized data repository with Spring Boot and Angular 15, creating new features and user interfaces"),
                                Map.of("fr", "Conception d'un moteur de dépendances temporelles et de règles de validation des modifications parent-enfant", "en", "Designed a temporal dependency engine and parent-child change validation rules"),
                                Map.of("fr", "Rédaction des spécifications techniques et plans de tests, et revues de code via GitLab", "en", "Wrote technical specifications and test plans, and conducted code reviews through GitLab")
                        ),
                        List.of("Java 17", "Spring Boot", "PostgreSQL", "Angular 15", "PrimeNG", "GitLab CI", "Jenkins", "SonarQube")
                ),
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Ingénieur d'études et développement (stage)", "en", "Software Engineer (internship)"),
                        "Sopra Steria",
                        "Lille / Saint-Denis",
                        Map.of("fr", "SNCF Réseau — Centre de Services", "en", "SNCF Réseau — Service Center"),
                        Map.of("fr", "Mars 2022 – Juillet 2022", "en", "March 2022 – July 2022"),
                        Map.of("fr", "Automatisation de processus internes et modernisation de l'écosystème logiciel.", "en", "Automated internal processes and modernized the software ecosystem."),
                        List.of(
                                Map.of("fr", "Migration de dépôts d'artefacts (Nexus vers Artifactory) et mise à jour des pipelines Jenkins (pratiques DevOps)", "en", "Migrated artifact repositories from Nexus to Artifactory and updated Jenkins pipelines using DevOps practices"),
                                Map.of("fr", "Modernisation de code legacy : migration de Java 7 vers Java 8 (Streams & Lambdas API) pour améliorer la maintenabilité", "en", "Modernized legacy code by migrating from Java 7 to Java 8 (Streams and Lambdas API) to improve maintainability"),
                                Map.of("fr", "Flux de données end-to-end : extraction de KPIs via l'API REST Jira (développé en Java / Spring Boot), stockage en base de données et alimentation des rapports Power BI", "en", "Built an end-to-end data flow in Java / Spring Boot: extracted KPIs via the Jira REST API, stored them in a database, and fed Power BI reports"),
                                Map.of("fr", "Conception de tableaux de bord et d'indicateurs avec Power BI et Power Query", "en", "Built dashboards and KPIs with Power BI and Power Query")
                        ),
                        List.of("Java 17", "REST API", "Artifactory", "Nexus", "Jenkins", "Git", "Mockito", "Power BI")
                ),
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Développeur web (stage)", "en", "Web developer (internship)"),
                        "Prodival",
                        "Rosult",
                        Map.of("fr", "Projets clients : Bombardier, ArcelorMittal, CFT", "en", "Client projects: Bombardier, ArcelorMittal, CFT"),
                        Map.of("fr", "Avril 2019 – Mai 2019", "en", "April 2019 – May 2019"),
                        Map.of("fr", "Refonte de l'aide en ligne d'un logiciel de management de la qualité.", "en", "Redesign of the online help system for a quality management software."),
                        List.of(
                                Map.of("fr", "Développement front-end et back-end de pages d'aide en ligne", "en", "Developed front-end and back-end pages for the online help system")
                        ),
                        List.of("HTML", "CSS", "jQuery", "C# (.NET)")
                )
        ));

        resumeRepository.save(resume);
    }
}
