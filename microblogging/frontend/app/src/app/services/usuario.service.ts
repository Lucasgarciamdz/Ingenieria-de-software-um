import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environment/environment.dev";
import {BasicResponse} from "../models/basic-response.model";
import {AuthService} from "./auth.service";
import {Usuario} from "../models/usuario.model";


@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  constructor(
    private http: HttpClient,
    private authSvc : AuthService
  ) {}


    // Todo arreglar el modelo para cuando añada el back
  obtenerUsuariosPorNombre(nombre : string) : Promise<any>{
    // const url = `${environment.url}/usuarios?nombre=`+nombre;
    const url = `${environment.url}/usuarios`;
    console.log("URL" , url)
    return new Promise<any | void>( (resolve,reject) => {
      // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
      this.http.get<Usuario[]>(url, this.authSvc.headers_http).subscribe(
        {
          next(res:any) {
              console.log("---- Se hizo la petición : " , res)
            resolve(res)
          },
          error(msg) {
            reject(msg)
          }
        });
    });

  }

    seguirUsuarioPorId(idUsuarioASeguir: number | string) {
        const url = `${environment.url}/usuarios/seguir`;
        console.log("URL" , url)
        return new Promise<any | void>( (resolve,reject) => {
            // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
            this.http.post<Usuario[]>(url, this.authSvc.headers_http).subscribe(
                {
                    next(res:any) {
                        console.log("---- Se hizo la petición : " , res)
                        resolve([res])
                    },
                    error(msg) {
                        reject(msg)
                    }
                });
        });
    }
}
