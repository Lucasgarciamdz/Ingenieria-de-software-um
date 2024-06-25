import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PublicacionMensajeComponent } from './publicacion-mensaje.component';

describe('PublicacionMensajeComponent', () => {
  let component: PublicacionMensajeComponent;
  let fixture: ComponentFixture<PublicacionMensajeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PublicacionMensajeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(PublicacionMensajeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
