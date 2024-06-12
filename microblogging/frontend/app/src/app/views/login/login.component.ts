import {afterNextRender, Component} from '@angular/core';
import { NavBarComponent } from "../../components/nav-bar/nav-bar.component";
import { FormsModule, NgForm } from "@angular/forms";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router"



@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NavBarComponent,
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private authService: AuthService, private router: Router) {

 
  }
}

