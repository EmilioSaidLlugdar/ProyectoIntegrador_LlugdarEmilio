/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Service;

import com.portfolio.llugdaremiliosaid.Entity.Experiencia;
import com.portfolio.llugdaremiliosaid.Repository.RExperiencia;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Said
 */

@Service
@Transactional //manentrdra la persistencia si se modifica acá se modifica en la BD
public class SExperiencia {
    @Autowired
    RExperiencia rExperiencia; //inyectamos el repositorio de experiencia
    
    public List<Experiencia> list(){
        return rExperiencia.findAll();//arma una lista con todas las expetiencias que existen
    }
     public Optional <Experiencia> getOne (int id){
         return rExperiencia.findById(id); // que busque 1 por id
     }
     
     public Optional <Experiencia> getByNombreE(String nombreE){
         return rExperiencia.findByNombreE(nombreE);
     }
     
     public void save(Experiencia expe){
         rExperiencia.save(expe);
     }
     
     public void delete (int id){
         rExperiencia.deleteById(id);
     }
     
     public boolean existsById (int id){
         return rExperiencia.existsById(id);
     }
     
     public boolean existsByNombreE(String nombreE){
         return rExperiencia.existsByNombreE(nombreE);
     }
}
