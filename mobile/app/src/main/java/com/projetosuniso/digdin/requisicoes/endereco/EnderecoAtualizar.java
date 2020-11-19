package com.projetosuniso.digdin.requisicoes.endereco;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//NOT CHECKED
public class EnderecoAtualizar extends AsyncTask<Void, Void, String> {

    private final String endereco;
    private final int id;

    public EnderecoAtualizar(int id, String endereco) {
        this.id = id;
        this.endereco = endereco;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected String doInBackground(Void... voids) {

        String result;

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

            OutputStream os = conn.getOutputStream();
            os.write(endereco.getBytes());

            result = readResponse(conn);
            conn.disconnect();

            return result;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private String readResponse(HttpURLConnection request) throws IOException {
        ByteArrayOutputStream os;
        try (InputStream is = request.getInputStream()) {
            os = new ByteArrayOutputStream();
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
        }
        return new String(os.toByteArray());
    }
}
