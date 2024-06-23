import {Component} from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {MatTabsModule} from "@angular/material/tabs";
import {MatCard, MatCardActions, MatCardContent, MatCardHeader} from "@angular/material/card";
import {Usuario} from "../../models/usuario.model";
import {UsuarioService} from "../../services/usuario.service";
import {MatList, MatListItem} from "@angular/material/list";
import {NgForOf} from "@angular/common";
import {MensajeComponent} from "../../components/mensaje/mensaje.component";
import {PublicacionMensajeComponent} from "../../components/publicacion-mensaje/publicacion-mensaje.component";

@Component({
    selector: 'app-profile',
    standalone: true,
    imports: [
        NavBarComponent,
        MatTabsModule,
        MatCard,
        MatCardHeader,
        MatCardContent,
        MatCardActions,
        MatList,
        NgForOf,
        MatListItem,
        MensajeComponent,
        PublicacionMensajeComponent
    ],
    templateUrl: './profile.component.html',
    styleUrl: './profile.component.css'
})
export class ProfileComponent {

    usuario: Usuario | any;

    constructor(
        private usuarioSvc: UsuarioService
    ) {
    }

    async ngOnInit() {
        console.log(" --- Buscando información de usuario por ID: ");
        await this.usuarioSvc.obtenerInformacionDeUsuarioPorId(Number(localStorage.getItem('idUsuario'))).then(
            res => {
                console.log(" --- Usuario encontrado:", res)
                this.usuario = res;
                alert("Credenciales incorrectas.")
            }
        ).catch(error =>{
            console.log(error)
        })
    }


}
