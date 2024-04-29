package com.example.projetolucas.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetolucas.domain.model.Usuario;


public interface UsuarioRepository extends
        JpaRepository<Usuario, Long> {
    

    Optional<Usuario> findByEmail(String email);
}
