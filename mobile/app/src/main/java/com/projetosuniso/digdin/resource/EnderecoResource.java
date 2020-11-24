package com.projetosuniso.digdin.resource;

import com.google.gson.Gson;
import com.projetosuniso.digdin.model.Endereco;
import com.projetosuniso.digdin.requisicoes.endereco.EnderecoAtualizar;
import com.projetosuniso.digdin.requisicoes.endereco.EnderecoCEP;
import com.projetosuniso.digdin.requisicoes.endereco.EnderecoPorID;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class EnderecoResource {

    public EnderecoResource() {
    }


    public Endereco enderecoPodID(int id) throws ExecutionException, InterruptedException, JSONException {
        EnderecoPorID enderecoPorID = new EnderecoPorID(id);
        Endereco end;

        JSONObject object =  enderecoPorID.execute().get();

        end = ConvertJsonToEndereco(object);

        return end;
    }

    public String enderecoAtualizar(int id, Endereco endereco) throws ExecutionException, InterruptedException, JSONException {
        EnderecoAtualizar atualizar;

        JSONObject end = convertEnderecoToJsonObj(endereco);
        atualizar = new EnderecoAtualizar(id, end.toString());

        String obj = atualizar.execute().get();

        return obj;
    }

    public Endereco enderecoCEP(String cep) throws ExecutionException, InterruptedException, JSONException {
        EnderecoCEP enderecoPorCEP = new EnderecoCEP(cep);
        Endereco endereco;

        JSONObject object =  enderecoPorCEP.execute().get();

        endereco = ConvertCEPToEndereco(object);

        return endereco;
    }

    public Endereco ConvertCEPToEndereco (JSONObject object) throws JSONException {
        Endereco endereco = new Endereco();

        endereco.setRua(object.getString("logradouro"));
        endereco.setCidade(object.getString("localidade"));
        endereco.setUF(object.getString("uf"));

        return endereco;
    }


    public Endereco ConvertJsonToEndereco (JSONObject object) throws JSONException {
        Endereco endereco = new Endereco();

        endereco.setId(object.getLong("id"));
        endereco.setRua(object.getString("rua"));
        endereco.setCidade(object.getString("cidade"));
        endereco.setNumero(object.getInt("numero"));
        endereco.setUF(object.getString("uf"));

        return endereco;
    }

    private JSONObject convertEnderecoToJsonObj(Endereco endereco) throws JSONException {
        JSONObject object;
        Gson g = new Gson();
        String jsonStr;

        jsonStr = g.toJson(endereco);

        object = new JSONObject(jsonStr);

        return object;
    }
}
