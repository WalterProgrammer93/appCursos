package com.example.appcursos.actividades;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class RegistrarseActivity extends AppCompatActivity {

    EditText et_username, et_email, et_password, et_confirmPassword;
    Button btn_anadir, btn_cancelar;
    UsuarioBD ubd;
    Animation animation;
    ConstraintLayout constraintLayout;
    private static final String AES = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);

        animation = AnimationUtils.loadAnimation(this, R.anim.aparicion);
        animation.setRepeatMode(Animation.RESTART);
        animation.setRepeatCount(20);
        constraintLayout = findViewById(R.id.layout_register);
        constraintLayout.startAnimation(animation);
        ubd = new UsuarioBD(this);
        et_username = findViewById(R.id.et_usuario);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        et_confirmPassword = findViewById(R.id.et_confirmPassword);
        btn_anadir = findViewById(R.id.btn_añadir);
        btn_cancelar = findViewById(R.id.btn_cancelar);

        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                // AGREGAR USUARIO
                if (validar()) {
                    String username = et_username.getText().toString();
                    String email = et_email.getText().toString();
                    String pass = et_password.getText().toString();
                    String confirmPassword = et_confirmPassword.getText().toString();

                    try {
                        encriptar(pass);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    if (confirmPassword.equals(pass) && !ubd.isEmailExists(email)) {
                        ubd.insertarUsuario(new Usuario(username, email, pass));
                        Toast.makeText(RegistrarseActivity.this, " Usuario creado correctamente ", Toast.LENGTH_SHORT).show();
                        Intent acceso = new Intent(RegistrarseActivity.this, MenuActivity.class);
                        acceso.putExtra("Username", username);
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

    @SuppressLint("GetInstance")
    public static String encriptar(String pass) throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesSecretKey = secretKey.getEncoded();
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytesSecretKey, AES);
        Cipher cipher = Cipher.getInstance(AES);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encriptado = cipher.doFinal(pass.getBytes());

        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] desencriptado = cipher.doFinal(encriptado);
        Log.d("TAG", Arrays.toString(desencriptado));
        return new String(desencriptado);
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
