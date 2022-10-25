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
  
  constructor(private http: HttpClient) { }

  /* angular usa observalble  para hacer todas las peticiones asincronas
  como definir determinador  eventos que envian informacion a algun componente HIJO
  y este a su vez le pasa la info a l PADRE 
  en el front creamos componentes hijos y lo anexamos al componente padre  */
  public getPersona(): Observable<persona>{
    return this.http.get<persona>(this.URL+'traer/perfil');
    

  }
}
