package com.example.portfolioback.config;
import com.example.portfolioback.dto.ResumeDto;
import com.example.portfolioback.entity.*;
import com.example.portfolioback.repository.*;
import com.example.portfolioback.service.TranslationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
            initMissionsData(translationService, missionRepository);
            initResumeData(translationService, resumeRepository);
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
        portfolio = projectRepository.save(portfolio); // Sauvegarde immédiate pour obtenir un ID

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
        javaFxProject = projectRepository.save(javaFxProject); // Sauvegarde immédiate

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
        flutterProject = projectRepository.save(flutterProject); // Sauvegarde immédiate

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

    private void initMissionsData(TranslationService translationService, MissionRepository missionRepository){
        Mission cgiTma = new Mission(
                "cgi-tma",
                Map.of(
                        "fr", "Ingénieur Études et Développement",
                        "en", "Software Engineer / Developer"
                ),
                "Sopra Steria",
                Map.of(
                        "fr", "CGI Finance — TMA Banque & Assurance",
                        "en", "CGI Finance — Banking & Insurance Maintenance & Evolution"
                ),
                Map.of(
                        "fr", "Février 2024 – Février 2025",
                        "en", "February 2024 – February 2025"
                ),
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
                                        "fr", "Architecture & Environnement technique",
                                        "en", "Architecture & Technical Environment"
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
                                        "fr", "Études d’impact & Chiffrage",
                                        "en", "Impact Studies & Estimation"
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
                                        "fr", "Développement & Qualité",
                                        "en", "Development & Quality"
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
                Map.of("fr", "Ingénieur Études et Développement Full-Stack", "en", "Full-Stack Software Engineer"),
                "Sopra Steria",
                Map.of("fr", "SNCF (Direction du Matériel / Réseau) — Gisement de Données", "en", "SNCF (Equipment / Network Directorate) — Data Repository"),
                Map.of("fr", "Août 2022 – Février 2024", "en", "August 2022 – February 2024"),
                Map.of("fr", "1 an 7 mois", "en", "1 year 7 months"),
                "Saint-Denis (à distance depuis Lille)",
                Mission.ContractType.CDI,
                Map.of("fr", "12 personnes (6 développeurs / 6 fonctionnels) — Centre de Services", "en", "12 members (6 developers / 6 functional consultants) — Service Center"),
                Map.of("fr", "Hybride : Cycle en V (refonte globale) puis Agile (évolutions), pilotage Jira, rituels dev quotidiens, revue de code croisée", "en", "Hybrid: V-Model (global redesign) then Agile (evolutions), Jira management, daily dev rituals, cross-code review"),
                Map.of("fr", "Refonte complète et évolutions d'un gisement de données centralisé regroupant l'ensemble des incidents, défaillances et ressources de la SNCF. L'application sert de « Source Unique de Vérité » (Single Source of Truth) : toute modification validée sur un élément est propagée en temps réel via des APIs REST aux sous-applications de l'écosystème SNCF.", "en", "Complete redesign and enhancements of a centralized data repository aggregating all SNCF incidents, failures, and resources. The application serves as a 'Single Source of Truth': any validated modification on an item is propagated in real-time via REST APIs to sub-applications within the SNCF ecosystem."),
                List.of(
                        new Responsibility(null, Map.of("fr", "Architecture & Stack technique", "en", "Architecture & Technical Stack"), List.of(
                                Map.of("fr", "Backend : Java 17, Spring Boot, Spring Data JPA (CrudRepository), PostgreSQL", "en", "Backend: Java 17, Spring Boot, Spring Data JPA (CrudRepository), PostgreSQL"),
                                Map.of("fr", "Frontend : Angular 15, TypeScript, PrimeNG (tableaux de données complexes et formulaires dynamiques)", "en", "Frontend: Angular 15, TypeScript, PrimeNG (complex data tables and dynamic forms)"),
                                Map.of("fr", "Qualité & DevOps : IntelliJ (SonarLint/SonarQube), GitLab CI, Jenkins (pipelines CI/CD d’automatisation des tests et déploiements)", "en", "Quality & DevOps: IntelliJ (SonarLint/SonarQube), GitLab CI, Jenkins (CI/CD test and deployment automation pipelines)")
                        )),
                        new Responsibility(null, Map.of("fr", "Refonte & Développement Full-Stack (binôme Front / Back)", "en", "Full-Stack Redesign & Development (Front/Back pair)"), List.of(
                                Map.of("fr", "Développement découplé et coordonné en binôme (alignement des DTOs, interfaces et endpoints REST entre dev Front et dev Back)", "en", "Decoupled and coordinated pair development (alignment of DTOs, interfaces, and REST endpoints between Front and Back devs)"),
                                Map.of("fr", "Implémentation d’un système complexe d’Espace de Travail (workflow de validation) : isolation des modifications d’incidents avant validation par des super-utilisateurs (gestion fine des droits et profils)", "en", "Implementation of a complex Workspace system (validation workflow): isolation of incident modifications before validation by super-users (fine-grained rights and profiles management)")
                        )),
                        new Responsibility(null, Map.of("fr", "Algorithmique & Contraintes métiers (relations Parent/Enfant)", "en", "Algorithms & Business Constraints (Parent/Child relations)"), List.of(
                                Map.of("fr", "Conception du moteur de dépendances temporelles entre défaillances (causes) et incidents (conséquences)", "en", "Design of the temporal dependency engine between failures (causes) and incidents (consequences)"),
                                Map.of("fr", "Algorithmes d’ajustement automatique des fenêtres de dates (superposition forcée si chevauchement) et blocage préventif avec messages d’erreur explicites", "en", "Algorithms for automatic date window adjustment (forced overlap if overlapping) and preventive blocking with explicit error messages")
                        )),
                        new Responsibility(null, Map.of("fr", "Cadrage, Négociation & Gestion de crise métier", "en", "Scoping, Negotiation & Business Crisis Management"), List.of(
                                Map.of("fr", "Rédaction des Spécifications Techniques Détaillées (STD), plans de tests et chiffrages associés", "en", "Drafting of Detailed Technical Specifications (DTS), test plans, and associated estimates"),
                                Map.of("fr", "Animation de réunions de cadrage technique pour résoudre les incohérences métier (contraintes temporelles contradictoires demandées par les utilisateurs)", "en", "Facilitation of technical scoping meetings to resolve business inconsistencies (contradictory time constraints requested by users)"),
                                Map.of("fr", "Arbitrage des périmètres, renégociation de faisabilité et compléments de chiffrage avec les équipes fonctionnelles", "en", "Scope arbitration, feasibility renegotiation, and additional estimations with functional teams")
                        )),
                        new Responsibility(null, Map.of("fr", "Qualité du code & Collaboration", "en", "Code Quality & Collaboration"), List.of(
                                Map.of("fr", "Revue de code rigoureuse (MR GitLab) en pair-reviewing entre développeurs et validation du Lead Dev", "en", "Rigorous code review (GitLab MR) in peer-reviewing between developers and validation by the Lead Dev")
                        ))
                ),
                List.of("Java 17", "Spring Boot", "Spring Data JPA", "PostgreSQL", "Angular 15", "TypeScript", "PrimeNG", "SonarQube", "GitLab CI", "Jenkins"),
                true
        );

        Mission sncfJira = new Mission(
                "sncf-jira",
                Map.of("fr", "Ingénieur Études et Développement (Stage de fin d’études)", "en", "Software Engineer (Master's Internship)"),
                "Sopra Steria",
                Map.of("fr", "SNCF Réseau — Centre de Services", "en", "SNCF Réseau — Service Center"),
                Map.of("fr", "Mars 2022 – Juillet 2022", "en", "March 2022 – July 2022"),
                Map.of("fr", "5 mois", "en", "5 months"),
                "Lille / Saint-Denis",
                Mission.ContractType.Stage,
                null,
                Map.of("fr", "Kanban / Agile, intervention multi-projets en centre de services", "en", "Kanban / Agile, multi-project intervention in a service center"),
                Map.of("fr", "Stage de fin d'études au sein du Centre de Services SNCF Réseau, axé sur l'automatisation des processus internes, la modernisation des stacks logicielles legacy, la refonte des infrastructures de build et le suivi décisionnel de la performance.", "en", "Master's internship within the SNCF Réseau Service Center, focusing on internal process automation, modernization of legacy software stacks, build infrastructure redesign, and decision-making performance tracking."),
                List.of(
                        new Responsibility(null, Map.of("fr", "Stack & Outils", "en", "Stack & Tools"), List.of(
                                Map.of("fr", "Langages & frameworks : Java, APIs REST Jira", "en", "Languages & frameworks: Java, Jira REST APIs"),
                                Map.of("fr", "DevOps & Build : Artifactory, Sonatype Nexus, Jenkins (Jenkinsfile / pipelines CI-CD), Git", "en", "DevOps & Build: Artifactory, Sonatype Nexus, Jenkins (Jenkinsfile / CI-CD pipelines), Git"),
                                Map.of("fr", "Data & BI : Power BI, Power Query (modélisation de données et rapports KPIs)", "en", "Data & BI: Power BI, Power Query (data modeling and KPI reports)")
                        )),
                        new Responsibility(null, Map.of("fr", "Infrastructure DevOps & Migration d’artefacts (Nexus => Artifactory)", "en", "DevOps Infrastructure & Artifact Migration (Nexus => Artifactory)"), List.of(
                                Map.of("fr", "Migration de la gestion des dépôts d’artefacts logiciels pour plus de 30 projets du centre de services", "en", "Migration of software artifact repository management for over 30 service center projects"),
                                Map.of("fr", "Audit et mise à jour des scripts de builds (Jenkinsfile) pour rediriger la résolution de dépendances vers Artifactory", "en", "Audit and update of build scripts (Jenkinsfile) to redirect dependency resolution to Artifactory"),
                                Map.of("fr", "Montée de version des bibliothèques et validation de la non-régression via la réexécution des pipelines complets (Checkout, Build, Test, Archive, Deploy)", "en", "Library upgrades and non-regression validation through re-execution of full pipelines (Checkout, Build, Test, Archive, Deploy)"),
                                Map.of("fr", "Coordination : prise d’initiative sur le pilotage du sujet, puis délégation et répartition des charges avec 2 autres développeurs", "en", "Coordination: initiative on topic management, followed by delegation and workload distribution with 2 other developers")
                        )),
                        new Responsibility(null, Map.of("fr", "Refactoring & Modernisation de code (projet Laplace — gestion de chantiers ferroviaires)", "en", "Code Refactoring & Modernization (Laplace project — rail construction management)"), List.of(
                                Map.of("fr", "Migration applicative de Java 7 vers Java 8", "en", "Application migration from Java 7 to Java 8"),
                                Map.of("fr", "Refactoring et optimisation des traitements complexes via l’API Streams et les Lambdas", "en", "Refactoring and optimization of complex processing via Streams API and Lambdas")
                        )),
                        new Responsibility(null, Map.of("fr", "Automatisation d’outillage interne (projet Pitaya — Java 17)", "en", "Internal Tooling Automation (Pitaya project — Java 17)"), List.of(
                                Map.of("fr", "Conception d'un service d'ingestion de données via l'API REST Jira pour extraire les KPIs des tickets, les stocker en base et alimenter les tableaux de bord Power BI", "en", "Design of a data ingestion service via the Jira REST API to extract ticket KPIs, store them in a database, and power Power BI dashboards"),
                                Map.of("fr", "Conception d’un service Java d’automatisation de génération de tickets Jira via les APIs REST de la suite Atlassian", "en", "Design of a Java service for automated Jira ticket generation via Atlassian REST APIs"),
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
                Map.of("fr", "Avril – Mai 2019", "en", "April – May 2019"),
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

    private void initResumeData(TranslationService translationService, ResumeRepository resumeRepository){
        Resume resume = resumeRepository.findByName("Sofiane ZIANE").orElseGet(Resume::new);
        resume.setName("Sofiane ZIANE");

        resume.setRole(Map.of(
                "fr", "Développeur Full-Stack Java / Angular",
                "en", "Full-Stack Java / Angular Developer"
        ));

        resume.setIntro(Map.of(
                "fr", "Développeur Full-Stack Java / Spring Boot et Angular avec 3 ans d'expérience en environnements industriels et agiles (SAFe). Habitué aux pipelines CI/CD et à la maintenance critique, je conçois des applications robustes et scalables.",
                "en", "Full-Stack Java / Spring Boot and Angular Developer with 3 years of experience in industrial and agile environments (SAFe). Experienced in CI/CD pipelines and critical maintenance, I design robust and scalable applications."
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
                        Map.of("fr", "Langages & Frameworks", "en", "Languages & Frameworks"),
                        "Java, Spring Boot, Spring Data JPA,<br />Angular, TypeScript, PL/SQL,<br />HTML/CSS, JavaScript, Flutter"
                ),
                new ResumeDto.SkillSectionDto(
                        Map.of("fr", "Outils DevOps & CI/CD", "en", "DevOps & CI/CD Tools"),
                        "GitLab CI, Jenkins, SonarQube,<br />Jira, Apache Tomcat, Azure"
                )
        ));

        resume.setInterests(List.of(
                new ResumeDto.InterestDto(
                        Map.of("fr", "Langues", "en", "Languages"),
                        Map.of("fr", "Français (Langue maternelle)<br/>Anglais B2", "en", "French (native language)<br/>English B2")
                ),
                new ResumeDto.InterestDto(
                        Map.of("fr", "Soft Skills", "en", "Soft Skills"),
                        Map.of("fr", "Esprit d'analyse, Autonomie,<br/>", "en", "Analytical mind, Autonomy<br/>")
                ),
                new ResumeDto.InterestDto(
                        Map.of("fr", "Centres d’intérêt", "en", "Interests"),
                        Map.of("fr", "Sport : Course à pied, VTT, natation<br/>Lecture", "en", "Sport : running, natation, cycling<br />reading")
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
                        Map.of("fr", "Ingénieur Études et Développement Java", "en", "Java Study and Development Engineer"),
                        "Sopra Steria",
                        "Lille",
                        Map.of("fr", "CGI Finance — TMA Banque & Assurance", "en", "CGI Finance — AMS Banking & Insurance"),
                        Map.of("fr", "Février 2024 – Février 2025", "en", "February 2024 – February 2025"),
                        null,
                        List.of(
                                Map.of("fr", "Analyse des tickets Jira, études de faisabilité technique et chiffrage des évolutions", "en", "Jira ticket analysis, technical feasibility studies, and estimation of developments"),
                                Map.of("fr", "Maintenance corrective et évolutive sur la stack Java / PL/SQL et édition documentaire (KSL Studio)", "en", "Corrective and evolutionary maintenance on the Java / PL/SQL stack and document publishing (KSL Studio)"),
                                Map.of("fr", "Mises en production nocturnes (astreintes) et support applicatif critique", "en", "Nightly releases (on-call) and critical application support")
                        ),
                        List.of("Java", "PL/SQL", "KSL Studio", "Shell", "Apache Tomcat", "Azure", "Jira")
                ),
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Ingénieur Études et Développement Full-Stack", "en", "Full-Stack Study and Development Engineer"),
                        "Sopra Steria",
                        "Saint-Denis (Distance / Lille)",
                        Map.of("fr", "SNCF Réseau — Gisement de Données", "en", "SNCF Réseau — Data Repository"),
                        Map.of("fr", "Août 2022 – Février 2024", "en", "August 2022 – February 2024"),
                        Map.of("fr", "Refonte complète d'un gisement de données centralisé (Source Unique de Vérité) pour l'ensemble des incidents et ressources de la SNCF.", "en", "Complete redesign of a centralized data repository (Single Source of Truth) for all SNCF incidents and resources."),
                        List.of(
                                Map.of("fr", "Développement Full-Stack : Spring Boot / Angular 15", "en", "Full-stack development: Spring Boot / Angular 15"),
                                Map.of("fr", "Conception du moteur de dépendances temporelles et des règles de validation d'incidents (Parent/Enfant)", "en", "Design of the temporal dependency engine and incident validation rules (Parent/Child)"),
                                Map.of("fr", "Rédaction des Spécifications Techniques Détaillées (STD), plans de tests et revues de code (GitLab MR)", "en", "Drafting Detailed Technical Specifications (DTS), test plans, and code reviews (GitLab MR)")
                        ),
                        List.of("Java 17", "Spring Boot", "Spring Data JPA", "PostgreSQL", "Angular 15", "TypeScript", "PrimeNG", "GitLab CI", "Jenkins", "SonarQube")
                ),
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Ingénieur Études et Développement (Stage)", "en", "Study and Development Engineer (Internship)"),
                        "Sopra Steria",
                        "Lille / Saint-Denis",
                        Map.of("fr", "SNCF Réseau — Centre de Services", "en", "SNCF Réseau — Service Center"),
                        Map.of("fr", "Mars 2022 – Juillet 2022", "en", "March 2022 – July 2022"),
                        Map.of("fr", "Automatisation de processus internes et modernisation de l'écosystème logiciel.", "en", "Automation of internal processes and modernization of the software ecosystem."),
                        List.of(
                                Map.of("fr", "Migration des dépôts d'artefacts (Nexus vers Artifactory) et mise à jour des pipelines Jenkins", "en", "Migration of artifact repositories (Nexus to Artifactory) and update of Jenkins pipelines"),
                                Map.of("fr", "Refactoring et migration de code legacy (Java 7 vers Java 8, API Streams & Lambdas)", "en", "Refactoring and migration of legacy code (Java 7 to Java 8, Streams & Lambdas API)"),
                                Map.of("fr", "Extraction des KPIs via l'API REST Jira, stockage en base et alimentation des rapports Power BI", "en", "Extraction of KPIs via the Jira REST API, database storage, and Power BI report feeding"),
                                Map.of("fr", "Modélisation de tableaux de bord décisionnels et KPIs (Power BI / Power Query)", "en", "Modeling of decision-making dashboards and KPIs (Power BI / Power Query)")
                        ),
                        List.of("Java 17", "REST API", "Artifactory", "Nexus", "Jenkins", "Git", "Mockito", "Power BI")
                ),
                new ResumeDto.ExperienceDto(
                        Map.of("fr", "Développeur Web (Stage)", "en", "Web Developer (Internship)"),
                        "Prodival",
                        "Rosult",
                        Map.of("fr", "Projets clients : Bombardier, ArcelorMittal, CFT", "en", "Client projects: Bombardier, ArcelorMittal, CFT"),
                        Map.of("fr", "Avril 2019 – Mai 2019", "en", "April 2019 – May 2019"),
                        Map.of("fr", "Refonte de l'aide en ligne d'un logiciel de management de la qualité.", "en", "Redesign of the online help system for a quality management software."),
                        List.of(
                                Map.of("fr", "Développement front et back des pages d'assistance utilisateur", "en", "Front-end and back-end development of user assistance pages")
                        ),
                        List.of("HTML", "CSS", "jQuery", "C# (.NET)")
                )
        ));

        resumeRepository.save(resume);
    }
}
