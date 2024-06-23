export class NuevoMensaje{

  idUsuario : number;
  texto : string;
  idDestinatarios: (string | number)[];
  etiquetas : string[];
  menciones : (string | number)[];

  constructor(idUsuario: number, texto: string, idDestinatarios: (string | number)[], etiquetas: string[], menciones: (string | number)[]) {
    this.idUsuario = idUsuario;
    this.texto = texto;
    this.idDestinatarios = idDestinatarios;
    this.etiquetas = etiquetas;
    this.menciones = menciones;
  }
}

