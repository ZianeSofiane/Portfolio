import { Component, computed, inject } from '@angular/core';
import { TranslationService } from '../../services/translation.service';
import { ResumeService } from '../../services/resume.service';
import { LocalizedResume } from '../../models/resume.model';

@Component({
  selector: 'app-resume',
  standalone: true,
  templateUrl: './resume.html',
  styleUrls: ['./resume.css'],
})
export class Resume {
  protected readonly resumeService = inject(ResumeService);
  protected readonly i18n = inject(TranslationService);

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
