package com.projetosuniso.digdin.model;

import java.io.Serializable;

public class Conta implements Serializable {
    private int agencia;
    private Cliente cliente;
    private int numero;
    private Long id;
    private double saldo;
    private double poupanca;
    private int senha;

    public Conta() {
    }

    public Conta(int agencia, int numero, Cliente cliente, Long id, float saldo, float poupanca, int senha) {
        this.id = id;
        this.numero = numero;
        this.agencia = agencia;
        this.saldo = saldo;
        this.poupanca = poupanca;
        this.senha = senha;
        this.cliente = cliente;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public double getPoupanca() {
        return poupanca;
    }

    public void setPoupanca(double poupanca) {
        this.poupanca = poupanca;
    }
}
