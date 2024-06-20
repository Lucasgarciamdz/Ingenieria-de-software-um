import { ActionReducerMap } from '@ngrx/store';

import * as fromReducer from './reducers/';


export interface AppState{
    auth:fromReducer.AuthState;
}

export const appReducers:ActionReducerMap<any,any>= {

    auth:fromReducer.authReducer,
};
