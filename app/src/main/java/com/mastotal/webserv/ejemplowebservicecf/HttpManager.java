package com.mastotal.webserv.ejemplowebservicecf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by diego on 6/08/2016.
 */
public class HttpManager {

    // El siguiente metodo va a traer los datos
    public static String getData(String uri){
        BufferedReader reader = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            StringBuilder stringBuilder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            // nos permitira ir leyendo lo que traemos
            String line;

            while ((line = reader.readLine())!= null){
                stringBuilder.append(line + "\n");

            }

            return stringBuilder.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally { //cerrando
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return  null;
                }
            }
        }

    }

}
