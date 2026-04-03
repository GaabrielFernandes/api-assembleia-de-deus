package com.gabriel.api_assemblei_de_deus.service;

import com.gabriel.api_assemblei_de_deus.DTO.request.DiretorRequestDTO;
import com.gabriel.api_assemblei_de_deus.DTO.response.DiretorResponseDTO;
import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import com.gabriel.api_assemblei_de_deus.exception.DiretorNaoEncontradoException;
import com.gabriel.api_assemblei_de_deus.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiretorService {
    private final DiretorRepository repository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public DiretorResponseDTO salvarDiretor(DiretorRequestDTO dto){
        Diretor diretoDb = modelMapper.map(dto, Diretor.class);
        diretoDb.setSenha(passwordEncoder.encode(dto.getSenha()));
        repository.save(diretoDb);
        return modelMapper.map(diretoDb, DiretorResponseDTO.class);
    }

    public DiretorResponseDTO atualizar(Long id, DiretorRequestDTO dto){
        Diretor diretorDb = repository.findById(id).orElseThrow(() -> new DiretorNaoEncontradoException("Diretor não encontrado"));
        modelMapper.map(dto, diretorDb);
        repository.save(diretorDb);
        return modelMapper.map(diretorDb, DiretorResponseDTO.class);
    }

}
