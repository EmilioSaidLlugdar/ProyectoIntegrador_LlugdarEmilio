import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Educacion } from '../model/educacion';

@Injectable({
  providedIn: 'root'
})
export class EducacionService {

  URL = 'http://localhost:8080/educacion/';


  constructor(private httpClient: HttpClient) { }
    //nos trae una lista de toda la educacion que tengamos
    public lista (): Observable<Educacion[]>{
      return this.httpClient.get<Educacion[]>(this.URL + 'lista'); 
    }
  
    public details(id:number): Observable<Educacion>{
    return this.httpClient.get<Educacion>(this.URL + `details/${id}`);
    }

    public save(educacion: Educacion): Observable<any>{
      return this.httpClient.post<any>(this.URL + 'create', educacion);
    }

    public  update(id:number, educacion: Educacion): Observable<any>{
      return this.httpClient.put<any>(this.URL + `update/${id}`, educacion);
    }

    public delete (id: Number): Observable<any>{
      return this.httpClient.delete<any>(this.URL + `delete/${id}`);
    }
}
