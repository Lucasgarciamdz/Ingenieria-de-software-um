import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  url = '/api'
  constructor(private httpClient: HttpClient) { }

  login(user_name: string, password: string): Observable<any> {
    return this.httpClient.post(this.url + '/usuarios/login', { user_name, password });
  }

  logout(): void {
    localStorage.removeItem('userId');
  }

  isLoggedIn(): boolean {
    return !!localStorage.getItem('userId') && !!localStorage.getItem('role');
  }

  getUserId(): string | null {
    return localStorage.getItem('userId');
  }
}
