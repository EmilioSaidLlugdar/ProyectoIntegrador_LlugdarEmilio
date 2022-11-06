import { HttpClient } from '@angular/common/http'; //utiliza observable para manejar las peticiones y respuestas
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { persona } from '../model/persona.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PersonaService {

  // de donde quiero que llame al Backend usamos las URl que declaramos en el netbeans
  // URL= 'http://localhost:8080/personas/'; //no le agregamos el crear, traer, etc, porque eso se encargará los metodos que creamos abajo
  
  URL = environment.URL + 'personas/';
  
  constructor(private httpClient: HttpClient) { }

  /* angular usa observalble  para hacer todas las peticiones asincronas
  como definir determinador  eventos que envian informacion a algun componente HIJO
  y este a su vez le pasa la info a l PADRE 
  en el front creamos componentes hijos y lo anexamos al componente padre  */
 
   //nos trae una lista de toda la educacion que tengamos
   public lista (): Observable<persona[]>{
    return this.httpClient.get<persona[]>(this.URL + 'lista'); 
  }

  public detail(id:number): Observable<persona>{
  return this.httpClient.get<persona>(this.URL + `detail/${id}`);
  }

  /*public save(educacion: Educacion): Observable<any>{
    return this.httpClient.post<any>(this.URL + 'create', educacion);
  }*/

  public  update(id:number, Persona: persona): Observable<any>{
    return this.httpClient.put<any>(this.URL + `update/${id}`, Persona);
  }

  /*public delete (id: Number): Observable<any>{
    return this.httpClient.delete<any>(this.URL + `delete/${id}`);
  }*/ 
}
