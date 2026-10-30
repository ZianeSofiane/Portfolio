import { Skill } from './skill.model';

export interface ProjectSkill {
  id: number;
  skill: Skill;
  version: string;
}

export type LocalizedText = Record<'en' | 'fr', string>;

export interface Project {
  id: number;
  title: LocalizedText;
  description: LocalizedText;
  imageUrl?: string;
  githubUrl?: string;
  demoUrl?: string;
  previewUrl?: string;
  featured: boolean;
  projectSkills: ProjectSkill[];
}

export type LocalizedProject = Omit<Project, 'title' | 'description'> & {
  title: string;
  description: string;
};
