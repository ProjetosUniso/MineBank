package com.projetosuniso.digdin.model;

public class HistMovimentacao {
    private Conta conta;
    private String dataInclusao;
    private String descricao;
    private int id;
    private Long idContaTransferencia;
    private TipoMovimentacao movimentacao;
    private double valor;

    public HistMovimentacao() {
    }

    public HistMovimentacao(Conta conta, String dataInclusao, String descricao, int id, Long idContaTransferencia, TipoMovimentacao movimentacao, double valor) {
        this.conta = conta;
        this.dataInclusao = dataInclusao;
        this.descricao = descricao;
        this.id = id;
        this.idContaTransferencia = idContaTransferencia;
        this.movimentacao = movimentacao;
        this.valor = valor;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getIdContaTransferencia() {
        return idContaTransferencia;
    }

    public void setIdContaTransferencia(Long idContaTransferencia) {
        this.idContaTransferencia = idContaTransferencia;
    }

    public TipoMovimentacao getMovimentacao() {
        return movimentacao;
    }

    public void setMovimentacao(TipoMovimentacao movimentacao) {
        this.movimentacao = movimentacao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
