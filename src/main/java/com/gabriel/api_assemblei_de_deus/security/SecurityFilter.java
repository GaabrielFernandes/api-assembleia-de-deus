package com.gabriel.api_assemblei_de_deus.security;

import com.gabriel.api_assemblei_de_deus.repository.DiretorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private DiretorRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Recupera o token do cabeçalho Authorization
        String token = recuperarToken(request);

        // 2. Se o token existir e for válido, autentica o usuário
        if (token != null && tokenService.isTokenValido(token)) {
            Long idUsuario = tokenService.getIdUsuario(token);
            var usuario = repository.findById(idUsuario).get();

            // Cria um objeto de autenticação para o Spring Security
            var authentication = new UsernamePasswordAuthenticationToken(
                    usuario, null, usuario.getAuthorities());

            // Define a autenticação no contexto da requisição
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // 3. Continua o processamento da requisição
        filterChain.doFilter(request, response);
    }

    // Extrai o token do cabeçalho Authorization (formato: "Bearer <token>")
    private String recuperarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
            return null;
        }
        return token.substring(7); // Remove "Bearer "
    }
}