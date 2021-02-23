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
import com.example.appcursos.modelos.Permiso;
import java.util.List;

public class PermisoAdaptador extends ArrayAdapter<Permiso> {

    private List<Permiso> listaPermisos;
    private Context context;
    private int listItemResLayout;

    public PermisoAdaptador(@NonNull Context context, int resource, @NonNull List<Permiso> listaPermisos) {
        super(context, resource, listaPermisos);
        this.listaPermisos = listaPermisos;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoPermiso;
        TextView usuarioId;
        TextView rolId;
    }

    @Override
    public int getCount() {
        if (listaPermisos != null) {
            return listaPermisos.size();
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
            holder.iconoPermiso = view.findViewById(R.id.ivPermiso);
            holder.usuarioId = view.findViewById(R.id.tvIdAlumno);
            holder.rolId = view.findViewById(R.id.tvIdRol);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (PermisoAdaptador.ViewHolder) view.getTag();
        }

        Permiso permiso = listaPermisos.get(position);
        holder.iconoPermiso.setImageResource(R.drawable.ic_school_black_24dp);
        holder.usuarioId.setText(permiso.getUsuario());
        holder.rolId.setText(permiso.getRol());
        return view;
    }
}
