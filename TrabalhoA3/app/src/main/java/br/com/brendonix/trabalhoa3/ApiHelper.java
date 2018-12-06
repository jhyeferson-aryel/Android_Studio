package br.com.brendonix.trabalhoa3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

import br.com.brendonix.model.Album;

public class ApiHelper {

    // Variaveis default.
    String json = null;
    String apiUrl = null;
    HttpURLConnection urlConnection = null;

    // Instância
    public ApiHelper(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    // Obter o resultado.
    public void loadResult(final Runnable runnable) {
        // Criando Thread.
        Thread tr = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(apiUrl);
                    urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.connect();

                    InputStream inputStream = urlConnection.getInputStream();
                    json = getStringFromInputStream(inputStream);

                    Type listType = new TypeToken<ArrayList<Album>>() {
                    }.getType();
                    Statics.albums = new Gson().fromJson(json, listType);

                    new Thread(runnable).start();

                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        // Iniciando a Thread.
        tr.start();
    }

    private static String getStringFromInputStream(InputStream is) {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

}
