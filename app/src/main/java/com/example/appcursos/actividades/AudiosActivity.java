package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.appcursos.R;
import java.io.IOException;

public class AudiosActivity extends AppCompatActivity {

    MediaPlayer mp1, mp2, mp3, mp4;
    Button btnPlay1, btnPause1, btnStop1;
    Button btnPlay2, btnPause2, btnStop2;
    Button btnPlay3, btnPause3, btnStop3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audios);

        mp1 = MediaPlayer.create(this, R.raw.audio1);
        mp1.start();

        mp2 = MediaPlayer.create(this, R.raw.audio2);
        btnPlay1 = findViewById(R.id.buttonPlay1);
        btnPlay1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2.start();
            }
        });
        btnPause1 = findViewById(R.id.buttonPause1);
        btnPause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp2.pause();
            }
        });
        btnStop1 = findViewById(R.id.buttonStop1);
        btnStop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp2.stop();
                    mp2.prepare();
                    mp2.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mp3 = MediaPlayer.create(this, R.raw.audio3);
        btnPlay2 = findViewById(R.id.buttonPlay2);
        btnPlay2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp3.start();
            }
        });
        btnPause2 = findViewById(R.id.buttonPause2);
        btnPause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp3.pause();
            }
        });
        btnStop2 = findViewById(R.id.buttonStop2);
        btnStop2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp3.stop();
                    mp3.prepare();
                    mp3.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        mp4 = MediaPlayer.create(this, R.raw.audio4);
        btnPlay3 = findViewById(R.id.buttonPlay3);
        btnPlay3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp4.start();
            }
        });
        btnPause3 = findViewById(R.id.buttonPause3);
        btnPause3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp4.pause();
            }
        });
        btnStop3 = findViewById(R.id.buttonStop3);
        btnStop3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    mp4.stop();
                    mp4.prepare();
                    mp4.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
