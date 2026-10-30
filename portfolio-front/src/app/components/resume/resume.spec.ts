import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Resume } from './resume';

describe('Resume', () => {
  let component: Resume;
  let fixture: ComponentFixture<Resume>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Resume],
    }).compileComponents();

    fixture = TestBed.createComponent(Resume);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should render the resume title', () => {
    const title = fixture.nativeElement.querySelector('.resume-name');
    expect(title?.textContent).toContain('Sofiane ZIANE');
  });

  it('should render the download button', () => {
    const button = fixture.nativeElement.querySelector('.download-button');
    expect(button).toBeTruthy();
    expect(button.disabled).toBe(false);
  });

  it('should not generate a pdf twice at the same time', async () => {
    component.generating.set(true);
    await component.downloadPdf();
    expect(component.generating()).toBe(true);
  });
});
