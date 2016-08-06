package com.mastotal.webserv.ejemplowebservicecf.Parsers;

import com.mastotal.webserv.ejemplowebservicecf.POJO.Usuario;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 6/08/2016.
 */
public class UsuarioXMLParser{


    public static List<Usuario> parser(String content){
        boolean inDataItemTag = false;
        String currentTagName = "";
        Usuario usuario = null;
        List<Usuario> usuarioList = new ArrayList<>();

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(content));

            int eventType = parser.getEventType();

            //leer el XML de informacion
            while(eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if(currentTagName.equals("usuario")){
                            inDataItemTag = true;
                            usuario = new Usuario();
                            usuarioList.add(usuario);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        //detectar cuando se termina
                        if(parser.getName().equals("usuario")){
                            inDataItemTag = false;

                        }
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        // el evento que nos trae el texto como tal
                        if (inDataItemTag && usuario != null){
                            switch (currentTagName){
                                case "usuarioid":
                                    usuario.setUsuarioId(Integer.parseInt(parser.getText()));
                                    break;
                                case "nombre":
                                    usuario.setNombre(parser.getText());
                                    break;
                                case "twitter":
                                    usuario.setTwitter(parser.getText());
                                    break;
                            }
                        }
                        break;
                }

                // leer el siguiente eveto
                eventType = parser.next();

            }
            return usuarioList;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
