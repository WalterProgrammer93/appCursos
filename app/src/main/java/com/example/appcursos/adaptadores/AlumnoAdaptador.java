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

public class AlumnoAdaptador extends RecyclerView.Adapter<AlumnoAdaptador.ViewHolder> {

    private List<String> listaAlumnos;
    private LayoutInflater mInflater;

    public AlumnoAdaptador(Context context, List<String> lista) {
        this.mInflater = LayoutInflater.from(context);
        this.listaAlumnos = lista;
    }

    @NonNull
    @Override
    public AlumnoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_alumnos, parent, false);
        return new AlumnoAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoAdaptador.ViewHolder holder, int position) {
        String coleccionAlumnos = listaAlumnos.get(position);
        holder.tvNombreAlumno.setText(coleccionAlumnos);
        holder.tvApellidosAlumno.setText(coleccionAlumnos);
        holder.tvAsignatura.setText(coleccionAlumnos);
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAlumno;
        TextView tvNombreAlumno, tvApellidosAlumno, tvAsignatura;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivAlumno = itemView.findViewById(R.id.ivAlumno);
            tvNombreAlumno = itemView.findViewById(R.id.tvNombreAlumno);
            tvApellidosAlumno = itemView.findViewById(R.id.tvApellidosAlumno);
            tvAsignatura = itemView.findViewById(R.id.tvAsignatura);
        }
    }
}
