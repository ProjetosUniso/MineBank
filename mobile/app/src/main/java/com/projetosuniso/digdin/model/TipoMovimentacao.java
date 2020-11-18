package com.projetosuniso.digdin.model;

public class TipoMovimentacao {
    private int id;
    private String chave;
    private String descricao;

    public TipoMovimentacao() {

    }

    public TipoMovimentacao(int id, String chave, String descricao) {
        setId(id);
        setChave(chave);
        setDescricao(descricao);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
