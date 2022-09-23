/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Security.Dto;

import javax.validation.constraints.NotBlank;

/**
 *
 * @author Said
 */
public class LoginUsuario {
    
    @NotBlank//no pueden estar vacios
    private String nombreUsuario;
    private String password;
    
    
    //getters y setters

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
