import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})

export class PublicationsService {

  private url = '/api/publications';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }

  get(id: number): Observable<any> {
    return this.http.get<any>(this.url, { params: { id: id } });
  }

  create(data: any): Observable<any> {
    return this.http.post<any>(this.url, data);
  }

  update(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.url}/$`, data, { params: { id: id } });
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.url}/${id}`);
  }

  getPublicationsByEditor(editorId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.url}/editor`, { params: { editor_id: editorId } });
  }
}
