package com.example.prutecpro.security;



import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Long jwtExpirationMillis;

    public String generarToken(Authentication authentication){

        String username = authentication.getName();
        Date fechaActual = new Date();
        Date fechaExpiracion = new Date(fechaActual.getTime() + jwtExpirationMillis);

        String token = Jwts.builder().setSubject(username)
                .setIssuedAt(new Date()).setExpiration(fechaExpiracion)
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();

        return token;
    }

    public String obtenerUsernameDelToken(String token){
        Claims claims = Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    public boolean validarToken(String token){

        try {

            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;

        }catch (SignatureException ex){
            System.out.println("token firma no valida");
            //throw new ValidarTokenException(HttpStatus.BAD_REQUEST,"firma no valida");
            return false;
        }catch (MalformedJwtException ex){
            System.out.println("token no valida");
            //throw new ValidarTokenException(HttpStatus.BAD_REQUEST,"no valido");
            return false;
        }catch (ExpiredJwtException ex){
            System.out.println("token caducado");
            //throw new ValidarTokenException(HttpStatus.BAD_REQUEST,"caducado");
            return false;
        }catch (UnsupportedJwtException ex){
            System.out.println("token no compatible");
            //throw new ValidarTokenException(HttpStatus.BAD_REQUEST,"no compatible");
            return false;
        }catch (IllegalArgumentException ex){
            System.out.println("la cadena claims esta vacia");
            //throw new ValidarTokenException(HttpStatus.BAD_REQUEST,"la cadena claims esta vacia");
            return false;
        }
    }


}

