import {afterNextRender, Component} from '@angular/core';
import { NgIf } from "@angular/common";
import { AuthService } from '../../services/auth.service';
import {MatIconModule} from "@angular/material/icon";
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [NgIf, MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent {
  user_id: string | null = null;
  rol: string | null = null;

  constructor(private authService: AuthService) {
    afterNextRender(() => {
      this.user_id = this.authService.getUserId();

    });
  }


}
