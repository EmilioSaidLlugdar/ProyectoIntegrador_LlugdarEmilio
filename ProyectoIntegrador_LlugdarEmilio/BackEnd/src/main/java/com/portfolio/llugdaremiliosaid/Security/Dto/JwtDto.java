/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Security.Dto;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

/**
 *
 * @author Said
 */
public class JwtDto {
    private String token; 
    
    /* Un formato que nos permite la autorización 
    en conjunto con la autenticación de usuarios. 
    Este es el esquema que está más de moda hoy en día.*/
    private String bearer = "Bearer";
    
    private String nombreUsuario;
    private Collection<? extends GrantedAuthority> authorities;
    
    
}
