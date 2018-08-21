package com.example.android.musicplayerapp;

import android.widget.ProgressBar;

import java.io.Serializable;

/**
 * Created by Prasoon Gupta on 11/30/2016.
 */
public class Song implements Serializable{
    private String songtitle, composer, singer, publicationyear;
    private int songid;
    public Song(String msongtitle, String mcomposer, String msinger, String mpublicationyear,int msongid){
        songtitle=msongtitle;
        composer=mcomposer;
        singer=msinger;
        publicationyear=mpublicationyear;
        songid=msongid;
    }

    public String getSongtitle(){
        return songtitle;
    }
    public String getComposer(){
        return composer;
    }
    public String getSinger(){
        return singer;
    }
    public String getPublicationyear(){
        return publicationyear;
    }
    public int getSongid(){
        return songid;
    }
}
