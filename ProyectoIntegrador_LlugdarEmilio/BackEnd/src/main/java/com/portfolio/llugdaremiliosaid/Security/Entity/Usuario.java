/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Security.Entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @NotNull
    private String nombre;
    
    @NotNull
    @Column(unique=true)
    private String nombreUsuario;
    
    @NotNull
    private String email;
    
    @NotNull
    private String password;
    /*Como tenemos una relacion M-M
    ManyToMany:Como su nombre indica le dice a Hibernate que la propiedad contendrá una lista de objetos que participa en una relación muchos a muchos.
    cascade: Este atributo tiene el mismo significado que el del fichero de mapeo de Hibernate. Más información en Cascade.
    JoinTable: Esta anotación contiene la información sobre la tabla que realiza la relación muchos a muchos
    name: Nombre de la tabla que realiza la relación muchos a muchos. En nuestro ejemplo es ProfesorModulo.
    joinColumns: Contiene cada una de las columnas que forman la clave primaria de esta clase que estamos definiendo. Cada columna se indica mediante una anotación @JoinColumn y en el atributo name contiene el nombre de la columna.
    inverseJoinColumns: Contiene cada una de las columnas que forman la clave primaria de la clase clase con la que tenemos la relación. Cada columna se indica mediante una anotación @JoinColumn y en el atributo name contiene el nombre de la columna.
     cremos una tabla (union de 2 tablas)
    nombre de la tabla usuario_rol
    tiene 2 columna
    - usuario_rol
    - inverseJoinColumns rol id
    
    */
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="usuario_rol", joinColumns =@JoinColumn(name="usuario_id"), inverseJoinColumns=@JoinColumn(name="rol_id"))
    private Set<Rol> roles=new HashSet<>();
    
    //constructores

    public Usuario() {
    }

    public Usuario(String nombre, String nombreUsuario, String email, String password) {
        this.nombre = nombre;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.password = password;
    }
    
    //getter y setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Rol> getRoles() {
        return roles;
    }

    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }
    
    
}
