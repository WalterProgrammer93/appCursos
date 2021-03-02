package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
//import com.bumptech.glide.Glide;
import com.example.appcursos.R;
import com.example.appcursos.modelos.Rol;
import java.util.List;

public class RolAdaptador extends ArrayAdapter<Rol> {

    private List<Rol> listaRoles;
    private int listItemResLayout;
    private Context context;

    public RolAdaptador(@NonNull Context context, int resource, @NonNull List<Rol> listaRoles) {
        super(context, resource, listaRoles);
        this.listaRoles = listaRoles;
        this.listItemResLayout = resource;
        this.context = context;
    }

    static class ViewHolder {
        ImageView iconoRol;
        TextView nombreRol;
        TextView descripcionRol;
    }

    @Override
    public int getCount() {
        if (listaRoles != null) {
            return listaRoles.size();
        }
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;
        // Si la View es null se crea de nuevo
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(listItemResLayout, parent, false);
            holder = new ViewHolder();
            holder.iconoRol = view.findViewById(R.id.ivRol);
            holder.nombreRol = view.findViewById(R.id.tvNombreRol);
            holder.descripcionRol = view.findViewById(R.id.tvDescripcionRol);
            view.setTag(holder);
        }
        /*
         * En caso de que la View no sea null se reutilizará con los
         * nuevos valores
         */
        else {
            holder = (ViewHolder) view.getTag();
        }

        final Rol rol = listaRoles.get(position);
        if (rol != null) {
            //Glide.with(getContext()).load(rol.getIconoRol()).into(holder.iconoRol);
            holder.iconoRol.setImageResource(R.drawable.ic_person_black_24dp);
            holder.nombreRol.setText(rol.getNombreRol());
            holder.descripcionRol.setText(rol.getDescripcionRol());
        }
        return view;
    }
}
