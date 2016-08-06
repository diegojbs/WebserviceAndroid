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

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }
    }

}
