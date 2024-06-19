import { Component, OnInit } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {PublicationSummaryComponent} from "../../components/publication-summary/publication-summary.component";
import {NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {MensajeComponent} from "../../components/mensaje/mensaje.component";
import {PublicacionMensajeComponent} from "../../components/publicacion-mensaje/publicacion-mensaje.component";
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatInputModule} from "@angular/material/input";
import {MatButtonModule} from "@angular/material/button";
import {MatIconModule} from "@angular/material/icon";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    NavBarComponent,
    PublicationSummaryComponent,
    NgFor,
    FormsModule,
    NgIf,
    MensajeComponent,
    PublicacionMensajeComponent,
    MatFormFieldModule, MatInputModule, MatButtonModule, MatIconModule
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})


export class HomeComponent {

  borrar() {

  }
}
