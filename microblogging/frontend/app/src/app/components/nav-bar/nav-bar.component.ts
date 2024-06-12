import {afterNextRender, Component} from '@angular/core';
import { NgIf } from "@angular/common";
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [NgIf],
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

  // ngAfterViewInit() {
  //   if (typeof localStorage !== 'undefined') {
  //     this.rol = localStorage.getItem('rol');
  //   }
  // }
}
