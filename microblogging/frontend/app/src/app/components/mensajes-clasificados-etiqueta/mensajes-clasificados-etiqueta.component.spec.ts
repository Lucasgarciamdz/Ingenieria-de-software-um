import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MensajesClasificadosEtiquetaComponent } from './mensajes-clasificados-etiqueta.component';

describe('MensajesClasificadosEtiquetaComponent', () => {
  let component: MensajesClasificadosEtiquetaComponent;
  let fixture: ComponentFixture<MensajesClasificadosEtiquetaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MensajesClasificadosEtiquetaComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MensajesClasificadosEtiquetaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
