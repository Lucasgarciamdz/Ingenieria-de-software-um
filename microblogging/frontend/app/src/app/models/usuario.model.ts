export class Usuario{
  id : number | string;
  siguiendo : boolean = false;
  nombreCompleto : string | null = "";
  nombreUsuario : string | null = "";
  descripcion : string | null = "";
  email : string | null = "";

  constructor(id: number | string, siguiendo: boolean, nombreCompleto: string | null, nombreUsuario: string | null, descripcion: string | null, email: string | null) {
    this.id = id;
    this.siguiendo = siguiendo;
    this.nombreCompleto = nombreCompleto;
    this.nombreUsuario = nombreUsuario;
    this.descripcion = descripcion;
    this.email = email;
  }
}
