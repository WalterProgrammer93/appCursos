package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.example.appcursos.R;

public class AudiosActivity extends AppCompatActivity {

    MediaPlayer audio_intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audios);

        audio_intro = MediaPlayer.create(this, R.raw.audio_intro);
        audio_intro.start();
    }
}
