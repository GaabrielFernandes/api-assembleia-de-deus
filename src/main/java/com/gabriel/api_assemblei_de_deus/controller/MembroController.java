package com.gabriel.api_assemblei_de_deus.controller;

import com.gabriel.api_assemblei_de_deus.DTO.MembroPage;
import com.gabriel.api_assemblei_de_deus.DTO.MembroResponseDTO;
import com.gabriel.api_assemblei_de_deus.DTO.MembroRequestDTO;
import com.gabriel.api_assemblei_de_deus.service.MembroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membros")
@RequiredArgsConstructor
public class MembroController {
    private final MembroService service;

    @PostMapping
    public ResponseEntity<MembroResponseDTO> cadastrarMembro(@RequestBody @Valid MembroRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping()
    public ResponseEntity<Page<MembroPage>> listarMembros(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarMembros(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> buscarMembro(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarMembro(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MembroResponseDTO> atualizarMembro(@PathVariable Long id, @RequestBody @Valid MembroRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarMembro(id, dto));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMembro(@PathVariable Long id){
        service.excluirMembro(id);
    }
}
