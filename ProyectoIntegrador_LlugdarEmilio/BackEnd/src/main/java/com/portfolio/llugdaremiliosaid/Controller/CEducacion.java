/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Controller;

import com.portfolio.llugdaremiliosaid.Entity.Educacion;
import com.portfolio.llugdaremiliosaid.Service.Seducacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/educacion")
@CrossOrigin(origins= "http://localhost:4200")
public class CEducacion {
    
    @Autowired
    Seducacion sEducacion;
    
    //debemos desarrollar los metodos
    public ResponseEntity<List<Educacion>> list(){
        List<Educacion> list = sEducacion.list();
        return new ResponseEntity(list, HttpStatus.OK);
        
    }
    
    
}
