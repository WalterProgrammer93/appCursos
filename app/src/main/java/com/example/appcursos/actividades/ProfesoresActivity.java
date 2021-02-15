package com.example.appcursos.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.adaptadores.ProfesorAdaptador;
import java.util.ArrayList;
import java.util.List;

public class ProfesoresActivity extends AppCompatActivity {

    List<String> listaProfesores;
    ProfesorAdaptador profesorAdaptador;
    RecyclerView rvProfesores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profesores);

        listaProfesores = new ArrayList<>();
        profesorAdaptador = new ProfesorAdaptador(this, listaProfesores);
        rvProfesores = findViewById(R.id.rvProfesores);
        rvProfesores.setAdapter(profesorAdaptador);
        registerForContextMenu(rvProfesores);
        profesorAdaptador.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nuevo:
                Intent nuevo_profesor = new Intent(ProfesoresActivity.this, NuevoProfesorActivity.class);
                startActivity(nuevo_profesor);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "LogOut Successful", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int itemSeleccionado = info.position;

        switch (itemSeleccionado) {
            case R.id.action_editar:
                // hacer algo
                return true;
            case R.id.action_eliminar:
                // hacer algo
                return true;
            case R.id.action_detalles:
                // hacer algo
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
