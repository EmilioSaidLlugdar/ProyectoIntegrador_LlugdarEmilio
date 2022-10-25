/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Repository;

import com.portfolio.llugdaremiliosaid.Entity.hys;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Said
 */
public interface Rhys extends JpaRepository<hys, Integer>{
    Optional<hys> findByNombre (String nombre);//trae la entidad hys
    public boolean existsByNombre (String nombre);
    
}
