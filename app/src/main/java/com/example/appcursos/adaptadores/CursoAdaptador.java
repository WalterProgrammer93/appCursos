package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import com.bumptech.glide.Glide;
import com.bumptech.glide.Glide;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Curso;
import java.util.List;

public class CursoAdaptador extends ArrayAdapter<Curso> {

    private List<Curso> listaCursos;
    private int listItemResLayout;
    private Context context;

    public CursoAdaptador(@NonNull Context context, int resource, @NonNull List<Curso> listaCursos) {
        super(context, resource, listaCursos);
        this.listaCursos = listaCursos;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoCursos;
        TextView nombreCurso;
        TextView centroCurso;
        TextView disponibilidad;
        TextView numeroAlumnos;
        TextView modos;
    }

    @Override
    public int getCount() {
        if (listaCursos != null)
            return listaCursos.size();
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);
            holder = new ViewHolder();
            holder.iconoCursos = view.findViewById(R.id.ivCurso);
            holder.nombreCurso = view.findViewById(R.id.tvNombreCurso);
            holder.centroCurso = view.findViewById(R.id.tvCentroCurso);
            holder.disponibilidad = view.findViewById(R.id.tvDisponibilidad);
            holder.numeroAlumnos = view.findViewById(R.id.tvNumeroAlumnos);
            holder.modos = view.findViewById(R.id.tvModos);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        final Curso curso = listaCursos.get(position);
        if (curso != null) {
            //Glide.with(getContext()).load(curso.getIconoCurso()).into(holder.iconoCursos);
            holder.iconoCursos.setImageResource(R.drawable.ic_school_black_24dp);
            holder.nombreCurso.setText(curso.getNombreCurso());
            holder.centroCurso.setText(curso.getCentro());
            holder.disponibilidad.setText(curso.getDisponibilidad());
            holder.numeroAlumnos.setText(curso.getNumeroAlumnos());
            holder.modos.setText(curso.getModos());
        }
        return view;
    }
}
