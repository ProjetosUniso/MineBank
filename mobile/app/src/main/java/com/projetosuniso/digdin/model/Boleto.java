package com.projetosuniso.digdin.model;

public class Boleto {

    private int id;
    private String descricao;
    private Long valor;

    public static final Boleto[] boletos = {
            new Boleto(100101, "Rodizio de Traveco anão", (long) 100),
            new Boleto(736001, " Boneco de ação do Naruto" , (long) 350),
            new Boleto(400289, "Puta virtual" , (long) 800),
            new Boleto(847566, "Peruca",  (long) 120),
    };

    public Boleto(int id, String descricao, Long valor) {
        setId(id);
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
