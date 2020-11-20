package com.projetosuniso.digdin.requisicoes.cliente;

import android.os.AsyncTask;
import android.os.Build;
import androidx.annotation.RequiresApi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

//OK
public class ClienteAtualiza extends AsyncTask<Void, Void, Boolean> {

    private final String cliente;
    private final int id;

    public ClienteAtualiza(String cliente, int id) {
        this.cliente = cliente;
        this.id = id;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected Boolean doInBackground(Void... voids) {

        String result;

        try {
            URL url = new URL("http://minebank-api-service.herokuapp.com/cliente/"+ id);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.setRequestProperty("Content-type", "application/json");
            connection.setRequestMethod("PUT");
            connection.setDoOutput(true);

            connection.connect();

            OutputStream os = connection.getOutputStream();
            os.write(cliente.getBytes());

            int statusCode = connection.getResponseCode();

            if (statusCode == HttpURLConnection.HTTP_BAD_REQUEST){

                return false;
            }else {
                readResponse(connection);
                connection.disconnect();

                return true;

            }


        } catch (ProtocolException e) {
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
