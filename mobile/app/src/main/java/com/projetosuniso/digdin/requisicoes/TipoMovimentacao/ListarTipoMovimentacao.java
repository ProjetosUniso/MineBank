package com.projetosuniso.digdin.requisicoes.TipoMovimentacao;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

public class ListarTipoMovimentacao extends AsyncTask<Void, Void, JSONArray> {

    @Override
    public JSONArray doInBackground(Void... voids) {

        String result = null;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/tiposMovimentacao");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(150000); //milliseconds
            connection.setConnectTimeout(15000); // milliseconds
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_MOVED_TEMP) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");

                }
                result = sb.toString();
            }

            return new JSONArray(result);

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
