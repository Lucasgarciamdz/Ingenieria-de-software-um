import {Component} from '@angular/core';
import {FormsModule, NgForm} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {MatFormFieldModule} from "@angular/material/form-field";

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

        }
    }
}
