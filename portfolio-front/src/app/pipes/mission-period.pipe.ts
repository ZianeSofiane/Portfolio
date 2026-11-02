import { Pipe, PipeTransform } from '@angular/core';
import { Language } from '../services/translation.service';

export function formatMissionPeriod(
  startDate: string,
  endDate: string | null | undefined,
  lang: Language,
): string {
  const fmt = new Intl.DateTimeFormat(lang, { month: 'long', year: 'numeric', timeZone: 'UTC' });
  const cap = (d: string) => {
    const s = fmt.format(new Date(d));
    return s.charAt(0).toUpperCase() + s.slice(1);
  };
  return `${cap(startDate)} – ${endDate ? cap(endDate) : lang === 'fr' ? "Aujourd'hui" : 'Present'}`;
}

@Pipe({ name: 'missionPeriod', standalone: true })
export class MissionPeriodPipe implements PipeTransform {
  transform(startDate: string, endDate: string | null | undefined, lang: Language): string {
    return formatMissionPeriod(startDate, endDate, lang);
  }
}
