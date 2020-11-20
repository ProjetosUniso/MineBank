package com.projetosuniso.digdin.requisicoes.conta;

import android.os.AsyncTask;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;


//NOT CHECKED
public class ContaAdiciona extends AsyncTask<Void, Void, String> {

    private final JSONObject conta;

    public ContaAdiciona(JSONObject conta) {
        this.conta = conta;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/contas");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);

            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(conta.toString().getBytes());
            os.flush();


            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()
                        , "UTF-8"));
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                result = sb.toString();

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
