package com.example.projetolucas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetolucas.domain.dto.centrodecusto.CentroDeCustoRequestDTO;
import com.example.projetolucas.domain.dto.centrodecusto.CentroDeCustoResponseDTO;
import com.example.projetolucas.domain.service.CentroDeCustoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/centrodecustos")
public class CentroDeCustoController {
    @Autowired
    private CentroDeCustoService service;

    @GetMapping
    public ResponseEntity<List<CentroDeCustoResponseDTO>> obterTodos(){
        return ResponseEntity.ok(service.obterTodos());
    }
    @GetMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> obterPorId(@PathVariable Long id){
        return ResponseEntity.ok(service.obterPorId(id));
    }
    @PostMapping
    public ResponseEntity<CentroDeCustoResponseDTO> cadastrar(@RequestBody CentroDeCustoRequestDTO dto){
        CentroDeCustoResponseDTO centroDeCusto = service.cadastrar(dto);
        return new ResponseEntity<>(centroDeCusto, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CentroDeCustoResponseDTO> atualizar(@PathVariable Long id, @RequestBody CentroDeCustoRequestDTO dto){
        CentroDeCustoResponseDTO centroDeCusto = service.atualizar(id, dto);
        return ResponseEntity.ok(centroDeCusto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id){
        service.deletar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
