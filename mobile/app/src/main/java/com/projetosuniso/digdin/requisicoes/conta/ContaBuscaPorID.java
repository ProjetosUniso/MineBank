package com.projetosuniso.digdin.requisicoes.conta;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

//OK
public class ContaBuscaPorID extends AsyncTask<Void, Void, JSONObject> {

    private final int id;

    public ContaBuscaPorID(int id) {
        this.id = id;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        String result = null;

        try {
            URL url = new URL("https://minebank-api-service.herokuapp.com/contas/id/"+ id);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                result = sb.toString();

            }

            connection.disconnect();
            return new JSONObject(result);

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
