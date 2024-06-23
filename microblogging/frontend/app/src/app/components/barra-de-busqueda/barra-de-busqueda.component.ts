import {ChangeDetectorRef, Component} from '@angular/core';
import {MatFormField, MatLabel, MatPrefix, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {NgFor, NgIf} from "@angular/common";
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";

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
        MatSuffix,
        NgFor,
        NgIf,
        MatDialogActions,
        MatDialogClose,
        MatDialogContent
    ],
    templateUrl: './barra-de-busqueda.component.html',
    styleUrl: './barra-de-busqueda.component.css'


})
export class BarraDeBusquedaComponent {


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
                this.usuarios = res.response.slice();
                console.log(" ------ USUARIOS encontrados: " + this.usuarios.length)
                console.log(" ------ USUARIOS encontrados: " + this.usuarios)
                this.changeDetector.detectChanges();
            }
        ).catch(error => {
            console.log(error)
        })
    }

    seguirUsuario(idUsuarioASeguir: number | string) {
        console.log(" --- Siguiendo usuario de ID: ", idUsuarioASeguir)
        this.usuarioSvc.seguirUsuarioPorId(idUsuarioASeguir);
    }
}
