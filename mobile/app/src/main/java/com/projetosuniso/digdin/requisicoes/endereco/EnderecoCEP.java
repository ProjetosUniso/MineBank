package com.projetosuniso.digdin.requisicoes.endereco;

import android.os.AsyncTask;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EnderecoCEP extends AsyncTask<String, Void, JSONObject> {

    private final String cep;

    public EnderecoCEP(String cep) {
        this.cep = cep;
    }

    @Override
    protected JSONObject doInBackground(String... strings) {

        String result = null;
        URL url;

        try {
            url = new URL("https://viacep.com.br/ws/" + cep +"/json/");

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                result = sb.toString();


            }

            JSONObject teste = new JSONObject(result);

            return teste;

        } catch (Exception e) {
            e.getMessage();
        }
        return null;

    }
}
