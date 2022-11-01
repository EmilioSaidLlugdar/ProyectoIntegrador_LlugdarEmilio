package com.portfolio.llugdaremiliosaid.Service;

import com.portfolio.llugdaremiliosaid.Entity.Persona;
import com.portfolio.llugdaremiliosaid.Repository.IPersonaRepository;
import java.util.List;
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
     public Optional <Experiencia> getOne (int id){
         return ipersonaRepository.findById(id); // que busque 1 por id
     }
     
     public Optional <Experiencia> getByNombreE(String nombreE){
         return ipersonaRepository.findByNombreE(nombreE);
     }
     
     public void save(Experiencia expe){
         ipersonaRepository.save(expe);
     }
     
     public void delete (int id){
         ipersonaRepository.deleteById(id);
     }
     
     public boolean existsById (int id){
         return ipersonaRepository.existsById(id);
     }
     
     public boolean existsByNombreE(String nombreE){
         return ipersonaRepository.existsByNombreE(nombreE);
     }
}
