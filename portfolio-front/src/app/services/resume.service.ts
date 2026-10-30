import { Injectable, inject, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Resume } from '../models/resume.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class ResumeService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/api/resumes`;

  resume = signal<Resume[]>([]);

  getResume(): Observable<Resume[]> {
    return this.http.get<Resume[]>(this.apiUrl);
  }

  constructor() {
    this.getResume().subscribe({
      next: (data) => {
        this.resume.set(data);
        console.log('CV chargé avec succès :', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement du CV depuis Spring Boot :', err);
      },
    });
  }
}
