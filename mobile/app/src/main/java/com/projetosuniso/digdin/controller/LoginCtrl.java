package com.projetosuniso.digdin.controller;

import com.projetosuniso.digdin.model.Cliente;
import com.projetosuniso.digdin.requisicoes.LoginGetCPF;

import java.util.concurrent.ExecutionException;

//Classe que vai validar o login do usuário
public class LoginCtrl {


    public boolean Login(String cpf){

        try {
            //Cria uma variavel cliente
            //Inicializada com um CPF como parametro
            Cliente cliente = new LoginGetCPF(cpf).execute().get();

            //Se o Cliente com o CPF indicado existir, ele retorna 'true' permitindo a entrada no app
            if (cliente != null){
                return true;
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //Caso não exista ele retorna 'false'
        return false;

    }

}
