package com.example.android.musicplayerapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GetMoreInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_more_info);
        Song song=(Song)getIntent().getSerializableExtra("songobject");
        TextView songnametext=(TextView)findViewById(R.id.songname);
        songnametext.setText(song.getSongtitle());

        TextView composertext=(TextView)findViewById(R.id.composer);
        composertext.setText(song.getComposer());

        TextView singertext=(TextView)findViewById(R.id.singer);
        singertext.setText(song.getSinger());

        TextView publicationyeartext=(TextView)findViewById(R.id.publicationyear);
        publicationyeartext.setText(song.getPublicationyear());
    }
}
