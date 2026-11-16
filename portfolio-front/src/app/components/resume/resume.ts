import { Component, computed, inject } from '@angular/core';
import { TranslationService } from '../../services/translation.service';
import { ResumeService } from '../../services/resume.service';
import { LocalizedResume } from '../../models/resume.model';
import { LocalizedProject } from '../../models/project.model';
import { ProjectService } from '../../services/project.service.';

@Component({
  selector: 'app-resume',
  standalone: true,
  templateUrl: './resume.html',
  styleUrls: ['./resume.css'],
})
export class Resume {
  protected readonly resumeService = inject(ResumeService);
  private readonly projectService = inject(ProjectService);
  protected readonly i18n = inject(TranslationService);

  portfolioProject = computed<LocalizedProject | undefined>(() => {
    const project = this.projectService.projects().find(
      (candidate) => candidate.githubUrl === 'https://github.com/ZianeSofiane/Portfolio',
    );
    return project
      ? {
          ...project,
          title: this.i18n.localize(project.title),
          description: this.i18n.localize(project.description),
        }
      : undefined;
  });

  displayUrl(url: string): string {
    return url.replace(/^https?:\/\//, '').replace(/\/$/, '');
  }

  resume = computed<LocalizedResume[]>(() =>
    this.resumeService.resume().map((r) => ({
      ...r,
      role: this.i18n.localize(r.role),
      intro: this.i18n.localize(r.intro),
      skills: r.skills?.map((s) => ({
        ...s,
        category: this.i18n.localize(s.category),
        items: s.items,
      })),
      education: r.education?.map((e) => ({
        ...e,
        title: this.i18n.localize(e.title),
        school: e.school,
      })),
      interests: r.interests?.map((i) => ({
        ...i,
        category: this.i18n.localize(i.category),
        items: this.i18n.localize(i.items),
      })),
      experiences: r.experiences?.map((exp) => ({
        ...exp,
        role: this.i18n.localize(exp.role),
        clientOrSector: exp.clientOrSector ? this.i18n.localize(exp.clientOrSector) : undefined,
        period: this.i18n.localize(exp.period),
        description: exp.description ? this.i18n.localize(exp.description) : undefined,
        tasks: exp.tasks?.map((t) => this.i18n.localize(t)),
      })),
    })),
  );

  printResume(): void {
    window.print();
  }
}
