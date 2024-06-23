import {Component} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    FormsModule, MatFormFieldModule, MatInputModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  constructor(
    private authSvc: AuthService,
    private router: Router
  ) {
  }

  async onSubmit(registerForm: NgForm) {
    if (registerForm.valid) {
      const userData = {
        nombreUsuario: registerForm.value.nombreUsuario,
        nombreCompleto: registerForm.value.nombreCompleto,
        descripcion: registerForm.value.descripcion,
        email: registerForm.value.email,
        clave: registerForm.value.clave
      };
      await this.authSvc.registrarUsuario(userData).then(
        res => {
          alert("Usuario registrado con éxito")
          this.router.navigate(['/login'])
        }).catch(error => {
        console.log("Error al registrar usuario.", error)
        alert("Error al registrar usuario")
      })

      ;
    }
  }
}
