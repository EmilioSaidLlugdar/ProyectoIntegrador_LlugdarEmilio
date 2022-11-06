/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Security.Controller;

import com.portfolio.llugdaremiliosaid.Security.Dto.JwtDto;
import com.portfolio.llugdaremiliosaid.Security.Dto.LoginUsuario;
import com.portfolio.llugdaremiliosaid.Security.Dto.NuevoUsuario;
import com.portfolio.llugdaremiliosaid.Security.Entity.Rol;
import com.portfolio.llugdaremiliosaid.Security.Entity.Usuario;
import com.portfolio.llugdaremiliosaid.Security.Enums.RolNombre;
import com.portfolio.llugdaremiliosaid.Security.Service.RolService;
import com.portfolio.llugdaremiliosaid.Security.Service.UsuarioService;
import com.portfolio.llugdaremiliosaid.Security.jwt.JwtProvider;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * esta clase se termina comunicando con nuestro front
 */
@RestController
@RequestMapping("/auth")
@CrossOrigin (origins= {"http://localhost:4200"})
/* cuandop trabaje con heroku debo descomentar esta linea
@CrossOrigin(origins = "http://localhost:4200, http:// heroku.........")
*/
public class AuthController {

    /*realizamos 5 inyecciones de dependencias 
    Spring @Autowired es una de las anotaciones más habituales 
    cuando trabajamos con Spring Framework ya que se trata de la anotación 
    que permite inyectar unas dependencias con otras dentro de Spring*/
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UsuarioService usuarioService;
    @Autowired
    RolService rolService;
    @Autowired
    JwtProvider jwtProvider;
    
    //creamos un usuario nuevo
    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){
        //creamos metodos y validaciones diferentes
        if (bindingResult.hasErrors())
                return new ResponseEntity(new Mensaje("Campos mal Puestos o email invalido"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("Ese Nombre de Usuario ya Existe"), HttpStatus.BAD_REQUEST);
        
        if(usuarioService.existsByEmail (nuevoUsuario.getEmail()))
            return new ResponseEntity(new Mensaje("Este Email ya Existe"), HttpStatus.BAD_REQUEST);
        
        Usuario usuario = new Usuario(nuevoUsuario.getNombre(), nuevoUsuario.getNombreUsuario(),
        nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        
        //agregamos nuevos roles
        //por defecto todos tendran ROLE_USER 
        //a menos que contenga admin entonces se le asigna ROLE_ADMIN
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        
        usuario.setRoles(roles);
        usuarioService.save(usuario);
        
        return new ResponseEntity(new Mensaje ("Usuario Guardado"), HttpStatus.CREATED);
    }
    
    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if (bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje ("Campos mal Puestos"), HttpStatus.BAD_REQUEST);
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
        loginUsuario.getNombreUsuario(),loginUsuario.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        String jwt=jwtProvider.generateToken(authentication);
        
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        
        JwtDto jwtDto= new JwtDto (jwt,userDetails.getUsername(), userDetails.getAuthorities());
        
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}   
