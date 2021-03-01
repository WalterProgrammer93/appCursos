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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.adaptadores.RolAdaptador;
import com.example.appcursos.bd.RolBD;
import com.example.appcursos.modelos.Rol;
import java.util.ArrayList;

public class RolesActivity extends AppCompatActivity {

    ArrayList<Rol> listaRoles;
    RolAdaptador rolAdaptador;
    /*ImageView ivRol;
    TextView tvNombreRol, tvDescripcionRol;*/
    ListView lvRoles;
    RolBD rolbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_roles);

        rolbd = new RolBD(this);
        listaRoles = new ArrayList<>();
        /*ivRol = findViewById(R.id.ivRol);
        tvNombreRol = findViewById(R.id.tvNombreRol);
        tvDescripcionRol = findViewById(R.id.tvDescripcionRol);*/
        lvRoles = findViewById(R.id.lvRoles);
        listaRoles = rolbd.listarRoles();
        rolbd.cerrarBD();
        rolAdaptador = new RolAdaptador(this, R.layout.activity_roles, listaRoles);
        lvRoles.setAdapter(rolAdaptador);
        //registerForContextMenu(lvRoles);
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
                Intent intent = new Intent(RolesActivity.this, NuevoRolActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(RolesActivity.this, MainActivity.class);
                startActivity(i);
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
