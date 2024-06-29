import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of, switchMap} from "rxjs";
import {environment} from "../environment/environment.dev";
import {BaseResponse} from "../models/basic-response.model";
import {Router} from "@angular/router";
import {Usuario} from "../models/usuario.model";
import {resolve} from "node:path";
import {rejects} from "assert";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(
    private _httpClient: HttpClient,
    private router: Router
  ) {
  }

  get headers_http() {
    let header = {
      'Content-Type': 'application/json',
    }
    return {headers: header};
  }


  logIn(email: string, password: string): Promise<any> {
    console.log("Creando petición de log in")
    const url = `${environment.url}/usuarios/login`;
    const body = {email: email, clave: password};
    return new Promise<any | void>((resolve, reject) => {
      this._httpClient.post<Usuario[]>(url, body, this.headers_http).subscribe(
        {
          next(res: any) {
            console.log("---- Se hizo la petición : ", res)
            resolve(res)
          },
          error(msg) {
            reject(msg)
          }
        });
    });
  }

  registrarUsuario(userData: { descripcion: any; clave: any; nombreUsuario: any; nombreCompleto: any; email: any }) {
    console.log(" Registrando usuario", userData)
    const url = `${environment.url}/usuarios`;
    return new Promise<any | void>((resolve, reject) => {
      this._httpClient.post<BaseResponse[]>(url, userData, this.headers_http).subscribe(
        {
          next(res: any) {
            console.log("---- Se hizo la petición : ", res)
            resolve(res)
          },
          error(msg) {
            reject(msg)
          }
        });
    });
  }
}
