import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environment/environment.dev";
import {AuthService} from "./auth.service";
import {Usuario} from "../models/usuario.model";
import {Etiqueta} from "../models/etiqueta.model";
import {BehaviorSubject} from "rxjs";
import {NuevoMensaje} from "../models/nuevo-mensaje.model";
import {BaseResponse} from "../models/basic-response.model";
import {resolve} from "node:path";
import {rejects} from "assert";


@Injectable({
  providedIn: 'root'
})

export class UsuarioService {

  public usuariosMencionados: BehaviorSubject<Usuario[]> = new BehaviorSubject<Usuario[]>([]);
  public usuarioMensajePrivado: BehaviorSubject<Usuario[]> = new BehaviorSubject<Usuario[]>([]);

  constructor(
    private http: HttpClient,
    private authSvc: AuthService
  ) {
  }


  // Todo arreglar el modelo para cuando añada el back
  obtenerUsuariosPorNombre(nombre: string): Promise<any> {
    const url = `${environment.url}/usuarios/buscar/` + nombre + '?idUsuario=' + localStorage.getItem('idUsuario');
    console.log("URL", url)
    return new Promise<any | void>((resolve, reject) => {
      this.http.get<BaseResponse>(url, this.authSvc.headers_http).subscribe(
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

  seguirUsuarioPorId(idUsuarioASeguir: number | string) {
    const url = `${environment.url}/usuarios/follow`;
    console.log("URL", url)
    const body = {
      idUsuario: localStorage.getItem('idUsuario'),
      idSeguir :idUsuarioASeguir
    }
    return new Promise<any | void>((resolve, reject) => {
      this.http.post<BaseResponse>(url, body, this.authSvc.headers_http).subscribe(
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

  async obtenerTemasDelMomento() {
    console.log(" --- Obteniendo etiquetas del momento")
    const url = `${environment.url}/etiquetas/temasMomento?cantidad=10`;
    console.log("URL", url)
    return new Promise<any | void>((resolve, reject) => {
      // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
      this.http.get<BaseResponse[]>(url, this.authSvc.headers_http).subscribe(
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

  async obtenerInformacionDeUsuarioPorId(idUsuario: number) {
    console.log(" --- Obteniendo etiquetas del momento")
    const url = `${environment.url}/usuarios/` + idUsuario;
    console.log("URL", url)
    return new Promise<any | void>((resolve, reject) => {
      // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
      this.http.get<BaseResponse>(url, this.authSvc.headers_http).subscribe(
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

  publicarMensaje(nuevoMensaje: NuevoMensaje) {
    console.log(" --- Publicando nuevo mensaje")
    const url = `${environment.url}/mensajes`;
    console.log("URL", url)
    return new Promise<any | void>((resolve, reject) => {
      this.http.post<BaseResponse>(url, nuevoMensaje, this.authSvc.headers_http).subscribe(
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



  async republicarMensaje(idUsuario: string | null, idMensaje: number) {
    console.log(" --- Republicando nuevo mensaje")
    const url = `${environment.url}/mensajes/repost`;
    const body = {
      idUsuario: idUsuario,
      idMensaje: idMensaje
    }
    return new Promise<any | void>((resolve, reject) => {
      this.http.post<BaseResponse>(url, body, this.authSvc.headers_http).subscribe(
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

  async eliminarMensaje(idMensaje: number): Promise<any> {
    console.log(" ---Eliminar mensaje de id ", idMensaje);
    const url = `${environment.url}/mensajes/` + idMensaje;
    return new Promise<any | void>((resolve, reject) => {
      this.http.delete<BaseResponse>(url, this.authSvc.headers_http).subscribe(
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


  obtenerMensajesDeUsuarioPorId(idUsuario: number) {
    console.log(" --- Buscando mensajes de usuario ID: ", idUsuario)
    const url = `${environment.url}/mensajes/usuario/` + idUsuario;
    console.log("URL", url)
    return new Promise<any | void>((resolve, reject) => {
      this.http.get<BaseResponse>(url, this.authSvc.headers_http).subscribe(
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
