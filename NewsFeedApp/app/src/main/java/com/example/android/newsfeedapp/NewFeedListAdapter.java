package com.example.android.newsfeedapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Prasoon Gupta on 12/30/2016.
 */

public class NewFeedListAdapter extends ArrayAdapter<NewsList>{
        public static final String LOG_CAT=NewFeedListAdapter.class.getSimpleName();
    public NewFeedListAdapter(Context context, ArrayList<NewsList> newsLists){
        super(context,0,newsLists);
    }

    static class ViewHolder{
        @BindView(R2.id.title) TextView titletext;
        @BindView(R2.id.sectionname) TextView sectionnametext;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }

    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.listitem,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder=(ViewHolder) convertView.getTag();
        }

        NewsList newslist1=getItem(position);
        viewHolder.titletext.setText(newslist1.gettitle());
        viewHolder.sectionnametext.setText(newslist1.getSection());

        return convertView;
    }
}
