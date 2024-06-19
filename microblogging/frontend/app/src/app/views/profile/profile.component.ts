import { Component } from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {MatTabsModule} from "@angular/material/tabs";

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [
    NavBarComponent,
    MatTabsModule
  ],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent {
  name: string = 'John'; // Valor predeterminado para el nombre
  lastname: string = 'Doe'; // Valor predeterminado para el apellido
  birth_date: Date = new Date('1990-01-01'); // Valor predeterminado para la fecha de nacimiento
  user_name: string = 'johndoe'; // Valor predeterminado para el nombre de usuario
  email: string = 'john@example.com'; // Valor predeterminado para el email
}
