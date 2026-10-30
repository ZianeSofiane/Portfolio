import { inject, Injectable, signal } from '@angular/core';
import { Mission } from '../models/mission.model';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class MissionService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/api/missions`;

  readonly missions = signal<Mission[]>([]);

  getMissions(): Observable<Mission[]> {
    return this.http.get<Mission[]>(this.apiUrl);
  }

  constructor() {
    this.getMissions().subscribe({
      next: (data) => {
        this.missions.set(data);
        console.log('Missions chargées avec succès :', data);
      },
      error: (err) => {
        console.error('Erreur lors du chargement des compétences depuis Spring Boot :', err);
      },
    });
  }
}
