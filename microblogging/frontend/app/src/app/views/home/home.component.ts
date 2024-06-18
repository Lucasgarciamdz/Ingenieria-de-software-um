import { Component, OnInit } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {PublicationSummaryComponent} from "../../components/publication-summary/publication-summary.component";
import {NgFor, NgIf} from "@angular/common";
import {FormsModule} from "@angular/forms";
import {MensajeComponent} from "../../components/mensaje/mensaje.component";
import {PublicacionMensajeComponent} from "../../components/publicacion-mensaje/publicacion-mensaje.component";

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
    PublicacionMensajeComponent
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})


export class HomeComponent {

}
