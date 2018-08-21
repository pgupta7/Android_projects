package com.example.android.newsfeedapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasoon Gupta on 1/1/2017.
 */

public class Recyclerviewadapterclass extends RecyclerView.Adapter<Recyclerviewadapterclass.ViewHolder>{
    private static final String LOG_CAT=Recyclerviewadapterclass.class.getSimpleName();
    private ArrayList<NewsList> newsList;
    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView titletext;
        private TextView sectionnametext;
        private NewsList newsListitem;
        public ViewHolder(View v){
            super(v);
            v.setOnClickListener(this);
            titletext=(TextView) v.findViewById(R.id.title);
            sectionnametext=(TextView) v.findViewById(R.id.sectionname);
        }
        void getarraylist(NewsList mnewslistitem){
            newsListitem=mnewslistitem;
            titletext.setText(mnewslistitem.gettitle());
            sectionnametext.setText(mnewslistitem.getSection());
        }

        @Override
        public void onClick(View v) {
            String weburl=newsListitem.getWeburl();
            if(!weburl.isEmpty()){
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(weburl));
                v.getContext().startActivity(i);
            }else {
                Toast.makeText(v.getContext(),R.string.no_url_present,Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Recyclerviewadapterclass(ArrayList<NewsList> mnewslist){
        newsList=mnewslist;
        Log.v(LOG_CAT,"size of the list "+newsList.size());
    }

    @Override
    public Recyclerviewadapterclass.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.getarraylist(newsList.get(position));

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

}
