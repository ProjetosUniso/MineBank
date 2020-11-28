package com.projetosuniso.digdin.requisicoes.conta;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ContaAtualizaPoupanca extends AsyncTask<Void, Void, String> {

    private final String novoSaldo;

    public ContaAtualizaPoupanca(String novoSaldo) {
        this.novoSaldo = novoSaldo;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/contas/poupanca/"+ novoSaldo);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            connection.connect();

            int responseCod = connection.getResponseCode();

            if (responseCod == HttpURLConnection.HTTP_OK){
                result = "exito";
            }else {
                result = "falha ao conectar com o banco" + responseCod;
            }

            connection.disconnect();
            return result;

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
