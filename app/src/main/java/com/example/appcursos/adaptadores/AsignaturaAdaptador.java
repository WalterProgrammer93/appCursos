package com.example.appcursos.adaptadores;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Asignatura;
import java.util.ArrayList;

public class AsignaturaAdaptador extends ArrayAdapter<Asignatura> {

    private ArrayList<Asignatura> listaAsignaturas;
    private Context context;
    private int viewRes;
    private Resources res;

    public AsignaturaAdaptador(Context context, int textViewResourceId, ArrayList<Asignatura> listaAsignaturas) {
        super(context, textViewResourceId, listaAsignaturas);
        this.listaAsignaturas = listaAsignaturas;
        this.context = context;
        this.viewRes = textViewResourceId;
        this.res = context.getResources();
    }

    static class ViewHolder {
        ImageView iconoAsignatura;
        TextView nombreAsignatura;
        TextView curso;
    }

    @Override
    public int getCount() {
        return listaAsignaturas.size();
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
            holder = new ViewHolder();
            holder.iconoAsignatura = view.findViewById(R.id.ivAsignatura);
            holder.nombreAsignatura = view.findViewById(R.id.tvNombreAsignatura);
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

        final Asignatura asignatura = listaAsignaturas.get(i);
        if (asignatura != null) {
            holder.iconoAsignatura.setImageBitmap(asignatura.getIconoAsignatura());
            holder.nombreAsignatura.setText(asignatura.getNombreAsignatura());
            holder.curso.setText(asignatura.getCurso());
        }

        return view;
    }
}
