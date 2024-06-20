import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {AppState} from "../store/app.reducers";
import { Store } from '@ngrx/store';
import {Observable, of, switchMap} from "rxjs";
import {environment} from "../environment/environment.dev";
import {UsuarioActual} from "../models/usuario.model";
import * as actions from "../store/actions";

@Injectable({
    providedIn: 'root'
})
export class AuthService {
    constructor(
        private _httpClient: HttpClient,
        //private store:Store<AppState>
    ) {
    }

    get usuarioActual(){
        let usuarioActual=''
/*        this.store.select('auth').subscribe(auth=>{
            usuarioActual=auth.usuarioActual;
        });*/
        return usuarioActual;

    }
    get accessToken(): string {
        let accessToken = ''
/*        this.store.select('auth').subscribe(auth => {
            accessToken = auth.usuarioActual?.token;
        });*/
        return accessToken;
    }

    get headers_http() {
        let header = {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${this.accessToken}`
        }
        return {headers: header};
    }


    logIn(username: string, password: string): Observable<any>
    {

        const url = `${environment.url}/login`;
        const body = { email: username, password: password };
        return this._httpClient.post<UsuarioActual>(url, body)
            .pipe(switchMap((response: any) =>
            {
                // this.store.dispatch(new actions.SetUserAction(response))
                console.log('response', response);
                return of(response);
            }),);
    }

    logOut(){
        // this.store.dispatch(new actions.UnsetUserAction())
        return of(true);
    }


}
