import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-logo-ap',
  templateUrl: './logo-ap.component.html',
  styleUrls: ['./logo-ap.component.css']
})
export class LogoAPComponent implements OnInit {

  //creamos una inyeccion de dependencias con el boton de login de logo-app-component.html
  constructor(private router:Router) { }

  ngOnInit(): void {
  }

  login(){
    this.router.navigate(['/login'])
  }
}
