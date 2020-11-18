package com.projetosuniso.digdin.model;

public class Endereco {
    private Long id;
    private String rua;
    private String cidade;
    private int numero;
    private String UF;

    public Endereco() {
    }

    public Endereco(String rua, String cidade, int numero, String UF) {
        setRua(rua);
        setCidade(cidade);
        setNumero(numero);
        setUF(UF);
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

    public String getUF() {
        return UF;
    }

    public void setUF(String UF) {
        this.UF = UF;
    }

}
