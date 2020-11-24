package com.projetosuniso.digdin.service;

import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.resource.HistMovimentacaoResource;

import java.util.List;

public class HistMovimentacaoService {
    private HistMovimentacaoResource historico;

    public HistMovimentacaoService() {
        historico = new HistMovimentacaoResource();
    }

    public List<HistMovimentacao> getID(int id) {
        try {
            return historico.historicoPorID(id);
        } catch (Exception e) {
            return null;
        }
    }
    public List<HistMovimentacao> listar(){
        try {
            return historico.historicoListar();
        } catch (Exception e) {
            return null;
        }
    }
    public String adicionar(HistMovimentacao movimentacao){
        try {
            return historico.adicionar(movimentacao);
        } catch (Exception e) {
            return null;
        }
    }

}
