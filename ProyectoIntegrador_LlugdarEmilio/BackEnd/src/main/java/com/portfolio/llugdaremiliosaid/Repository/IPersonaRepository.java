
package com.portfolio.llugdaremiliosaid.Repository;

import com.portfolio.llugdaremiliosaid.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    
     public Optional<Persona> findByNombreE(String nombre); //buscamos por nombre
    public boolean existsByNombreE(String nombre); //existe o no VoF
}
