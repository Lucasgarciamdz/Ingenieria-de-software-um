import {ChangeDetectionStrategy, Component} from '@angular/core';
import { NavBarComponent } from "../../components/nav-bar/nav-bar.component";
import { FormsModule } from "@angular/forms";
import { AuthService } from "../../services/auth.service";
import { Router } from "@angular/router"
import { MatIconModule} from '@angular/material/icon';
import { MatInputModule} from '@angular/material/input';
import { FormControl, FormGroupDirective, NgForm, Validators , ReactiveFormsModule} from '@angular/forms';
import { ErrorStateMatcher} from '@angular/material/core';
import { MatFormFieldModule} from '@angular/material/form-field';
import {NgIf} from "@angular/common";

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
  constructor(private authService: AuthService, private router: Router) {}
    emailFormControl = new FormControl('', [Validators.required, Validators.email]);
    contrasenaFormControl = new FormControl('', [Validators.required]);
    matcher = new EstadoErrorMatcher();


}

