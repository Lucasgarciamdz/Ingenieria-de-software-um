import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImagesService {

  private url = '/api/images';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any[]> {
    return this.http.get<any[]>(this.url);
  }

  get(id: number): Observable<any> {
    return this.http.get<any>(`${this.url}/`, { params: { id: id } });
  }

  create(data: any): Observable<any> {
    return this.http.post<any>(this.url, data);
  }

  update(id: number, data: any): Observable<any> {
    return this.http.put<any>(`${this.url}/`, data, { params: { id: id } });
  }

  delete(id: number): Observable<any> {
    return this.http.delete<any>(`${this.url}/${id}`);
  }

  getImageByPublicationId(publicationId: number): Observable<any> {
    return this.http.get<any>(`${this.url}/publication/`, { params: { publication_id: publicationId } });
  }
}
