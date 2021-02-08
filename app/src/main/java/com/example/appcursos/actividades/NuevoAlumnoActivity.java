package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.appcursos.R;
import com.example.appcursos.bd.AlumnoBD;

public class NuevoAlumnoActivity extends AppCompatActivity {

    EditText nombreAlumno, apellidosAlumno, dniAlumno, telefonoAlumno;
    Spinner asignaturaId;
    Button altaAlumno, cancelarAlumno;
    AlumnoBD alumbd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_alumno);

        alumbd = new AlumnoBD(this);
        nombreAlumno = findViewById(R.id.et_nombreAlumno);
        apellidosAlumno = findViewById(R.id.et_apellidosAlumno);
        dniAlumno = findViewById(R.id.et_dniAlumno);
        telefonoAlumno = findViewById(R.id.et_telefonoAlumno);
        asignaturaId = findViewById(R.id.spinner_asignaturaId);
        altaAlumno = findViewById(R.id.altaAlumno);
        cancelarAlumno = findViewById(R.id.cancelarAlumno);

        altaAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (validarDatos()) {
                    String nombreAlum = nombreAlumno.getText().toString();
                    String apelAlum = apellidosAlumno.getText().toString();
                    String dniAlum = dniAlumno.getText().toString();
                    String telfAlum = telefonoAlumno.getText().toString();
                    String asigId = asignaturaId.getSelectedItem().toString();
                }*/
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
