package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class CursoAdaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Curso> listaCursos;
    private LayoutInflater inflater;

    public CursoAdaptador(Context context, ArrayList<Curso> listaCursos) {
        this.context = context;
        this.listaCursos = listaCursos;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView iconoCursos;
        TextView nombreCurso;
        TextView centro;
        TextView numeroAlumnos;
        TextView disponibilidad;
        TextView temas;
    }

    @Override
    public int getCount() {
        return listaCursos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaCursos.get(i);
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
            view = inflater.inflate(R.layout.activity_cursos, viewGroup);

            holder = new ViewHolder();
            holder.iconoCursos = view.findViewById(R.id.ivCurso);
            holder.nombreCurso = view.findViewById(R.id.tvNombreCurso);
            holder.centro = view.findViewById(R.id.tvCentro);
            holder.numeroAlumnos = view.findViewById(R.id.tvNumeroAlumnos);
            holder.disponibilidad = view.findViewById(R.id.tvDisponibilidad);
            holder.temas = view.findViewById(R.id.tvTemas);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        Curso curso = listaCursos.get(i);
        holder.iconoCursos.setImageBitmap(curso.getFoto());
        holder.nombreCurso.setText(curso.getNombreCurso());
        holder.centro.setText(curso.getCentro());
        holder.numeroAlumnos.setText(curso.getNumeroAlumnos());
        holder.disponibilidad.setText(curso.getDisponibilidad());
        holder.temas.setText(curso.getTemas());

        return view;
    }
}
