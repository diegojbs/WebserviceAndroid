package com.mastotal.webserv.ejemplowebservicecf.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.mastotal.webserv.ejemplowebservicecf.POJO.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 7/08/2016.
 */
public class MyAdapter extends BaseAdapter{
    List<Usuario> usuarioList = new ArrayList<>();
    // para ir colocando los eementos
    LayoutInflater layoutInflater;
    Context context;

    // creamos un constructor
    public MyAdapter (Context context, List<Usuario> usuarioList) {
        this.context = context;
        this.usuarioList = usuarioList;
        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Usuario getItem(int position) {
        return usuarioList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}