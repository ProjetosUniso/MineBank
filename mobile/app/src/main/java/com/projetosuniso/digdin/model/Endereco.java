package com.projetosuniso.digdin.model;

public class Endereco {
    private Long id;
    private String rua;
    private String cidade;
    private int numero;
    private String uf;

    public Endereco() {
    }

    public Endereco(Long id, String rua, String cidade, int numero, String uf) {
        this.id = id;
        this.rua = rua;
        this.cidade = cidade;
        this.numero = numero;
        this.uf = uf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getUf() {
        return uf;
    }

    public void setUF(String uf) {
        this.uf = uf;
    }

}
