import { Component, OnInit } from '@angular/core';
import { SExperienciaService } from '../../service/s-experiencia.service';
import { TokenService } from '../../service/token.service';
import { Experiencia } from '../../model/experiencia';

@Component({
  selector: 'app-experiencia',
  templateUrl: './experiencia.component.html',
  styleUrls: ['./experiencia.component.css']
})
export class ExperienciaComponent implements OnInit {
  expe: Experiencia[] = [];
  //si estas logueado podras hacer algunas cosas, sino no
  constructor(private sExperiencia: SExperienciaService, private tokenService: TokenService) { }

  isLogged = false;
  ngOnInit(): void {
    this.cargarExperiencia();
    if (this.tokenService.getToken()) {
      this.isLogged = true;
    } else {
      this.isLogged = false;
    }
  }

  //metodo
  cargarExperiencia(): void {
    this.sExperiencia.lista().subscribe(
      data => {
        this.expe = data;
      }
    )

  }

  delete(id?: number) {
    if (id != undefined) {
      this.sExperiencia.delete(id).subscribe(
        data => {
          this.cargarExperiencia();//vuelvo a refrescar la pantalla despues de eliminar
        }, err => {
          alert("No se pudo BORRAR la Experiencia");
        }
      )
    }
  }
}


