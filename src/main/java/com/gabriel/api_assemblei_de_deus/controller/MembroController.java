package com.gabriel.api_assemblei_de_deus.controller;

import com.gabriel.api_assemblei_de_deus.DTO.page.MembroPage;
import com.gabriel.api_assemblei_de_deus.DTO.response.MembroResponseDTO;
import com.gabriel.api_assemblei_de_deus.DTO.request.MembroRequestDTO;
import com.gabriel.api_assemblei_de_deus.service.MembroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/membros")
@RequiredArgsConstructor
public class MembroController {
    private final MembroService service;

    @PostMapping("/salvar")
    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    public ResponseEntity<MembroResponseDTO> cadastrarMembro(@RequestBody @Valid MembroRequestDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(dto));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    public ResponseEntity<Page<MembroPage>> listarMembros(
            @PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(service.listarMembros(pageable));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    public ResponseEntity<MembroResponseDTO> buscarMembro(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarMembro(id));
    }

    @GetMapping("/buscarMembroPeloNome")
    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    public ResponseEntity<List<MembroPage>> buscarMembroPeloNome(@RequestParam String nome){
        return ResponseEntity.status(HttpStatus.OK).body(service.buscarPeloNome(nome));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    public ResponseEntity<MembroResponseDTO> atualizarMembro(@PathVariable Long id, @RequestBody @Valid MembroRequestDTO dto){
        return ResponseEntity.status(HttpStatus.OK).body(service.atualizarMembro(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SECRETARIA')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarMembro(@PathVariable Long id){
        service.excluirMembro(id);
    }
}
