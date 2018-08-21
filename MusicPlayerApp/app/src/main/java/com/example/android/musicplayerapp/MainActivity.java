package com.example.android.musicplayerapp;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements Serializable{
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener onCompletionListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            releasemediaplayer();
        }
    };
    private AudioManager audioManager;
    private AudioManager.OnAudioFocusChangeListener onAudioFocusChangeListener=new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                mediaPlayer.pause();
            }
            if(focusChange==AudioManager.AUDIOFOCUS_LOSS){
                releasemediaplayer();
            }
            if(focusChange==AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
                mediaPlayer.seekTo(0);
            }
        }
    };

    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        audioManager=(AudioManager)this.getSystemService(Context.AUDIO_SERVICE);
        listView=(ListView)findViewById(R.id.list);
        ArrayList<Song> audioname=new ArrayList<Song>();
        audioname.add(new Song("ABC","Me","Me","2016",R.raw.abc));
        audioname.add(new Song("DEF","Me","I","2016",R.raw.def));
        audioname.add(new Song("GHI","I","I","2015",R.raw.ghi));

        AudioAdapter audioAdapter=new AudioAdapter(this,audioname);

        //listView.setAdapter(new ArrayAdapter<String>(this,R.layout.audiolist,R.id.audioname,audioname));
        listView.setAdapter(audioAdapter);
        final Intent nowplayingintent=new Intent(this,NowPlayingActivity.class);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song=(Song)listView.getItemAtPosition(position);
                /*nowplayingintent.putExtra("songtitle",song.getSongtitle());
                nowplayingintent.putExtra("songid",song.getSongid());
                nowplayingintent.putExtra("composer",song.getComposer());
                nowplayingintent.putExtra("singer",song.getSinger());
                nowplayingintent.putExtra("publicationyear",song.getPublicationyear());*/
                nowplayingintent.putExtra("songobj",song);
                startActivity(nowplayingintent);
                //new PlaymediaTask().execute();
                /*int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
                if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.recording);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }*/
            }
        });

        while (mediaPlayer!=null){
            try{
                Toast.makeText(this,mediaPlayer.getCurrentPosition(),Toast.LENGTH_SHORT).show();
                Thread.sleep(1000);
            }catch (Exception e){

            }

        }



    }

    private void releasemediaplayer(){
        if(mediaPlayer!=null) {
            mediaPlayer.release();
            mediaPlayer=null;
            audioManager.abandonAudioFocus(onAudioFocusChangeListener);
        }
    }

    private class PlaymediaTask extends AsyncTask<Void, Integer, Void>{
        @Override
        protected Void doInBackground(Void... params) {
            int result=audioManager.requestAudioFocus(onAudioFocusChangeListener,AudioManager.STREAM_MUSIC,AudioManager.AUDIOFOCUS_GAIN);
            if(result==AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.recording);
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(onCompletionListener);
            }
            while (mediaPlayer!=null){
                publishProgress(mediaPlayer.getCurrentPosition());
                try{
                    Thread.sleep(1000);
                }catch (Exception e){

                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Toast.makeText(getApplicationContext(),""+values[0],Toast.LENGTH_SHORT).show();
        }

    }
}
