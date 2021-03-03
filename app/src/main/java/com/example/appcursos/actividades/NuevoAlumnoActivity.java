package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.AlumnoBD;
import com.example.appcursos.modelos.Alumno;
import com.example.appcursos.modelos.Asignatura;

import java.util.ArrayList;
import java.util.List;

public class NuevoAlumnoActivity extends AppCompatActivity {

    EditText et_nombreAlumno, et_apellidosAlumno, et_dniAlumno, et_telefonoAlumno;
    Spinner s_asignaturas;
    Button altaAlumno, editarAlumno, eliminarAlumno, buscarAlumno, cancelarAlumno;
    List<Asignatura> load_asignaturas;
    ArrayAdapter<Asignatura> arrayAdapter;
    AlumnoBD alumbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);

        alumbd = new AlumnoBD(this);
        et_nombreAlumno = findViewById(R.id.et_nombreAlumno);
        et_apellidosAlumno = findViewById(R.id.et_apellidosAlumno);
        et_dniAlumno = findViewById(R.id.et_dniAlumno);
        et_telefonoAlumno = findViewById(R.id.et_telefonoAlumno);
        s_asignaturas = findViewById(R.id.spinner_asignaturaId);
        load_asignaturas = new ArrayList<>();
        alumbd.escribirBD();
        load_asignaturas = alumbd.cargarAsignaturas();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, load_asignaturas);
        s_asignaturas.setAdapter(arrayAdapter);

        altaAlumno = findViewById(R.id.altaAlumno);
        editarAlumno = findViewById(R.id.editarAlumno);
        eliminarAlumno = findViewById(R.id.eliminarAlumno);
        buscarAlumno = findViewById(R.id.buscarAlumno);
        cancelarAlumno = findViewById(R.id.cancelarAlumno);

        altaAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                    String nombreAlum = et_nombreAlumno.getText().toString();
                    String apellidosAlum = et_apellidosAlumno.getText().toString();
                    String dniAlum = et_dniAlumno.getText().toString();
                    String telfAlum = et_telefonoAlumno.getText().toString();
                    String asigId = s_asignaturas.getSelectedItem().toString();
                    Alumno alumno = new Alumno(nombreAlum, apellidosAlum, dniAlum, telfAlum, asigId);
                    if (!alumbd.isAlumnoExists(alumno.getNombreAlumno())) {
                        alumbd.insertarAlumno(alumno);
                        Toast.makeText(getApplicationContext(), "El alumno se ha creado correctamente",
                                Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NuevoAlumnoActivity.this, "El alumno ya existe", Toast.LENGTH_SHORT).show();
                    }
                /*} else {
                    Toast.makeText(NuevoAlumnoActivity.this, "Se ha producido un error en la insercion del alumno", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        editarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAlum = et_nombreAlumno.getText().toString();
                String apellidosAlum = et_apellidosAlumno.getText().toString();
                String dniAlum = et_dniAlumno.getText().toString();
                String telfAlum = et_telefonoAlumno.getText().toString();
                String asigId = s_asignaturas.getSelectedItem().toString();
                Alumno alumno = new Alumno(nombreAlum, apellidosAlum, dniAlum, telfAlum, asigId);
                int cant = alumbd.editarAlumno(nombreAlum, alumno);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos del alumno", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el alumno",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        eliminarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAlumno = et_nombreAlumno.getText().toString();
                int cant = alumbd.eliminarAlumno(nombreAlumno);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se borró el alumno correctamente",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el alumno",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        buscarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAlumno = et_nombreAlumno.getText().toString();
                Cursor fila = (Cursor) alumbd.buscarAlumno(nombreAlumno);
                if (fila.moveToFirst()) {
                    et_apellidosAlumno.setText(fila.getString(2));
                    et_dniAlumno.setText(fila.getString(3));
                    et_telefonoAlumno.setText(fila.getString(4));
                    s_asignaturas.setSelected(Boolean.parseBoolean(fila.getString(5)));
                    Intent intent = new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el rol",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoAlumnoActivity.this, AlumnosActivity.class);
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
            Intent salir = new Intent(NuevoAlumnoActivity.this, MainActivity.class);
            startActivity(salir);
        }
        return super.onOptionsItemSelected(item);

    }

    /*private boolean validarDatos() {
    }*/
}
