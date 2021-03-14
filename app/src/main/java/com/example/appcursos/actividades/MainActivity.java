package com.example.appcursos.actividades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.PendingIntent;
import android.content.DialogInterface;
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
import com.example.appcursos.R;
import com.example.appcursos.bd.UsuarioBD;
import com.example.appcursos.modelos.Usuario;
import java.util.Random;

import javax.crypto.SecretKey;

import static com.example.appcursos.bd.UsuarioBD.generateKey;

public class MainActivity extends AppCompatActivity {

    ImageView iv;
    TextView titulo_login;
    EditText email_login, password_login;
    Button btn_entrar, btn_registrarse;
    UsuarioBD ubd;
    Animation animation;
    ConstraintLayout constraintLayout;
    int PROGRESS_MAX = 100;
    int PROGRESS_CURRENT = 0;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showDialog(0);
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

                        menu.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(MainActivity.this, 0, menu, 0);

                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(MainActivity.this);
                        Random random = new Random();
                        NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                        builder.setSmallIcon(R.drawable.logo);
                        builder.setContentTitle("Menu appcursos");
                        builder.setContentText("Bienvenido al menu de appcursos!").setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false);
                        builder.setPriority(NotificationCompat.PRIORITY_HIGH);
                        // Set the intent that will fire when the user taps the notification
                        builder.setContentIntent(pendingIntent);
                        builder.setAutoCancel(true);
                        notificationManager.notify(random.nextInt(), builder.build());

                        menu.putExtra("Username", usuario.getUsername());
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

    protected Dialog onCreateDialog(int id) {
        // Use the Builder class for convenient dialog construction
        Dialog dialogo = null;
        if (id == 0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(R.string.bienvenida)
                    .setNegativeButton(R.string.lb_cancelar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // User cancelled the dialog
                            dialog.dismiss();
                        }
                    });
            // Create the AlertDialog object and return it
            dialogo = builder.create();
        }
        return dialogo;
    }

}
