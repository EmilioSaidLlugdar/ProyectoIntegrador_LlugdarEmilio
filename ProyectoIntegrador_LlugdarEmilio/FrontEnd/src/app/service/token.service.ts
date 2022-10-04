import { Injectable } from '@angular/core';

const TOKEN_KEY = 'AuthToken';
const USERNAME_KEY = 'AuthUsername';
const AUTHORITIES_KEY= 'AuthAutorities';

@Injectable({
  providedIn: 'root'
})
export class TokenService {
  roles: Array<string>= [];
  constructor() { }

  //GET TOKEN
  public setToken (token: string): void{
    window.sessionStorage.removeItem(TOKEN_KEY); //elimina por las dudas este guardado un token
    window.sessionStorage.setItem (TOKEN_KEY, token);//copia el nuevo token
  }

  public getToken():string{
    return sessionStorage.getItem(TOKEN_KEY)!;
  }

  public setUserName(userName: string): void{
    window.sessionStorage.removeItem(USERNAME_KEY); //elimina por las dudas este guardado un token
    window.sessionStorage.setItem (USERNAME_KEY, userName);//copia el nuevo token
  }

  public getUserName():string{
    return sessionStorage.getItem(USERNAME_KEY)!;
  }

  public setAuthorities (authorities: string[]): void{
    window.sessionStorage.removeItem(AUTHORITIES_KEY);
    window.sessionStorage.setItem(AUTHORITIES_KEY, JSON.stringify(authorities));
    // {
      // JSON.parse(sessionStorage.getItem())
    // }
  }

  public getAuthorities():string[]{
    this.roles=[];
    if(sessionStorage.getItem(AUTHORITIES_KEY)){
      JSON.parse(sessionStorage.getItem(AUTHORITIES_KEY)!).forEach((authority:any )=>{ 
          this.roles.push(authority.authority);
      });
    }
  return this.roles;
  }
//pensando en el futuro, debemos hacer un logout

public logOut():void{
  window.sessionStorage.clear();
}
}
