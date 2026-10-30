export type LocalizedText = Record<'en' | 'fr', string>;

export interface Resume {
  name: string;
  role: LocalizedText;
  intro: LocalizedText;
  contact: {
    phone: string;
    email: string;
    location: string;
    license: string;
    linkedin: string;
    github: string;
  };
  skills: {
    category: LocalizedText;
    items: string;
  }[];
  education: {
    title: LocalizedText;
    school: string;
  }[];
  interests: {
    category: LocalizedText;
    items: LocalizedText;
  }[];
  experiences: {
    role: LocalizedText;
    company: string;
    location: string;
    clientOrSector?: LocalizedText;
    period: LocalizedText;
    description?: LocalizedText;
    tasks: LocalizedText[];
    techStack: string[];
  }[];
}

export type LocalizedResume = Omit<
  Resume,
  'role' | 'intro' | 'skills' | 'education' | 'interests' | 'experiences'
> & {
  role: string;
  intro: string;
  skills: {
    category: string;
    items: string;
  }[];
  education: {
    title: string;
    school: string;
  }[];
  interests: {
    category: string;
    items: string;
  }[];
  experiences: {
    role: string;
    company: string;
    location: string;
    clientOrSector?: string;
    period: string;
    description?: string;
    tasks: string[];
    techStack: string[];
  }[];
};
