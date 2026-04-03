package com.gabriel.api_assemblei_de_deus.security;

import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class TokenService {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private String expiration;

    // Gera uma chave HMAC a partir do secret
    private Key getKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Gera o token JWT para um usuário autenticado
    public String gerarToken(Authentication authentication) {
        Diretor usuario = (Diretor) authentication.getPrincipal(); // Pega o usuário autenticado
        Date hoje = new Date();
        Date dataExpiracao = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("API Assembleia de Deus") // Quem emitiu o token
                .setSubject(usuario.getId().toString()) // Identificador do usuário (ID)
                .claim("email", usuario.getEmail()) // Claims adicionais
                .claim("departamento", usuario.getDepartamento())
                .setIssuedAt(hoje) // Data de emissão
                .setExpiration(dataExpiracao) // Data de expiração
                .signWith(getKey(), SignatureAlgorithm.HS256) // Assinatura
                .compact();
    }

    // Valida se o token é válido
    public boolean isTokenValido(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    // Extrai o ID do usuário do token
    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }
}
