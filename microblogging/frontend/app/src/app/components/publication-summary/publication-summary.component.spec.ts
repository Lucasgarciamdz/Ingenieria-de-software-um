import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicationSummaryComponent } from './publication-summary.component';

describe('PublicationSummaryComponent', () => {
  let component: PublicationSummaryComponent;
  let fixture: ComponentFixture<PublicationSummaryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicationSummaryComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublicationSummaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
