package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.appcursos.R;
import com.example.appcursos.bd.AlumnoBD;
import com.example.appcursos.modelos.Alumno;

import java.util.ArrayList;

public class NuevoAlumnoActivity extends AppCompatActivity {

    EditText et_nombreAlumno, et_apellidosAlumno, et_dniAlumno, et_telefonoAlumno;
    Spinner spin_asignaturaId;
    Button altaAlumno, editarAlumno, eliminarAlumno, buscarAlumno, cancelarAlumno;
    ArrayList<Alumno> load_asignaturas;
    ArrayAdapter<String> spinner_adapter;
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
        spin_asignaturaId = findViewById(R.id.spinner_asignaturaId);
        load_asignaturas = new ArrayList<>();
        alumbd.escribirBD();
        load_asignaturas = alumbd.cargarAsignaturas();
        altaAlumno = findViewById(R.id.altaAlumno);
        editarAlumno = findViewById(R.id.editarAlumno);
        eliminarAlumno = findViewById(R.id.eliminarAlumno);
        buscarAlumno = findViewById(R.id.buscarAlumno);
        cancelarAlumno = findViewById(R.id.cancelarAlumno);

        altaAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                    String nombre = et_nombreAlumno.getText().toString();
                    String apellidosAlumno = et_apellidosAlumno.getText().toString();
                    String dniAlum = dniAlumno.getText().toString();
                    String telfAlum = telefonoAlumno.getText().toString();
                    String asigId = asignaturaId.getSelectedItem().toString();
                    Alumno alumno = new Alumno(nombreAlumno, )
                //}
            }
        });

        editarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        eliminarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        buscarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        cancelarAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    /*private boolean validarDatos() {
    }*/
}
