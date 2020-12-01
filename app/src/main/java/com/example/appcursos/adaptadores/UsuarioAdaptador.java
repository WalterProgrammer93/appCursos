package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Usuario;
import java.util.ArrayList;

public class UsuarioAdaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Usuario> listaUsuarios;
    private LayoutInflater inflater;

    public UsuarioAdaptador(Context context, ArrayList<Usuario> listaUsuarios) {
        this.context = context;
        this.listaUsuarios = listaUsuarios;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView iconoUsuario;
        TextView username;
        TextView email;
        TextView rol;
    }
    @Override
    public int getCount() {
        return listaUsuarios.size();
    }

    @Override
    public Object getItem(int i) {
        return listaUsuarios.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;

        // Si la View es null se crea de nuevo
        if (view == null) {
            view = inflater.inflate(R.layout.activity_asignaturas, viewGroup);

            holder = new ViewHolder();
            holder.iconoUsuario = view.findViewById(R.id.ivUsuario);
            holder.username = view.findViewById(R.id.tvUsername);
            holder.email = view.findViewById(R.id.tvEmail);
            holder.rol = view.findViewById(R.id.tvRol);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        Usuario usuario = listaUsuarios.get(i);
        holder.iconoUsuario.setImageBitmap(usuario.getIconoUsuario());
        holder.username.setText(usuario.getUsername());
        holder.email.setText(usuario.getEmail());
        holder.rol.setText(usuario.getRol());
        return view;
    }
}
