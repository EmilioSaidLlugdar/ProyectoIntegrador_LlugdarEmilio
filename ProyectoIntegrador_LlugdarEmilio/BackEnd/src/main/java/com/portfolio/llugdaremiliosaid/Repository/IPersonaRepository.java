
package com.portfolio.llugdaremiliosaid.Repository;

import com.portfolio.llugdaremiliosaid.Entity.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonaRepository extends JpaRepository<Persona, Integer> {
    
     public Optional<Persona> findByNombre(String nombre); //buscamos por nombre
    public boolean existsByNombre(String nombre); //existe o no VoF
}
