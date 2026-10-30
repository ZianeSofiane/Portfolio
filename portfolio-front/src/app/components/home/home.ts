import { Component } from '@angular/core';
import { Hero } from '../hero/hero';
import { ProjectList } from '../project-list/project-list';
import { SkillList } from '../skill-list/skill-list';
import { Resume } from '../resume/resume';
import { Missions } from '../missions/missions';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [Hero, ProjectList, SkillList, Resume, Missions],
  templateUrl: './home.html',
})
export class Home {}
