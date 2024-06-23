import {Component, Input, ChangeDetectionStrategy, inject} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {NgFor, NgIf} from "@angular/common";
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';
import {Usuario} from "../../models/usuario.model";
import {Mensaje} from "../../models/mensaje.model";
import {Etiqueta} from "../../models/etiqueta.model";
import {UsuarioService} from "../../services/usuario.service";
import {MatDialog, MatDialogConfig} from "@angular/material/dialog";
import {BarraDeBusquedaComponent} from "../barra-de-busqueda/barra-de-busqueda.component";
import {EditarMensajeComponent} from "../editar-mensaje/editar-mensaje.component";
import {EliminarMensajeComponent} from "../eliminar-mensaje/eliminar-mensaje.component";


@Component({
  selector: 'app-mensaje',
  standalone: true,
  imports: [MatCardModule, MatChipsModule, MatProgressBarModule, NgFor, MatButtonModule, MatMenuModule, MatIconModule, NgIf],
  changeDetection: ChangeDetectionStrategy.OnPush, templateUrl: './mensaje.component.html',
  styleUrl: './mensaje.component.css'
})
export class MensajeComponent {
  @Input() mensaje: Mensaje | any;


  listadosEtiquetas: (Etiqueta | any)[] = [];
  listadosMenciones: (Usuario | any)[] = [];
  nombreUsuario: string = "";
  texto = ""
  readonly dialogEditarMensaje = inject(MatDialog);
  readonly dialgoELiminarMensaje = inject(MatDialog);

  constructor(
    private usuarioSvc: UsuarioService
  ) {}

  ngOnInit() {
    this.listadosEtiquetas = this.mensaje?.etiquetas;
    this.listadosMenciones = this.mensaje?.usuarioDestinatario;
    this.nombreUsuario = this.mensaje?.autor.nombreUsuario;
    this.texto = this.mensaje?.texto;
  }

  protected readonly localStorage = localStorage;

  repostearMensaje(id: number) {
    console.log(" -- Reposteando mensaje")
    this.usuarioSvc.republicarMensaje(localStorage.getItem('idUsuario'), id).then(
      res => {
        if (res.response == null) {
          console.log("No se pudo republicar le mensaje")
          alert("No se pudo republicar el mensaje");
          return;
        }
        alert("Mensaje republicado")
      }
    )
  }

  abrirEdicionMensaje() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '40%';
    const dialogRef = this.dialogEditarMensaje.open(EditarMensajeComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  abrirEliminarMensaje(id: number) {
  const dialogConfig = new MatDialogConfig();
  dialogConfig.width = '40%';
  dialogConfig.data = { mensajeId: id }; // Pasar el ID del mensaje
  const dialogRef = this.dialgoELiminarMensaje.open(EliminarMensajeComponent, dialogConfig);
  dialogRef.afterClosed().subscribe(result => {
    console.log(`Dialog result: ${result}`);
  });
}
}
