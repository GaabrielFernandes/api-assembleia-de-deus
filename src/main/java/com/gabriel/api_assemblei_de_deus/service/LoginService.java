package com.gabriel.api_assemblei_de_deus.service;

import com.gabriel.api_assemblei_de_deus.DTO.login.LoginRequestDto;
import com.gabriel.api_assemblei_de_deus.DTO.login.TokenResponseDto;
import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import com.gabriel.api_assemblei_de_deus.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public TokenResponseDto login(LoginRequestDto dto){
        // 1. Cria o token de autenticação com email e senha
        UsernamePasswordAuthenticationToken usernamePassword =
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());

        // 2. Autentica o usuário
        Authentication authentication = authenticationManager.authenticate(usernamePassword);

        // 3. Gera o token JWT
        String token = tokenService.gerarToken(authentication);

        // 4. Obtém os dados do usuário autenticado
        Diretor usuario = (Diretor) authentication.getPrincipal();

        // 5. Retorna o token
        return new TokenResponseDto(
                usuario.getNome(),
                token,
                "Bearer",
                usuario.getEmail(),
                usuario.getDepartamento()
        );
    }
}
