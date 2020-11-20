package com.projetosuniso.digdin.requisicoes.endereco;

import android.os.AsyncTask;
import org.json.JSONException;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

//OK
public class EnderecoPorID extends AsyncTask<Void, Void, JSONObject> {

    private final int id;

    public EnderecoPorID(int id) {
        this.id = id;
    }

    @Override
    protected JSONObject doInBackground(Void... voids) {

        String result = null;

        try {
            URL url = new URL("https://minebank-api-service.herokuapp.com/endereco/"+ id);

            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();

            connection.setReadTimeout(150000); //milliseconds
            connection.setConnectTimeout(15000); // milliseconds
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("GET");

            connection.connect();

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line = null;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                result = sb.toString();

            }

            JSONObject object = new JSONObject(result);

            return object;

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
