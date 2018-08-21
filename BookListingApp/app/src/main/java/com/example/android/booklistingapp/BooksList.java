package com.example.android.booklistingapp;

import java.util.ArrayList;

/**
 * Created by Prasoon Gupta on 11/20/2016.
 */
public class BooksList {
    private String bookname=null;
    private ArrayList<String> authorlist=new ArrayList<String>();
    private String publicationdate="";
    private String weblink="";

    public BooksList(String mbookname,ArrayList<String> mauthorlist,String mpublicationdate,String mweblink){
        bookname=mbookname;
        authorlist=mauthorlist;
        publicationdate=mpublicationdate;
        weblink=mweblink;
    }
    public BooksList(String mbookname,ArrayList<String> mauthorlist){
        bookname=mbookname;
        authorlist=mauthorlist;
    }

    public String getBookname(){
        return bookname;
    }
    public String getPublicationdate(){
        return publicationdate;
    }
    public ArrayList<String> getAuthorlist(){
        return authorlist;
    }
    public String getWeblink(){
        return weblink;
    }
}
