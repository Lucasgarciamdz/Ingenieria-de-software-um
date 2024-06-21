export class Etiqueta{
  id : string | number;
  nombre : string = "";
  delMomento : boolean = false;
  headers : any;


  constructor(id :string | number, nombre: string, delMomento: boolean) {
    this.id = id;
    this.nombre = nombre;
    this.delMomento = delMomento;
    this.headers = "";
  }
}
