import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Missions } from './components/missions/missions';

export const routes: Routes = [
  { path: '', component: Home },
  { path: 'missions', component: Missions },
  { path: '**', redirectTo: '' },
];
