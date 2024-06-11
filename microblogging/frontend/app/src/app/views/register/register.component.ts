import { Component } from '@angular/core';
import { NavBarComponent } from "../../components/nav-bar/nav-bar.component";
import { UsersService } from '../../services/users.service';
import { FormsModule, NgForm } from "@angular/forms";
import { tap } from 'rxjs/operators';
import { Router } from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NavBarComponent,
    FormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  constructor(private userService: UsersService, private router: Router) { }

  onSubmit(registerForm: NgForm) {
    if (registerForm.valid) {
      const userData = {
        name: registerForm.value.name,
        lastname: registerForm.value.lastname,
        birth_date: registerForm.value.birthdate,
        user_name: registerForm.value.username,
        email: registerForm.value.email,
        password: registerForm.value.password,
        rol: registerForm.value.rol.toLowerCase() // Convertir el rol a minúsculas
      };

      console.log('Datos del usuario:', userData);

      this.userService.post(userData)
        .pipe(
          tap((response) => {
            console.log('Registro exitoso:', response);
          })
        )
        .subscribe(
          () => {
            this.router.navigate(['/home']);
          },
          (error) => {
            console.error('Error al registrar usuario:', error);
          }
        );
    }
  }
}
