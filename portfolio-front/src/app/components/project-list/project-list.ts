import { Component, computed, inject } from '@angular/core';
import { LocalizedProject } from '../../models/project.model';
import { TranslationService } from '../../services/translation.service';
import { ProjectService } from '../../services/project.service.';

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.html',
  styleUrl: './project-list.css',
})
export class ProjectList {
  private projectService = inject(ProjectService);
  protected readonly i18n = inject(TranslationService);

  localizedProjects = computed<LocalizedProject[]>(() =>
    this.projectService.projects().map((p) => ({
      ...p,
      title: this.i18n.localize(p.title),
      description: this.i18n.localize(p.description),
      projectSkills: p.projectSkills.map((ps) => ({
        ...ps,
        skill: {
          ...ps.skill,
          name: ps.skill.name,
        },
      })),
    })),
  );

  // Moves the preview with the cursor; flips to the left of the cursor in the right half of the card
  followCursor(event: MouseEvent): void {
    const card = event.currentTarget as HTMLElement;
    const rect = card.getBoundingClientRect();
    const x = event.clientX - rect.left;
    card.style.setProperty('--x', `${x}px`);
    card.style.setProperty('--y', `${event.clientY - rect.top}px`);
    card.style.setProperty('--tx', x > rect.width / 2 ? 'calc(-100% - 16px)' : '16px');
  }
}
