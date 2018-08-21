package com.example.android.musicplayerapp;

import android.content.Context;
import android.media.AudioManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prasoon Gupta on 11/27/2016.
 */
public class AudioListAdapter extends ArrayAdapter<String>{
    public AudioListAdapter(Context context, ArrayList<String> audiolist){
        super(context,0,audiolist);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.audiolist,parent,false);
        }
        TextView audioname=(TextView)convertView.findViewById(R.id.audioname);
        audioname.setText(getItem(position));
        return convertView;
    }
}
