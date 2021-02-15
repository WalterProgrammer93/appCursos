package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

        listaRoles = new ArrayList<>();
        rolAdaptador = new RolAdaptador(this, listaRoles);
        lvRoles = findViewById(R.id.lvRoles);
        lvRoles.setAdapter(rolAdaptador);
        registerForContextMenu(lvRoles);
        rolAdaptador.notifyDataSetChanged();
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
                Toast.makeText(getApplicationContext(), "LogOut Successful", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
