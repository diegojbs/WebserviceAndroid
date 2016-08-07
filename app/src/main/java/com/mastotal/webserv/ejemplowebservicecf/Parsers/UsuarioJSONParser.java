package com.mastotal.webserv.ejemplowebservicecf.Parsers;

import com.mastotal.webserv.ejemplowebservicecf.POJO.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 7/08/2016.
 */
public class UsuarioJSONParser {

    public static List<Usuario> parse(String content){
        try {
            JSONArray jsonArray = new JSONArray(content);
            List<Usuario> usuarioList = new ArrayList<>();

            // recorrer el array que nos llega a la antigua
            for (int i = 0; i < jsonArray.length();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Usuario usuario = new Usuario();

                usuario.setUsuarioId(jsonObject.getInt("usuarioid"));
                usuario.setNombre(jsonObject.getString("nombre"));
                usuario.setTwitter(jsonObject.getString("twitter"));

                usuarioList.add(usuario);
            }

            return usuarioList;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
