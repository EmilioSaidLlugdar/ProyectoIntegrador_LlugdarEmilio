/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Security.Service;

import com.portfolio.llugdaremiliosaid.Security.Entity.Rol;
import com.portfolio.llugdaremiliosaid.Security.Enums.RolNombre;
import com.portfolio.llugdaremiliosaid.Security.Repository.iRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional 
/*trata de mantener lo mismo que tenemos aca con la BD se llama Persistencia
si una operacion falla esta anotation hace un rolback y que no impacte en la BD o 
que se mantenga en un estado anterior y que todo siga como estaba*/
public class RolService {
    @Autowired
    iRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre (RolNombre rolNombre){ // crea un objeto tipo rolnombre
        return irolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
    
}
