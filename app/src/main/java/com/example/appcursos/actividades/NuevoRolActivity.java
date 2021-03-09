package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.RolBD;
import com.example.appcursos.modelos.Rol;

public class NuevoRolActivity extends AppCompatActivity {

    EditText et_nombreRol, et_descripRol;
    Button b_altaRol, b_cancelar;
    RolBD rbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_rol);

        rbd = new RolBD(this);
        et_nombreRol = findViewById(R.id.et_nombreRol);
        et_descripRol = findViewById(R.id.et_descripcionRol);
        b_altaRol = findViewById(R.id.altaRol);
        /*b_editarRol = findViewById(R.id.editarRol);
        b_eliminarRol = findViewById(R.id.eliminarRol);
        b_buscarRol = findViewById(R.id.buscarRol);*/
        b_cancelar = findViewById(R.id.cancelarRol);

        b_altaRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                    String nombreRol = et_nombreRol.getText().toString();
                    String descripRol = et_descripRol.getText().toString();
                    Rol rol = new Rol(nombreRol, descripRol);
                    if (!rbd.isExistsRol(rol.getNombreRol())) {
                        rbd.insertarRol(rol);
                        et_nombreRol.setText("");
                        et_descripRol.setText("");
                        Toast.makeText(NuevoRolActivity.this, "El rol se ha creado correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NuevoRolActivity.this, RolesActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NuevoRolActivity.this, "El rol ya existe", Toast.LENGTH_SHORT).show();
                    }
                /*} else {
                        Toast.makeText(NuevoRolActivity.this, "Se ha producido un error en la insercion del rol", Toast.LENGTH_SHORT).show();
                    }*/
            }
        });

        /*b_editarRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreRol = et_nombreRol.getText().toString();
                String descripRol = et_descripRol.getText().toString();
                Rol rol = new Rol(nombreRol, descripRol);
                int cant = rbd.editarRol(nombreRol, rol);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos del rol", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(NuevoRolActivity.this, AsignaturasActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el rol",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_eliminarRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreRol = et_nombreRol.getText().toString();
                int cant = rbd.eliminarRol(nombreRol);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se borró el rol correctamente",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoRolActivity.this, AsignaturasActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el rol",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_buscarRol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreRol = et_nombreRol.getText().toString();
                Cursor fila = (Cursor) rbd.buscarRol(nombreRol);
                if (fila.moveToFirst()) {
                    et_descripRol.setText(fila.getString(2));
                    Intent intent = new Intent(NuevoRolActivity.this, RolesActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el rol",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoRolActivity.this, RolesActivity.class);
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
            Intent salir = new Intent(NuevoRolActivity.this, MainActivity.class);
            startActivity(salir);
        }
        return super.onOptionsItemSelected(item);

    }
    /*private boolean validarDatos() {
    }*/
}
