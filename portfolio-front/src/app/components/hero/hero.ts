import {
  afterNextRender,
  Component,
  computed,
  DestroyRef,
  effect,
  ElementRef,
  inject,
  PLATFORM_ID,
  signal,
  viewChild,
} from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { RouterLink } from '@angular/router';
import { TranslationService } from '../../services/translation.service';
import { SkillService } from '../../services/skill.service';

@Component({
  selector: 'app-hero',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './hero.html',
})
export class Hero {
  protected skillService = inject(SkillService);

  readonly i18n = inject(TranslationService);
  private readonly rain = viewChild.required<ElementRef<HTMLCanvasElement>>('rain');
  private readonly destroyRef = inject(DestroyRef);
  private readonly isBrowser = isPlatformBrowser(inject(PLATFORM_ID));

  readonly title = computed(() => this.i18n.translate('hero.title'));
  // Full text on the server and with reduced motion; typed char by char in the browser
  readonly typedTitle = signal(this.title());

  // 0 at the top of the page, 1 once the hero has been scrolled out of the viewport
  private readonly progress = signal(0);
  readonly opacity = computed(() => 1 - this.progress());
  readonly transform = computed(() => `translateY(${-this.progress() * 120}px)`);

  constructor() {
    afterNextRender(() => this.startCodeRain());

    effect((onCleanup) => {
      const full = this.title();
      if (!this.isBrowser || window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
        this.typedTitle.set(full);
        return;
      }
      let i = 0;
      this.typedTitle.set('');
      const timer = setInterval(() => {
        this.typedTitle.set(full.slice(0, ++i));
        if (i >= full.length) {
          clearInterval(timer);
        }
      }, 60);
      onCleanup(() => clearInterval(timer));
    });

    if (this.isBrowser) {
      const reduced = window.matchMedia('(prefers-reduced-motion: reduce)').matches;
      const onScroll = () => {
        const ratio = Math.min(window.scrollY / (window.innerHeight * 0.6), 1);
        this.progress.set(reduced ? 0 : ratio);
      };
      window.addEventListener('scroll', onScroll, { passive: true });
      this.destroyRef.onDestroy(() => window.removeEventListener('scroll', onScroll));
      onScroll();
    }
  }

  private startCodeRain(): void {
    if (window.matchMedia('(prefers-reduced-motion: reduce)').matches) {
      return;
    }
    const canvas = this.rain().nativeElement;
    const ctx = canvas.getContext('2d');
    if (!ctx) {
      return;
    }
    const glyphs = '01010101{}<>/;=()[]$#&|'.split('');
    const size = 16;
    const accent = () =>
      getComputedStyle(document.documentElement).getPropertyValue('--color-accent').trim() ||
      '#2dd4bf';
    let drops: number[] = [];

    const resize = () => {
      canvas.width = canvas.clientWidth;
      canvas.height = canvas.clientHeight;
      drops = Array.from({ length: Math.ceil(canvas.width / size) }, () => Math.random() * -50);
      ctx.font = `${size}px monospace`;
    };

    let last = 0;
    let frame = 0;
    const draw = (time: number) => {
      frame = requestAnimationFrame(draw);
      if (time - last < 60 || document.hidden || window.scrollY > window.innerHeight) {
        return;
      }
      last = time;
      ctx.globalCompositeOperation = 'destination-out';
      ctx.fillStyle = 'rgba(0, 0, 0, 0.12)';
      ctx.fillRect(0, 0, canvas.width, canvas.height);
      ctx.globalCompositeOperation = 'source-over';
      ctx.fillStyle = accent();
      drops.forEach((y, i) => {
        ctx.fillText(glyphs[Math.floor(Math.random() * glyphs.length)], i * size, y * size);
        drops[i] = y * size > canvas.height && Math.random() > 0.975 ? 0 : y + 1;
      });
    };

    resize();
    const observer = new ResizeObserver(resize);
    observer.observe(canvas);
    frame = requestAnimationFrame(draw);
    this.destroyRef.onDestroy(() => {
      cancelAnimationFrame(frame);
      observer.disconnect();
    });
  }

}
