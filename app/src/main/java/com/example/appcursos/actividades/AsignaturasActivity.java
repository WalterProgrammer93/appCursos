package com.example.appcursos.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
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
import com.example.appcursos.adaptadores.AsignaturaAdaptador;
import com.example.appcursos.modelos.Asignatura;
import java.util.ArrayList;

public class AsignaturasActivity extends AppCompatActivity {

    ArrayList<Asignatura> listaAsignaturas;
    AsignaturaAdaptador asignaturaAdaptador;
    ListView lvAsignaturas;
    ImageView ivAsignaturas;
    TextView tvNombreAsignatura, tvCurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asignaturas);

        listaAsignaturas = new ArrayList<>();
        asignaturaAdaptador = new AsignaturaAdaptador(this, R.layout.item_asignaturas, listaAsignaturas);
        ivAsignaturas = findViewById(R.id.ivAsignatura);
        tvNombreAsignatura = findViewById(R.id.tvNombreAsignatura);
        tvCurso = findViewById(R.id.tvCurso);
        lvAsignaturas = findViewById(R.id.lvAsignaturas);
        lvAsignaturas.setAdapter(asignaturaAdaptador);
        registerForContextMenu(lvAsignaturas);

        /*try {
            Bundle recibir = getIntent().getExtras();
            if (recibir != null) {
                String nombreAsig = recibir.getString("NombreAsignatura");
                String curso = recibir.getString("Curso");
                tvNombreAsignatura.setText(nombreAsig);
                tvCurso.setText(curso);
                listaAsignaturas.add((Asignatura) tvNombreAsignatura.getText());
                listaAsignaturas.add((Asignatura) tvCurso.getText());
            }
        } catch (Exception e) {
            lvAsignaturas.setEmptyView(tvNombreAsignatura);
            lvAsignaturas.setEmptyView(tvCurso);
        }*/
        asignaturaAdaptador.notifyDataSetChanged();
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
                Intent nuevo_asignatura = new Intent(AsignaturasActivity.this, NuevoAsignaturaActivity.class);
                startActivity(nuevo_asignatura);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
                Intent salir = new Intent(AsignaturasActivity.this, MainActivity.class);
                startActivity(salir);
                return true;
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
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(R.string.lb_esta_seguro)
                        .setPositiveButton(R.string.lb_si,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"
                                        Intent editar_curso = new Intent(AsignaturasActivity.this, NuevoAsignaturaActivity.class);
                                        editar_curso.putExtra("NombreAsignatura", listaAsignaturas.get(0).getNombreAsignatura());
                                        editar_curso.putExtra("DescripcionAsignatura", listaAsignaturas.get(1).getDescripcionAsignatura());
                                        editar_curso.putExtra("Curso", listaAsignaturas.get(2).getCurso());
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
                builder.create().show();
                return true;
            case R.id.action_eliminar:
                // hacer algo
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setMessage(R.string.lb_esta_seguro)
                        .setPositiveButton(R.string.lb_si,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"
                                        Intent eliminar_asignatura = new Intent(AsignaturasActivity.this, NuevoAsignaturaActivity.class);
                                        startActivity(eliminar_asignatura);
                                    }})
                        .setNegativeButton(R.string.lb_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }});
                builder2.create().show();
                return true;
            case R.id.action_buscar:
                // hacer algo

                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
