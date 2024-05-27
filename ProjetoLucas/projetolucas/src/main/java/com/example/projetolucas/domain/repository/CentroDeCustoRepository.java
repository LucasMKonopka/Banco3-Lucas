package com.example.projetolucas.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetolucas.domain.model.CentroDeCusto;
import com.example.projetolucas.domain.model.Usuario;

public interface CentroDeCustoRepository extends JpaRepository <CentroDeCusto, Long> {

    List<CentroDeCusto> findByUsuario(Usuario usuario);
    
}
