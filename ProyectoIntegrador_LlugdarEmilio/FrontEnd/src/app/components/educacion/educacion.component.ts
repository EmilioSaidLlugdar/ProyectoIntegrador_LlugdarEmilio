import { Component, OnInit } from '@angular/core';
import { EducacionService } from 'src/app/service/educacion.service';
import { Educacion } from '../../model/educacion';
import { TokenService } from '../../service/token.service';

@Component({
  selector: 'app-educacion',
  templateUrl: './educacion.component.html',
  styleUrls: ['./educacion.component.css']
})
export class EducacionComponent implements OnInit {

  educacion : Educacion[] = [];

  constructor(private educacionS: EducacionService, private tokenService: TokenService) { }

  isLogged= false;

  ngOnInit(): void {
    this.cargarEducacion(); //automatic// llamamos al metodo
    if(this.tokenService.getToken()){
      this.isLogged=true;
    }else{
      this.isLogged=false;
    }
  }

  cargarEducacion(): void{
    this.educacionS.lista().subscribe(
    data=>{
      this.educacion=data;
    }
    )
  }

  delete(id?: number){
    if(id != undefined){
      this.educacionS.delete(id).subscribe(
        data=>{
          this.cargarEducacion();
        },err=>{
          alert("No se pudo ELIMINAR!");
        }
      )
    }
  }
}
