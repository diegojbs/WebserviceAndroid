package com.mastotal.webserv.ejemplowebservicecf.POJO;

/**
 * Created by diego on 6/08/2016.
 */
public class Usuario {
    private int usuarioId;
    private String nombre;
    private String twitter;

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
}
