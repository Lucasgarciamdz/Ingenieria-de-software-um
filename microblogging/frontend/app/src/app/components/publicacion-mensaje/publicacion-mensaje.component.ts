import {MatCardModule} from "@angular/material/card";
import {MatProgressBarModule} from "@angular/material/progress-bar";
import {NgFor} from "@angular/common";
import {MatButtonModule} from "@angular/material/button";
import {MatMenuModule} from "@angular/material/menu";
import {MatIconModule} from "@angular/material/icon";
import { MatFormFieldModule} from "@angular/material/form-field";
import { MatCheckboxModule} from "@angular/material/checkbox";
import {LiveAnnouncer} from '@angular/cdk/a11y';
import {COMMA, ENTER} from '@angular/cdk/keycodes';
import {ChangeDetectionStrategy, Component, inject, signal} from '@angular/core';
import {MatChipEditedEvent, MatChipInputEvent, MatChipsModule} from '@angular/material/chips';
import {FormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";


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
    MatCheckboxModule,
    MatIconModule,
    MatFormFieldModule,
    FormsModule,
    MatInputModule

  ],
  templateUrl: './publicacion-mensaje.component.html',
  styleUrl: './publicacion-mensaje.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class PublicacionMensajeComponent {

  readonly addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;
  readonly etiquetas = signal<any[]>([]);
  readonly menciones = signal<any[]>([]);
  readonly announcer = inject(LiveAnnouncer);

  addEtiqueta(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value && value) {
      this.etiquetas.update(etiquetas => [...etiquetas, {valor: "#"+value}]);
    }
    event.chipInput!.clear();
  }

  addMencion(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();
    if (value && value) {
      this.menciones.update(menciones => [...menciones, {valor: value}]);
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
}


export interface Etiqueta {
  valor: string;
}

export interface Mencion {
  valor: string;
}
