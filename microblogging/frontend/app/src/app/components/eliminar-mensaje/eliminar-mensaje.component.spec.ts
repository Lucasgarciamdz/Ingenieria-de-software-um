import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EliminarMensajeComponent } from './eliminar-mensaje.component';

describe('EliminarMensajeComponent', () => {
  let component: EliminarMensajeComponent;
  let fixture: ComponentFixture<EliminarMensajeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EliminarMensajeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(EliminarMensajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
