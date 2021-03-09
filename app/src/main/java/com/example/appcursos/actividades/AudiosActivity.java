package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.appcursos.R;

public class AudiosActivity extends AppCompatActivity {

    MediaPlayer mp1, mp2, mp3;
    Button btnPlay1, btnPause1, btnStop1;
    Button btnPlay2, btnPause2, btnStop2;

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
                mp2.stop();
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
                mp3.stop();
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
            Intent salir = new Intent(AudiosActivity.this, MainActivity.class);
            startActivity(salir);
        }
        return super.onOptionsItemSelected(item);

    }
}
