import {Usuario} from "./usuario.model";
import {Etiqueta} from "./etiqueta.model";

export class Mensaje{
    id : number;
    autor : Usuario;
    texto : string;
    fechaPublicacion : string;
    usuarioDestinatario : Usuario[];
    etiquetas : Etiqueta[];

    constructor(id:number, autor: Usuario, texto: string, fechaPublicacion: string, usuarioDestinatario: Usuario[], etiquetas: Etiqueta[]) {
        this.id = id;
        this.autor = autor;
        this.texto = texto;
        this.fechaPublicacion = fechaPublicacion;
        this.usuarioDestinatario = usuarioDestinatario;
        this.etiquetas = etiquetas;
    }
}
