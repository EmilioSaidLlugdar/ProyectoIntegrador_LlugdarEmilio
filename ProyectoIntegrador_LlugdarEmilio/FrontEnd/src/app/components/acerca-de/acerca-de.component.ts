import { Component, OnInit } from '@angular/core';
import { persona } from '../../model/persona.model';
import { PersonaService } from '../../service/persona.service';
import { TokenService } from '../../service/token.service';

@Component({
  selector: 'app-acerca-de',
  templateUrl: './acerca-de.component.html',
  styleUrls: ['./acerca-de.component.css']
})
export class AcercaDeComponent implements OnInit {

  persona: persona = null;
 /*El suscribe vincula al Observable con algunos eventos observables
  es un metodo que escucha siempre que observable realiza un cambio, ejecuta el codigo
  y da una respuesta */
 
  constructor(public personaService: PersonaService, private tokenService: TokenService) { }

  isLogged=false;

 
  //cada vez que se carga la pagina, se ejecuta automa// lo que esta dentro de ngOninit
  ngOnInit(): void {
  
    this.cargarPersona();
    if(this.tokenService.getToken()){
      this.isLogged=true;}
      else{
        this.isLogged=false;}
      
  }

  cargarPersona(){
    this.personaService.detail(1).subscribe(data =>
      {this.persona=data}
      )
  }
}
