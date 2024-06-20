import {afterNextRender, ChangeDetectionStrategy, Component, signal} from '@angular/core';
import { NgIf } from "@angular/common";
import { AuthService } from '../../services/auth.service';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatFormField, MatLabel, MatPrefix, MatSuffix} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {
  MatExpansionPanel,
  MatExpansionPanelDescription,
  MatExpansionPanelHeader,
  MatExpansionPanelTitle
} from "@angular/material/expansion";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [NgIf, MatToolbarModule, MatButtonModule, MatIconModule, MatFormField, MatInput, MatLabel, MatPrefix, MatSuffix, MatExpansionPanel, MatExpansionPanelDescription, MatExpansionPanelHeader, MatExpansionPanelTitle],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,

})
export class NavBarComponent {
  user_id: string | null = null;
  rol: string | null = null;

  constructor(private authService: AuthService) {
    afterNextRender(() => {
      this.user_id = this.authService.getUserId();

    });
  }
  readonly panelOpenState = signal(false);

  borrar() {

  }


}
