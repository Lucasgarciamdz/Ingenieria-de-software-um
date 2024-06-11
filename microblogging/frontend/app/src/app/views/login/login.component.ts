import {afterNextRender, Component} from '@angular/core';
import { NavBarComponent } from "../../components/nav-bar/nav-bar.component";
import { FormsModule, NgForm } from "@angular/forms";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router"
import { EditorsService } from "../../services/editors.service";


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

  constructor(private authService: AuthService, private router: Router, private editorService: EditorsService) {
    afterNextRender(() => {
      localStorage.clear()
    });
  }

  onSubmit(loginForm: NgForm) {
    if (loginForm.valid) {
      const username = loginForm.value.username;
      const password = loginForm.value.password;

      this.authService.login(username, password).subscribe(
        (response) => {
          console.log('Response:', response);
          if (response.user_id) {
            localStorage.setItem('userId', response.user_id);
            localStorage.setItem('role', response.rol);

            this.editorService.getEditorByUser(response.user_id).subscribe(
              (editorId) => {
                localStorage.setItem('editorId', editorId.editor_id);
                this.router.navigate(['/home']);
              },
              (error) => {
                console.error('Error obteniendo editorId:', error);
                alert('Ocurrió un error al obtener el editorId');
              }
            );
          } else {
            alert('Nombre de usuario o contraseña inválidos');
          }
        },
        (error) => {
          console.error('Error:', error);
          alert('Ocurrió un error al intentar iniciar sesión');
        }
      );
    }
  }
}
