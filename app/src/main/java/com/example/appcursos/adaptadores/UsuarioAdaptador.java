package com.example.appcursos.adaptadores;

//import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
//import com.bumptech.glide.Glide;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Usuario;
import java.util.List;

public class UsuarioAdaptador extends RecyclerView.Adapter<UsuarioAdaptador.ViewHolder> implements View.OnClickListener {

    private List<Usuario> listaUsuarios;
    //private Context context;
    private View.OnClickListener escuchador;

    public UsuarioAdaptador(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    @NonNull
    @Override
    public UsuarioAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_usuarios, parent, false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull UsuarioAdaptador.ViewHolder holder, int position) {
        final Usuario usuario = listaUsuarios.get(position);
        holder.ivUsuario.setImageResource(R.drawable.ic_person_black_24dp);
        holder.tvUserName.setText(usuario.getUsername());
        holder.tvEmail.setText(usuario.getEmail());
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
