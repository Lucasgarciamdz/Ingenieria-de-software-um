import {ChangeDetectorRef, Component} from '@angular/core';
import {MatDialogActions, MatDialogClose, MatDialogContent} from "@angular/material/dialog";
import {MatFormField, MatLabel, MatSuffix} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {NgForOf, NgIf} from "@angular/common";
import {Etiqueta} from "../../models/etiqueta.model";
import {UsuarioService} from "../../services/usuario.service";

@Component({
    selector: 'app-temas-del-momento',
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
    templateUrl: './temas-del-momento.component.html',
    styleUrl: './temas-del-momento.component.css'
})
export class TemasDelMomentoComponent {

    temas: Etiqueta[] = [];
    constructor(
        private usuarioSvc: UsuarioService,
        private changeDetector: ChangeDetectorRef
    ) {
    }

    ngOnInit(){
        this.obtenerEtiquetasDelMomento();
    }


    async obtenerEtiquetasDelMomento() {
        this.temas = [];
        console.log(" --- Buscando temas del momento")
        await this.usuarioSvc.obtenerTemasDelMomento().then(
            res => {
                console.log(" --- Respuesta:", res)
                this.temas = res;
                console.log(" --- Temas encontrados: ", this.temas, this.temas.length)
                this.changeDetector.detectChanges();

            }
        ).catch(error => {
            console.log(error)
        })
    }

}
