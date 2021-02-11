package com.example.appcursos.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.adaptadores.CursoAdaptador;
import com.example.appcursos.bd.CursoBD;
import com.example.appcursos.modelos.Curso;
import java.util.ArrayList;

public class CursosActivity extends AppCompatActivity {

    ArrayList<Curso> listaCursos;
    CursoAdaptador cursoAdaptador;
    ListView lvCursos;
    ImageView ivCursos;
    TextView tvNombreCurso, tvCentro, tvDisponibilidad, tvNumeroAlumnos, tvModos;
    CursoBD cbd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        listaCursos = new ArrayList<>();
        ivCursos = findViewById(R.id.ivCurso);
        tvNombreCurso = findViewById(R.id.tvNombreCurso);
        tvCentro = findViewById(R.id.tvCentro);
        tvDisponibilidad = findViewById(R.id.tvDisponibilidad);
        tvNumeroAlumnos = findViewById(R.id.tvNumeroAlumnos);
        tvModos = findViewById(R.id.tvModos);
        lvCursos = findViewById(R.id.lvCursos);
        cbd = new CursoBD(this);
        Cursor fila = (Cursor) cbd.listarCursos();
        if (fila.moveToFirst()) {
            do {
                Curso c = new Curso();
                c.setNombreCurso(fila.getString(1));
                c.setCentro(fila.getString(2));
                c.setDisponibilidad(fila.getString(3));
                c.setNumeroAlumnos(fila.getString(4));
                c.setModos(fila.getString(5));
                listaCursos.add(c);
                tvNombreCurso.setText(c.getNombreCurso());
                tvCentro.setText(c.getCentro());
                tvDisponibilidad.setText(c.getDisponibilidad());
                tvNumeroAlumnos.setText(c.getNumeroAlumnos());
                tvModos.setText(c.getModos());
            } while (fila.moveToNext());
        }
        /*Bundle mostrarCurso = getIntent().getExtras();
        if (mostrarCurso != null) {
            String nameCurso = mostrarCurso.getString("NombreCurso");
            String nameCentro = mostrarCurso.getString("CentroCurso");
            String disponibilidad = mostrarCurso.getString("Disponibilidad");
            String nAlumnos = mostrarCurso.getString("NumeroAlumnos");
            String modos = mostrarCurso.getString("Modos");*/




       // }
        cursoAdaptador = new CursoAdaptador(this, listaCursos);
        lvCursos.setAdapter(cursoAdaptador);
        cursoAdaptador.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_nuevo:
                Intent nuevo_curso = new Intent(CursosActivity.this, NuevoCursoActivity.class);
                startActivity(nuevo_curso);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "LogOut Successful", Toast.LENGTH_SHORT).show();
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contextual, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int itemSeleccionado = info.position;
        switch (itemSeleccionado) {
            case R.id.action_editar:
                // hacer algo
                /*AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.lb_esta_seguro)
                        .setPositiveButton(R.string.lb_si,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"
                                        Intent editar_curso = new Intent(CursosActivity.this, NuevoCursoActivity.class);
                                        editar_curso.putExtra("NombreCurso", listaCursos.get(0).getNombreCurso());
                                        editar_curso.putExtra("Centro", listaCursos.get(1).getCentro());
                                        editar_curso.putExtra("NumeroAlumnos", listaCursos.get(2).getNumeroAlumnos());
                                        editar_curso.putExtra("Disponibilidad", listaCursos.get(3).getDisponibilidad());
                                        editar_curso.putExtra("Temas", listaCursos.get(4).getTemas());
                                        startActivity(editar_curso);
                                    }})
                        .setNegativeButton(R.string.lb_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }});
                builder.create().show();*/
                return true;
            case R.id.action_eliminar:
                // hacer algo
                /*AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage(R.string.lb_esta_seguro)
                        .setPositiveButton(R.string.lb_si,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"
                                        Intent eliminar_curso = new Intent(CursosActivity.this, NuevoCursoActivity.class);
                                        startActivity(eliminar_curso);
                                    }})
                        .setNegativeButton(R.string.lb_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }});
                builder2.create().show();*/
                return true;
            case R.id.action_detalles:
                // hacer algo
                /*Intent detalles_curso = new Intent(CursosActivity.this, NuevoCursoActivity.class);
                detalles_curso.putExtra("NombreCurso", listaCursos.get(0).getNombreCurso());
                detalles_curso.putExtra("Centro", listaCursos.get(1).getCentro());
                detalles_curso.putExtra("NumeroAlumnos", listaCursos.get(2).getNumeroAlumnos());
                detalles_curso.putExtra("Disponibilidad", listaCursos.get(3).getDisponibilidad());
                detalles_curso.putExtra("Temas", listaCursos.get(4).getTemas());
                startActivity(detalles_curso);*/
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
