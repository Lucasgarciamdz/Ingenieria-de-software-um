import { Component } from '@angular/core';
import {MatCardModule} from "@angular/material/card";
import {MatChipListbox, MatChipsModule} from "@angular/material/chips";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {NgFor} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatIconModule} from "@angular/material/icon";
import {MatFormFieldModule} from "@angular/material/form-field";
import { MatCheckboxModule} from "@angular/material/checkbox";


@Component({
  selector: 'app-publicacion-mensaje',
  standalone: true,
  imports: [
    MatCardModule,
    MatChipsModule,
    MatProgressBarModule,
    NgFor,
    MatButtonModule,
    MatMenuModule,
    MatIconModule,
    MatFormFieldModule,
    MatCheckboxModule,
    MatChipsModule
  ],
  templateUrl: './publicacion-mensaje.component.html',
  styleUrl: './publicacion-mensaje.component.css'
})
export class PublicacionMensajeComponent {
  tags: string[] = [];
  mentions: string[] = [];
  tagInput = '';
  mentionInput = '';


}
