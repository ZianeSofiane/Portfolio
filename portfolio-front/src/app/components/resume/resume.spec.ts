import { ComponentFixture, TestBed } from '@angular/core/testing';
import { signal } from '@angular/core';
import { Resume } from './resume';
import { Resume as ResumeData } from '../../models/resume.model';
import { ResumeService } from '../../services/resume.service';
import { ProjectService } from '../../services/project.service.';

const resumeData: ResumeData = {
  name: 'Sofiane ZIANE',
  role: { fr: 'Développeur Full Stack Java / Angular', en: 'Full Stack Java / Angular Developer' },
  intro: { fr: 'Profil de test', en: 'Test profile' },
  contact: {
    phone: '0600000000',
    email: 'sofiane@example.com',
    location: 'Lille',
    license: '',
    linkedin: 'https://www.linkedin.com/in/example',
    github: 'https://github.com/example',
  },
  skills: [{ category: { fr: 'Back-end', en: 'Back end' }, items: 'Java, Spring Boot' }],
  education: [{ title: { fr: 'Master informatique', en: 'Computer Science MSc' }, school: 'UPHF' }],
  interests: [{ category: { fr: 'Langues', en: 'Languages' }, items: { fr: 'Français', en: 'French' } }],
  experiences: [{
    role: { fr: 'Développeur Full Stack', en: 'Full Stack Developer' },
    company: 'Sopra Steria',
    location: 'Lille',
    period: { fr: '2024 – 2025', en: '2024 – 2025' },
    tasks: [{ fr: 'Développement Java', en: 'Java development' }],
    techStack: ['Java'],
  }],
};

describe('Resume', () => {
  let component: Resume;
  let fixture: ComponentFixture<Resume>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Resume],
      providers: [{
        provide: ResumeService,
        useValue: { resume: signal([resumeData]) },
      }, {
        provide: ProjectService,
        useValue: { projects: signal([]) },
      }],
    }).compileComponents();

    fixture = TestBed.createComponent(Resume);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the resume title', () => {
    const title = fixture.nativeElement.querySelector('.resume-name');
    expect(title?.textContent).toContain('Sofiane ZIANE');
  });

  it('should render a print-to-PDF button and omit the profile photo', () => {
    const button = fixture.nativeElement.querySelector('.download-button');
    expect(button).toBeTruthy();
    expect(fixture.nativeElement.querySelector('img')).toBeNull();
  });

  it('should display the summary, portfolio link and compact experience heading', () => {
    const element = fixture.nativeElement as HTMLElement;
    expect(element.querySelector('.resume-intro')?.textContent).toContain('Profil de test');
    expect(element.querySelector('#resume-summary')).toBeNull();
    expect(element.querySelector('a[href="https://zianesofiane-portfolio.duckdns.org/"]')?.textContent)
      .toContain('zianesofiane-portfolio.duckdns.org');
    expect(element.querySelector('.experience-role')?.textContent)
      .toContain('Sopra Steria, Lille — Développeur Full Stack (2024 – 2025)');
  });

  it('should render a personal projects section when the portfolio project is loaded', () => {
    const projectService = TestBed.inject(ProjectService);
    projectService.projects.set([{
      id: 1,
      title: { fr: 'Mon Portfolio Full-Stack', en: 'My Full-Stack Portfolio' },
      description: { fr: 'Portfolio Angular et Spring', en: 'Angular and Spring portfolio' },
      githubUrl: 'https://github.com/ZianeSofiane/Portfolio',
      demoUrl: 'https://zianesofiane-portfolio.duckdns.org/',
      featured: true,
      projectSkills: [{
        id: 1,
        skill: { id: 1, name: 'Java', category: 'Backend' },
        version: '25',
      }],
    }]);
    fixture.detectChanges();

    const section = fixture.nativeElement.querySelector('section[aria-labelledby="resume-projects"]');
    expect(section?.textContent).toContain('Projets personnels');
    const links = section?.querySelector('.resume-project-links');
    expect(links?.querySelector('a[href="https://github.com/ZianeSofiane/Portfolio"]')?.textContent)
      .toContain('GitHub: https://github.com/ZianeSofiane/Portfolio');
    expect(links?.querySelector('a[href="https://zianesofiane-portfolio.duckdns.org/"]')?.textContent)
      .toContain('Portfolio: zianesofiane-portfolio.duckdns.org');
  });

  it('should open the browser print dialog for a text-based PDF', () => {
    const print = vi.spyOn(window, 'print').mockImplementation(() => undefined);
    component.printResume();
    expect(print).toHaveBeenCalledOnce();
    print.mockRestore();
  });
});
