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
import com.example.appcursos.adaptadores.RolAdaptador;
import com.example.appcursos.modelos.Rol;
import java.util.ArrayList;

public class RolesActivity extends AppCompatActivity {

    ArrayList<Rol> listaRoles;
    RolAdaptador rolAdaptador;
    ListView lvRoles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roles);

        /*listaRoles = new ArrayList<>();
        rolAdaptador = new RolAdaptador(this, listaRoles);
        lvRoles = findViewById(R.id.lvRoles);
        lvRoles.setAdapter(rolAdaptador);
        registerForContextMenu(lvRoles);
        rolAdaptador.notifyDataSetChanged();*/
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
                Intent nuevo_rol = new Intent(RolesActivity.this, NuevoRolActivity.class);
                startActivity(nuevo_rol);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
                Intent salir = new Intent(RolesActivity.this, MainActivity.class);
                startActivity(salir);
                return true;
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
            case R.id.action_buscar:
                // hacer algo
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
