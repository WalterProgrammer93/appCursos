package com.example.appcursos.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class EditarCursoActivity extends AppCompatActivity {

    EditText et_edit_nombreCurso, et_edit_centroCurso;
    RadioGroup rg_edit_disponibilidad;
    RadioButton rb_edit_disponible, rb_edit_nodisponible;
    Spinner s_edit_numeroAlumnos;
    CheckBox cb_edit_modo1, cb_edit_modo2, cb_edit_modo3;
    Button btn_edit_editarCurso, btn_edit_cancelarCurso;
    ArrayAdapter<String> spinner_adapter;
    ArrayList<String> numAlumnos;
    ArrayList<String> listaDisponibilidad, listaModo;
    CursoBD cbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_curso);

        cbd = new CursoBD(this);
        et_edit_nombreCurso = findViewById(R.id.et_edit_nombreCurso);
        et_edit_centroCurso = findViewById(R.id.et_edit_centroCurso);
        rg_edit_disponibilidad = findViewById(R.id.rg_edit_disponibilidad);
        rb_edit_disponible = findViewById(R.id.rb_edit_disponible);
        rb_edit_nodisponible = findViewById(R.id.rb_edit_nodisponible);
        s_edit_numeroAlumnos = findViewById(R.id.s_edit_numeroAlumnos);
        numAlumnos = new ArrayList<>();
        numAlumnos.add("5");
        numAlumnos.add("10");
        numAlumnos.add("15");
        numAlumnos.add("20");
        numAlumnos.add("25");
        numAlumnos.add("30");
        spinner_adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item, numAlumnos);
        s_edit_numeroAlumnos.setAdapter(spinner_adapter);
        cb_edit_modo1 = findViewById(R.id.cb_edit_modo1);
        cb_edit_modo2 = findViewById(R.id.cb_edit_modo2);
        cb_edit_modo3 = findViewById(R.id.cb_edit_modo3);

        Intent datos = getIntent();
        String nombre = datos.getStringExtra("nombreCurso");
        Cursor cursor = (Cursor) cbd.buscarCurso(nombre);
        //if (cursor.moveToFirst() && cursor.getCount() > 0) {
            do {
                Curso curso = new Curso();
                curso.setCursoId(cursor.getInt(0));
                curso.setNombreCurso(cursor.getString(1));
                curso.setCentro(cursor.getString(2));
                et_edit_nombreCurso.setText(curso.getNombreCurso());
                et_edit_centroCurso.setText(curso.getCentro());
                rg_edit_disponibilidad.check(Integer.parseInt(cursor.getString(3)));
                s_edit_numeroAlumnos.setSelected(Boolean.parseBoolean(cursor.getString(4)));
                cb_edit_modo1.setChecked(Boolean.parseBoolean(cursor.getString(5)));
                cb_edit_modo2.setChecked(Boolean.parseBoolean(cursor.getString(5)));
                cb_edit_modo3.setChecked(Boolean.parseBoolean(cursor.getString(5)));
                Intent i = new Intent(EditarCursoActivity.this, CursosActivity.class);
                startActivity(i);
            } while (cursor.moveToNext());
        /*} else {
            Toast.makeText(getApplicationContext(), "No existe el curso",
                    Toast.LENGTH_SHORT).show();
        }*/

        btn_edit_editarCurso = findViewById(R.id.btn_edit_editarCurso);
        btn_edit_editarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(0);
                String nombreCurso = et_edit_nombreCurso.getText().toString();
                String centroCurso = et_edit_centroCurso.getText().toString();
                String disponible = rb_edit_disponible.getText().toString();
                String noDisponible = rb_edit_nodisponible.getText().toString();
                String numeroAlumnos = s_edit_numeroAlumnos.getSelectedItem().toString();
                String modo1 = cb_edit_modo1.getText().toString();
                String modo2 = cb_edit_modo2.getText().toString();
                String modo3 = cb_edit_modo3.getText().toString();
                listaDisponibilidad = new ArrayList<>();
                if (rb_edit_disponible.isChecked()) {
                    listaDisponibilidad.add(disponible);
                } else {
                    if (rb_edit_nodisponible.isChecked()) {
                        listaDisponibilidad.add(noDisponible);
                    }
                }
                listaModo = new ArrayList<>();
                if (cb_edit_modo1.isChecked()) {
                    listaModo.add(modo1);
                }
                if (cb_edit_modo2.isChecked()) {
                    listaModo.add(modo2);
                }
                if (cb_edit_modo3.isChecked()) {
                    listaModo.add(modo3);
                }
                Curso curso = new Curso(nombreCurso, centroCurso, listaDisponibilidad, numeroAlumnos, listaModo);
                int cant = cbd.editarCurso(nombreCurso, curso);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos del curso", Toast.LENGTH_SHORT)
                            .show();
                    showDialog(0);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el curso",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_edit_cancelarCurso = findViewById(R.id.btn_edit_cancelarCurso);
        btn_edit_cancelarCurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditarCursoActivity.this, CursosActivity.class);
                startActivity(i);
            }
        });

    }

    protected Dialog onCreateDialog(int id) {
        // Use the Builder class for convenient dialog construction
        Dialog dialogo = null;
        if (id == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.titulo_editar);
            builder.setMessage(R.string.msg_confirmEditarCurso)
                    .setIcon(R.drawable.ic_done_black_24dp)
                    .setPositiveButton(R.string.lb_si, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Qué hacer si el usuario pulsa "Si"
                                    Intent i = new Intent(EditarCursoActivity.this, CursosActivity.class);
                                    startActivity(i);
                                }
                            })
                    .setNegativeButton(R.string.lb_no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            dialogo = builder.create();
        }
        return dialogo;
    }
}
