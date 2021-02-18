package com.example.appcursos.adaptadores;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Permiso;
import java.util.ArrayList;

public class PermisoAdaptador extends ArrayAdapter<Permiso> {

    private ArrayList<Permiso> listaPermisos;
    private Context context;
    private int viewRes;
    private Resources res;

    public PermisoAdaptador(Context context, int textViewResourcesId, ArrayList<Permiso> lista) {
        super(context, textViewResourcesId, lista);
        this.listaPermisos = lista;
        this.context = context;
        this.viewRes = textViewResourcesId;
        this.res = context.getResources();
    }

    static class ViewHolder {
        ImageView iconoPermisos;
        TextView idAlumno;
        TextView idRol;
    }

    @Override
    public int getCount() {
        return listaPermisos.size();
    }

    @NonNull
    @Override
    public View getView(int i, View convertView, @NonNull ViewGroup parent) {
        PermisoAdaptador.ViewHolder holder;
        View view = convertView;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(viewRes, parent, false);
            holder = new PermisoAdaptador.ViewHolder();
            holder.iconoPermisos = view.findViewById(R.id.ivPermiso);
            holder.idAlumno = view.findViewById(R.id.tvIdAlumno);
            holder.idRol = view.findViewById(R.id.tvIdRol);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (PermisoAdaptador.ViewHolder) view.getTag();
        }

        Permiso permiso = listaPermisos.get(i);
        holder.iconoPermisos.setImageResource(R.drawable.ic_school_black_24dp);
        holder.idAlumno.setText(permiso.getUsuario());
        holder.idRol.setText(permiso.getRol());
        return view;
    }
}
