import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../environment/environment.dev";
import {AuthService} from "./auth.service";
import {Usuario} from "../models/usuario.model";
import {Etiqueta} from "../models/etiqueta.model";
import {BehaviorSubject} from "rxjs";
import {NuevoMensaje} from "../models/nuevo-mensaje.model";


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
        // const url = `${environment.url}/usuarios?nombre=`+nombre;
        const url = `${environment.url}/usuarios`;
        console.log("URL", url)
        return new Promise<any | void>((resolve, reject) => {
            this.http.get<Usuario[]>(url, this.authSvc.headers_http).subscribe(
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
        const url = `${environment.url}/usuarios/seguir`;
        console.log("URL", url)

        // TODO Setear algun tipo de respuesta si se siguio correcto!
        return new Promise<any | void>((resolve, reject) => {
            // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
            this.http.post<Usuario[]>(url, this.authSvc.headers_http).subscribe(
                {
                    next(res: any) {
                        console.log("---- Se hizo la petición : ", res)
                        resolve([res])
                    },
                    error(msg) {
                        reject(msg)
                    }
                });
        });
    }

    async obtenerTemasDelMomento() {
        console.log(" --- Obteniendo etiquetas del momento")
        // const url = `${environment.url}/etiqueta/temas-del-momento`;
        const url = `${environment.url}/etiqueta`;
        console.log("URL", url)
        return new Promise<any | void>((resolve, reject) => {
            // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
            this.http.get<Etiqueta[]>(url, this.authSvc.headers_http).subscribe(
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
        const url = `${environment.url}/usuario?id=` + idUsuario;
        console.log("URL", url)
        return new Promise<any | void>((resolve, reject) => {
            // this.http.post<BasicResponse>(url, this.authSvc.headers_http).subscribe(
            this.http.post<Usuario[]>(url, this.authSvc.headers_http).subscribe(
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

    publicarMensaje(nuevoMensaje :NuevoMensaje){
      console.log(" --- Publicando nuevo mensaje")
      const url = `${environment.url}/mensajes`;
      console.log("URL", url)
      this.http.post<Usuario[]>(url, nuevoMensaje, this.authSvc.headers_http).subscribe(
        res => {
          console.log("---- Se hizo la petición : ", res)
        },
        error => {
          console.log(error)
        }
      );
    }
}
