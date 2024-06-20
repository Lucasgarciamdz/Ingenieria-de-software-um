export class BasicResponse{
  message : string = "";
  response : any;
  constructor(message :string, response : any) {
    this.message = message;
    this.response = response;
  }

}
