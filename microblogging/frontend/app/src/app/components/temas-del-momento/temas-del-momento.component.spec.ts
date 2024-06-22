import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TemasDelMomentoComponent } from './temas-del-momento.component';

describe('TemasDelMomentoComponent', () => {
  let component: TemasDelMomentoComponent;
  let fixture: ComponentFixture<TemasDelMomentoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TemasDelMomentoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TemasDelMomentoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
