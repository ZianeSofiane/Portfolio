import { Injectable, computed, signal } from '@angular/core';

export type Language = 'fr' | 'en';

const DICTIONARY: Record<Language, Record<string, string>> = {
  fr: {
    'theme.toLight': 'Passer en mode clair',
    'theme.toDark': 'Passer en mode sombre',
    'hero.status': 'Disponible pour de nouvelles missions',
    'hero.title': 'Développeur Full-Stack Java / Angular',
    'hero.hook':
      "Fort de 3 ans d'expérience en environnements industriels et agiles (SAFe), je conçois et maintiens des applications web robustes et scalables, de la modélisation backend (Java / Spring Boot) jusqu'aux interfaces utilisateurs dynamiques (Angular)",
    'hero.missions': 'Voir mes missions',
    'hero.resume': 'Consulter mon CV',
    'hero.scroll': 'Défiler vers le bas',
    'hero.skills': 'Technologies maîtrisées',
    'nav.about': 'À propos',
    'nav.skills': 'Compétences',
    'nav.projects': 'Projets',
    'nav.missions': 'Missions',
    'nav.contact': 'Contact',
    'nav.contactMe': 'Me contacter',
    'nav.resume': 'CV',
    'footer.contact': 'Me contacter',
    'footer.socials': 'Réseaux sociaux',
    'footer.navigation': 'Navigation',
    'footer.opensNewTab': 'ouvre un nouvel onglet',
    'projects.title': 'Mes Projets',
    'projects.subtitle': 'Découvrez les réalisations développées avec ma stack Full-Stack.',
    'projects.featured': 'Featured',
    'projects.source': 'Code source →',
    'projects.preview': 'Aperçu',
    'projects.hidePreview': 'Masquer',
    'projects.demo': 'Live Demo ->',
    'skills.title': 'Mes Compétences',
    'skills.subtitle': "Les technologies et outils que j'utilise au quotidien.",
    'cv.generating': 'Generation...',
    'cv.download': 'Télécharger le pdf',
    'cv.techEnv': 'Environement technique',
    'cv.xp': 'Expériences professionnelles',
    'cv.degree': 'Diplômes et formations',
    'missions.copy': '📋 Copier',
    'missions.copied': '✔ Copié',
    'missions.title': 'Missions',
    'missions.description': 'Développeur fullstack Java / Spring / Angular, en environnement CI/CD',
    'missions.search': 'Rechercher',
    'missions.context': 'Contexte & méthodologie',
    'missions.team': 'Équipe',
    'missions.resp': 'Responsabilités & réalisations',
    'footer.rights': 'Tous droits réservés',
  },
  en: {
    'theme.toLight': 'Switch to light mode',
    'theme.toDark': 'Switch to dark mode',
    'hero.status': 'Available for new missions',
    'hero.title': 'Java / Angular Full-Stack Developer',
    'hero.hook':
      'With 3 years of experience in industrial and agile environments (SAFe), I design and maintain robust, scalable web applications, ranging from backend modeling (Java / Spring Boot) to dynamic user interfaces (Angular)',
    'hero.missions': 'View my missions',
    'hero.resume': 'View my resume',
    'hero.scroll': 'Scroll down',
    'hero.skills': 'Technologies I work with',
    'nav.about': 'About',
    'nav.skills': 'Skills',
    'nav.projects': 'Projects',
    'nav.missions': 'Missions',
    'nav.contact': 'Contact',
    'nav.contactMe': 'Contact me',
    'nav.resume': 'Resume',
    'footer.contact': 'Contact',
    'footer.socials': 'Social media',
    'footer.navigation': 'Navigation',
    'footer.opensNewTab': 'opens a new tab',
    'projects.title': 'My Projects',
    'projects.subtitle': 'Discover the projects I built with my Full-Stack stack.',
    'projects.featured': 'Featured',
    'projects.source': 'Source code →',
    'projects.preview': 'Preview',
    'projects.hidePreview': 'Hide',
    'projects.demo': 'Live Demo ->',
    'skills.title': 'My Skills',
    'skills.subtitle': 'The technologies and tools I use every day.',
    'cv.generating': 'Generating...',
    'cv.download': 'Download pdf',
    'cv.techEnv': 'Technical environment',
    'cv.xp': 'Professional experiences',
    'cv.degree': 'Degrees & training',
    'missions.copy': '📋 Copy',
    'missions.copied': '✔ Copied',
    'missions.title': 'Missions',
    'missions.description': 'Java/Spring/Angular Fullstack developer in CI/CD environment',
    'missions.search': 'Search',
    'missions.context': 'Context & methodology',
    'missions.team': 'Team',
    'missions.resp': 'Responsabilities & achievements',
    'footer.rights': 'All rights reserved',
  },
};

@Injectable({
  providedIn: 'root',
})
export class TranslationService {
  readonly language = signal<Language>('fr');
  readonly dictionary = computed(() => DICTIONARY[this.language()]);

  translate(key: string): string {
    return this.dictionary()[key] ?? key;
  }

  localize(text: Partial<Record<Language, string>> | null | undefined): string {
    return text?.[this.language()] ?? text?.['en'] ?? '';
  }

  setLanguage(lang: Language): void {
    this.language.set(lang);
  }

  toggleLanguage(): void {
    this.setLanguage(this.language() === 'fr' ? 'en' : 'fr');
  }
}
