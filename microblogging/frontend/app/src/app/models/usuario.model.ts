import {Mensaje} from "./mensaje.model";

export class Usuario {
  id: number | string;
  seguido: boolean = false;
  nombreCompleto: string | null = "";
  nombreUsuario: string | null = "";
  descripcion: string | null = "";
  email: string | null = "";
  mensajePrivado: (Mensaje | any)[] = [];
  seguidores: (Usuario | any)[] = [];
  seguidos: (Usuario | any)[] = [];


  constructor(id: number | string, siguiendo: boolean, nombreCompleto: string | null, nombreUsuario: string | null, descripcion: string | null, email: string | null, mensajes: (Mensaje | any)[], seguidores: (Usuario | any)[], seguidos: (Usuario | any)[]) {
    this.id = id;
    this.seguido = siguiendo;
    this.nombreCompleto = nombreCompleto;
    this.nombreUsuario = nombreUsuario;
    this.descripcion = descripcion;
    this.email = email;
    this.mensajePrivado = mensajes;
    this.seguidores = seguidores;
    this.seguidos = seguidos;
  }

}

