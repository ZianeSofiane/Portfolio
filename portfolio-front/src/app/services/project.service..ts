import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Project } from '../models/project.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/api/projects`;

  projects = signal<Project[]>([]);

  getProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(this.apiUrl);
  }

  /*getFeaturedProjects(): Observable<Project[]> {
    return this.http.get<Project[]>(`${this.apiUrl}/featured`);
  }*/

  constructor() {
    this.getProjects().subscribe({
      next: (data) => {
        this.projects.set(data);
        console.log('Projets chargés avec succès :', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des projets depuis Spring Boot :', err);
      },
    });
  }
}
