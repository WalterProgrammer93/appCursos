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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.AsignaturaBD;
import com.example.appcursos.modelos.Asignatura;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;
import java.util.List;

public class NuevoAsignaturaActivity extends AppCompatActivity {

    EditText et_nombreAsignatura, et_descripcionAsignatura;
    Spinner s_curso;
    Button b_altaAsignatura, b_cancelarAsignatura;
    List<Curso> load_cursos;
    ArrayAdapter<Curso> arrayAdapter;
    AsignaturaBD asbd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_asignatura);

        asbd = new AsignaturaBD(this);
        et_nombreAsignatura = findViewById(R.id.et_nombreAsignatura);
        et_descripcionAsignatura = findViewById(R.id.et_descripcionAsignatura);
        s_curso = findViewById(R.id.spinner_cursoAsignatura);
        load_cursos = new ArrayList<>();
        asbd.escribirBD();
        load_cursos = asbd.cargarCursos();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, load_cursos);
        s_curso.setAdapter(arrayAdapter);

        b_altaAsignatura = findViewById(R.id.altaAsignatura);
        /*b_editarAsignatura = findViewById(R.id.editarAsignatura);
        b_buscarAsignatura = findViewById(R.id.buscarAsignatura);*/
        b_cancelarAsignatura = findViewById(R.id.cancelarAsignatura);

        b_altaAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                    String nombreAsig = et_nombreAsignatura.getText().toString();
                    String descripAsig = et_descripcionAsignatura.getText().toString();
                    String cursoId = s_curso.getSelectedItem().toString();
                    Asignatura asignatura = new Asignatura(nombreAsig, descripAsig, cursoId);
                    if (!asbd.isAsignaturaExists(nombreAsig)) {
                        asbd.insertarAsignatura(asignatura);
                        et_nombreAsignatura.setText("");
                        et_descripcionAsignatura.setText("");
                        s_curso.setSelected(false);
                        Toast.makeText(NuevoAsignaturaActivity.this, "La asignatura se ha creado correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NuevoAsignaturaActivity.this, "La asignatura ya existe", Toast.LENGTH_SHORT).show();
                    }
                /*} else {
                    Toast.makeText(NuevoAsignaturaActivity.this, "Se ha producido un error en la insercion de la asignatura", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        /*b_editarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAsig = et_nombreAsignatura.getText().toString();
                String descripAsig = et_descripcionAsignatura.getText().toString();
                String cursoId = s_curso.getSelectedItem().toString();
                Asignatura asignatura = new Asignatura(nombreAsig, descripAsig, cursoId);
                int cant = asbd.editarAsignatura(nombreAsig, asignatura);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos de la asignatura", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe la asignatura",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_buscarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAsig = et_nombreAsignatura.getText().toString();
                Cursor fila = (Cursor) asbd.buscarAsignatura(nombreAsig);
                if (fila.moveToFirst()) {
                    et_descripcionAsignatura.setText(fila.getString(1));
                    s_curso.setSelected(Boolean.parseBoolean(fila.getString(2)));
                    Intent intent = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe la asignatura",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        b_cancelarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoAsignaturaActivity.this, MenuActivity.class);
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
            Intent intent = new Intent(NuevoAsignaturaActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    /*public boolean validarDatos() {
        boolean valido;
        String campo1 = et_nombreAsignatura.getText().toString();
        String campo2 = et_descripcionAsignatura.getText().toString();
        String campo3 = s_curso.getSelectedItem().toString();

        if (campo1.isEmpty()) {
            valido = false;
            et_nombreAsignatura.setError("El nombre de la asignatura es obligatorio!");
        } else {
            if (campo1.length() <= 10) {
                valido = true;
                et_nombreAsignatura.setError(null);
            } else {
                et_nombreAsignatura.setError("El nombre de la asignatura es largo!");
                valido = false;
            }
        }
        if (campo2.isEmpty()) {
            valido = false;
            et_descripcionAsignatura.setError("La descripcion de la asignatura es obligatoria!");
        } else {
            if (campo2.length() <= 20) {
                valido = true;
                et_descripcionAsignatura.setError(null);
            } else {
                et_descripcionAsignatura.setError("La descripcion de la asignatura es largo!");
                valido = false;
            }
        }
        if (s_curso.isSelected()) {
            valido = true;
            s_curso.setSelected(true);
            Toast.makeText(NuevoAsignaturaActivity.this, "Se ha seleccionado el curso" + campo3, Toast.LENGTH_SHORT).show();
        } else {
            valido = false;
            s_curso.setSelected(false);
            Toast.makeText(NuevoAsignaturaActivity.this, "No ha seleccionado un curso", Toast.LENGTH_SHORT).show();
        }
        return valido;
    }*/
}
