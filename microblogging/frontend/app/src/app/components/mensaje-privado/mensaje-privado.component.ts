import {ChangeDetectorRef, Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogRef} from "@angular/material/dialog";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {NgForOf, NgIf} from "@angular/common";
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";

@Component({
  selector: 'app-mensaje-privado',
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
    MatDialogClose
  ],
  templateUrl: './mensaje-privado.component.html',
  styleUrl: './mensaje-privado.component.css'
})
export class MensajePrivadoComponent {
  usuarios: Usuario[] = [];

  constructor(
    private usuarioSvc: UsuarioService,
    private changeDetector: ChangeDetectorRef,
    private dialogRef: MatDialogRef<MensajePrivadoComponent>
  ) {
  }

  async buscarUsuario(nombreUsuario: string) {
    this.usuarios = [];
    console.log(" --- Buscando usuarios por nombre de usuario : " + nombreUsuario);
    await this.usuarioSvc.obtenerUsuariosPorNombre(nombreUsuario).then(
      res => {
        this.usuarios = res.response.slice();
        console.log(" ------ USUARIOS encontrados: " + this.usuarios.length)
        console.log(" ------ USUARIOS encontrados: " + this.usuarios)
        this.changeDetector.detectChanges();
      }
    ).catch(error => {
      console.log(error)
    })
  }

  agregarUsuariosPrivados(usuario: Usuario) {
    console.log(" --- Agregando usuario de  ID: ", usuario.id)
    this.usuarioSvc.usuarioMensajePrivado.next([usuario]);
    this.dialogRef.close();
  }
}
