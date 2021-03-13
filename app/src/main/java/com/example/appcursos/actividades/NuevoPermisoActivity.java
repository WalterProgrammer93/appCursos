package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
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
import com.example.appcursos.modelos.Rol;
import com.example.appcursos.modelos.Usuario;
import java.util.ArrayList;
import java.util.List;

public class NuevoPermisoActivity extends AppCompatActivity {

    Spinner spin_alumno, spin_rol;
    Button altaPermiso, cancelarPermiso;
    List<Usuario> load_usuario;
    List<Rol> load_rol;
    ArrayAdapter<Usuario> selectUsuario;
    ArrayAdapter<Rol> selectRol;
    PermisoBD pbd;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_permiso);

        pbd = new PermisoBD(this);
        load_usuario = new ArrayList<>();
        load_rol = new ArrayList<>();
        pbd.escribirBD();
        spin_alumno = findViewById(R.id.spinner_idusuario);
        spin_rol = findViewById(R.id.spinner_idrol);
        load_usuario = pbd.cargarUsuarios();
        load_rol = pbd.cargarRoles();
        selectUsuario = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, load_usuario);
        selectRol = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, load_rol);
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
        inflater.inflate(R.menu.menu3, menu);
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
