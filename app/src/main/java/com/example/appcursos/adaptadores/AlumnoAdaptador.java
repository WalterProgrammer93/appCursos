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
import com.example.appcursos.modelos.Alumno;
import java.util.List;

public class AlumnoAdaptador extends ArrayAdapter<Alumno> {

    private List<Alumno> listaAlumnos;
    private int listItemResLayout;
    private Context context;

    public AlumnoAdaptador(Context context, int resource, List<Alumno> listaAlumnos) {
        super(context, resource, listaAlumnos);
        this.listaAlumnos = listaAlumnos;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoAlumno;
        TextView nombreAlumno;
        TextView apellidosAlumno;
        TextView curso;
    }

    @Override
    public int getCount() {
        if (listaAlumnos != null) {
            return listaAlumnos.size();
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
            holder.iconoAlumno = view.findViewById(R.id.ivAlumno);
            holder.nombreAlumno = view.findViewById(R.id.tvNombreAlumno);
            holder.apellidosAlumno = view.findViewById(R.id.tvApellidosAlumno);
            holder.curso = view.findViewById(R.id.tvCurso);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        final Alumno alumno = listaAlumnos.get(position);
        if (alumno != null) {
            holder.iconoAlumno.setImageResource(R.drawable.ic_person_black_24dp);
            holder.nombreAlumno.setText(alumno.getNombreAlumno());
            holder.apellidosAlumno.setText(alumno.getApellidosAlumno());
            holder.curso.setText(alumno.getCurso());
        }
        return view;
    }


}