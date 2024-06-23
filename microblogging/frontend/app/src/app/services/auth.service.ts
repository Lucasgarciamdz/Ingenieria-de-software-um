import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of, switchMap} from "rxjs";
import {environment} from "../environment/environment.dev";
import {BasicResponse} from "../models/basic-response.model";
import {Router} from "@angular/router";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(
        private _httpClient: HttpClient,
        private router: Router
    ) {}

    get headers_http() {
        let header = {
            'Content-Type': 'application/json',
        }
        return {headers: header};
    }


    logIn(username: string, password: string): Observable<any> {
        console.log("Creando petición de log in")
        const url = `${environment.url}/login`;
        const body = {email: username, password: password};
        return this._httpClient.post<BasicResponse>(url, body)
            .pipe(switchMap((response: any) => {
                if (response.response == null) {
                    throw new Error('Las credenciales son inválidas');
                }
                localStorage.setItem('idUsuario', response.response.id.toString());
                localStorage.setItem('nombreUsuario', response.response.nombre);
                localStorage.setItem('emailUsuario', response.response.email);
                console.log('response', response);
                this.router.navigate(['/home']);
                return of(response);
            }),);
    }



}
