package com.gabriel.api_assemblei_de_deus.service;

import com.gabriel.api_assemblei_de_deus.DTO.MembroPage;
import com.gabriel.api_assemblei_de_deus.DTO.response.MembroResponseDTO;
import com.gabriel.api_assemblei_de_deus.DTO.request.MembroRequestDTO;
import com.gabriel.api_assemblei_de_deus.entity.Membro;
import com.gabriel.api_assemblei_de_deus.exception.MembroNaoEncontradoException;
import com.gabriel.api_assemblei_de_deus.repository.MembroRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MembroService {
    private final MembroRepository membroRepository;
    private final ModelMapper modelMapper;

    public MembroResponseDTO buscarMembro(Long id){
        Membro membro = membroRepository.findById(id).orElseThrow(()-> new MembroNaoEncontradoException("Erro ao buscar membro"));
        return modelMapper.map(membro, MembroResponseDTO.class);
    }

    public MembroResponseDTO cadastrar(MembroRequestDTO dto){
            Membro membro = modelMapper.map(dto, Membro.class);
            membroRepository.save(membro);
            return modelMapper.map(membro, MembroResponseDTO.class);
    }

    public Page<MembroPage> listarMembros(Pageable pageable){
        return membroRepository.findAll(pageable).map(
                membro -> modelMapper.map(membro, MembroPage.class)
        );
    }

    public MembroResponseDTO atualizarMembro(Long id, MembroRequestDTO dto){
            Membro membroDb = membroRepository.findById(id).orElseThrow(() -> new MembroNaoEncontradoException("Erro ao buscar código"));
            modelMapper.map(dto, membroDb);
            Membro membroSalvo = membroRepository.save(membroDb);
            return modelMapper.map(membroSalvo, MembroResponseDTO.class);
    }

    public List<MembroPage> buscarPeloNome(String nome){
        List<Membro> listaDeMembrosDb = membroRepository.findByNomeCompletoIgnoreCaseContaining(nome);
        return listaDeMembrosDb.stream().map(membros -> modelMapper.map(membros, MembroPage.class)).collect(Collectors.toList());
    }

    public void excluirMembro(Long id){
        if (!membroRepository.existsById(id)){
            throw new MembroNaoEncontradoException(id);
        }
        membroRepository.deleteById(id);
    }
}
