package com.projetosuniso.digdin.requisicoes.conta;

import android.os.AsyncTask;
import org.json.JSONArray;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

//OK
public class ListarConta extends AsyncTask<Void, Void, JSONArray> {


    @Override
    protected JSONArray doInBackground(Void... voids) {

        String result = null;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/contas");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line ;

                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");

                }
                result = sb.toString();
            }

            JSONArray jsonArray = new JSONArray(result);

            return jsonArray;

        } catch (Exception e) {
            e.getMessage();
        }


        return null;
    }
}
