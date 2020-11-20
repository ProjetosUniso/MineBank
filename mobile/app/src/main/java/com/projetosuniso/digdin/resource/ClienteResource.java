package com.projetosuniso.digdin.resource;

import com.google.gson.Gson;
import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Endereco;
import com.projetosuniso.digdin.requisicoes.cliente.ClienteAtualiza;
import com.projetosuniso.digdin.requisicoes.cliente.ClienteBuscaPorID;
import com.projetosuniso.digdin.requisicoes.cliente.ClienteVerificarCPF;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ClienteResource {

    private final EnderecoResource endResource = new EnderecoResource();

    public ClienteResource() {
    }

    public Cliente buscarPorID (int id) throws ExecutionException, InterruptedException, JSONException {
        ClienteBuscaPorID buscaPorID = new ClienteBuscaPorID(id);
        Cliente cliente;

        JSONObject object = buscaPorID.execute().get();

        cliente = convertJsonToCliente(object);

        return cliente;
    }

    public boolean verificarCPF (String cpf) throws ExecutionException, InterruptedException {
        ClienteVerificarCPF verificarCPF = new ClienteVerificarCPF(cpf);
        return verificarCPF.execute().get();
    }

    public boolean atualiza (Cliente cliente, int id) throws JSONException, ExecutionException, InterruptedException {
        ClienteAtualiza atualiza;
        boolean resul ;

        JSONObject object = convertClienteToJsonObj(cliente);
        atualiza = new ClienteAtualiza(object.toString(), id);

        resul = atualiza.execute().get();

        return resul;
    }


    public Cliente convertJsonToCliente (JSONObject object) throws JSONException {
        Cliente cliente = new Cliente();
        JSONObject end;
        Endereco endereco;

        cliente.setId(object.getInt("id"));
        cliente.setNome(object.getString("nome"));
        cliente.setCpf(object.getString("cpf"));
        cliente.setRg(object.getString("rg"));
        cliente.setEmail(object.getString("email"));
        cliente.setDataNascimento(object.getString("dataNascimento"));

        end = object.getJSONObject("endereco");

        endereco = endResource.ConvertJsonToEndereco(end);

        cliente.setEndereco(endereco);

        return cliente;
    }

    private JSONObject convertClienteToJsonObj(Cliente cliente) throws JSONException {
        JSONObject object;
        Gson g = new Gson();
        String jsonStr;

        jsonStr = g.toJson(cliente);

        object = new JSONObject(jsonStr);

        return object;
    }
}
