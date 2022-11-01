/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Controller;

import com.portfolio.llugdaremiliosaid.Dto.dtoHys;
import com.portfolio.llugdaremiliosaid.Entity.Experiencia;
import com.portfolio.llugdaremiliosaid.Entity.hys;
import com.portfolio.llugdaremiliosaid.Security.Controller.Mensaje;
import com.portfolio.llugdaremiliosaid.Service.Shys;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* cuandop trabaje con heroku debo descomentar esta linea
@CrossOrigin(origins = "http://localhost:4200, http:// heroku.........")
*/
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/skill")
public class CHys {

    @Autowired
    Shys shys;

    //crear una lista
    @GetMapping("/lista")
    public ResponseEntity<List<hys>> list() {
        List<hys> list = shys.list(); //una variable que contiene una lista
        return new ResponseEntity(list, HttpStatus.OK);

    }

    // crear datos
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody dtoHys dtohys) {
        if (StringUtils.isBlank(dtohys.getNombre())) //si el campo es blanco? no puede ser blanco sino sale msj y no te deja avanzar
        {
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        if (shys.existsByNombre(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("Esa Skill ya Existe!"), HttpStatus.BAD_REQUEST);
        }

        //si pasa esta 2 validaciones entonces
        hys hYs = new hys(dtohys.getNombre(), dtohys.getPorcentaje());
        shys.save(hYs);

        return new ResponseEntity(new Mensaje("Skill Agregada"), HttpStatus.OK);

    }

    // actualizar datos
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoHys dtohys) {
        //debemos preguntar si existe para poder actualizar 
        //validamos si existe el id
        if (!shys.existsById(id)) //si no existe el id entonces
        {
            return new ResponseEntity(new Mensaje("El id no Existe"), HttpStatus.BAD_REQUEST);
        }

        //compara nombre de skills
        if (shys.existsByNombre(dtohys.getNombre()) && shys.getByNombre(dtohys.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa Skill ya Existe"), HttpStatus.BAD_REQUEST);
        }

        //no puede estar vacio el campo
        if (StringUtils.isBlank(dtohys.getNombre())) {
            return new ResponseEntity(new Mensaje("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
        }

        //al cumplir todas las validaciones recien actualizamos el objeto
        hys hYs = shys.getOne(id).get();
        hYs.setNombre(dtohys.getNombre());
        hYs.setPorcentaje(dtohys.getPorcentaje());

        shys.save(hYs);
        return new ResponseEntity(new Mensaje("Skill Actuliazada"), HttpStatus.OK);
    }

    //Borrar Experiencia
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id) {
        //validamos si existe el id
        if (!shys.existsById(id)) //si no existe el id entonces
        {
            return new ResponseEntity(new Mensaje("El id NO Existe"), HttpStatus.BAD_REQUEST);
        }

        shys.delete(id);

        return new ResponseEntity(new Mensaje("Skill Eliminado"), HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id) {
        if (!shys.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        hys hYs = shys.getOne(id).get();
        return new ResponseEntity(hYs, HttpStatus.OK);
    }
}


