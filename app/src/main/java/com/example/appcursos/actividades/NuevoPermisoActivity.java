package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.PermisoBD;
import com.example.appcursos.modelos.Permiso;
import java.util.ArrayList;

public class NuevoPermisoActivity extends AppCompatActivity {

    Spinner spin_alumno, spin_rol;
    Button altaPermiso, editarPermiso, eliminarPermiso, buscarPermiso, cancelarPermiso;
    ArrayList<String> load_curso, load_rol;
    ArrayAdapter<String> selectUsuario, selectRol;
    PermisoBD pbd;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_permiso);

        pbd = new PermisoBD(this);
        load_curso = new ArrayList<>();
        load_rol = new ArrayList<>();
        pbd.escribirBD();
        spin_alumno = findViewById(R.id.spinner_idusuario);
        spin_rol = findViewById(R.id.spinner_idusuario);
        load_curso.add(String.valueOf(pbd.cargarUsuarios()));
        load_rol.add(String.valueOf(pbd.cargarRoles()));
        selectUsuario = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, load_curso);
        selectRol = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, load_rol);
        spin_alumno.setAdapter(selectUsuario);
        spin_rol.setAdapter(selectRol);
        altaPermiso = findViewById(R.id.altaPermiso);
        cancelarPermiso = findViewById(R.id.cancelarPermiso);

        altaPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alumnoId = spin_alumno.getSelectedItem().toString();
                String rolId = spin_rol.getSelectedItem().toString();
                Permiso permiso = new Permiso(alumnoId, rolId);
                if (!pbd.isPermisoExists(permiso.getUsuario(), permiso.getRol())) {
                    pbd.insertarPermiso(permiso);
                    spin_alumno.setSelected(false);
                    spin_alumno.setSelected(false);
                    Toast.makeText(NuevoPermisoActivity.this, "El permiso se ha creado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoPermisoActivity.this, PermisosActivity.class);
                    startActivity(intent);
                }
            }
        });
        editarPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alumnoId = spin_alumno.getSelectedItem().toString();
                String rolId = spin_rol.getSelectedItem().toString();
                Permiso permiso = new Permiso(alumnoId, rolId);
                int cant = pbd.editarPermiso(alumnoId, permiso);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se modificaron los datos del permiso", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(NuevoPermisoActivity.this, PermisosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el permiso",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        eliminarPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usuarioId = spin_alumno.getSelectedItem().toString();
                int cant = pbd.eliminarPermiso(usuarioId);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se borró el permiso",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoPermisoActivity.this, PermisosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el permiso",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        buscarPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String alumnoId = spin_alumno.getSelectedItem().toString();
                Cursor fila = (Cursor) pbd.buscarPermiso(alumnoId);
                if (fila.moveToFirst()) {
                    spin_alumno.setSelected(Boolean.parseBoolean(fila.getString(1)));
                    spin_rol.setSelected(Boolean.parseBoolean(fila.getString(2)));
                    Intent intent = new Intent(NuevoPermisoActivity.this, PermisosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el permiso",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancelarPermiso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoPermisoActivity.this, PermisosActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salir) {
            finish();
            Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NuevoPermisoActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    /*private boolean validarDatos() {
    }*/
}
