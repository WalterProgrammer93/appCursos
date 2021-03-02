package com.example.appcursos.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ImageView;
import android.widget.ListView;
//import android.widget.TextView;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.adaptadores.PermisoAdaptador;
import com.example.appcursos.bd.PermisoBD;
import com.example.appcursos.modelos.Permiso;
import java.util.ArrayList;

public class PermisosActivity extends AppCompatActivity {

    ArrayList<Permiso> listaPermisos;
    PermisoAdaptador permisoAdaptador;
    PermisoBD pbd;
    /*ImageView ivPermisos;
    TextView tvIdAlumno, tvIdRol;*/
    ListView lvPermisos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_permisos);

        pbd = new PermisoBD(this);
        listaPermisos = new ArrayList<>();
        /*ivPermisos = findViewById(R.id.ivPermiso);
        tvIdAlumno = findViewById(R.id.tvIdAlumno);
        tvIdRol = findViewById(R.id.tvIdRol);*/
        lvPermisos = findViewById(R.id.lvPermisos);
        listaPermisos = pbd.listarPermisos();
        pbd.cerrarBD();
        permisoAdaptador = new PermisoAdaptador(this, R.layout.activity_permisos, listaPermisos);
        lvPermisos.setAdapter(permisoAdaptador);
        registerForContextMenu(lvPermisos);
        permisoAdaptador.notifyDataSetChanged();

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
                Intent intent = new Intent(PermisosActivity.this, NuevoPermisoActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(PermisosActivity.this, MainActivity.class);
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

        if (itemSeleccionado == R.id.action_editar) {// hacer algo
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.titulo)
                    .setMessage(R.string.lb_esta_seguro)
                    .setPositiveButton(R.string.lb_si,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Qué hacer si el usuario pulsa "Si"
                                    Intent editar = new Intent(PermisosActivity.this, NuevoPermisoActivity.class);
                                    startActivity(editar);
                                }
                            })
                    .setNegativeButton(R.string.lb_no,
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Qué hacer si el usuario pulsa "No"
                                    // En este caso se cierra directamente el diálogo y no se hace nada más
                                    dialog.dismiss();
                                }
                            });
            builder.create().show();
            return true;
        }
        return super.onContextItemSelected(item);
    }
}
