package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcursos.R;
import java.util.List;

public class ProfesorAdaptador extends RecyclerView.Adapter<ProfesorAdaptador.ViewHolder> {

    private List<String> listaProfesores;
    private LayoutInflater mInflater;

    public ProfesorAdaptador(Context context, List<String> lista) {
        this.mInflater = LayoutInflater.from(context);
        this.listaProfesores = lista;
    }

    @NonNull
    @Override
    public ProfesorAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_profesores, parent, false);
        return new ProfesorAdaptador.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfesorAdaptador.ViewHolder holder, int position) {
        String coleccionProfesores = listaProfesores.get(position);
        holder.tvNombreProfesor.setText(coleccionProfesores);
        holder.tvDepartamento.setText(coleccionProfesores);
        holder.tvAsignatura.setText(coleccionProfesores);
    }

    @Override
    public int getItemCount() {
        return listaProfesores.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProfesor;
        TextView tvNombreProfesor, tvDepartamento, tvAsignatura;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProfesor = itemView.findViewById(R.id.ivProfesor);
            tvNombreProfesor = itemView.findViewById(R.id.tvNombreProfesor);
            tvDepartamento = itemView.findViewById(R.id.tvDepartamento);
            tvAsignatura = itemView.findViewById(R.id.tvAsignatura);
        }
    }
}
