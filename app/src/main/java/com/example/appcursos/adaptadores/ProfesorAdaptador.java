package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appcursos.R;
import com.example.appcursos.modelos.Profesor;
import java.util.ArrayList;

public class ProfesorAdaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Profesor> listaProfesores;
    private LayoutInflater inflater;

    public ProfesorAdaptador(Context context, ArrayList<Profesor> listaProfesores) {
        this.context = context;
        this.listaProfesores = listaProfesores;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView iconoProfesor;
        TextView nombreProfesor;
        TextView apellidosProfesor;
        TextView departamento;
        TextView telefonoProfesor;
        TextView asignatura;
    }

    @Override
    public int getCount() {
        return listaProfesores.size();
    }

    @Override
    public Object getItem(int i) {
        return listaProfesores.get(i);
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
            view = inflater.inflate(R.layout.activity_profesores, viewGroup);

            holder = new ViewHolder();
            holder.iconoProfesor = view.findViewById(R.id.ivProfesor);
            holder.nombreProfesor = view.findViewById(R.id.tvNombreProfesor);
            holder.apellidosProfesor = view.findViewById(R.id.tvApellidosProfesor);
            holder.departamento = view.findViewById(R.id.tvDepartamento);
            holder.telefonoProfesor = view.findViewById(R.id.tvTelefonoProfesor);
            holder.asignatura = view.findViewById(R.id.tvAsignatura);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        Profesor profesor = listaProfesores.get(i);
        holder.iconoProfesor.setImageBitmap(profesor.getIconoProfesor());
        holder.nombreProfesor.setText(profesor.getNombreProfesor());
        holder.apellidosProfesor.setText(profesor.getApellidosProfesor());
        holder.departamento.setText(profesor.getDepartamento());
        holder.telefonoProfesor.setText(profesor.getTelefonoProfesor());
        holder.asignatura.setText(profesor.getAsignatura().getAsignaturaId());
        return view;
    }
}
