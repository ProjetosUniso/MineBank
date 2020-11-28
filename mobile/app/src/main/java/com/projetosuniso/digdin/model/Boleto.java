package com.projetosuniso.digdin.model;

public class Boleto {

    private int id;
    private String descricao;
    private Long valor;

    public static final Boleto[] boletos = {
            new Boleto("Rodizio de Traveco anao", (long) 100),
            new Boleto("Boneco de acao do Naruto" , (long) 350),
            new Boleto("Puta virtual" , (long) 800),
            new Boleto("Peruca",  (long) 120)
    };

    public Boleto(String descricao, Long valor) {
        setDescricao(descricao);
        setValor(valor);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getValor() {
        return valor;
    }

    public void setValor(Long valor) {
        this.valor = valor;
    }
}
