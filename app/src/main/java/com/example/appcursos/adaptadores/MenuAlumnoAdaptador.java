package com.example.appcursos.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.appcursos.R;
import java.util.List;

public class MenuAlumnoAdaptador extends RecyclerView.Adapter<MenuAlumnoAdaptador.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private MenuAlumnoAdaptador.ItemClickListener mClickListener;

    public MenuAlumnoAdaptador(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }
    @NonNull
    @Override
    public MenuAlumnoAdaptador.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.activity_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuAlumnoAdaptador.ViewHolder holder, int position) {
        String menu = mData.get(position);
        holder.opcion.setText(menu);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder /*implements View.OnClickListener*/ {
        TextView opcion;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            opcion = itemView.findViewById(R.id.opcion);
            //itemView.setOnClickListener(this);
        }

        /*@Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }*/
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    public void setClickListener(AdapterView.OnItemClickListener itemClickListener) {
        this.mClickListener = (ItemClickListener) itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}
