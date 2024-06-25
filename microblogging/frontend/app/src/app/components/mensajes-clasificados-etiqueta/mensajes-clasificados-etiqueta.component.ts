import {ChangeDetectorRef, Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {NgForOf, NgIf} from "@angular/common";
import {MensajeComponent} from "../mensaje/mensaje.component";
import {Mensaje} from "../../models/mensaje.model";
import {UsuarioService} from "../../services/usuario.service";

@Component({
  selector: 'app-mensajes-clasificados-etiqueta',
  standalone: true,
  imports: [
    MatDialogActions,
    MatDialogContent,
    MatFormField,
    MatIcon,
    MatIconButton,
    MatInput,
    MatLabel,
    MatSuffix,
    NgForOf,
    NgIf,
    MatDialogClose,
    MensajeComponent
  ],
  templateUrl: './mensajes-clasificados-etiqueta.component.html',
  styleUrl: './mensajes-clasificados-etiqueta.component.css'
})
export class MensajesClasificadosEtiquetaComponent {
  mensajes: Mensaje[] = [];

  constructor(
    private usuarioSvc: UsuarioService,
    private changeDetector :ChangeDetectorRef
  ) {}

buscarMensajePorEtiqueta(nombre :string) {
  console.log(" --Buscando mensajes por etiquetas")

  if (nombre.startsWith('#')) {
    nombre = nombre.slice(1);
  }

  this.usuarioSvc.obtenerMensajesPorEtiqueta(nombre).then(
    res=> {
      this.mensajes = res.response;
      this.changeDetector.detectChanges()
    }
  )
}
}
