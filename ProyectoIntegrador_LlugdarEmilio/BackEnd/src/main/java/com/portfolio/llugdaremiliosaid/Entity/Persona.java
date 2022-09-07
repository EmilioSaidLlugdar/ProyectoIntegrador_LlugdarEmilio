package com.portfolio.llugdaremiliosaid.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Said
 */
@Getter @Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) /*sera un numero autogenerado*/
    private Long id;
    
    @NotNull
    @Size (min=1, max=50, message="no cumple con la longitud")
    private String nombre;
    
    @NotNull
    @Size (min=1, max=50, message="no cumple con la longitud")
    private String apellido;
    
    
    @Size (min=1, max=50, message="no cumple con la longitud")
    private String img;
    
    
}
