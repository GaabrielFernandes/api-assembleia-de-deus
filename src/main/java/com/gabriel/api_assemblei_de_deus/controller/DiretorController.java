package com.gabriel.api_assemblei_de_deus.controller;

import com.gabriel.api_assemblei_de_deus.DTO.request.DiretorRequestDTO;
import com.gabriel.api_assemblei_de_deus.DTO.response.DiretorResponseDTO;
import com.gabriel.api_assemblei_de_deus.service.DiretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diretor")
@RequiredArgsConstructor
public class DiretorController {
    private final DiretorService service;

    @PostMapping("/salvar")
    public ResponseEntity<DiretorResponseDTO> salvar(@RequestBody DiretorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarDiretor(dto));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<DiretorResponseDTO> atualizar(@PathVariable Long id, @RequestBody DiretorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(id,dto));
    }

}
