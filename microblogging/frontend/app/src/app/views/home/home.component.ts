import {Component, ChangeDetectionStrategy, inject, ChangeDetectorRef} from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {MensajeComponent} from "../../components/mensaje/mensaje.component";
import {PublicacionMensajeComponent} from "../../components/publicacion-mensaje/publicacion-mensaje.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";
import {MatExpansionModule} from "@angular/material/expansion";
import {BarraDeBusquedaComponent} from "../../components/barra-de-busqueda/barra-de-busqueda.component";
import {MatDialog, MatDialogConfig, MatDialogModule} from "@angular/material/dialog";
import {TemasDelMomentoComponent} from "../../components/temas-del-momento/temas-del-momento.component";
import {Usuario} from "../../models/usuario.model";
import {Mensaje} from "../../models/mensaje.model";
import {UsuarioService} from "../../services/usuario.service";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NavBarComponent,
    NgFor,
    FormsModule,
    NgIf,
    MensajeComponent,
    PublicacionMensajeComponent,
    MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule,
    MatExpansionModule, BarraDeBusquedaComponent, MatButtonModule,
    MatDialogModule

  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})


export class HomeComponent {

  readonly dialogBusqueda = inject(MatDialog);
  readonly dialogTemasMomento = inject(MatDialog);
  usuariosMencionados: Usuario[] = [];
  mensajes: (Mensaje | any)[] = [];

  constructor(
    private usuarioSvc :UsuarioService,
    private changeDetectorRef : ChangeDetectorRef
  ) {

  }

  ngOnInit(){
    this.usuarioSvc.obtenerMensajesDeUsuarioPorId(Number(localStorage.getItem('idUsuario'))).then(
      res=>{
        this.mensajes = res.response;
        console.log("Mensajes cargados: ", this.mensajes.length, this.mensajes)
        this.changeDetectorRef.detectChanges();
      }
    )
  }

  abrirBarraDeBusqueda() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '40%';
    const dialogRef = this.dialogBusqueda.open(BarraDeBusquedaComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  abrirTemasDelMomento() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '40%';
    const dialogRef = this.dialogTemasMomento.open(TemasDelMomentoComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });


  }
}
