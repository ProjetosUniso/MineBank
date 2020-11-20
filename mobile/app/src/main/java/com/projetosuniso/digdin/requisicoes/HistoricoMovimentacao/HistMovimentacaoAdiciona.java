package com.projetosuniso.digdin.requisicoes.HistoricoMovimentacao;

import android.os.AsyncTask;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

//OK
public class HistMovimentacaoAdiciona extends AsyncTask<Void, Void, String> {

    private final JSONObject historico;

    public HistMovimentacaoAdiciona(JSONObject historico) {
        this.historico = historico;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/movimentacao");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(historico.toString().getBytes());
            os.flush();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                result = "exito";

            }else {
                result = "falha";
            }

            return result;


        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
