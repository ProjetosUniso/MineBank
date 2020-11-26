package com.projetosuniso.digdin.resource;

import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.requisicoes.TipoMovimentacao.ListarTipoMovimentacao;
import com.projetosuniso.digdin.requisicoes.TipoMovimentacao.TipoMovimentacaoPorChave;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TipoMovimentacaoResource {

    private final ListarTipoMovimentacao listar;

    public TipoMovimentacaoResource() {
        listar = new ListarTipoMovimentacao();
    }


    public List<TipoMovimentacao> listarTipoMovimentacao() throws JSONException, ExecutionException, InterruptedException {

        ArrayList<TipoMovimentacao> listaTipo = new ArrayList<>();

        JSONArray objs = listar.execute().get();

        for (int i = 0; i < objs.length(); i++) {

            JSONObject obj = objs.getJSONObject(i);

            TipoMovimentacao movimentacao = convertJsonObjectToTipoMovimentacao(obj);

            listaTipo.add(movimentacao);
        }

        return listaTipo;
    }

    public TipoMovimentacao getChave(String chave) throws ExecutionException, InterruptedException, JSONException {
        TipoMovimentacaoPorChave movimentChave = new TipoMovimentacaoPorChave(chave);
        TipoMovimentacao tipoMovimentacao;

        JSONObject obj = movimentChave.execute().get();

        tipoMovimentacao = convertJsonObjectToTipoMovimentacao(obj);

        return tipoMovimentacao;
    }


    public TipoMovimentacao convertJsonObjectToTipoMovimentacao(JSONObject obj) throws JSONException {

        TipoMovimentacao tipoMovimentacao = new TipoMovimentacao();

        tipoMovimentacao.setId(obj.getInt("id"));
        tipoMovimentacao.setChave(obj.getString("chave"));
        tipoMovimentacao.setDescricao(obj.getString("descricao"));

        return tipoMovimentacao;
    }

}
