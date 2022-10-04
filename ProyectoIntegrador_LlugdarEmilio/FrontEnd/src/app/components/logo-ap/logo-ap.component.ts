import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { TokenService } from '../../service/token.service';

@Component({
  selector: 'app-logo-ap',
  templateUrl: './logo-ap.component.html',
  styleUrls: ['./logo-ap.component.css']
})
export class LogoAPComponent implements OnInit {

  //creamos una inyeccion de dependencias con el boton de login de logo-app-component.html

  isLogged =false;

  constructor(private router:Router, private tokenService: TokenService) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){ //se obtiene el token?
        this.isLogged=true // si lo obtiene es por que esta logueado
    }
    else{
      this.isLogged= false; //no esta logueado
    }
  }

  onLogOut():void{
    this.tokenService.logOut();
    window.location.reload();
  }
  login(){
    this.router.navigate(['/login'])
  }
}
