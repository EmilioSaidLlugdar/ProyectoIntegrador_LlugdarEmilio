import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Experiencia } from '../model/experiencia';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SExperienciaService {

  // expURL = 'http://localhost:8080/explab/' //esta linea viene de controller --> CExperiencia
  URL = environment.URL + 'explab/';


  constructor(private httpClient: HttpClient) { }

  public lista(): Observable<Experiencia[]> {
    return this.httpClient.get<Experiencia[]>(this.URL + 'lista');
  }

  public detail(id: number): Observable<Experiencia> {
    return this.httpClient.get<Experiencia>(this.URL + `detail/${id}`); //comillas a la izquierda para pasar un valor por el patch variable
  }

  public save(experiencia: Experiencia): Observable<any> {
    return this.httpClient.post<any>(this.URL + 'create', experiencia);
  }

  public update(id: number, experiencia: Experiencia): Observable<any> {
    return this.httpClient.put<any>(this.URL + `update/${id}`, experiencia);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.URL + `delete/${id}`);
  }

}
