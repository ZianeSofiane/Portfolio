import { Component, inject, computed } from '@angular/core';
import { SkillService } from '../../services/skill.service';
import { TranslationService } from '../../services/translation.service';

@Component({
  selector: 'app-skill-list',
  templateUrl: './skill-list.html',
  styleUrl: './skill-list.css',
})
export class SkillList {
  protected readonly skillService = inject(SkillService);
  protected readonly i18n = inject(TranslationService);

  backEndSkills = computed(() =>
    this.skillService.skills().filter((skill) => ['Backend', 'Database'].includes(skill.category)),
  );

  frontEndSkills = computed(() =>
    this.skillService.skills().filter((skill) => skill.category == 'Frontend'),
  );

  devOpsSkills = computed(() =>
    this.skillService.skills().filter((skill) => skill.category == 'DevOps'),
  );

  dataSkills = computed(() =>
    this.skillService.skills().filter((skill) => skill.category == 'Data'),
  );
}
