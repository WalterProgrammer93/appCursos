package com.example.appcursos.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Profesor;
import java.util.List;

public class ProfesorAdaptador extends RecyclerView.Adapter<ProfesorAdaptador.ViewHolder> implements View.OnClickListener {

    private List<Profesor> listaProfesores;
    private View.OnClickListener escuchador;

    public ProfesorAdaptador(List<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    @NonNull
    @Override
    public ProfesorAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_profesores, parent, false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfesorAdaptador.ViewHolder holder, int position) {
        final Profesor profesor = listaProfesores.get(position);
        holder.ivProfesor.setImageResource(R.drawable.ic_person_black_24dp);
        holder.tvNombreProfesor.setText(profesor.getNombreProfesor());
        holder.tvDepartamento.setText(profesor.getDepartamento());
        holder.tvAsignatura.setText(profesor.getAsignatura());
    }

    @Override
    public int getItemCount() {
        if (listaProfesores != null)
        {
            return listaProfesores.size();
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
        ImageView ivProfesor;
        TextView tvNombreProfesor, tvDepartamento, tvAsignatura;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfesor = itemView.findViewById(R.id.ivProfesor);
            tvNombreProfesor = itemView.findViewById(R.id.tvNombreProfesor);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            tvAsignatura = itemView.findViewById(R.id.tvAsignatura);
        }
    }
}
