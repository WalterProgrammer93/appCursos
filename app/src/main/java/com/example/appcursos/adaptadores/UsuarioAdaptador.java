package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
//import com.bumptech.glide.Glide;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Usuario;
import java.util.List;

public class UsuarioAdaptador extends ArrayAdapter<Usuario> {

    private List<Usuario> listaUsuarios;
    private int listItemResLayout;
    private Context context;

    public UsuarioAdaptador(Context context, int resource, List<Usuario> listaUsuarios) {
        super(context, resource, listaUsuarios);
        this.listaUsuarios = listaUsuarios;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoUsuario;
        TextView username;
        TextView email;
    }

    @Override
    public int getCount() {
        if (listaUsuarios != null) {
            return listaUsuarios.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);
            holder = new ViewHolder();
            holder.iconoUsuario = view.findViewById(R.id.ivUsuario);
            holder.username = view.findViewById(R.id.tvUsername);
            holder.email = view.findViewById(R.id.tvEmail);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        final Usuario usuario = listaUsuarios.get(position);
        if (usuario != null) {
            holder.iconoUsuario.setImageResource(R.drawable.ic_account_circle_black_24dp);
            holder.username.setText(usuario.getUsername());
            holder.email.setText(usuario.getEmail());
        }
        return view;
    }
}
