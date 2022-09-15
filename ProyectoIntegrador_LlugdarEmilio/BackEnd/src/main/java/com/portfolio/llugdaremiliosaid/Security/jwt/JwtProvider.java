/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.portfolio.llugdaremiliosaid.Security.jwt;

import com.portfolio.llugdaremiliosaid.Security.Entity.UsuarioPrincipal;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

/*Esta clase genera el token y tiene metodos de validacion para 
saber si esta bien armado el mismo*/

@Component
public class JwtProvider {
        private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
        
/*estas 2 clases la traen de other sources/src/main/resources/<default package>/ application.properties
alli dentro tenemos 
jwr.secret = secret
jwt.expiration=3600        */
        @Value("${jwt.secret}")
        private String secret;
        @Value("${jwt.expiration}")
        private int expiration;
        
public String generateToken(Authentication authentication){
    UsuarioPrincipal usuarioPrincipal= (UsuarioPrincipal) authentication.getPrincipal();
    return Jwts.builder().setSubject(usuarioPrincipal.getUsername())
            .setIssuedAt(new Date())
            .setExpiration(new Date(new Date().getTime()+expiration*1000))
            .signWith(SignatureAlgorithm.HS512,secret)
            .compact();
}

public String getNombreUsuarioFromToken (String token){
    return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
}

public boolean validateToken(String token){
    try{
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
    }catch(MalformedJwtException e){
        logger.error("Token mal Formado");
    }catch(UnsupportedJwtException e){
        logger.error("Token no Soportado");
    }catch(ExpiredJwtException e){
        logger.error("Token Expirado");
    }catch(IllegalArgumentException e){
        logger.error("Token Vacío");
    }catch(SignatureException e){
        logger.error("Firma no Válida");
    }
    return false;
}
}
