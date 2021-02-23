package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;

public class NuevoUsuarioActivity extends AppCompatActivity {

    EditText et_username, et_email, et_password;
    Button b_altaUser, b_editarUser, b_eliminarUser, b_buscarUser, b_cancelar;
    UsuarioBD ubd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_usuario);

        ubd = new UsuarioBD(this);
        et_username = findViewById(R.id.et_nombreUsuario);
        et_email = findViewById(R.id.et_emailUsuario);
        et_password = findViewById(R.id.et_passwordUsuario);
        b_altaUser = findViewById(R.id.altaUsuario);
        b_editarUser = findViewById(R.id.editarUsuario);
        b_eliminarUser = findViewById(R.id.eliminarUsuario);
        b_buscarUser = findViewById(R.id.buscarUsuario);
        b_cancelar = findViewById(R.id.cancelarUsuario);

        b_altaUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (validarDatos()) {
                    String username = et_username.getText().toString();
                    String email = et_email.getText().toString();
                    String password = et_password.getText().toString();
                    Usuario usuario = new Usuario(username, email, password);
                    if (!ubd.isUsuarioExists(usuario.getUsername())) {
                        ubd.insertarUsuario(usuario);
                        et_username.setText("");
                        et_email.setText("");
                        et_password.setText("");
                        Toast.makeText(NuevoUsuarioActivity.this, "El usuario se ha creado correctamente", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(NuevoUsuarioActivity.this, UsuariosActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(NuevoUsuarioActivity.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
                    }
                /*} else {
                    Toast.makeText(NuevoUsuarioActivity.this, "Se ha producido un error en la insercion del usuario", Toast.LENGTH_SHORT).show();
                }*/
            }
        });

        b_editarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();
                Usuario usuario = new Usuario(username, email, password);
                int cant = ubd.editarUsuario(username, usuario);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "se modificaron los datos del usuario", Toast.LENGTH_SHORT)
                            .show();
                    Intent intent = new Intent(NuevoUsuarioActivity.this, UsuariosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el usuario",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_eliminarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                int cant = ubd.eliminarUsuario(username);
                if (cant == 1) {
                    Toast.makeText(getApplicationContext(), "Se borró el usuario correctamente",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(NuevoUsuarioActivity.this, UsuariosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el usuario",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_buscarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = et_username.getText().toString();
                Cursor fila = (Cursor) ubd.buscarUsuario(username);
                if (fila.moveToFirst()) {
                    et_email.setText(fila.getString(2));
                    et_password.setText(fila.getString(3));
                    Intent intent = new Intent(NuevoUsuarioActivity.this, UsuariosActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "No existe el usuario",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        b_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NuevoUsuarioActivity.this, UsuariosActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu3, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_salir) {
            finish();
            Toast.makeText(getApplicationContext(), "Ha salido correctamente", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(NuevoUsuarioActivity.this, MainActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);

    }

    /*private boolean validarDatos() {
    }*/
}
