import { Component, OnInit } from '@angular/core';
import { LoginUsuario } from '../../model/login-usuario';
import { TokenService } from '../../service/token.service';
import { AuthService } from '../../service/auth.service';
import { Router } from '@angular/router';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isLogged = false;
  isLogginFail = false;
  loginUsuario!: LoginUsuario;
  nombreUsuario!: string;
  password! :string;
  roles: string[]= [];
  errMsj!: string;

  constructor(private tokenService: TokenService, private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    if(this.tokenService.getToken()){ //pregunta si esta logueado ?
      this.isLogged=true; //me dice que sim esta logueado
      this.isLogginFail= false; // no falla aunque sea redundante hacemos esto
      this.roles= this.tokenService.getAuthorities(); //guardamos en roles lo que traigas de token services
    }
  }

  onLogin(): void{
    this.loginUsuario= new LoginUsuario(this.nombreUsuario, this.password); 
    this.authService.login(this.loginUsuario).subscribe(data=>{
        this.isLogged=true;
        this.isLogginFail=false;
        this.tokenService.setToken(data.token);
        this.tokenService.setUsername(data.nombreUsuario);
        this.tokenService.setAuthorities(data.authorities);
        this.roles=data.authorities;
        this.router.navigate(['']);

      }, err=>{
        this.isLogged=false;
        this.isLogginFail=true;
        this.errMsj=err.error.mensaje;
        console.log(this.errMsj);
        
      })
  }

}
