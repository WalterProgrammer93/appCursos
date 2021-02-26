package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView titulo_login;
    EditText email_login, password_login;
    Button btn_entrar, btn_registrarse;
    UsuarioBD ubd;
    Animation animation;
    ConstraintLayout constraintLayout;
    VideoView vv_video;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animation = AnimationUtils.loadAnimation(this, R.anim.aparicion);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(20);
        constraintLayout = findViewById(R.id.layout_login);
        constraintLayout.startAnimation(animation);
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
                        Intent menu = new Intent(MainActivity.this, MenuActivity.class);
                        menu.putExtra("Email", usuario.getEmail());
                        menu.putExtra("Name", usuario.getUsername());
                        startActivity(menu);
                        email_login.setText("");
                        password_login.setText("");
                        Toast.makeText(MainActivity.this, "Bienvenido a appCursos " + usuario.getUsername(), Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(MainActivity.this, " El Login ha fallado !", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
        //Go to Register Activity
        btn_registrarse = findViewById(R.id.btn_register);
        btn_registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registrarse = new Intent(MainActivity.this, SplashToActivity.class);
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
