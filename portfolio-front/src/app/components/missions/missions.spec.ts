import { signal } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Missions } from './missions';
import { MissionService } from '../../services/mission.service';
import { ProjectService } from '../../services/project.service.';
import { SkillService } from '../../services/skill.service';
import { TranslationService } from '../../services/translation.service';

describe('Missions personal projects', () => {
  let fixture: ComponentFixture<Missions>;
  const portfolio = {
    id: 1,
    title: { fr: 'Portfolio', en: 'Portfolio' },
    description: { fr: 'Description courte', en: 'Short description' },
    skillsPortfolioDescription: {
      fr: 'Contexte & objectif : Portfolio.\n\nArchitecture & outillage :\n- Caddy et DuckDNS',
      en: 'Context & objective: Portfolio.\n\nArchitecture & tooling:\n- Caddy and DuckDNS',
    },
    githubUrl: 'https://github.com/example/portfolio',
    featured: true,
    projectSkills: [{ id: 1, skill: { id: 1, name: 'Java', category: 'Backend' }, version: '25' }],
  };
  const geoquizz = {
    id: 2,
    title: { fr: 'GeoQuizz', en: 'GeoQuizz' },
    description: { fr: 'Jeu de géographie', en: 'Geography game' },
    featured: true,
    projectSkills: [],
  };

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Missions],
      providers: [
        { provide: MissionService, useValue: { missions: signal([]) } },
        { provide: ProjectService, useValue: { projects: signal([portfolio, geoquizz]) } },
        { provide: SkillService, useValue: { skills: signal([]) } },
      ],
    }).compileComponents();
    fixture = TestBed.createComponent(Missions);
    fixture.detectChanges();
  });

  it('shows localized details and falls back to the short description for other projects', () => {
    const element = fixture.nativeElement as HTMLElement;
    element.querySelector<HTMLButtonElement>('.mission-card-title')!.click();
    fixture.detectChanges();
    expect(element.textContent).toContain('Architecture & outillage');
    expect(element.textContent).toContain('Caddy et DuckDNS');
    expect(element.textContent).toContain('Jeu de géographie');
    expect(element.textContent).not.toContain('Description courte');

    TestBed.inject(TranslationService).setLanguage('en');
    fixture.detectChanges();
    expect(element.textContent).toContain('Architecture & tooling');
    expect(element.textContent).toContain('Caddy and DuckDNS');
    expect(element.textContent).toContain('Geography game');
  });

  it('copies detailed descriptions for both project and copy-all actions', async () => {
    const writeText = vi.fn().mockResolvedValue(undefined);
    const clipboard = Object.getOwnPropertyDescriptor(navigator, 'clipboard');
    const secureContext = Object.getOwnPropertyDescriptor(window, 'isSecureContext');
    Object.defineProperty(navigator, 'clipboard', { configurable: true, value: { writeText } });
    Object.defineProperty(window, 'isSecureContext', { configurable: true, value: true });

    try {
      await fixture.componentInstance.copyProjects(fixture.componentInstance.visibleProjects());
      await fixture.componentInstance.copyAll();
      expect(writeText).toHaveBeenCalledTimes(2);
      for (const [text] of writeText.mock.calls) {
        expect(text).toContain('Architecture & outillage');
        expect(text).toContain('Caddy et DuckDNS');
        expect(text).toContain('Jeu de géographie');
        expect(text).not.toContain('Description courte');
      }
    } finally {
      if (clipboard) Object.defineProperty(navigator, 'clipboard', clipboard);
      else Reflect.deleteProperty(navigator, 'clipboard');
      if (secureContext) Object.defineProperty(window, 'isSecureContext', secureContext);
      else Reflect.deleteProperty(window, 'isSecureContext');
    }
  });
});
