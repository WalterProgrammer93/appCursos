package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Asignatura;
import java.util.ArrayList;

public class AsignaturaAdaptador extends BaseAdapter {

    private Context context;
    private ArrayList<Asignatura> listaAsignaturas;
    private LayoutInflater inflater;

    public AsignaturaAdaptador(Context context, ArrayList<Asignatura> listaAsignaturas) {
        this.context = context;
        this.listaAsignaturas = listaAsignaturas;
        inflater = LayoutInflater.from(context);
    }

    static class ViewHolder {
        ImageView iconoAsignatura;
        TextView nombreAsignatura;
        TextView descripcionAsignatura;
        TextView curso;
    }

    @Override
    public int getCount() {
        return listaAsignaturas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaAsignaturas.get(i);
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
            holder.iconoAsignatura = view.findViewById(R.id.ivAsignatura);
            holder.nombreAsignatura = view.findViewById(R.id.tvNombreAsignatura);
            holder.descripcionAsignatura = view.findViewById(R.id.tvDescripcionAsignatura);
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

        Asignatura asignatura = listaAsignaturas.get(i);
        holder.iconoAsignatura.setImageBitmap(asignatura.getIconoAsignatura());
        holder.nombreAsignatura.setText(asignatura.getNombreAsignatura());
        holder.descripcionAsignatura.setText(asignatura.getDescripcionAsignatura());
        holder.curso.setText(asignatura.getCurso().getCursoId());
        return view;
    }
}
