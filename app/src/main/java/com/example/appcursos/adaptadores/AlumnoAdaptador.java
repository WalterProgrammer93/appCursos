package com.example.appcursos.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Alumno;
import java.util.ArrayList;

public class AlumnoAdaptador extends RecyclerView.Adapter<AlumnoAdaptador.ViewHolder> implements View.OnClickListener {

    private ArrayList<Alumno> listaAlumnos;
    private View.OnClickListener escuchador;

    public AlumnoAdaptador(ArrayList<Alumno> listaAlumnos) {
        this.listaAlumnos = listaAlumnos;
    }

    @NonNull
    @Override
    public AlumnoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_alumnos, parent, false);
        vista.setOnClickListener(this);
        return new ViewHolder(vista);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoAdaptador.ViewHolder holder, int position) {
        holder.tvNombreAlumno.setText((CharSequence) listaAlumnos.get(position));
        holder.tvApellidosAlumno.setText((CharSequence) listaAlumnos.get(position));
        holder.tvAsignatura.setText((CharSequence) listaAlumnos.get(position));
    }

    @Override
    public int getItemCount() {
        if (listaAlumnos != null)
        {
            return listaAlumnos.size();
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
        ImageView ivAlumno;
        TextView tvNombreAlumno, tvApellidosAlumno, tvAsignatura;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAlumno = itemView.findViewById(R.id.ivAlumno);
            tvNombreAlumno = itemView.findViewById(R.id.tvNombreAlumno);
            tvApellidosAlumno = itemView.findViewById(R.id.tvApellidosAlumno);
            tvAsignatura = itemView.findViewById(R.id.tvAsignatura);
        }
    }
}
