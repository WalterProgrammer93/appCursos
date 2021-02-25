package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.appcursos.R;

public class SplashToActivity extends AppCompatActivity {

    Animation animacion;
    ImageView iv_splash;
    TextView tv_splash;
    ProgressBar pb_splash;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_activity);

        animacion = AnimationUtils.loadAnimation(this, R.anim.aparicion);
        iv_splash = findViewById(R.id.iv_splash);
        tv_splash = findViewById(R.id.tv_splash);
        pb_splash = findViewById(R.id.pb_splash);
        animacion.setRepeatMode(Animation.RESTART);
        animacion.setRepeatCount(20);
        iv_splash.startAnimation(animacion);
        tv_splash.startAnimation(animacion);
        pb_splash.startAnimation(animacion);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent = new Intent(SplashToActivity.this, RegistrarseActivity.class);
                SplashToActivity.this.startActivity(intent);
                SplashToActivity.this.finish();
            }
        }, 3000);
    }
}
