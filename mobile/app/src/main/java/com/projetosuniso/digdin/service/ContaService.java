package com.projetosuniso.digdin.service;

import com.projetosuniso.digdin.model.Conta;
import com.projetosuniso.digdin.resource.ContaResource;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ContaService {
    private final ContaResource conta;

    public ContaService() {
        conta = new ContaResource();
    }

    public List<Conta> listar(){
        try {
            return conta.listarConta();
        } catch (Exception e) {
            return null;
        }
    }
    public Conta getID(int id){
        try {
            return conta.buscarPorID(id);
        } catch (Exception e){
            return null;
        }
    }
    public Conta getCPF(String cpf){
        try {
            return conta.buscarPorCPF(cpf);
        } catch (Exception e) {
            return null;
        }
    }
    public boolean login(String senha, String cpf) {
        try {
            return conta.validarLogin(cpf, senha);
        } catch (Exception e){
            return Boolean.parseBoolean(null);
        }
    }
    public String adicionar(Conta c){
        try {
            return conta.adicionar(c);
        } catch (Exception e) {
            return null;
        }
    }
    public String atualizar(Conta c, Long id){
        try {
            return conta.atualiza(c, id);
        } catch (Exception e){
            return e.getMessage();
        }
    }
    public String atualizarSaldo(Long id, int saldo){
        try {
            return conta.atualizarSaldo(id,saldo);
        } catch (Exception e) {
            return e.getMessage();
        }
    }
    public String atualizaPoupanca(Long id, int poupanca){
        try {
            return conta.atualizarPoupanca(id, poupanca);
        } catch (Exception e){
            return e.getMessage();
        }
    }

}
