package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Profesor;
import java.util.List;

public class ProfesorAdaptador extends ArrayAdapter<Profesor> {

    private List<Profesor> listaProfesores;
    private int listItemResLayout;
    private Context context;

    public ProfesorAdaptador(Context context, int resource, List<Profesor> listaProfesores) {
        super(context, resource, listaProfesores);
        this.listaProfesores = listaProfesores;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoProfesor;
        TextView nombreProfesor;
        TextView departamento;
        TextView asignatura;
    }

    @Override
    public int getCount() {
        if (listaProfesores != null) {
            return listaProfesores.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);
            holder = new ViewHolder();
            holder.iconoProfesor = view.findViewById(R.id.ivProfesor);
            holder.nombreProfesor = view.findViewById(R.id.tvNombreProfesor);
            holder.departamento = view.findViewById(R.id.tvDepartamento);
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

        final Profesor profesor = listaProfesores.get(position);
        if (profesor != null) {
            holder.iconoProfesor.setImageResource(R.drawable.ic_person_black_24dp);
            holder.nombreProfesor.setText(profesor.getNombreProfesor());
            holder.departamento.setText(profesor.getDepartamento());
            holder.asignatura.setText(profesor.getAsignatura());
        }
        return view;
    }
}
