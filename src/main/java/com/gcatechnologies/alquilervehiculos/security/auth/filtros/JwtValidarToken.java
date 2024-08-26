package com.gcatechnologies.alquilervehiculos.security.auth.filtros;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gcatechnologies.alquilervehiculos.security.auth.TokenJwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.*;

public class JwtValidarToken extends BasicAuthenticationFilter {
    public JwtValidarToken(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }
    /**
     * El token nos deja realizar acciones que estan protegidas
     * */

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        //Obtener las cabeceras
        //en la cabecera se envia cuandp queremos acceder a un recurso protegido
        //enviamos el token con el bearer

        String header = request.getHeader(TokenJwtConfig.HEADER_AUTORIZATION);
        //validar si el token contiene la palabra bearer
        if (header == null || !header.startsWith(TokenJwtConfig.PREFIX_TOKEN)) {

            chain.doFilter(request, response);
            return;

        }
        //eliminamos el beare para tener la cabecera exacta que generamos
        String token = header.replace(TokenJwtConfig.PREFIX_TOKEN, "");

        try {
            // SI COINCIDE LA FIRMA LE PASAMOS LA LLAVE
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(TokenJwtConfig.SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            Object authoritiesClaims = claims.get("authorities");
            String username = claims.getSubject();

            //si es igual nos autenticamos y dejamos pasar
            Collection<? extends GrantedAuthority> authorities = Arrays
                    .asList(new ObjectMapper()
                            .addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityJsonCreator.class)
                            .readValue(authoritiesClaims.toString().getBytes(), SimpleGrantedAuthority[].class));
            //dejamos pasar el usuario
            UsernamePasswordAuthenticationToken autenticacion = new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(autenticacion);
            chain.doFilter(request,response);

        }catch (JwtException e){
            Map<String,String> body = new HashMap<>();
            body.put("error", e.getMessage());
            body.put("message", "El token jwt no es valido");
            response.getWriter().write(new ObjectMapper().writeValueAsString(body));
            response.setStatus(403);
            response.setContentType("application/json");

        }



    }
}

