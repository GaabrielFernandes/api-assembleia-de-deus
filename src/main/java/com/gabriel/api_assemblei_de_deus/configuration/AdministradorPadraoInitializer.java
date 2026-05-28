package com.gabriel.api_assemblei_de_deus.configuration;

import com.gabriel.api_assemblei_de_deus.entity.Diretor;
import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import com.gabriel.api_assemblei_de_deus.repository.DiretorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdministradorPadraoInitializer implements CommandLineRunner {

    private final DiretorRepository diretorRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${sistema.admin.nome:Administrador do Sistema}")
    private String nome;

    @Value("${sistema.admin.email:admin@sistema.com}")
    private String email;

    @Value("${sistema.admin.senha:admin1234}")
    private String senha;

    @Override
    public void run(String... args) {
        if (diretorRepository.findByEmail(email).isPresent()) {
            return;
        }

        Diretor administrador = new Diretor();
        administrador.setNome(nome);
        administrador.setEmail(email);
        administrador.setSenha(passwordEncoder.encode(senha));
        administrador.setDepartamento(Departamento.ADMINISTRADOR);
        administrador.setSenhaProvisoria(false);

        diretorRepository.save(administrador);
    }
}
