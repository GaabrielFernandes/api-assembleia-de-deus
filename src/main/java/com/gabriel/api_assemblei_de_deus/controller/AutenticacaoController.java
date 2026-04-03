package com.gabriel.api_assemblei_de_deus.controller;
import com.gabriel.api_assemblei_de_deus.DTO.login.LoginRequestDto;
import com.gabriel.api_assemblei_de_deus.DTO.login.TokenResponseDto;
import com.gabriel.api_assemblei_de_deus.service.LoginService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AutenticacaoController {
    private final LoginService service;

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDto> login(@RequestBody @Valid LoginRequestDto dados) {
        return ResponseEntity.status(HttpStatus.OK).body(service.login(dados));
    }
}