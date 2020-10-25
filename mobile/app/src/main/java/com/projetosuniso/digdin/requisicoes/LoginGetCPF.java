package com.projetosuniso.digdin.requisicoes;

import android.os.AsyncTask;
import com.projetosuniso.digdin.model.Cliente;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

//Criando um sistema de login onde a validação é feita com o CPF do cliente
//Extende a classe AsyncTask, permitindo que faça a requisição GET em paralelo com o processo principal
public class LoginGetCPF extends AsyncTask<Void, Void, Cliente> {

    private final String cpf;

    //Construtor que pega o CPF que foi informado pelo cliente
    public LoginGetCPF(String cpf) {
        this.cpf = cpf;

    }

    //Metodo Principal da classe AsyncTask
    //Faz a ligação com o banco e retorna um cliente com valores, ou um cliente null caso não exista
    @Override
    protected Cliente doInBackground(Void... voids) {
        Cliente cliente = null;

        try {
            //URL do GET por CPF, onde o cpf é pego do construtor
            URL url = new URL("https://minebank-api.herokuapp.com/clientes/cpf/"+ cpf);

            //Conecta a URL à uma classe HttpURLConnection que permite interagir com os metodos da API
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //Informando os tipos de parametros que serão usados na requisição
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestProperty("Accept", "application/json");
            connection.setDoOutput(true);
            connection.setConnectTimeout(5000);
            //Confirmando a Conexão
            connection.connect();

            //Lê os dados devolvidos da requisição
            Scanner scanner = new Scanner(url.openStream());

            //Armazena os dados devolvidos em um Objeto do formato Json
            JSONObject obj = new JSONObject(scanner.next());


                cliente = new Cliente();

                //Fazendo a conversão dos dados em formato Json, para uma String
                String id = obj.getString("id");
                String nome = obj.getString("nome");
                String cpf = obj.getString("cpf");

                //Inserindo os dados da conversão em um Objeto Cliente
                cliente.setId(id);
                cliente.setNome(nome);
                cliente.setCpf(cpf);



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return cliente;
    }

}
