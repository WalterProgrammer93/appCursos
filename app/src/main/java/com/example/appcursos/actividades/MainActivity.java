package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView titulo_login;
    EditText email_login, password_login;
    Button btn_entrar, btn_registrarse;
    UsuarioBD ubd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv_logo);
        titulo_login = findViewById(R.id.et_titulo);
        email_login = findViewById(R.id.et_email);
        password_login = findViewById(R.id.et_password);
        ubd = new UsuarioBD(this);

        //Go to Register Activity
        btn_entrar = findViewById(R.id.btn_entrar);
        btn_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()) {

                    //Get values from EditText fields
                    String email = email_login.getText().toString();
                    String password = password_login.getText().toString();

                    //Authenticate User
                    Usuario usuario = ubd.Authenticate(new Usuario(email, password));

                    //Check Authentication is successful or not
                    if (usuario != null) {
                        Intent menu = new Intent(getApplicationContext(), MenuActivity.class);
                        menu.putExtra("Email", usuario.getEmail());
                        menu.putExtra("Name", usuario.getUsername());
                        startActivity(menu);
                        email_login.setText("");
                        password_login.setText("");
                        Toast.makeText(getApplicationContext(), "Bienvenido a appCursos" + usuario.getUsername(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(getApplicationContext(), " Failed to Logged in!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        //Go to Register Activity
        btn_registrarse = findViewById(R.id.btn_register);
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrarse = new Intent(getApplicationContext(), RegistrarseActivity.class);
                startActivity(registrarse);
            }
        });

    }
    private boolean validate() {
        boolean valid;

        //Get values from EditText fields
        String email = email_login.getText().toString();
        String pass = password_login.getText().toString();

        //Handling validation
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false;
            email_login.setError("Please enter valid Email");
        } else {
            valid = true;
            email_login.setError(null);

        }

        //Handling Password
        if (pass.isEmpty()) {
            valid = false;
            password_login.setError("Please enter Valid Password");
        }
        return valid;

    }

}
