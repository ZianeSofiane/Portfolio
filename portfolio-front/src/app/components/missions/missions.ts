import { Component, computed, inject, signal } from '@angular/core';
import { LocalizedMission } from '../../models/mission.model';
import { MissionService } from '../../services/mission.service';
import { SkillService } from '../../services/skill.service';
import { TranslationService } from '../../services/translation.service';

interface MissionFilter {
  key: string;
  label: string;
  match: (m: LocalizedMission) => boolean;
}

const hasTech = (m: LocalizedMission, ...terms: string[]) =>
  m.techStack.some((s) => terms.some((t) => s.toLowerCase().includes(t)));

@Component({
  selector: 'app-missions',
  standalone: true,
  templateUrl: './missions.html',
})
export class Missions {
  private missionService = inject(MissionService);
  protected readonly i18n = inject(TranslationService);
  protected skillService = inject(SkillService);

  missions = computed<LocalizedMission[]>(() =>
    this.missionService.missions().map((m) => ({
      ...m,
      role: this.i18n.localize(m.role),
      clientOrSector: this.i18n.localize(m.clientOrSector),
      period: this.i18n.localize(m.period),
      duration: this.i18n.localize(m.duration),
      teamSize: this.i18n.localize(m.teamSize),
      methodology: this.i18n.localize(m.methodology),
      context: this.i18n.localize(m.context),
      responsibilities: m.responsibilities?.map((r) => ({
        ...r,
        category: this.i18n.localize(r.category),
        tasks: r.tasks?.map((t) => this.i18n.localize(t)),
      })),
    })),
  );

  readonly allFilter: MissionFilter[] = [{ key: 'all', label: 'Tous', match: () => true }];

  readonly filters = computed<MissionFilter[]>(() => [
    ...this.allFilter,
    ...this.skillService.skills().map((s) => ({
      key: s.name.toLowerCase(),
      label: s.name,
      match: (m: LocalizedMission) => hasTech(m, s.name.toLowerCase()),
    })),
  ]);

  readonly query = signal('');
  readonly activeFilter = signal('all');
  readonly expanded = signal<ReadonlySet<string>>(new Set([this.missions()[1]?.id]));
  readonly copiedId = signal<string | null>(null);

  readonly visible = computed(() => {
    const filter = this.filters().find((f) => f.key === this.activeFilter()) ?? this.filters()[0];
    const q = this.query().trim().toLowerCase();
    return this.missions().filter(
      (m) => filter.match(m) && (!q || JSON.stringify(m).toLowerCase().includes(q)),
    );
  });

  toggle(id: string): void {
    this.expanded.update((current) => {
      const next = new Set(current);
      if (!next.delete(id)) {
        next.add(id);
      }
      return next;
    });
  }

  async copy(m: LocalizedMission): Promise<void> {
    const header = [
      `${m.company} — ${m.role}`,
      [m.clientOrSector, m.location, m.contractType].filter(Boolean).join(' · '),
      `${m.period} · ${m.duration}`,
      [m.methodology, m.teamSize && `Équipe : ${m.teamSize}`].filter(Boolean).join(' · '),
    ].filter(Boolean);
    const responsibilities = m.responsibilities.map(
      (r) => `${r.category}\n${r.tasks.map((x) => `- ${x}`).join('\n')}`,
    );
    const text = [
      header.join('\n'),
      `Contexte\n${m.context}`,
      `Responsabilités\n${responsibilities.join('\n\n')}`,
      `Technologies : ${m.techStack.join(', ')}`,
    ].join('\n\n');
    await navigator.clipboard.writeText(text);
    this.copiedId.set(m.id);
    setTimeout(() => this.copiedId.set(null), 1500);
  }
}
