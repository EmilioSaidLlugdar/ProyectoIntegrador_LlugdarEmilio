package com.portfolio.llugdaremiliosaid.Service;

import com.portfolio.llugdaremiliosaid.Entity.Persona;
import com.portfolio.llugdaremiliosaid.Repository.IPersonaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpPersonaService {

    @Autowired
    IPersonaRepository ipersonaRepository;
   public List<Persona > list(){
        return ipersonaRepository.findAll();//arma una lista con todas las expetiencias que existen
    }
     public Optional <Persona> getOne (int id){
         return ipersonaRepository.findById(id); // que busque 1 por id
     }
     
     public Optional <Persona> getByNombre(String nombre){
         return ipersonaRepository.findByNombre(nombre);
     }
     
     public void save(Persona persona){
         ipersonaRepository.save(persona);
     }
     
     public void delete (int id){
         ipersonaRepository.deleteById(id);
     }
     
     public boolean existsById (int id){
         return ipersonaRepository.existsById(id);
     }
     
     public boolean existsByNombre(String nombre){
         return ipersonaRepository.existsByNombre(nombre);
     }
}
