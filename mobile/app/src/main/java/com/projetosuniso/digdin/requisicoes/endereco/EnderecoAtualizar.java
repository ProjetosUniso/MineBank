package com.projetosuniso.digdin.requisicoes.endereco;

import android.os.AsyncTask;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;

//NOT CHECKED
public class EnderecoAtualizar extends AsyncTask<Void, Void, String> {

    private final JSONObject endereco;
    private final int id;

    public EnderecoAtualizar(int id, JSONObject endereco) {
        this.id = id;
        this.endereco = endereco;
    }

    @Override
    protected String doInBackground(Void... voids) {

        String result = null;

        URL url;

        try {
            url = new URL("https://minebank-api-service.herokuapp.com/endereco/"+ id);

            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

            conn.setReadTimeout(5000);
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-type", "application/json");
            conn.setDoOutput(true);
            conn.connect();

            OutputStreamWriter os = new OutputStreamWriter( conn.getOutputStream() );
            os.write(endereco.toString());
            os.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            result = sb.toString();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
