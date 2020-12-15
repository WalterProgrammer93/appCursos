package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.CursoBD;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class NuevoCursoActivity extends AppCompatActivity {

    EditText et_nombreCurso, et_centroCurso;
    RadioButton rb_disponible, rb_nodisponible;
    Spinner s_numeroAlumnos;
    CheckBox cb_tema1, cb_tema2, cb_tema3;
    Button b_altaCurso, b_buscarCurso, b_eliminarCurso, b_editarCurso, b_cancelarCurso;
    ArrayAdapter<String> spinner_adapter;
    ArrayList<String> numAlumnos;
    CursoBD cbd;
    Cursor fila;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_curso);

        cbd = new CursoBD(this);
        et_nombreCurso = findViewById(R.id.et_nombreCurso);
        et_centroCurso = findViewById(R.id.et_centroCurso);
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
        cb_tema1 = findViewById(R.id.cb_tema1);
        cb_tema2 = findViewById(R.id.cb_tema2);
        cb_tema3 = findViewById(R.id.cb_tema3);
        b_altaCurso = findViewById(R.id.b_altaCurso);
        b_altaCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDatos()) {
                    String nombreCurso = et_nombreCurso.getText().toString();
                    String centroCurso = et_centroCurso.getText().toString();
                    String disponible = rb_disponible.getText().toString();
                    String nodisponible = rb_nodisponible.getText().toString();
                    String numeroAlumnos =s_numeroAlumnos.getSelectedItem().toString();
                    String tema1 = cb_tema1.getText().toString();
                    String tema2 = cb_tema2.getText().toString();
                    String tema3 = cb_tema3.getText().toString();
                    ArrayList<String> disponibilidad = new ArrayList<>();
                    disponibilidad.add(disponible);
                    disponibilidad.add(nodisponible);
                    ArrayList<String> tema = new ArrayList<>();
                    tema.add(tema1);
                    tema.add(tema2);
                    tema.add(tema3);
                    Curso curso = new Curso(nombreCurso, centroCurso, disponibilidad, numeroAlumnos, tema);
                    if (!cbd.isCursoExists(curso.getNombreCurso(), curso.getCentro())) {
                        cbd.insertarCurso(curso);
                        et_nombreCurso.setText("");
                        et_centroCurso.setText("");
                        rb_disponible.setChecked(false);
                        rb_nodisponible.setChecked(false);
                        s_numeroAlumnos.setEnabled(false);
                        cb_tema1.setChecked(false);
                        cb_tema2.setChecked(false);
                        cb_tema3.setChecked(false);
                        Toast.makeText(NuevoCursoActivity.this, "El curso se ha creado correctamente!", Toast.LENGTH_LONG).show();
                        Intent i_insert = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                        i_insert.putExtra("NombreCurso", nombreCurso);
                        i_insert.putExtra("CentroCurso", centroCurso);
                        i_insert.putExtra("Disponibilidad", disponibilidad);
                        i_insert.putExtra("NumeroAlumnos", numeroAlumnos);
                        i_insert.putExtra("Temas", tema);
                        startActivity(i_insert);
                    } else {
                        Toast.makeText(NuevoCursoActivity.this, "El curso ya existe!", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoCursoActivity.this, "Se ha producido un error en la insercion del curso", Toast.LENGTH_LONG).show();
                }
            }
        });
        b_buscarCurso = findViewById(R.id.b_buscarCurso);
        b_buscarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String nombre = et_nombreCurso.getText().toString();
                    fila = (Cursor) cbd.buscarCurso(nombre);
                    if (fila.moveToFirst() && fila.getCount() > 0) {
                        Intent buscar = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                        buscar.putExtra("NombreCurso", nombre);
                        startActivity(buscar);
                    } else {
                        Toast.makeText(NuevoCursoActivity.this, "No existe el curso con dicho nombre",
                                Toast.LENGTH_LONG).show();
                    }
            }
        });
        b_eliminarCurso = findViewById(R.id.b_eliminarCurso);
        b_eliminarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String nom = et_nombreCurso.getText().toString();
                    int cant = cbd.eliminarCurso(nom);
                    et_nombreCurso.setText("");
                    et_centroCurso.setText("");
                    rb_disponible.setChecked(false);
                    rb_nodisponible.setChecked(false);
                    s_numeroAlumnos.setEnabled(false);
                    cb_tema1.setChecked(false);
                    cb_tema2.setChecked(false);
                    cb_tema3.setChecked(false);

                    if (cant == 1) {
                        Toast.makeText(NuevoCursoActivity.this, "Se borró el curso con dicho nombre", Toast.LENGTH_SHORT).show();
                        Intent i_eliminar = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                        startActivity(i_eliminar);
                    } else
                        Toast.makeText(NuevoCursoActivity.this, "No existe un curso con dicho nombre", Toast.LENGTH_SHORT).show();
            }
        });
        b_editarCurso = findViewById(R.id.b_editarCurso);
        b_editarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validarDatos()) {
                    String et1 = et_nombreCurso.getText().toString();
                    String et2 = et_centroCurso.getText().toString();
                    String et3 = rb_disponible.getText().toString();
                    String et4 = rb_nodisponible.getText().toString();
                    String et5 =s_numeroAlumnos.getSelectedItem().toString();
                    String et6 = cb_tema1.getText().toString();
                    String et7 = cb_tema2.getText().toString();
                    String et8 = cb_tema3.getText().toString();

                    ArrayList<String> disponibilidad2 = new ArrayList<>();
                    disponibilidad2.add(et3);
                    disponibilidad2.add(et4);
                    ArrayList<String> tema2 = new ArrayList<>();
                    tema2.add(et6);
                    tema2.add(et7);
                    tema2.add(et8);
                    Curso curso = new Curso(et1, et2, disponibilidad2, et5, tema2);
                    int editar = cbd.editarCurso(et1, curso);
                    if (editar == 1) {
                        Toast.makeText(NuevoCursoActivity.this, "Se modificaron los datos del curso", Toast.LENGTH_SHORT)
                                .show();
                        Intent i_edit = new Intent(NuevoCursoActivity.this, CursosActivity.class);
                        i_edit.putExtra("NombreCurso", et1);
                        i_edit.putExtra("CentroCurso", et2);
                        i_edit.putExtra("Disponibilidad", disponibilidad2);
                        i_edit.putExtra("NumeroAlumnos", et5);
                        i_edit.putExtra("Temas", tema2);
                        startActivity(i_edit);
                    } else
                        Toast.makeText(NuevoCursoActivity.this, "No existe un curso con el nombre ingresado",
                                Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(NuevoCursoActivity.this, "Se ha producido un error en la edicion del curso", Toast.LENGTH_LONG).show();
                }
            }
        });
        b_cancelarCurso = findViewById(R.id.b_cancelarCurso);
        b_cancelarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelar_curso = new Intent(NuevoCursoActivity.this, MenuActivity.class);
                startActivity(cancelar_curso);
            }
        });
    }

    public boolean validarDatos() {
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
    }
}
