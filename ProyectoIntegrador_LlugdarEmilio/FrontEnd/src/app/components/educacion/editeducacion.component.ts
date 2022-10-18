import { Component, OnInit } from '@angular/core';
import { Educacion } from '../../model/educacion';
import { EducacionService } from '../../service/educacion.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-editeducacion',
  templateUrl: './editeducacion.component.html',
  styleUrls: ['./editeducacion.component.css']
})
export class EditeducacionComponent implements OnInit {
  educacion: Educacion = null;

  constructor(
    private educacionS: EducacionService,
    private activatedRouter: ActivatedRoute,
    private router: Router
  ) {

   }

  ngOnInit(): void {
    const id=this.activatedRouter.snapshot.params ['id'];
    this.educacionS.details(id).subscribe(
      data=> {
        this.educacion= data;

      }, err=>{
        alert("ERROR al Modificar");
        this.router.navigate(['']);
      }
    )
  }

  onUpdate(): void{
    const id = this.activatedRouter.snapshot.params['id'];
    this.educacionS.update(id, this.educacion).subscribe(
      data=> {
        this.router.navigate(['']);
      }, err=>{
        alert("ERROR al Modificar la Educación");
        this.router.navigate(['']);
      }
    )
  }
}
