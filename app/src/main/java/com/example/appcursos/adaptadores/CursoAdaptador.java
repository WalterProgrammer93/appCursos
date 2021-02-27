package com.example.appcursos.adaptadores;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class CursoAdaptador extends ArrayAdapter<Curso> {

    private ArrayList<Curso> listaCursos;
    private Context context;
    private int listItemResLayout;
    private Resources res;

    public CursoAdaptador(Context context, int resource, ArrayList<Curso> listaCursos) {
        super(context, resource, listaCursos);
        this.listaCursos = listaCursos;
        this.listItemResLayout = resource;
        this.context = context;
        this.res = context.getResources();
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
        return listaCursos.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);
            holder = new ViewHolder();
            //holder.iconoCursos = view.findViewById(R.id.ivCurso);
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
            final String nombre = curso.getNombreCurso();
            holder.nombreCurso.setText(nombre);
            final String centro = curso.getCentro();
            holder.centroCurso.setText(centro);
            final String disp = curso.getDisponibilidad();
            holder.disponibilidad.setText(disp);
            final String numA = curso.getNumeroAlumnos();
            holder.numeroAlumnos.setText(numA);
            final String modo = curso.getModos();
            holder.modos.setText(modo);
        }
        //holder.iconoCursos.setImageResource(R.drawable.ic_school_black_24dp);
        return view;
    }
}
