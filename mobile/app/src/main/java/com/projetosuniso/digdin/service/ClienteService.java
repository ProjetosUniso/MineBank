package com.projetosuniso.digdin.service;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.resource.ClienteResource;

public class ClienteService {

    private final ClienteResource cliente;

    public ClienteService() {
        this.cliente = new ClienteResource();
    }

    public Cliente getID (int id){
        try {
            return cliente.buscarPorID(id);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean verificarCPF (String cpf){
        try {
            return cliente.verificarCPF(cpf);
        } catch (Exception e) {
            return Boolean.parseBoolean(null);
        }
    }
    public boolean verificarEmail (String email){
        try {
            return cliente.verificarEmail(email);
        } catch (Exception e) {
            return Boolean.parseBoolean(null);
        }
    }
    public boolean atualizar (Cliente clienteAtt, int id){
        try {
            return cliente.atualiza(clienteAtt, id);
        } catch (Exception e)  {
            return false;
        }
    }
}
