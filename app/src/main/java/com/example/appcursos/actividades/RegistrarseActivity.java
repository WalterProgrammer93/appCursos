package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;

public class RegistrarseActivity extends AppCompatActivity {

    EditText et_username, et_email, et_password, et_confirmPassword;
    Button btn_anadir, btn_cancelar;
    UsuarioBD ubd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        ubd = new UsuarioBD(this);

        et_username = findViewById(R.id.et_usuario);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btn_anadir = findViewById(R.id.btn_añadir);
        btn_cancelar = findViewById(R.id.btn_cancelar);

        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // AGREGAR USUARIO
                if (validar()) {
                    String username = et_username.getText().toString();
                    String email = et_email.getText().toString();
                    String pass = et_password.getText().toString();
                    String confirmPassword = et_confirmPassword.getText().toString();

                    if (confirmPassword.equals(pass) && !ubd.isEmailExists(email)) {
                        ubd.insertarUsuario(new Usuario(username, email, pass));
                        Toast.makeText(RegistrarseActivity.this, "Usuario creado correctamente! Por favor inicie sesion ", Toast.LENGTH_SHORT).show();
                        Intent acceso = new Intent(RegistrarseActivity.this, MenuActivity.class);
                        startActivity(acceso);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, Toast.LENGTH_LONG);

                    } else if (!confirmPassword.equals(pass)) {
                        Toast.makeText(RegistrarseActivity.this, "La contraseña no coincide ", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(RegistrarseActivity.this, "El usuario ya existe, Verifique su email ", Toast.LENGTH_SHORT).show();

                    }
                }

            }
        });
        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // REGRESAR AL LOGIN
                Intent cancelar = new Intent(RegistrarseActivity.this, MainActivity.class);
                startActivity(cancelar);
            }
        });

    }

    private boolean validar() {

        boolean valido;
        String UserName = et_username.getText().toString();
        String Email = et_email.getText().toString();
        String Password = et_password.getText().toString();

        if (UserName.isEmpty()) {
            valido = false;
            et_username.setError("Please enter valid username!");
        } else {
            if (UserName.length() > 5) {
                valido = true;
                et_username.setError(null);
            } else {
                valido = false;
                et_username.setError("Username is to short!");
            }
        }

        //Handling validation for Email field
        if (!Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
            valido = false;
            et_email.setError("Please enter valid email!");
        } else {
            valido = true;
            et_email.setError(null);
        }

        //Handling validation for Password field
        if (Password.isEmpty()) {
            valido = false;
            et_password.setError("Please enter valid password!");
        } else {
            if (Password.length() > 2) {
                valido = true;
                et_password.setError(null);
            } else {
                valido = false;
                et_password.setError("Password is to short!");
            }
        }
        return valido;
    }

}
