import {ChangeDetectionStrategy, Component} from '@angular/core';
import {NavBarComponent} from "../../components/nav-bar/nav-bar.component";
import {FormsModule} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router"
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {FormControl, FormGroupDirective, NgForm, Validators, ReactiveFormsModule} from '@angular/forms';
import {ErrorStateMatcher} from '@angular/material/core';
import {MatFormFieldModule} from '@angular/material/form-field';
import {NgIf} from "@angular/common";
import {of} from "rxjs";
import {UsuarioService} from "../../services/usuario.service";

export class EstadoErrorMatcher implements ErrorStateMatcher {
  isErrorState(control: FormControl | null, form: FormGroupDirective | NgForm | null): boolean {
    const isSubmitted = form && form.submitted;
    return !!(control && control.invalid && (control.dirty || control.touched || isSubmitted));
  }
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    NavBarComponent,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    ReactiveFormsModule,
    NgIf

  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent {
  constructor(
    private router: Router,
    private authService: AuthService
  ) {
  }

  emailFormControl = new FormControl('', [Validators.required, Validators.email]);
  contrasenaFormControl = new FormControl('', [Validators.required]);
  matcher = new EstadoErrorMatcher();

  ngOnInit() {
    localStorage.removeItem('idUsuario');
    localStorage.removeItem('nombreUsuario');
    localStorage.removeItem('emailUsuario');
  }

  async autenticarUsuario() {
    console.log("--- Autenticando usuario")
    const email = this.emailFormControl.value;
    const password = this.contrasenaFormControl.value;
    if (!email || !password) {
      console.error('Por favor, ingrese un email y una contraseña');
      return;
    }
    console.log("----- Email ", email)
    console.log("------Password ", password)
    await this.authService.logIn(email, password).then(
      res => {
        if (res.response == null) {
          console.log("Credenciales inválidas")
          alert("Crendenciales incorrectas")
          return;
        }
        localStorage.setItem('idUsuario', res.response.id);
        localStorage.setItem('nombreUsuario', res.response.nombreUsuario);
        localStorage.setItem('emailUsuario', res.response.email);
        this.router.navigate(['/home'])
      }
    )
  }

}
