package com.projetosuniso.digdin.service;

import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.resource.TipoMovimentacaoResource;

import java.util.List;

public class TipoMovimentacaoService {

    private TipoMovimentacaoResource tipoMovimentacaoResource;

    public TipoMovimentacaoService() {
        tipoMovimentacaoResource = new TipoMovimentacaoResource();
    }

    public List<TipoMovimentacao> listar() {
        try {
            return tipoMovimentacaoResource.listarTipoMovimentacao();

        } catch (Exception e) {
            return null;
        }
    }

}
