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
import com.example.appcursos.modelos.Curso;
import java.util.List;

public class CursoAdaptador extends ArrayAdapter<Curso> {

    private List<Curso> listaCursos;
    private Context context;
    private int listItemResLayout;

    public CursoAdaptador(Context context, int resource, List<Curso> listaCursos) {
        super(context, resource, listaCursos);
        this.listaCursos = listaCursos;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoCursos;
        TextView nombreCurso;
        TextView disponibilidad;
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

        Curso curso = listaCursos.get(position);
        holder.iconoCursos.setImageResource(R.drawable.ic_school_black_24dp);
        holder.nombreCurso.setText(curso.getNombreCurso());
        holder.disponibilidad.setText(curso.getDisponibilidad());
        holder.modos.setText(curso.getModos());
        return view;
    }
}
