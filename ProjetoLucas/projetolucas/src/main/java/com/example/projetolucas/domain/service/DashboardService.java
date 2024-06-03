package com.example.projetolucas.domain.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.projetolucas.domain.Enum.ETipoTitulo;
import com.example.projetolucas.domain.dto.dashboard.DashboardResponseDTO;
import com.example.projetolucas.domain.dto.titulo.TituloResponseDTO;

@Service
public class DashboardService {
    @Autowired
    private TituloService tituloService;

    public DashboardResponseDTO obterFluxoDeCaixa (String periodoInicial, String periodoFinal){
        List<TituloResponseDTO> titulos = tituloService.obterPorDataVencimento(periodoInicial, periodoFinal);
        Double totalPagar = 0.0;
        Double totalReceber = 0.0;
        Double saldo = 0.0;
        List<TituloResponseDTO> titulosPagar = new ArrayList<>();
        List<TituloResponseDTO> titulosReceber = new ArrayList<>();
        for(TituloResponseDTO titulo : titulos){
            if(titulo.getTipo() == ETipoTitulo.APAGAR){
                totalPagar += titulo.getValor();
                titulosPagar.add(titulo);
            }else{
                totalReceber += titulo.getValor();
                titulosReceber.add(titulo);
            }
        }
        saldo = totalReceber - totalPagar;
        return new DashboardResponseDTO(totalPagar, totalReceber, saldo, titulosPagar, titulosReceber);
    }
}
