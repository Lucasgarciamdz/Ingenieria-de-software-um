import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environment/environment.dev";
import {BasicResponse} from "../models/basic-response.model";


@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  constructor(
    private http: HttpClient
  ) {}

/*  obtenerUsuariosPorNombre(nombre : string) : Promise<any>{
    const url = `${environment.url}/usuarios?nombre=`+nombre;
    return new Promise<any | void>( (resolve,reject) => {
      this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
        {
          next(res:any) {
            resolve(res.response)
          },
          error(msg) {
            reject(msg)
          }
        });

    });

  }*/
}
