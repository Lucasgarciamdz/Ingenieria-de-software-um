export class NuevoMensaje{

  autorId : number;
  texto : string;
  destinatiarioId: number;
  etiquetas : string[];
  menciones : (string | number)[];

  constructor(autorId: number, texto: string, destinatiarioId:  number, etiquetas: string[], menciones: (string | number)[]) {
    this.autorId = autorId;
    this.texto = texto;
    this.destinatiarioId = destinatiarioId;
    this.etiquetas = etiquetas;
    this.menciones = menciones;
  }
}

