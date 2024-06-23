import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BusquedaMencionesComponent } from './busqueda-menciones.component';

describe('BusquedaMencionesComponent', () => {
  let component: BusquedaMencionesComponent;
  let fixture: ComponentFixture<BusquedaMencionesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BusquedaMencionesComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BusquedaMencionesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
