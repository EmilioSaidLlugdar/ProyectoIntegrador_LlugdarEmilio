/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Controller;

import com.portfolio.llugdaremiliosaid.Dto.dtoExperiencia;
import com.portfolio.llugdaremiliosaid.Entity.Experiencia;
import com.portfolio.llugdaremiliosaid.Security.Controller.Mensaje;
import com.portfolio.llugdaremiliosaid.Service.SExperiencia;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("explab")
@CrossOrigin (origins = "http://localhost:4200")
public class CExperiencia {
    
    @Autowired
    SExperiencia sExperiencia;
    
    //crear una lista
    @GetMapping("/lista")
    public ResponseEntity<List<Experiencia>> list(){
        List<Experiencia> list = sExperiencia.list(); //una variable que contiene una lista
        return new ResponseEntity(list, HttpStatus.OK);
        
        
    }
    // crear datos
    @PostMapping("/create")
    public ResponseEntity<?> create (@RequestBody dtoExperiencia dtoexp){
        if(StringUtils.isBlank(dtoexp.getNombreE())) //si el campo es blanco? no puede ser blanco sino sale msj y no te deja avanzar
            return new ResponseEntity(new Mensaje("El nombre es obligatorio"), HttpStatus.BAD_REQUEST);
        if (sExperiencia.existsByNombreE(dtoexp.getNombreE()))
            return new ResponseEntity(new Mensaje("Esa Experiencia Existe"), HttpStatus.BAD_REQUEST);
        
        //si pasa esta 2 validaciones entonces
        Experiencia experiencia = new Experiencia(dtoexp.getNombreE(), dtoexp.getDescripcionE());
        sExperiencia.save(experiencia);
        
        return new ResponseEntity(new Mensaje ("Experiencia Agregada"), HttpStatus.OK);
        
    }
    
    // actualizar datos
    
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
       //debemos preguntar si existe para poder actualizar 
       //validamos si existe el id
       if(!sExperiencia.existsById(id)) //si no existe el id entonces
        return new ResponseEntity(new Mensaje ("El id no Existe"), HttpStatus.BAD_REQUEST);
       
        //compara nombre de experiencias
       if(sExperiencia.existsByNombreE(dtoexp.getNombreE())&& sExperiencia.getByNombreE(dtoexp.getNombreE()).get().getId()!= id)
           return new ResponseEntity(new Mensaje("Esa Experiencia ya Existe"), HttpStatus.BAD_REQUEST);
    
       //no puede estar vacio el campo
       if(StringUtils.isBlank(dtoexp.getNombreE()))
           return new ResponseEntity(new Mensaje ("El Nombre es Obligatorio"), HttpStatus.BAD_REQUEST);
    
       //al cumplir todas las validaciones recien actualizamos el objeto
       
       Experiencia experiencia= sExperiencia.getOne(id).get();
       experiencia.setNombreE(dtoexp.getNombreE());
       experiencia.setDescripcionE(get);
    }
    
}
