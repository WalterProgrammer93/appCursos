package com.example.appcursos.adaptadores;

import android.content.Context;
import android.content.res.Resources;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class CursoAdaptador extends ArrayAdapter<Curso> {

    private ArrayList<Curso> listaCursos;
    private Context context;
    private LayoutInflater inflater;
    private int viewRes;
    private Resources res;

    public CursoAdaptador(Context context, int textViewResourcesId, ArrayList<Curso> lista) {
        super(context, textViewResourcesId, lista);
        this.listaCursos = lista;
        this.context = context;
        this.viewRes = textViewResourcesId;
        this.res = context.getResources();
    }

    static class ViewHolder {
        ImageView iconoCursos;
        TextView nombreCurso;
        TextView centro;
        TextView disponibilidad;
        TextView numeroAlumnos;
        TextView modos;
    }

    @Override
    public int getCount() {
        return listaCursos.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view = convertView;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
            holder = new ViewHolder();
            holder.iconoCursos = view.findViewById(R.id.ivCurso);
            holder.nombreCurso = view.findViewById(R.id.tvNombreCurso);
            holder.disponibilidad = view.findViewById(R.id.tvDisponibilidad);
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

        Curso curso = listaCursos.get(i);
        holder.iconoCursos.setImageResource(R.drawable.ic_school_black_24dp);
        holder.nombreCurso.setText(curso.getNombreCurso());
        holder.centro.setText(curso.getCentro());
        holder.disponibilidad.setText(curso.getDisponibilidad());
        holder.numeroAlumnos.setText(curso.getNumeroAlumnos());
        holder.modos.setText(curso.getModos());
        return view;
    }
}
