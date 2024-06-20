import { Action } from "@ngrx/store";

export const SET_USER = '[Auth] Set User';
export const UNSET_USER = '[Auth] Unset User';


export class SetUserAction implements Action {
    constructor( public payload:any){   }
    readonly type=SET_USER;
}

export class UnsetUserAction implements Action {
    readonly type = UNSET_USER;
}



export type authActions=
    SetUserAction  |
    UnsetUserAction ;
