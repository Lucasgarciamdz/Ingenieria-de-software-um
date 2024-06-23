import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensajePrivadoComponent } from './mensaje-privado.component';

describe('MensajePrivadoComponent', () => {
  let component: MensajePrivadoComponent;
  let fixture: ComponentFixture<MensajePrivadoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MensajePrivadoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MensajePrivadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
