package com.example.appcursos.actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.widget.MediaController;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import com.example.appcursos.R;

public class VideosActivity extends AppCompatActivity {

    TextView tv_label1, tv_label2, tv_label3, tv_label4, tv_label5;
    VideoView vv_video1, vv_video2, vv_video3, vv_video4, vv_video5;

    @SuppressLint("SdCardPath")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

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
                R.raw.video1;
        vv_video1.setVideoURI(Uri.parse(path));
        //vv_video1.setMediaController(new MediaController(this));
        vv_video1.start();
        vv_video1.requestFocus();

        String path2 = "android.resource://" + getPackageName() + "/" +
                R.raw.video2;
        vv_video2.setVideoURI(Uri.parse(path2));
        vv_video2.start();
        vv_video2.requestFocus();

        String path3 = "android.resource://" + getPackageName() + "/" +
                R.raw.video3;
        vv_video3.setVideoURI(Uri.parse(path3));
        vv_video3.setMediaController(new MediaController(this));
        vv_video3.start();
        vv_video3.requestFocus();

        String path4 = "android.resource://" + getPackageName() + "/" +
                R.raw.video4;
        vv_video4.setVideoURI(Uri.parse(path4));
        vv_video4.setMediaController(new MediaController(this));
        vv_video4.start();
        vv_video4.requestFocus();

        String path5 = "android.resource://" + getPackageName() + "/" +
                R.raw.video5;
        vv_video5.setVideoURI(Uri.parse(path5));
        vv_video5.setMediaController(new MediaController(this));
        vv_video5.start();
        vv_video5.requestFocus();
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
            Intent salir = new Intent(VideosActivity.this, MainActivity.class);
            startActivity(salir);
        }
        return super.onOptionsItemSelected(item);

    }
}
