import {Component, Input, ChangeDetectionStrategy} from '@angular/core';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import {MatCardModule} from '@angular/material/card';
import {MatChipsModule} from '@angular/material/chips';
import {NgFor} from "@angular/common";
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import {MatButtonModule} from '@angular/material/button';


@Component({
  selector: 'app-mensaje',
  standalone: true,
  imports: [MatCardModule, MatChipsModule, MatProgressBarModule, NgFor, MatButtonModule, MatMenuModule, MatIconModule],
  changeDetection: ChangeDetectionStrategy.OnPush,  templateUrl: './mensaje.component.html',
  styleUrl: './mensaje.component.css'
})
export class MensajeComponent {
  @Input() listadosEtiquetas: string[] = ["perros", "gatos","tech"];
  @Input() listadosMenciones: string[] = ["Augusto", "Lucas","Franco"];
  @Input() nombreUsuario: string= "Usuario Prueba";
  @Input() texto = "est ultricies integer quis auctor elit sed vulputate mi sit amet mauris commodo quis imperdiet massa tincidunt nunc pulvinar sapien et ligula ullamcorper malesuada proin\n"

}
