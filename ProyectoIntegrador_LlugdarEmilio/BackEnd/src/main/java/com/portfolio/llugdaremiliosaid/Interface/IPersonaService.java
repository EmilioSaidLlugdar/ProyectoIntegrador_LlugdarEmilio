
package com.portfolio.llugdaremiliosaid.Interface;

import com.portfolio.llugdaremiliosaid.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Traer una lista de personas
    public List<Persona> getPersona();
    
    //guardar un objeto de tipo persona
    public void savePersona (Persona persona);
    
    //eliminar un usuario por ID
    public void deletePersona (Long id);
    
    //buscar una persona por ID
    public Persona findPersona (Long id);
    
    
}
