import { ComponentFixture, TestBed } from '@angular/core/testing';
import { provideRouter } from '@angular/router';
import { Footer } from './footer';

describe('Footer', () => {
  let fixture: ComponentFixture<Footer>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Footer],
      providers: [provideRouter([])],
    }).compileComponents();

    fixture = TestBed.createComponent(Footer);
    fixture.detectChanges();
  });

  it('should render a tap-to-call phone link', () => {
    const phoneLink: HTMLAnchorElement | null = fixture.nativeElement.querySelector(
      'a[href="tel:+33671426565"]',
    );

    expect(phoneLink?.textContent).toContain('06.71.42.65.65');
  });

  it('should render social links and the copyright notice', () => {
    const element: HTMLElement = fixture.nativeElement;

    expect(element.querySelector('a[href="https://github.com/ZianeSofiane"]')).toBeTruthy();
    expect(element.querySelector('a[href^="https://www.linkedin.com/"]')).toBeTruthy();
    expect(element.textContent).toContain('© 2026 Sofiane Ziane — Tous droits réservés');
  });
});
