package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.appcursos.R;
import com.example.appcursos.bd.AdminBD;
import com.example.appcursos.bd.AsignaturaBD;
import com.example.appcursos.modelos.Asignatura;
import com.example.appcursos.modelos.Curso;

import java.util.ArrayList;

public class NuevoAsignaturaActivity extends AppCompatActivity {

    EditText et_nombreAsignatura, et_descripcionAsignatura;
    Spinner s_curso;
    Button b_altaAsignatura, b_buscarAsignatura, b_eliminarAsignatura, b_editarAsignatura, b_cancelarAsignatura;
    ArrayList<String> cursos;
    ArrayAdapter<String> s_adapter;
    AsignaturaBD asbd;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_asignatura);

        asbd = new AsignaturaBD(this);
        et_nombreAsignatura = findViewById(R.id.tvNombreAsignatura);
        et_descripcionAsignatura = findViewById(R.id.tvDescripcionAsignatura);
        s_curso = findViewById(R.id.spinner_cursoAsignatura);
        cursos = new ArrayList<>();
        Asignatura asignatura = new Asignatura();
        for (int i = 0; i < cursos.size(); i++) {
            cursos.add(i, String.valueOf(asignatura.getCurso()));
        }
        s_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cursos);
        s_curso.setAdapter(s_adapter);
        b_altaAsignatura = findViewById(R.id.alta_asignatura);
        b_altaAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAsignatura = et_nombreAsignatura.getText().toString();
                String descripcionAsignatura = et_descripcionAsignatura.getText().toString();
                String curso = s_curso.getSelectedItem().toString();
                Asignatura asignatura = new Asignatura(nombreAsignatura, descripcionAsignatura, curso);
                if (!asbd.isAsignaturaExists(nombreAsignatura)) {
                    asbd.insertarAsignatura(asignatura);
                }
            }
        });
        b_buscarAsignatura = findViewById(R.id.buscar_asignatura);
        b_buscarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b_eliminarAsignatura = findViewById(R.id.eliminar_asignatura);
        b_eliminarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b_editarAsignatura = findViewById(R.id.editar_asignatura);
        b_editarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        b_cancelarAsignatura = findViewById(R.id.cancelar_asignatura);
        b_cancelarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
