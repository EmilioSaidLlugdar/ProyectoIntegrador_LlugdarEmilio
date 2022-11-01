import { Component, OnInit } from '@angular/core';
import { persona } from '../../model/persona.model';
import { PersonaService } from '../../service/persona.service';

@Component({
  selector: 'app-acerca-de',
  templateUrl: './acerca-de.component.html',
  styleUrls: ['./acerca-de.component.css']
})
export class AcercaDeComponent implements OnInit {

  persona: persona = new persona("","","");
 
  constructor(public personaService: PersonaService) { }


  /*El suscribe vincula al Observable con algunos eventos observables
  es un metodo que escucha siempre que observable realiza un cambio, ejecuta el codigo
  y da una respuesta */
  ngOnInit(): void {
  this.personaService.getPersona().subscribe(data => {this.persona=data})
  }

}
