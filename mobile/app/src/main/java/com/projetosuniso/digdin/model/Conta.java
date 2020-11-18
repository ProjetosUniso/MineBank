package com.projetosuniso.digdin.model;

public class Conta {
    private int agencia;
    private Cliente cliente;
    private int id;
    private double saldo;
    private int senha;

    public Conta() {
    }

    public Conta(int agencia, Cliente cliente, int id, float saldo, int senha) {
        this.agencia = agencia;
        this.cliente = cliente;
        this.id = id;
        this.saldo = saldo;
        this.senha = senha;
    }

    public int getAgencia() {
        return agencia;
    }

    public void setAgencia(int agencia) {
        this.agencia = agencia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }
}
