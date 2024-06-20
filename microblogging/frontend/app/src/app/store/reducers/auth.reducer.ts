import * as fromAuth from '../actions/auth.actions';

export interface AuthState{

    usuarioActual:any;

}

const initialState: AuthState={
    usuarioActual:null,

};

export function authReducer (state=initialState,action:fromAuth.authActions): AuthState{
    switch (action.type) {
        case fromAuth.SET_USER:
            return{
                ...state,
                usuarioActual: {... action.payload}
            };
        case fromAuth.UNSET_USER:
            return {
                ...state,
                usuarioActual: null
            };
        default:
            return state;
    }
}

