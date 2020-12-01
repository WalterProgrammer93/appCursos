package com.example.appcursos.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.adaptadores.AsignaturaAdaptador;
import com.example.appcursos.modelos.Asignatura;
import java.util.ArrayList;

public class AsignaturasActivity extends AppCompatActivity {

    ArrayList<Asignatura> listaAsignaturas;
    AsignaturaAdaptador asignaturaAdaptador;
    ListView lvAsignaturas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        listaAsignaturas = new ArrayList<>();
        asignaturaAdaptador = new AsignaturaAdaptador(this, listaAsignaturas);
        lvAsignaturas = findViewById(R.id.lvAsignaturas);
        lvAsignaturas.setAdapter(asignaturaAdaptador);
        registerForContextMenu(lvAsignaturas);
        asignaturaAdaptador.notifyDataSetChanged();
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
                Intent nuevo_asignatura = new Intent(AsignaturasActivity.this, NuevoAsignaturaActivity.class);
                startActivity(nuevo_asignatura);
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

        switch (item.getItemId()) {
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
