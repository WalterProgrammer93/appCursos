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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.CursoBD;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class NuevoCursoActivity extends AppCompatActivity {

    EditText et_nombreCurso, et_centroCurso;
    RadioGroup rg_disponibilidad;
    RadioButton rb_disponible, rb_nodisponible;
    Spinner s_numeroAlumnos;
    CheckBox cb_modo1, cb_modo2, cb_modo3;
    Button altaCurso, editarCurso, eliminarCurso, buscarCurso, cancelarCurso;
    ArrayAdapter<String> spinner_adapter;
    ArrayList<String> numAlumnos;
    ArrayList<String> listaDisponibilidad, listaModo;
    CursoBD cbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_curso);

        cbd = new CursoBD(this);
        et_nombreCurso = findViewById(R.id.et_nombreCurso);
        et_centroCurso = findViewById(R.id.et_centroCurso);
        rg_disponibilidad = findViewById(R.id.rg_disponibilidad);
        rb_disponible = findViewById(R.id.rb_disponible);
        rb_nodisponible = findViewById(R.id.rb_nodisponible);
        s_numeroAlumnos = findViewById(R.id.s_numeroAlumnos);
        numAlumnos = new ArrayList<>();
        numAlumnos.add("5");
        numAlumnos.add("10");
        numAlumnos.add("15");
        numAlumnos.add("20");
        numAlumnos.add("25");
        numAlumnos.add("30");
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, numAlumnos);
        s_numeroAlumnos.setAdapter(spinner_adapter);
        cb_modo1 = findViewById(R.id.cb_modo1);
        cb_modo2 = findViewById(R.id.cb_modo2);
        cb_modo3 = findViewById(R.id.cb_modo3);
        altaCurso = findViewById(R.id.altaCurso);
        //eliminarCurso = findViewById(R.id.eliminarCurso);
        cancelarCurso = findViewById(R.id.cancelarCurso);
        altaCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                    String nombreCurso = et_nombreCurso.getText().toString();
                    String centroCurso = et_centroCurso.getText().toString();
                    String disponible = rb_disponible.getText().toString();
                    String noDisponible = rb_nodisponible.getText().toString();
                    String numeroAlumnos =s_numeroAlumnos.getSelectedItem().toString();
                    String modo1 = cb_modo1.getText().toString();
                    String modo2 = cb_modo2.getText().toString();
                    String modo3 = cb_modo3.getText().toString();
                    listaDisponibilidad = new ArrayList<>();
                    if (rb_disponible.isChecked()) {
                        listaDisponibilidad.add(disponible);
                    } else {
                        if (rb_nodisponible.isChecked()) {
                            listaDisponibilidad.add(noDisponible);
                        }
                    }
                    listaModo = new ArrayList<>();
                    if (cb_modo1.isChecked()) {
                        listaModo.add(modo1);
                    }
                    if (cb_modo2.isChecked()) {
                        listaModo.add(modo2);
                    }
                    if (cb_modo3.isChecked()) {
                        listaModo.add(modo3);
                    }

                    Curso curso = new Curso(nombreCurso, centroCurso, listaDisponibilidad, numeroAlumnos, listaModo);
                    if (!cbd.isCursoExists(curso.getNombreCurso(), curso.getCentro())) {
                        cbd.insertarCurso(curso);
                        et_nombreCurso.setText("");
                        et_centroCurso.setText("");
                        rb_disponible.setChecked(false);
                        rb_nodisponible.setChecked(false);
                        cb_modo1.setChecked(false);
                        cb_modo2.setChecked(false);
                        cb_modo3.setChecked(false);
                        Toast.makeText(NuevoCursoActivity.this, "El curso se ha creado correctamente", Toast.LENGTH_LONG).show();
                        Intent i = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                        startActivity(i);
                    } else {
                        Toast.makeText(NuevoCursoActivity.this, "El curso ya existe!", Toast.LENGTH_LONG).show();
                    }
                /*} else {
                    Toast.makeText(NuevoCursoActivity.this, "Se ha producido un error en la insercion del curso", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        /*eliminarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreCurso = et_nombreCurso.getText().toString();
                int cant = cbd.eliminarCurso(nombreCurso);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se borró el curso con dicho nombre",
                            Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el curso",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });*/

        cancelarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                startActivity(i);
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
            Intent intent = new Intent(NuevoCursoActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    /*public boolean validarDatos() {
        boolean valido;
        String campo1 = et_nombreCurso.getText().toString();
        String campo2 = et_centroCurso.getText().toString();
        String campo5 = s_numeroAlumnos.getSelectedItem().toString();

        if (campo1.isEmpty()) {
            valido = false;
            et_nombreCurso.setError("El nombre del curso es obligatorio!");
        } else {
            if (campo1.length() > 5) {
                valido = true;
                et_nombreCurso.setError(null);
            } else {
                et_nombreCurso.setError("El nombre del curso es corto!");
                valido = false;
            }
        }

        if (campo2.isEmpty()) {
            valido = false;
            et_centroCurso.setError("El nombre del centro es obligatorio!");
        } else {
            if (campo2.length() > 15) {
                valido = true;
                et_centroCurso.setError(null);
            } else {
                valido = false;
                et_nombreCurso.setError("El nombre del centro es corto!");
            }
        }

        if (!rb_disponible.isChecked()) {
            valido = false;
            rb_disponible.setError("La disponiblidad es obligatoria!");
        } else {
            valido = true;
            rb_disponible.setError(null);
            Toast.makeText(NuevoCursoActivity.this, "Ha seleccionado " + rb_disponible.getText().toString(), Toast.LENGTH_LONG).show();
        }

        if (!rb_nodisponible.isChecked()) {
            valido = false;
            rb_nodisponible.setError("La disponiblidad es obligatoria!");
        } else {
            valido = true;
            rb_nodisponible.setError(null);
            Toast.makeText(NuevoCursoActivity.this, "Ha seleccionado " + rb_nodisponible.getText().toString(), Toast.LENGTH_LONG).show();
        }

        if (campo5.equals("")) {
            valido = false;
            Toast.makeText(NuevoCursoActivity.this, "La seleccion del numero de alumnos es obligatoria!", Toast.LENGTH_LONG).show();
        } else {
            valido = true;
            Toast.makeText(NuevoCursoActivity.this, "Ha seleccionado " + campo5, Toast.LENGTH_LONG).show();
        }

        if (!cb_tema1.isChecked()) {
            valido = false;
            cb_tema1.setError("El tema es obligatorio!");
        } else {
            valido = true;
            cb_tema1.setError(null);
            Toast.makeText(NuevoCursoActivity.this, "Ha seleccionado " + cb_tema1.getText().toString(), Toast.LENGTH_LONG).show();

        }

        if (!cb_tema2.isChecked()) {
            valido = false;
            cb_tema2.setError("El tema es obligatorio!");
        } else {
            valido = true;
            cb_tema2.setError(null);
            Toast.makeText(NuevoCursoActivity.this, "Ha seleccionado " + cb_tema2.getText().toString(), Toast.LENGTH_LONG).show();

        }

        if (!cb_tema3.isChecked()) {
            valido = false;
            cb_tema3.setError("El tema es obligatorio!");
        } else {
            valido = true;
            cb_tema3.setError(null);
            Toast.makeText(NuevoCursoActivity.this, "Ha seleccionado " + cb_tema3.getText().toString(), Toast.LENGTH_LONG).show();

        }
        return valido;
    }*/
}
