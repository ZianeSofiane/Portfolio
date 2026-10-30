import { TestBed } from '@angular/core/testing';
import { ThemeService } from './theme.service';

describe('ThemeService', () => {
  let stored: Record<string, string>;

  beforeEach(() => {
    stored = {};
    vi.stubGlobal('localStorage', {
      getItem: (key: string) => stored[key] ?? null,
      setItem: (key: string, value: string) => (stored[key] = value),
    });
    document.documentElement.removeAttribute('data-theme');
  });

  afterEach(() => vi.unstubAllGlobals());

  it('should default to the dark theme', () => {
    expect(TestBed.inject(ThemeService).theme()).toBe('dark');
  });

  it('should toggle, apply and persist the theme', () => {
    const service = TestBed.inject(ThemeService);

    service.toggle();
    TestBed.tick();

    expect(service.theme()).toBe('light');
    expect(document.documentElement.getAttribute('data-theme')).toBe('light');
    expect(stored['theme']).toBe('light');
  });
});
