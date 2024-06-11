import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EditorsService {

  private url = '/api/editors';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }

  get(id: number): Observable<any> {
    return this.http.get<any>(`${this.url}/${id}`);
  }

  create(data: any): Observable<any> {
    return this.http.post<any>(this.url, data);
  }

  update(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.url}/${id}`, data);
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.url}/${id}`);
  }

  getEditorByUser(userId: number): Observable<any> {
    return this.http.get<any>(`${this.url}/user`, { params: { user_id: userId } });
  }
}
