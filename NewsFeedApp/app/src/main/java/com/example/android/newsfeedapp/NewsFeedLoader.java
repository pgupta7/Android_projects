package com.example.android.newsfeedapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Prasoon Gupta on 12/21/2016.
 */
public class NewsFeedLoader extends AsyncTaskLoader<ArrayList<NewsList>> {
    private final static String LOG_CAT=NewsFeedLoader.class.getSimpleName();
    String resourceurl;
    public NewsFeedLoader(Context context, String murl){
        super(context);
        resourceurl=murl;
    }

    @Override
    public ArrayList<NewsList> loadInBackground() {
        return QueryUtils.extractnewslist(resourceurl);
    }
}
