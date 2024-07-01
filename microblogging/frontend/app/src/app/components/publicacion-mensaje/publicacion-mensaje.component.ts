import {MatCardModule} from "@angular/material/card";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {NgFor, NgIf} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {AfterViewInit, ChangeDetectionStrategy, ChangeDetectorRef, Component, inject, signal} from '@angular/core';
import {MatChipEditedEvent, MatChipInputEvent, MatChipsModule} from '@angular/material/chips';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators
} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {BarraDeBusquedaComponent} from "../barra-de-busqueda/barra-de-busqueda.component";
import {MatDialog, MatDialogActions, MatDialogClose, MatDialogConfig, MatDialogContent} from "@angular/material/dialog";
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {BusquedaMencionesComponent} from "../busqueda-menciones/busqueda-menciones.component";
import {MensajePrivadoComponent} from "../mensaje-privado/mensaje-privado.component";
import {Subscription} from "rxjs";
import {NuevoMensaje} from "../../models/nuevo-mensaje.model";


@Component({
  selector: 'app-publicacion-mensaje',
  standalone: true,
  imports: [
    MatCardModule,
    MatChipsModule,
    MatProgressBarModule,
    NgFor,
    NgIf,
    MatButtonModule,
    MatMenuModule,
    MatCheckboxModule,
    MatIconModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule,
    ReactiveFormsModule,
    BarraDeBusquedaComponent,
    MatDialogActions,
    MatDialogContent,
    MatDialogClose,


  ],
  templateUrl: './publicacion-mensaje.component.html',
  styleUrl: './publicacion-mensaje.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PublicacionMensajeComponent {

  readonly addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  readonly etiquetas = signal<(any | string)[]>([]);
  readonly announcer = inject(LiveAnnouncer);
  readonly dialogBusqueda = inject(MatDialog);
  readonly dialogMensajePrivado = inject(MatDialog);
  form: FormGroup;
  usuariosMencionados: Usuario[] = [];
  usuarioMensajePrivado: Usuario[] = [];
  private subscription: Subscription | null = null;

  constructor(
    private fb: FormBuilder,
    private usuarioSvc: UsuarioService,
    private changeDetectorRef: ChangeDetectorRef
  ) {
    this.form = this.fb.group({
      textoFormControl: ['', [Validators.required, Validators.maxLength(140)]]
    });
  }

  ngOnInit() {
    this.subscription = this.usuarioSvc.usuariosMencionados.subscribe(
      usuarios => {
        this.usuariosMencionados = usuarios;
        console.log("USUARIOS CARGADOS: ", this.usuariosMencionados.length, this.usuariosMencionados)
        this.changeDetectorRef.detectChanges();
      }
    );
    this.subscription = this.usuarioSvc.usuarioMensajePrivado.subscribe(
      usuarioMsjPrivado => {
        console.log("USUARIOS CARGADOS: ", this.usuarioMensajePrivado.length, this.usuarioMensajePrivado)
        this.usuarioMensajePrivado = usuarioMsjPrivado;
        this.changeDetectorRef.detectChanges();
      }
    )
  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }


  addEtiqueta(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value && value) {
      this.etiquetas.update(etiquetas => [...etiquetas, {valor: "#" + value}]);
    }
    event.chipInput!.clear();
  }


  remove(etiqueta: Etiqueta | Mencion): void {
    this.etiquetas.update(etiquetas => {
      const index = etiquetas.indexOf(etiqueta);
      if (index < 0) {
        return etiquetas;
      }
      etiquetas.splice(index, 1);
      this.announcer.announce(`Removed ${etiqueta.valor}`);
      return [...etiquetas];
    });
  }

  edit(etiqueta: Etiqueta | Mencion, event: MatChipEditedEvent) {
    const value = event.value.trim();
    if (!value) {
      this.remove(etiqueta);
      return;
    }
    this.etiquetas.update(etiquetas => {
      const index = etiquetas.indexOf(etiqueta);
      if (index >= 0) {
        etiquetas[index].valor = value;
        return [...etiquetas];
      }
      return etiquetas;
    });
  }


  abrirMenciones() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '40%';
    const dialogRef = this.dialogBusqueda.open(BusquedaMencionesComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  abrirMensajePrivado() {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.width = '40%';
    const dialogRef = this.dialogMensajePrivado.open(MensajePrivadoComponent, dialogConfig);
    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }

  publicarMensaje() {
    console.log("Publicando mensaje")

    if (!this.form.valid) {
      console.log("El formulario no es válido. Por favor, revisa los campos.");
      alert("El mensaje debe contener al menos texto.")
    }
    let destinatarioId = this.usuarioMensajePrivado && this.usuarioMensajePrivado.length > 0 ? Number(this.usuarioMensajePrivado[0].id) : null;
    let etiquetasValue = this.etiquetas();
    let etiquetas: string[] = etiquetasValue && etiquetasValue.length > 0 ? this.etiquetas().map(etiqueta => etiqueta.valor) : [];
    let usuariosMencionadosValue = this.usuariosMencionados;
    let mencionUsuarioId = usuariosMencionadosValue && usuariosMencionadosValue.length > 0 ? usuariosMencionadosValue.map(usuario => usuario.id) : [];
    let nuevoMensaje = new NuevoMensaje(
      Number(localStorage.getItem('idUsuario')),
      this.form.get('textoFormControl')?.value,
      destinatarioId,
      etiquetas,
      mencionUsuarioId
    );
    this.usuarioSvc.publicarMensaje(nuevoMensaje).then(
      res => {
        if (res.response == null) {
          console.log("Mensaje no creado")
          return;
        }
        alert("Mensaje Publicado")
        location.reload();
      }
    )
  }
}


export interface Etiqueta {
  valor: string;
}

export interface Mencion {
  valor: string;
}
