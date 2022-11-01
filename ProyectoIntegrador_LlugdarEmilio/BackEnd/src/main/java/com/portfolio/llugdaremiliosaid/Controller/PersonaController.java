
package com.portfolio.llugdaremiliosaid.Controller;

import com.portfolio.llugdaremiliosaid.Entity.Persona;
import com.portfolio.llugdaremiliosaid.Interface.IPersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
/* cuandop trabaje con heroku debo descomentar esta linea
@CrossOrigin(origins = "http://localhost:4200, http:// heroku.........")
*/
@CrossOrigin(origins="http://localhost:4200")
public class PersonaController {
    @Autowired IPersonaService ipersonaService;
    
    //como muestra el front un mensaje?
    
    @GetMapping("personas/traer")
    public  List <Persona> getPersona(){
        return ipersonaService.getPersona();
    }
    //creamos una persona, enviamos una persona del front al back
    @PreAuthorize("hasRole('ADMIN')")// SE NECESITA SER ADMIN PARA REALIZAR ESTA ACCION
    @PostMapping("/personas/crear")
    public String createPersona(@RequestBody Persona persona){
        ipersonaService.savePersona(persona);
        return "La persona fué creada correctamente";
    }
    
    //borrar una persona
    @PreAuthorize("hasRole('ADMIN')")// SE NECESITA SER ADMIN PARA REALIZAR ESTA ACCION
    @DeleteMapping("/personas/borrar/{id}")
    public String deletePersona(@PathVariable Long id){
        ipersonaService.deletePersona(id);
        return "La persona fué Eliminada correctamente";
    }
    
    
    //URL:PUERTO/personas/editar/4/nombre & apellido & img---- maso asi quedará la url
    @PreAuthorize("hasRole('ADMIN')")// SE NECESITA SER ADMIN PARA REALIZAR ESTA ACCION
    @PutMapping ("/personas/editar/{id}")
    public Persona editPersona(@PathVariable Long id,
                               @RequestParam ("nombre") String nuevoNombre,
                               @RequestParam ("apellido") String nuevoApellido,
                               @RequestParam ("img") String nuevoImg){
        Persona persona = ipersonaService.findPersona(id);
        
        persona.setNombre(nuevoNombre);
        persona.setApellido(nuevoApellido);
        persona.setImg(nuevoImg);
        
        ipersonaService.savePersona(persona);
        return persona;
        }
    
    //traer perfil
    @GetMapping("/personas/traer/perfil")
    public Persona findPersona(){
        return ipersonaService.findPersona((long)1);
    }
    
  
}
