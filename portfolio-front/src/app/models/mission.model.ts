export type LocalizedText = Record<'en' | 'fr', string>;

export interface Mission {
  id: string;
  role: LocalizedText;
  company: string;
  clientOrSector?: LocalizedText;
  period: LocalizedText;
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
  | 'period'
  | 'duration'
  | 'teamSize'
  | 'methodology'
  | 'context'
  | 'responsibilities'
> & {
  role: string;
  clientOrSector?: string;
  period: string;
  duration: string;
  teamSize?: string;
  methodology?: string;
  context: string;
  responsibilities: {
    category: string;
    tasks: string[];
  }[];
};
