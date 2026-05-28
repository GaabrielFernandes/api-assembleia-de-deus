package com.gabriel.api_assemblei_de_deus.controller;

import com.gabriel.api_assemblei_de_deus.DTO.page.DiretorPage;
import com.gabriel.api_assemblei_de_deus.DTO.request.DiretorRequestDTO;
import com.gabriel.api_assemblei_de_deus.DTO.response.DiretorResponseDTO;
import com.gabriel.api_assemblei_de_deus.service.DiretorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diretor")
@RequiredArgsConstructor
public class DiretorController {
    private final DiretorService service;

    @PostMapping("/salvar")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<DiretorResponseDTO> salvar(@RequestBody DiretorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvarDiretor(dto));
    }

    @PutMapping("/atualizar/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<DiretorResponseDTO> atualizar(@PathVariable Long id, @RequestBody DiretorRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizar(id,dto));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<DiretorResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPorId(id));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    public ResponseEntity<Page<DiretorPage>> listar(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable
            ){
        return ResponseEntity.status(HttpStatus.OK).body(service.listar(pageable));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        service.deletarDiretor(id);
    }

}
