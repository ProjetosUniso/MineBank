package com.projetosuniso.digdin.resource;

import com.google.gson.Gson;
import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.model.HistMovimentacao;
import com.projetosuniso.digdin.model.TipoMovimentacao;
import com.projetosuniso.digdin.requisicoes.HistoricoMovimentacao.HistMovimentacaoAdiciona;
import com.projetosuniso.digdin.requisicoes.HistoricoMovimentacao.HistMovimentacaoBuscaPorID;
import com.projetosuniso.digdin.requisicoes.HistoricoMovimentacao.HistMovimentacaoListar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HistMovimentacaoResource {


    private Conta conta;
    private final ContaResource contaResource = new ContaResource();
    private final TipoMovimentacaoResource tipoMovimentacaoResource = new TipoMovimentacaoResource();

    public HistMovimentacaoResource() {
    }

    public List<HistMovimentacao> historicoPorID(int id) throws JSONException, ExecutionException, InterruptedException {
        HistMovimentacaoBuscaPorID buscaPorID = new HistMovimentacaoBuscaPorID(id);
        List<HistMovimentacao> historico = new ArrayList<>();

        JSONArray objects = buscaPorID.execute().get();

        for (int i = 0; i < objects.length(); i++) {

            JSONObject obj = objects.getJSONObject(i);

            HistMovimentacao historicoTmp = convertJsonObjectToHistMovimentacao(obj);

            historico.add(historicoTmp);
        }

        return historico;
    }

    //ERRO - idContaTransferencia is null
    public List<HistMovimentacao> historicoListar() throws JSONException, ExecutionException, InterruptedException {
        HistMovimentacaoListar listar = new HistMovimentacaoListar();
        ArrayList<HistMovimentacao> listHistorico = new ArrayList<>();

        JSONArray objs = listar.execute().get();

        for (int i = 0; i < objs.length(); i++) {

            JSONObject obj = objs.getJSONObject(i);

            HistMovimentacao historico = convertJsonObjectToHistMovimentacao(obj);

            listHistorico.add(historico);
        }

        return listHistorico;
    }

    public String adicionar (HistMovimentacao movimentacao) throws JSONException, ExecutionException, InterruptedException {
        HistMovimentacaoAdiciona adiciona;
        String resul;
        conta = movimentacao.getConta();

        JSONObject object = convertHistoricoToJsonObj(movimentacao);
        adiciona = new HistMovimentacaoAdiciona(object);

        switch (movimentacao.getDescricao()){
            case "saque":
                if (conta.getSaldo() >= movimentacao.getValor()){
                    resul = adiciona.execute().get();
                }else {
                    resul = "O valor do Saque excede o saldo";
                }
                break;
            case "transferencia":
                if (conta.getSaldo() >= movimentacao.getValor()){
                    resul = adiciona.execute().get();
                }else {
                    resul = "O valor da Transferencia excede o saldo";
                }
                break;
            case "deposito":
                resul = adiciona.execute().get();
                break;

            default:
                resul = "erro ao selecionar o descrição do movimento";
        }

        return resul;
    }


    private HistMovimentacao convertJsonObjectToHistMovimentacao(JSONObject obj) throws JSONException {
        HistMovimentacao historico = new HistMovimentacao();
        JSONObject cnt, mvt;
        TipoMovimentacao movimentacao;

        historico.setId(obj.getInt("id"));
        historico.setDataInclusao(obj.getString("dataInclusao"));
        historico.setDescricao(obj.getString("descricao"));
        historico.setIdContaTransferencia(obj.getInt("idContaTransferencia"));

        cnt = obj.getJSONObject("conta");
        mvt = obj.getJSONObject("movimentacao");

        conta = contaResource.convertJsonObjectToConta(cnt);
        movimentacao = tipoMovimentacaoResource.convertJsonObjectToTipoMovimentacao(mvt);

        historico.setConta(conta);
        historico.setMovimentacao(movimentacao);

        return historico;
    }

    private JSONObject convertHistoricoToJsonObj(HistMovimentacao movimentacao) throws JSONException {
        JSONObject object;
        Gson g = new Gson();
        String jsonStr;

        jsonStr = g.toJson(movimentacao);

        object = new JSONObject(jsonStr);

        return object;
    }
}
