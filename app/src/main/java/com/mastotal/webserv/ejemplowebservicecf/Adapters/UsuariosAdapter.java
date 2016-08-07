package com.mastotal.webserv.ejemplowebservicecf.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mastotal.webserv.ejemplowebservicecf.POJO.Usuario;
import com.mastotal.webserv.ejemplowebservicecf.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diego on 7/08/2016.
 */
public class UsuariosAdapter extends RecyclerView.Adapter<UsuariosAdapter.ViewHolder>{
    List<Usuario> usuarioList = new ArrayList<>();
    Context context;

    public UsuariosAdapter(Context context, List<Usuario> usuarioList){
        this.context = context;
        this.usuarioList = usuarioList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // donde se hace la asignacion
        holder.title.setText(usuarioList.get(position).getNombre());
        Picasso.with(context).load(usuarioList.get(position).getTwitter()).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        //que no se le olvide
        return usuarioList.size();
    }

    // Se crea manualmente
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imageView;
        CardView cardView;

        public ViewHolder(View item) {
            super(item);
            cardView= (CardView) item.findViewById(R.id.cardView);
            title = (TextView) item.findViewById(R.id.title);
            imageView = (ImageView) item.findViewById(R.id.imageView);
        }
    }
}
