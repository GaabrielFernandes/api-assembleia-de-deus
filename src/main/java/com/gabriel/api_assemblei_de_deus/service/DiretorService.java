package com.gabriel.api_assemblei_de_deus.service;

import com.gabriel.api_assemblei_de_deus.DTO.page.DiretorPage;
import com.gabriel.api_assemblei_de_deus.DTO.request.DiretorRequestDTO;
import com.gabriel.api_assemblei_de_deus.DTO.response.DiretorResponseDTO;
import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import com.gabriel.api_assemblei_de_deus.exception.DiretorNaoEncontradoException;
import com.gabriel.api_assemblei_de_deus.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiretorService {
    private final DiretorRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public DiretorResponseDTO salvarDiretor(DiretorRequestDTO dto){
        validarDepartamentoGerenciavel(dto.getDepartamento());
        Diretor diretoDb = modelMapper.map(dto, Diretor.class);
        diretoDb.setSenha(passwordEncoder.encode(dto.getSenha()));
        diretoDb.setSenhaProvisoria(true);
        repository.save(diretoDb);
        return modelMapper.map(diretoDb, DiretorResponseDTO.class);
    }

    public DiretorResponseDTO buscarPorId(Long id){
        Diretor diretorDb = repository.findById(id).orElseThrow(() -> new DiretorNaoEncontradoException("Diretor nao encontrado"));
        validarDiretorGerenciavel(diretorDb);
        return modelMapper.map(diretorDb, DiretorResponseDTO.class);
    }

    public DiretorResponseDTO atualizar(Long id, DiretorRequestDTO dto){
        Diretor diretorDb = repository.findById(id).orElseThrow(() -> new DiretorNaoEncontradoException("Diretor nao encontrado"));
        validarDiretorGerenciavel(diretorDb);
        validarDepartamentoGerenciavel(dto.getDepartamento());
        diretorDb.setNome(dto.getNome());
        diretorDb.setEmail(dto.getEmail());
        diretorDb.setDepartamento(dto.getDepartamento());

        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            diretorDb.setSenha(passwordEncoder.encode(dto.getSenha()));
            diretorDb.setSenhaProvisoria(true);
        }

        repository.save(diretorDb);
        return modelMapper.map(diretorDb, DiretorResponseDTO.class);
    }


    public Page<DiretorPage> listar(Pageable pageable) {
        return repository.findByDepartamentoNot(Departamento.ADMINISTRADOR, pageable).map(
                diretor -> modelMapper.map(diretor, DiretorPage.class)
        );
    }

    public void deletarDiretor(Long id) {
        Diretor diretorDb = repository.findById(id).orElseThrow(() -> new DiretorNaoEncontradoException("Diretor nao encontrado"));
        validarDiretorGerenciavel(diretorDb);
        repository.deleteById(id);
    }

    public void alterarSenhaPrimeiroAcesso(String novaSenha) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Acesso negado");
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof Diretor diretorAutenticado)) {
            throw new AccessDeniedException("Acesso negado");
        }

        Diretor diretorDb = repository.findById(diretorAutenticado.getId())
                .orElseThrow(() -> new DiretorNaoEncontradoException("Diretor nao encontrado"));

        if (!diretorDb.isSenhaProvisoria()) {
            throw new AccessDeniedException("A senha desse usuario nao esta marcada como provisoria");
        }

        diretorDb.setSenha(passwordEncoder.encode(novaSenha));
        diretorDb.setSenhaProvisoria(false);
        repository.save(diretorDb);
    }

    private void validarDepartamentoGerenciavel(Departamento departamento) {
        if (Departamento.ADMINISTRADOR.equals(departamento)) {
            throw new AccessDeniedException("O usuario administrador nao pode ser cadastrado pelo modulo de diretores");
        }
    }

    private void validarDiretorGerenciavel(Diretor diretor) {
        if (Departamento.ADMINISTRADOR.equals(diretor.getDepartamento())) {
            throw new AccessDeniedException("O usuario administrador nao pode ser gerenciado pelo modulo de diretores");
        }
    }
}
