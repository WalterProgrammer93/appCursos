package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.appcursos.R;
import java.io.IOException;

public class AudiosActivity extends AppCompatActivity {

    MediaPlayer audio_intro, audio_android;
    Button btnPlay1, btnPause1, btnStop1;
    Button btnPlay2, btnPause2, btnStop2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audios);

        audio_intro = MediaPlayer.create(this, R.raw.audio1);
        audio_intro.start();

        audio_android = MediaPlayer.create(this, R.raw.intro_android_video);
        btnPlay1 = findViewById(R.id.buttonPlay1);
        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio_android.start();
            }
        });
        btnPause1 = findViewById(R.id.buttonPause1);
        btnPause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                audio_android.pause();
            }
        });
        btnStop1 = findViewById(R.id.buttonStop1);
        btnStop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    audio_android.stop();
                    audio_android.prepare();
                    audio_android.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPlay2 = findViewById(R.id.buttonPlay2);
        btnPause2 = findViewById(R.id.buttonPause2);
        btnStop2 = findViewById(R.id.buttonStop2);

    }
}
