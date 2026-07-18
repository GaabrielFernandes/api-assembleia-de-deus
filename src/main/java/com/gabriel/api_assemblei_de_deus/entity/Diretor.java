package com.gabriel.api_assemblei_de_deus.entity;

import com.gabriel.api_assemblei_de_deus.enuns.Departamento;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diretor")
public class Diretor implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String email;
    private String senha;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "usuario_departamento",
            foreignKey = @ForeignKey(name = "fk_usuario_departamento_id"),
            joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Enumerated(EnumType.STRING)
    @Column(name = "departamentos", nullable = false, length = 100)
    private Set<Departamento> departamentos;
    @Column(name = "senha_provisoria", nullable = false)
    private boolean senhaProvisoria = false;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return departamentos.stream()
                .map(departamento -> new SimpleGrantedAuthority("ROLE_"+ departamento.name())).collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
