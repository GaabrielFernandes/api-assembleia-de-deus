package com.gabriel.api_assemblei_de_deus.security;

import com.gabriel.api_assemblei_de_deus.repository.DiretorRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final DiretorRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String token = recuperarToken(request);

        if (token != null && tokenService.isTokenValido(token)) {

            Long idUsuario = tokenService.getIdUsuario(token);

            repository.findById(idUsuario).ifPresent(usuario -> {

                // evita recriar autenticação
                if (SecurityContextHolder.getContext().getAuthentication() == null) {

                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            });
        }

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        return request.getDispatcherType() != DispatcherType.REQUEST;
    }

    private String recuperarToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
            return null;
        }

        return header.substring(7);
    }
}