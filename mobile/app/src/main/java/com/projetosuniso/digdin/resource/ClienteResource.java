package com.projetosuniso.digdin.resource;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.model.Endereco;
import com.projetosuniso.digdin.requisicoes.cliente.ClienteVerificarCPF;
import com.projetosuniso.digdin.requisicoes.cliente.ClienteBuscaPorID;
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



    public Cliente convertJsonToCliente (JSONObject object) throws JSONException {
        Cliente cliente = new Cliente();
        JSONObject end;
        Endereco endereco;

        cliente.setId(object.getInt("id"));
        cliente.setCpf(object.getString("cpf"));
        cliente.setDataNascimento(object.getString("dataNascimento"));
        cliente.setEmail(object.getString("email"));
        cliente.setNome(object.getString("nome"));
        cliente.setRg(object.getString("rg"));

        end = object.getJSONObject("endereco");

        endereco = endResource.ConvertJsonToEndereco(end);

        cliente.setEndereco(endereco);

        return cliente;
    }
}
