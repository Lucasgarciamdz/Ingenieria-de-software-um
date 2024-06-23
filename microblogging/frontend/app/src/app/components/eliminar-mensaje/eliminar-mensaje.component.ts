import {ChangeDetectionStrategy, Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent, MatDialogTitle} from "@angular/material/dialog";
import {MatButtonModule} from "@angular/material/button";
import {MatCard, MatCardContent} from "@angular/material/card";
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Inject } from '@angular/core';
import {UsuarioService} from "../../services/usuario.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-eliminar-mensaje',
  standalone: true,
  imports: [MatDialogTitle, MatDialogContent, MatDialogActions, MatDialogClose, MatButtonModule, MatCardContent, MatCard],
  templateUrl: './eliminar-mensaje.component.html',
  styleUrl: './eliminar-mensaje.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class EliminarMensajeComponent {

  mensajeId: number;

  constructor(
    private usuarioSvc : UsuarioService,
    private router : Router,
    private dialogRef: MatDialogRef<EliminarMensajeComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    this.mensajeId = data.mensajeId;
  }

  eliminarMensaje() {
    console.log(" --- Eliminando mensaje")
    this.usuarioSvc.eliminarMensaje(this.mensajeId).then(
      res=>{
        if (res.response ==null){
          alert("No se pudo eliminar el mensaje");
          return;
        }
        alert("Mensaje eliminado con éxito")
      }
    )
    this.dialogRef.close()
  }
}
