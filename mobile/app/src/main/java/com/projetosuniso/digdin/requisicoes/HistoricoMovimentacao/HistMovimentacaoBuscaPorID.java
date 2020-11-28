package com.projetosuniso.digdin.requisicoes.HistoricoMovimentacao;

import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

//OK
public class HistMovimentacaoBuscaPorID extends AsyncTask<Void, Void, JSONArray> {

    private final Long id;

    public HistMovimentacaoBuscaPorID(Long id) {
        this.id = id;
    }

    @Override
    protected JSONArray doInBackground(Void... voids) {

        String result = null;

        try {
            URL url = new URL("https://minebank-api-service.herokuapp.com/movimentacao/"+ id);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(),
                        "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                result = sb.toString();

                connection.disconnect();
                return new JSONArray(result);
            }

        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
