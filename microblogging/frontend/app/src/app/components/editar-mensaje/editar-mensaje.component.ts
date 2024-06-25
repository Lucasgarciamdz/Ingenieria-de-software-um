import {ChangeDetectorRef, Component, inject, Inject, signal} from '@angular/core';
import {Etiqueta, Mencion, PublicacionMensajeComponent} from "../publicacion-mensaje/publicacion-mensaje.component";
import {MatCard, MatCardContent} from "@angular/material/card";
import {
    MAT_DIALOG_DATA, MatDialog,
    MatDialogActions,
    MatDialogClose, MatDialogConfig,
    MatDialogContent,
    MatDialogTitle
} from "@angular/material/dialog";
import {Mensaje} from "../../models/mensaje.model";
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatButton} from "@angular/material/button";
import {
    MatChipEditedEvent,
    MatChipGrid,
    MatChipInput,
    MatChipInputEvent,
    MatChipRemove,
    MatChipRow
} from "@angular/material/chips";
import {MatError, MatFormField, MatLabel} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatInput} from "@angular/material/input";
import {NgForOf, NgIf} from "@angular/common";
import {COMMA, ENTER} from "@angular/cdk/keycodes";
import {LiveAnnouncer} from "@angular/cdk/a11y";
import {Usuario} from "../../models/usuario.model";
import {Subscription} from "rxjs";
import {UsuarioService} from "../../services/usuario.service";
import {BusquedaMencionesComponent} from "../busqueda-menciones/busqueda-menciones.component";
import {MensajePrivadoComponent} from "../mensaje-privado/mensaje-privado.component";
import {NuevoMensaje} from "../../models/nuevo-mensaje.model";

@Component({
    selector: 'app-editar-mensaje',
    standalone: true,
    imports: [
        PublicacionMensajeComponent,
        MatCard,
        MatCardContent,
        MatDialogActions,
        MatDialogClose,
        MatDialogContent,
        MatDialogTitle,
        FormsModule,
        MatButton,
        MatChipGrid,
        MatChipInput,
        MatChipRemove,
        MatChipRow,
        MatError,
        MatFormField,
        MatIcon,
        MatInput,
        MatLabel,
        NgForOf,
        NgIf,
        ReactiveFormsModule
    ],
    templateUrl: './editar-mensaje.component.html',
    styleUrl: './editar-mensaje.component.css'
})
export class EditarMensajeComponent {

    mensaje: Mensaje | any;


    readonly addOnBlur = true;
    readonly separatorKeysCodes = [ENTER, COMMA] as const;
    readonly etiquetas = signal<any[]>([]);
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
        private changeDetectorRef: ChangeDetectorRef,
        @Inject(MAT_DIALOG_DATA) public data: Mensaje
    ) {
        this.form = this.fb.group({
            textoFormControl: ['', [Validators.required, Validators.maxLength(140)]]
        });
    }

    ngOnInit() {
        this.mensaje = this.data;
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

    editarMensaje() {

        console.log("Editando mensaje", this.mensaje.id)

        if (!this.form.valid) {
            console.log("El formulario no es válido. Por favor, revisa los campos.");
            return;
        }
        const body = {
            texto : this.form.get('textoFormControl')?.value,
        }
        this.usuarioSvc.editarMensaje(body, this.mensaje.id).then(
            res => {
                if (res.response == null) {
                    console.log("Mensaje no creado")
                    return;
                }
                alert("Mensaje Editado")
                location.reload();
            }
        )
    }

}
