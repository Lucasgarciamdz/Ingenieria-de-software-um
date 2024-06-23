import {ChangeDetectorRef, Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {NgForOf, NgIf} from "@angular/common";
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";

@Component({
    selector: 'app-busqueda-menciones',
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
    templateUrl: './busqueda-menciones.component.html',
    styleUrl: './busqueda-menciones.component.css'
})
export class BusquedaMencionesComponent {
    usuarios: Usuario[] = [];

    constructor(
        private usuarioSvc: UsuarioService,
        private changeDetector: ChangeDetectorRef
    ) {
    }

    async buscarUsuario(nombreUsuario: string) {
        this.usuarios = [];
        console.log(" --- Buscando usuarios por nombre de usuario : " + nombreUsuario);
        await this.usuarioSvc.obtenerUsuariosPorNombre(nombreUsuario).then(
            res => {
                this.usuarios = res.slice();
                console.log(" ------ USUARIOS encontrados: " + this.usuarios.length)
                console.log(" ------ USUARIOS encontrados: " + this.usuarios)
                this.changeDetector.detectChanges();
            }
        ).catch(error => {
            console.log(error)
        })
    }

    agregarMenciones(usuario: Usuario) {
        console.log(" --- Agregando usuario de  ID: ", usuario.id)
        const usuariosActuales = this.usuarioSvc.usuariosMencionados.getValue();
        this.usuarioSvc.usuariosMencionados.next([...usuariosActuales, usuario]);
    }
}
