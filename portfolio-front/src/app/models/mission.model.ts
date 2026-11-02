export type LocalizedText = Record<'en' | 'fr', string>;

export interface Mission {
  id: string;
  role: LocalizedText;
  company: string;
  clientOrSector?: LocalizedText;
  startDate: string;
  endDate?: string | null;
  duration: LocalizedText;
  location: string;
  contractType: 'CDI' | 'CDD' | 'Stage' | 'Freelance';
  teamSize?: LocalizedText;
  methodology?: LocalizedText;
  context: LocalizedText;
  responsibilities: {
    category: LocalizedText;
    tasks: LocalizedText[];
  }[];
  techStack: string[];
  featured?: boolean;
}

export type LocalizedMission = Omit<
  Mission,
  | 'role'
  | 'clientOrSector'
  | 'duration'
  | 'teamSize'
  | 'methodology'
  | 'context'
  | 'responsibilities'
> & {
  role: string;
  clientOrSector?: string;
  duration: string;
  teamSize?: string;
  methodology?: string;
  context: string;
  responsibilities: {
    category: string;
    tasks: string[];
  }[];
};
