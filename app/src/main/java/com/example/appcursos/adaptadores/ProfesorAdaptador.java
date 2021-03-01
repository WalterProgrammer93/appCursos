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
import java.util.ArrayList;

public class ProfesorAdaptador extends RecyclerView.Adapter<ProfesorAdaptador.ViewHolder> implements View.OnClickListener {

    private ArrayList<Profesor> listaProfesores;
    private View.OnClickListener escuchador;

    public ProfesorAdaptador(ArrayList<Profesor> listaProfesores) {
        this.listaProfesores = listaProfesores;
    }

    @NonNull
    @Override
    public ProfesorAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_alumnos, parent, false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfesorAdaptador.ViewHolder holder, int position) {
        holder.tvNombreProfesor.setText((CharSequence) listaProfesores.get(position));
        holder.tvDepartamento.setText((CharSequence) listaProfesores.get(position));
        holder.tvAsignatura.setText((CharSequence) listaProfesores.get(position));
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
