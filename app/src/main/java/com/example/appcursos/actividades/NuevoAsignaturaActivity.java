package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
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

public class NuevoAsignaturaActivity extends AppCompatActivity {

    EditText et_nombreAsignatura, et_descripcionAsignatura;
    Spinner s_curso;
    Button b_altaAsignatura, b_buscarAsignatura, b_eliminarAsignatura, b_editarAsignatura, b_cancelarAsignatura;
    ArrayList<String> cursos;
    ArrayAdapter<String> s_adapter;
    AsignaturaBD asbd;
    static Cursor cursor;

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
        asbd.leerBD();
        cursor = asbd.getBD().rawQuery("select curso_id as curso from cursos",null);
        for (int i = 0; i < cursos.size(); i++) {
            if (cursor.moveToFirst() && cursor.getCount() > 0) {
                cursos.add(i, String.valueOf(cursor));
            }
        }
        s_adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, cursos);
        s_curso.setAdapter(s_adapter);
        b_altaAsignatura = findViewById(R.id.alta_asignatura);
        b_altaAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAsignatura = et_nombreAsignatura.getText().toString();
                String descripcionAsignatura = et_descripcionAsignatura.getText().toString();
                Curso curso = new Curso();
                curso.setCursoId((Integer) s_curso.getSelectedItem());
                Asignatura asignatura = new Asignatura(nombreAsignatura, descripcionAsignatura, curso);
                if (!asbd.isAsignaturaExists(nombreAsignatura)) {
                    asbd.insertarAsignatura(asignatura);
                    et_nombreAsignatura.setText("");
                    et_descripcionAsignatura.setText("");
                    Toast.makeText(NuevoAsignaturaActivity.this, "La asignatura se ha creado correctamente!", Toast.LENGTH_LONG).show();
                    Intent alta = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                    alta.putExtra("NombreAsignatura", nombreAsignatura);
                    alta.putExtra("DescripcionAsignatura", descripcionAsignatura);
                    alta.putExtra("Curso", String.valueOf(curso));
                    startActivity(alta);
                } else {
                    Toast.makeText(NuevoAsignaturaActivity.this, "La asignatura ya existe!", Toast.LENGTH_LONG).show();
                }
            }
        });
        b_buscarAsignatura = findViewById(R.id.buscar_asignatura);
        b_buscarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = et_nombreAsignatura.getText().toString();
                cursor = (Cursor) asbd.buscarAsignatura(nombre);
                if (cursor.moveToFirst() && cursor.getCount() > 0) {
                    Intent buscar = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                    buscar.putExtra("NombreCurso", nombre);
                    startActivity(buscar);
                } else {
                    Toast.makeText(NuevoAsignaturaActivity.this, "No existe la asignatura con dicho nombre",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        b_eliminarAsignatura = findViewById(R.id.eliminar_asignatura);
        b_eliminarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAsig = et_nombreAsignatura.getText().toString();
                int cant = asbd.eliminarAsignatura(nombreAsig);
                et_nombreAsignatura.setText("");
                et_descripcionAsignatura.setText("");
                if (cant == 1) {
                    Toast.makeText(NuevoAsignaturaActivity.this, "Se borró la asignatura con dicho nombre", Toast.LENGTH_SHORT).show();
                    Intent eliminar = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                    startActivity(eliminar);
                } else
                    Toast.makeText(NuevoAsignaturaActivity.this, "No existe una asignatura con dicho nombre", Toast.LENGTH_SHORT).show();
            }
        });
        b_editarAsignatura = findViewById(R.id.editar_asignatura);
        b_editarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombreAsig = et_nombreAsignatura.getText().toString();
                String descripAsig = et_descripcionAsignatura.getText().toString();
                Curso curso = new Curso();
                curso.setCursoId((Integer) s_curso.getSelectedItem());
                Asignatura asignatura = new Asignatura(nombreAsig, descripAsig, curso);
                int editar = asbd.editarAsignatura(nombreAsig, asignatura);
                if (editar == 1) {
                    Toast.makeText(NuevoAsignaturaActivity.this, "Se modificaron los datos de la asignatura", Toast.LENGTH_SHORT)
                            .show();
                    Intent edit = new Intent(NuevoAsignaturaActivity.this, AsignaturasActivity.class);
                    edit.putExtra("NombreAsignatura", nombreAsig);
                    edit.putExtra("DescripcionAsignatura", descripAsig);
                    edit.putExtra("Curso", String.valueOf(curso));
                    startActivity(edit);
                } else
                    Toast.makeText(NuevoAsignaturaActivity.this, "No existe la asignatura",
                            Toast.LENGTH_SHORT).show();
            }
        });
        b_cancelarAsignatura = findViewById(R.id.cancelar_asignatura);
        b_cancelarAsignatura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelar = new Intent(NuevoAsignaturaActivity.this, MenuActivity.class);
                startActivity(cancelar);
            }
        });

    }
    public boolean validarDatos() {
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
    }
}
