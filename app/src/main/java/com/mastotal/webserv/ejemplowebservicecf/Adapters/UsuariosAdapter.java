package com.mastotal.webserv.ejemplowebservicecf.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastotal.webserv.ejemplowebservicecf.R;

/**
 * Created by diego on 7/08/2016.
 */
public class UsuariosAdapter {


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;

        public ViewHolder(View item) {
            super(item);
            title = (TextView) item.findViewById(R.id.title);
            imageView = (ImageView) item.findViewById(R.id.textView);
        }
    }
}
