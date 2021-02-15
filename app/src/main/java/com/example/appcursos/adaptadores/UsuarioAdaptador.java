package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcursos.R;
import java.util.List;

public class UsuarioAdaptador extends RecyclerView.Adapter<UsuarioAdaptador.ViewHolder> {

    private List<String> listaProfesores;
    private LayoutInflater mInflater;

    public UsuarioAdaptador(Context context, List<String> lista) {
        this.mInflater = LayoutInflater.from(context);
        this.listaProfesores = lista;
    }

    @NonNull
    @Override
    public UsuarioAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_usuarios, parent, false);
        return new UsuarioAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdaptador.ViewHolder holder, int position) {
        String coleccionUsuarios = listaProfesores.get(position);
        holder.tvUserName.setText(coleccionUsuarios);
        holder.tvEmail.setText(coleccionUsuarios);
    }

    @Override
    public int getItemCount() {
        return listaProfesores.size();
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
