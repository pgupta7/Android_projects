package com.example.android.booklistingapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Prasoon Gupta on 11/18/2016.
 */
public class BookListLoader extends AsyncTaskLoader<ArrayList<BooksList>> {
    String resourseurl;
    public BookListLoader(Context context, String murl){
        super(context);
        resourseurl=murl;
    }

    @Override
    public ArrayList<BooksList> loadInBackground() {
        return QueryUtils.extractbooklist(resourseurl);
    }
}
