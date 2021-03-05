package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
//import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.appcursos.R;

import java.io.IOException;

public class MultimediaActivity extends AppCompatActivity {

    TextView tv_label1, tv_label2, tv_label3, tv_label4, tv_label5;
    VideoView vv_video1, vv_video2, vv_video3, vv_video4, vv_video5;
    //Uri video;
    String streamingURL = "https://thirdrockradio.net/";

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        tv_label1 = findViewById(R.id.tv_label1);
        vv_video1 = findViewById(R.id.vv_video1);
        tv_label2 = findViewById(R.id.tv_label2);
        vv_video2 = findViewById(R.id.vv_video2);
        tv_label3 = findViewById(R.id.tv_label3);
        vv_video3 = findViewById(R.id.vv_video3);
        tv_label4 = findViewById(R.id.tv_label4);
        vv_video4 = findViewById(R.id.vv_video4);
        tv_label5 = findViewById(R.id.tv_label5);
        vv_video5 = findViewById(R.id.vv_video5);

        String path = "android.resource://" + getPackageName() + "/" +
                R.raw.intro;
        vv_video1.setVideoURI(Uri.parse(path));
        //vv_video1.setMediaController(new MediaController(this));
        vv_video1.start();
        vv_video1.requestFocus();

        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.start();
            }
        });
        try {
            mediaPlayer.setDataSource(streamingURL);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //mediaPlayer.prepareAsync();
    }
}
