package com.projetosuniso.digdin.model;


//Criando um Objeto Java parar representar cliente, o que vai auxiliar na manipulação de dados durante a aplicação
public class Cliente {
    private String id;
    private String nome;
    private String cpf;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "ID: " + getId()
                + "\nNome: " + getNome()
                + "\nCpf: " + getCpf();
    }
}
