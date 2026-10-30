import { Component, computed, ElementRef, inject, signal, viewChild } from '@angular/core';
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

  readonly generating = signal(false);

  private readonly paper = viewChild.required<ElementRef<HTMLElement>>('paper');

  async downloadPdf(): Promise<void> {
    if (this.generating()) {
      return;
    }
    this.generating.set(true);
    try {
      const [{ default: html2canvas }, { jsPDF }] = await Promise.all([
        import('html2canvas-pro'),
        import('jspdf'),
      ]);

      const paper = this.paper().nativeElement;
      paper.classList.add('pdf-export');
      try {
        const canvas = await html2canvas(paper, {
          scale: 2,
          backgroundColor: '#fff',
          windowWidth: 1200,
        });

        const pdf = new jsPDF({ orientation: 'portrait', unit: 'mm', format: 'a4' });
        const pageWidth = pdf.internal.pageSize.getWidth();
        const pageHeight = pdf.internal.pageSize.getHeight();
        const imgHeight = (canvas.height * pageWidth) / canvas.width;
        const image = canvas.toDataURL('image/jpeg', 0.95);

        const ratio = Math.min(1, pageHeight / imgHeight);
        const drawWidth = pageWidth * ratio;
        pdf.addImage(image, 'JPEG', (pageWidth - drawWidth) / 2, 0, drawWidth, imgHeight * ratio);

        pdf.save('Cv_ZIANE_Sofiane.pdf');
      } finally {
        paper.classList.remove('pdf-export');
      }
    } finally {
      this.generating.set(false);
    }
  }
}
