package com.example.android.musicplayerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasoon Gupta on 11/30/2016.
 */
public class AudioAdapter extends ArrayAdapter<Song>{
    public AudioAdapter(Context context, ArrayList<Song> audios){
        super(context,0,audios);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView=LayoutInflater.from(getContext()).inflate(R.layout.audiolist,parent,false);
        }

        TextView audionametext=(TextView)convertView.findViewById(R.id.audioname);
        Song song=getItem(position);
        audionametext.setText(song.getSongtitle());

        return convertView;
    }
}
