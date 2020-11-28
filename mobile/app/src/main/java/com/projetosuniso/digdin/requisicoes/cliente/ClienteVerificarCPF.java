package com.projetosuniso.digdin.requisicoes.cliente;

import android.os.AsyncTask;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Scanner;

public class ClienteVerificarCPF extends AsyncTask<Void, Void, Boolean> {

    private final String cpf;

    public ClienteVerificarCPF(String cpf) {
        this.cpf = cpf;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        String result;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/cliente/cpfExiste/"+ cpf);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setDoOutput(true);
            connection.setRequestMethod("GET");

            connection.connect();

            Scanner scan = new Scanner(url.openStream());

            result = scan.next();


            return Boolean.valueOf(result);


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
