package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Alumno;
import java.util.ArrayList;

public class AlumnoAdaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Alumno> listaAlumnos;
    private LayoutInflater inflater;

    public AlumnoAdaptador(Context context, ArrayList<Alumno> listaAlumnos) {
        this.context = context;
        this.listaAlumnos = listaAlumnos;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView iconoAlumno;
        TextView nombreAlumno;
        TextView apellidosAlumno;
        TextView dni;
        TextView telefonoAlumno;
        TextView asignatura;
    }

    @Override
    public int getCount() {
        return listaAlumnos.size();
    }

    @Override
    public Object getItem(int i) {
        return listaAlumnos.get(i);
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
            view = inflater.inflate(R.layout.activity_asignaturas, viewGroup);

            holder = new ViewHolder();
            holder.iconoAlumno = view.findViewById(R.id.ivAlumno);
            holder.nombreAlumno = view.findViewById(R.id.tvNombreAlumno);
            holder.apellidosAlumno = view.findViewById(R.id.tvApellidosAlumno);
            holder.dni = view.findViewById(R.id.tvDni);
            holder.telefonoAlumno = view.findViewById(R.id.tvTelefonoAlumno);
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

        Alumno alumno = listaAlumnos.get(i);
        holder.iconoAlumno.setImageBitmap(alumno.getIconoAlumno());
        holder.nombreAlumno.setText(alumno.getNombreAlumno());
        holder.apellidosAlumno.setText(alumno.getApellidosAlumno());
        holder.dni.setText(alumno.getDni());
        holder.telefonoAlumno.setText(alumno.getTelefonoAlumno());
        holder.asignatura.setText(alumno.getAsignatura().getAsignaturaId());
        return view;
    }
}
