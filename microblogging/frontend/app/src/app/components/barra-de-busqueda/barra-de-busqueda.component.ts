import { Component } from '@angular/core';
import {MatFormField, MatLabel, MatPrefix, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {Usuario} from "../../models/usuario.model";

@Component({
  selector: 'app-barra-de-busqueda',
  standalone: true,
    imports: [
        MatFormField,
        MatIcon,
        MatIconButton,
        MatInput,
        MatLabel,
        MatPrefix,
        MatSuffix
    ],
  templateUrl: './barra-de-busqueda.component.html',
  styleUrl: './barra-de-busqueda.component.css'
})
export class BarraDeBusquedaComponent {
  usuarios: Usuario[] = [];

  borrar() {

  }

  buscarUsuario(value: string) {

  }

  seguirUsuario(name) {

  }
}
