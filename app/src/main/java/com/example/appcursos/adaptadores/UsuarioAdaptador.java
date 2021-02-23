package com.example.appcursos.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Usuario;
import java.util.ArrayList;

public class UsuarioAdaptador extends RecyclerView.Adapter<UsuarioAdaptador.ViewHolder> implements View.OnClickListener {

    private ArrayList<Usuario> listaUsuarios;
    private View.OnClickListener escuchador;

    public UsuarioAdaptador(ArrayList<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios, parent, false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdaptador.ViewHolder holder, int position) {
        holder.tvUserName.setText((CharSequence) listaUsuarios.get(position));
        holder.tvEmail.setText((CharSequence) listaUsuarios.get(position));
    }

    @Override
    public int getItemCount() {
        if (listaUsuarios != null)
        {
            return listaUsuarios.size();
        }
        return 0;
    }

    public void setOnClickListener(View.OnClickListener escucha) {
        this.escuchador = escucha;
    }

    @Override
    public void onClick(View view) {
        if (escuchador != null)
            escuchador.onClick(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivUsuario;
        TextView tvUserName, tvEmail;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivUsuario = itemView.findViewById(R.id.ivUsuario);
            tvUserName = itemView.findViewById(R.id.tvUsername);
            tvEmail = itemView.findViewById(R.id.tvEmail);
        }
    }
}
