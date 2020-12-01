package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.appcursos.R;
import com.example.appcursos.adaptadores.CursoAdaptador;
import com.example.appcursos.modelos.Curso;

import java.util.ArrayList;

public class CursosActivity extends AppCompatActivity {

    ArrayList<Curso> listaCursos;
    CursoAdaptador cursoAdaptador;
    ListView lvCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        listaCursos = new ArrayList<>();
        cursoAdaptador = new CursoAdaptador(this, listaCursos);
        lvCursos = findViewById(R.id.lvCursos);
        lvCursos.setAdapter(cursoAdaptador);
        cursoAdaptador.notifyDataSetChanged();
    }
}
