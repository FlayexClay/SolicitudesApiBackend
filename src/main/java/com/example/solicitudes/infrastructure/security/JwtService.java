package com.example.solicitudes.infrastructure.security;

import com.example.solicitudes.domain.model.Usuario;
import com.example.solicitudes.domain.port.out.TokenProviderPort;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtService implements TokenProviderPort {
    private final SecretKey key;
    private final long expirationMs;


    public JwtService(JwtProperties properties){
        this.key = Keys.hmacShaKeyFor(properties.secret().getBytes(StandardCharsets.UTF_8));
        this.expirationMs = properties.expirationMs();
    }

    @Override
    public String generar(Usuario usuario) {
        Date ahora = new Date();
        Date expira = new Date(ahora.getTime() + expirationMs);

        return Jwts.builder()
                .subject(usuario.id().toString())
                .claim("username", usuario.username())
                .claim("rol", usuario.rol().name())
                .issuedAt(ahora)
                .expiration(expira)
                .signWith(key)
                .compact();
    }

    public Claims parsear(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public UUID extraerUserId(Claims claims){
        return UUID.fromString(claims.getSubject());
    }
}
