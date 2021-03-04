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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.ProfesorBD;
import com.example.appcursos.modelos.Asignatura;
import com.example.appcursos.modelos.Profesor;
import java.util.ArrayList;
import java.util.List;

public class NuevoProfesorActivity extends AppCompatActivity {

    EditText et_nombreProf, et_apellidosProf, et_telfProf;
    RadioGroup rg_departamentos;
    RadioButton rb_lengua, rb_matematicas, rb_ingles,
            rb_fisicaQuimica, rb_tecnologia, rb_informatica,
            rb_musica, rb_biologiaGeologia, rb_historiaGeografia;
    Spinner s_asignaturas;
    ArrayList<String> listaDepartamentos;
    Button b_altaProf, b_editarProf, b_eliminarProf, b_buscarProf, b_cancelar;
    List<Asignatura> load_asignaturas;
    ArrayAdapter<Asignatura> arrayAdapter;
    ProfesorBD probd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_profesor);

        probd = new ProfesorBD(this);
        et_nombreProf = findViewById(R.id.et_nombreProfesor);
        et_apellidosProf = findViewById(R.id.et_apellidosProfesor);
        rg_departamentos = findViewById(R.id.rg_departamentos);
        rb_lengua = findViewById(R.id.rb_lengua);
        rb_matematicas = findViewById(R.id.rb_matematicas);
        rb_ingles = findViewById(R.id.rb_ingles);
        rb_fisicaQuimica = findViewById(R.id.rb_fisicaQuimica);
        rb_tecnologia = findViewById(R.id.rb_tecnologia);
        rb_informatica = findViewById(R.id.rb_informatica);
        rb_musica = findViewById(R.id.rb_musica);
        rb_biologiaGeologia = findViewById(R.id.rb_biologiaGeologia);
        rb_historiaGeografia = findViewById(R.id.rb_historiaGeografia);
        et_telfProf = findViewById(R.id.et_telefonoProfesor);
        s_asignaturas = findViewById(R.id.s_asignaturas);
        load_asignaturas = new ArrayList<>();
        probd.escribirBD();
        load_asignaturas = probd.cargarAsignaturas();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, load_asignaturas);
        s_asignaturas.setAdapter(arrayAdapter);

        b_altaProf = findViewById(R.id.altaProfesor);
        b_editarProf = findViewById(R.id.editarProfesor);
        b_eliminarProf = findViewById(R.id.eliminarProfesor);
        b_buscarProf = findViewById(R.id.buscarProfesor);
        b_cancelar = findViewById(R.id.cancelarProfesor);

        b_altaProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                String nombreProf = et_nombreProf.getText().toString();
                String apellProf = et_apellidosProf.getText().toString();
                String departamento1 = rb_lengua.getText().toString();
                String departamento2 = rb_matematicas.getText().toString();
                String departamento3 = rb_ingles.getText().toString();
                String departamento4 = rb_fisicaQuimica.getText().toString();
                String departamento5 = rb_tecnologia.getText().toString();
                String departamento6 = rb_informatica.getText().toString();
                String departamento7 = rb_musica.getText().toString();
                String departamento8 = rb_biologiaGeologia.getText().toString();
                String departamento9 = rb_historiaGeografia.getText().toString();
                listaDepartamentos = new ArrayList<>();
                if (rb_lengua.isChecked()) {
                    listaDepartamentos.add(departamento1);
                } else {
                    if (rb_matematicas.isChecked()) {
                        listaDepartamentos.add(departamento2);
                    } else {
                        if (rb_ingles.isChecked()) {
                            listaDepartamentos.add(departamento3);
                        } else {
                            if (rb_fisicaQuimica.isChecked()) {
                                listaDepartamentos.add(departamento4);
                            } else {
                                if (rb_tecnologia.isChecked()) {
                                    listaDepartamentos.add(departamento5);
                                } else {
                                    if (rb_informatica.isChecked()) {
                                        listaDepartamentos.add(departamento6);
                                    } else {
                                        if (rb_musica.isChecked()) {
                                            listaDepartamentos.add(departamento7);
                                        } else {
                                            if (rb_biologiaGeologia.isChecked()) {
                                                listaDepartamentos.add(departamento8);
                                            } else {
                                                if (rb_historiaGeografia.isChecked()) {
                                                    listaDepartamentos.add(departamento9);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                /*listaDepartamentos.add(departamento1);
                listaDepartamentos.add(departamento2);
                listaDepartamentos.add(departamento3);
                listaDepartamentos.add(departamento4);
                listaDepartamentos.add(departamento5);
                listaDepartamentos.add(departamento6);
                listaDepartamentos.add(departamento7);
                listaDepartamentos.add(departamento8);
                listaDepartamentos.add(departamento9);*/
                String telefonoProf = et_telfProf.getText().toString();
                String asignaturaId = s_asignaturas.getSelectedItem().toString();
                Profesor profesor = new Profesor(nombreProf, apellProf, listaDepartamentos, telefonoProf, asignaturaId);
                if (!probd.isProfesorExists(profesor.getNombreProfesor())) {
                    probd.insertarProfesor(profesor);
                    et_nombreProf.setText("");
                    et_apellidosProf.setText("");
                    rb_lengua.setChecked(false);
                    rb_matematicas.setChecked(false);
                    rb_ingles.setChecked(false);
                    rb_fisicaQuimica.setChecked(false);
                    rb_tecnologia.setChecked(false);
                    rb_informatica.setChecked(false);
                    rb_musica.setChecked(false);
                    rb_biologiaGeologia.setChecked(false);
                    rb_historiaGeografia.setChecked(false);
                    et_telfProf.setText("");
                    s_asignaturas.setSelected(false);
                    Toast.makeText(NuevoProfesorActivity.this, "El profesor se ha creado correctamente", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(NuevoProfesorActivity.this, "El profesor ya existe", Toast.LENGTH_SHORT).show();
                }
                /*} else {
                    Toast.makeText(NuevoCursoActivity.this, "Se ha producido un error en la insercion del profesor", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        b_editarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProf = et_nombreProf.getText().toString();
                String apellProf = et_apellidosProf.getText().toString();
                String departamento1 = rb_lengua.getText().toString();
                String departamento2 = rb_matematicas.getText().toString();
                String departamento3 = rb_ingles.getText().toString();
                String departamento4 = rb_fisicaQuimica.getText().toString();
                String departamento5 = rb_tecnologia.getText().toString();
                String departamento6 = rb_informatica.getText().toString();
                String departamento7 = rb_musica.getText().toString();
                String departamento8 = rb_biologiaGeologia.getText().toString();
                String departamento9 = rb_historiaGeografia.getText().toString();
                listaDepartamentos = new ArrayList<>();
                listaDepartamentos.add(departamento1);
                listaDepartamentos.add(departamento2);
                listaDepartamentos.add(departamento3);
                listaDepartamentos.add(departamento4);
                listaDepartamentos.add(departamento5);
                listaDepartamentos.add(departamento6);
                listaDepartamentos.add(departamento7);
                listaDepartamentos.add(departamento8);
                listaDepartamentos.add(departamento9);
                String telefonoProf = et_telfProf.getText().toString();
                String asignaturaId = s_asignaturas.getSelectedItem().toString();
                Profesor profesor = new Profesor(nombreProf, apellProf, listaDepartamentos, telefonoProf, asignaturaId);
                int cant = probd.editarProfesor(nombreProf, profesor);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos del profesor", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el profesor",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_eliminarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProf = et_nombreProf.getText().toString();
                int cant = probd.eliminarProfesor(nombreProf);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se borró el profesor correctamente",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el profesor",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_buscarProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreProf = et_nombreProf.getText().toString();
                Cursor fila = (Cursor) probd.buscarProfesor(nombreProf);
                if (fila.moveToFirst()) {
                    et_apellidosProf.setText(fila.getString(3));
                    rb_lengua.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_matematicas.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_ingles.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_fisicaQuimica.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_tecnologia.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_informatica.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_musica.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_biologiaGeologia.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    rb_historiaGeografia.setChecked(Boolean.parseBoolean(fila.getString(4)));
                    et_telfProf.setText(fila.getString(5));
                    s_asignaturas.setSelected(Boolean.parseBoolean(fila.getString(6)));
                    Intent intent = new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el profesor",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoProfesorActivity.this, ProfesoresActivity.class);
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
            Intent salir = new Intent(NuevoProfesorActivity.this, MainActivity.class);
            startActivity(salir);
        }
        return super.onOptionsItemSelected(item);

    }

    /*private boolean validarDatos() {
    }*/


}
