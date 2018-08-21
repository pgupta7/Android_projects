package com.example.android.booklistingapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Prasoon Gupta on 11/21/2016.
 */
public class BookListAdapter extends ArrayAdapter<BooksList>{
    private static final String LOG_CAT=BookListAdapter.class.getSimpleName();
    public BookListAdapter(Context context, ArrayList<BooksList> booksLists){
        super(context,0,booksLists);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.booklist,parent,false);
        }

        BooksList booksList=getItem(position);
        TextView booktitle=(TextView)convertView.findViewById(R.id.title);
        booktitle.setText(booksList.getBookname());

        TextView authorslist=(TextView)convertView.findViewById(R.id.authorslist);
        ArrayList<String> authors=booksList.getAuthorlist();
        StringBuilder authorsstring=new StringBuilder();

        authorsstring.append("");
        Log.v(LOG_CAT,"authors list size"+authors.size());
        if(authors.size()>0){
            if(authors.size()>1){
                for(int i=0;i<authors.size()-1;i++){
                    authorsstring.append(authors.get(i)).append(", ");
                }
                authorsstring.append("and ").append(authors.get(authors.size()-1));
            }else {
                authorsstring.append(authors.get(0));
            }
            authorslist.setText(authorsstring.toString());
        }else authorslist.setVisibility(View.GONE);

        return convertView;
    }
}
