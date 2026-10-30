import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Skill } from '../models/skill.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class SkillService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/api/skills`;

  skills = signal<Skill[]>([]);

  getIconPath(techName: string): string {
    const formatted = techName.toLowerCase().replace(/[^a-z0-9]/g, '-'); // "Spring Boot" -> "spring-boot"
    return `logos/skills/${formatted}.svg`;
  }

  getSkills(): Observable<Skill[]> {
    return this.http.get<Skill[]>(this.apiUrl);
  }

  constructor() {
    this.getSkills().subscribe({
      next: (data) => {
        this.skills.set(data);
        console.log('Compétences chargées avec succès :', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des compétences depuis Spring Boot :', err);
      },
    });
  }
}
