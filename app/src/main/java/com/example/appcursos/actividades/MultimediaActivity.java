package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.appcursos.R;

public class MultimediaActivity extends AppCompatActivity {

    TextView tv_label1;
    VideoView vv_video;
    Uri video;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multimedia);

        tv_label1 = findViewById(R.id.tv_label1);
        vv_video = findViewById(R.id.vv_video1);
        /*String path = "android.resource://" + getPackageName() + "/" +
                R.raw.intro;
        vv_video.setVideoURI(Uri.parse(path));*/
        vv_video.setVideoPath("/mnt/sdcard/DCIM/Camera/VID_20180811_225944.mp4");
        //mVideoView.setMediaController(new MediaController(this));
        vv_video.start();
        vv_video.requestFocus();
    }
}
