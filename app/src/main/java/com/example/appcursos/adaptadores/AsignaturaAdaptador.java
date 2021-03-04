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
import com.example.appcursos.modelos.Asignatura;
import java.util.List;

public class AsignaturaAdaptador extends ArrayAdapter<Asignatura> {

    private List<Asignatura> listaAsignaturas;
    private int listItemResLayout;
    private Context context;

    public AsignaturaAdaptador(Context context, int resource, List<Asignatura> listaAsignaturas) {
        super(context, resource, listaAsignaturas);
        this.listaAsignaturas = listaAsignaturas;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoAsignatura;
        TextView nombreAsignatura;
        TextView curso;
    }

    @Override
    public int getCount() {
        if (listaAsignaturas != null) {
            return listaAsignaturas.size();
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

        final Asignatura asignatura = listaAsignaturas.get(position);
        if (asignatura != null) {
            holder.iconoAsignatura.setImageResource(R.drawable.ic_assignment_black_24dp);
            holder.nombreAsignatura.setText(asignatura.getNombreAsignatura());
            holder.curso.setText(asignatura.getCurso());
        }
        return view;
    }
}
