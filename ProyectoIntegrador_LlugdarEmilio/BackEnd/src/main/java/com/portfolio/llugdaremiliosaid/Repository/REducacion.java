/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Repository;

import com.portfolio.llugdaremiliosaid.Entity.Educacion;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Said
 */

@Repository
public interface REducacion  extends JpaRepository<Educacion, Integer> {
    public Optional<Educacion> findByNombreE(String nombreE); //buscamos por nombre
    public boolean existeByNombreE(String nombreE); //existe o no VoF
    
}
