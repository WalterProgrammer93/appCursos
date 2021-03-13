package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appcursos.R;
import com.example.appcursos.bd.AlumnoBD;
import com.example.appcursos.modelos.Alumno;
import com.example.appcursos.modelos.Curso;

import java.util.ArrayList;
import java.util.List;

public class EditarAlumnoActivity extends AppCompatActivity {

    EditText et_edit_nombreAlumno, et_edit_apellidosAlumno, et_edit_dniAlumno, et_edit_telefonoAlumno;
    Spinner s_edit_curso;
    Button btn_edit_editarAlumno, btn_edit_cancelarAlumno;
    List<Curso> load_cursos;
    ArrayAdapter<Curso> arrayAdapter;
    AlumnoBD alumbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_alumno);

        alumbd = new AlumnoBD(this);
        et_edit_nombreAlumno = findViewById(R.id.et_edit_nombreAlumno);
        et_edit_apellidosAlumno = findViewById(R.id.et_edit_apellidosAlumno);
        et_edit_dniAlumno = findViewById(R.id.et_edit_dniAlumno);
        et_edit_telefonoAlumno = findViewById(R.id.et_edit_telefonoAlumno);
        s_edit_curso = findViewById(R.id.spinner_edit_curso);
        load_cursos = new ArrayList<>();
        alumbd.escribirBD();
        load_cursos = alumbd.cargarCursos();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, load_cursos);
        s_edit_curso.setAdapter(arrayAdapter);
        btn_edit_editarAlumno = findViewById(R.id.btn_edit_editarAlumno);
        btn_edit_cancelarAlumno = findViewById(R.id.btn_edit_cancelarAlumno);
        btn_edit_editarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAlum = et_edit_nombreAlumno.getText().toString();
                String apellidosAlum = et_edit_apellidosAlumno.getText().toString();
                String dniAlum = et_edit_dniAlumno.getText().toString();
                String telfAlum = et_edit_telefonoAlumno.getText().toString();
                String curso = s_edit_curso.getSelectedItem().toString();
                Alumno alumno = new Alumno(nombreAlum, apellidosAlum, dniAlum, telfAlum, curso);
                /*int cant = alumbd.editarAlumno(id, alumno);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos del alumno", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(EditarAlumnoActivity.this, AlumnosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el alumno",
                            Toast.LENGTH_SHORT).show();
                }*/
            }
        });
    }
}
