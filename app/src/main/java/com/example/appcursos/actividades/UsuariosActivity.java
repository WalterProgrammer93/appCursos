package com.example.appcursos.actividades;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.adaptadores.UsuarioAdaptador;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;
import java.util.ArrayList;

public class UsuariosActivity extends AppCompatActivity {

    ArrayList<Usuario> listaUsuarios;
    UsuarioAdaptador usuarioAdaptador;
    RecyclerView rvUsuarios;
    UsuarioBD ubd;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_usuarios);

        ubd = new UsuarioBD(this);
        listaUsuarios = new ArrayList<>();
        rvUsuarios = findViewById(R.id.rvUsuarios);
        rvUsuarios.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        rvUsuarios.setLayoutManager(layoutManager);
        listaUsuarios = ubd.listarUsuarios();
        ubd.cerrarBD();
        usuarioAdaptador = new UsuarioAdaptador(listaUsuarios);
        rvUsuarios.setAdapter(usuarioAdaptador);
        registerForContextMenu(rvUsuarios);
        usuarioAdaptador.notifyDataSetChanged();
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
                Intent intent = new Intent(UsuariosActivity.this, NuevoUsuarioActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_salir:
                finish();
                Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(UsuariosActivity.this, MainActivity.class);
                startActivity(i);
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

        switch (item.getItemId()) {// hacer algo
            case R.id.action_editar:
                showDialog(0);
                return true;
            case R.id.action_eliminar:
                showDialog(1);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public Dialog onCreateDialog(int id) {
        Dialog dialogo;
        switch (id) {
            case 0:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.titulo_editar)
                        .setMessage(R.string.msg_editar)
                        .setPositiveButton(R.string.lb_si,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"
                                        Intent editar = new Intent(UsuariosActivity.this, EditarUsuarioActivity.class);
                                        startActivity(editar);
                                    }
                                })
                        .setNegativeButton(R.string.lb_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }
                                });
                dialogo = builder.create();
                break;
            case 1:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle(R.string.titulo_eliminar)
                        .setMessage(R.string.msg_eliminar)
                        .setPositiveButton(R.string.lb_si,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "Si"

                                    }
                                })
                        .setNegativeButton(R.string.lb_no,
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Qué hacer si el usuario pulsa "No"
                                        // En este caso se cierra directamente el diálogo y no se hace nada más
                                        dialog.dismiss();
                                    }
                                });
                dialogo = builder2.create();
                break;
            default:
                return super.onCreateDialog(id);

        }
        return dialogo;
    }
}
