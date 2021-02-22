package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;

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

            }
        });

        b_editarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b_eliminarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        b_buscarUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
}
