package com.example.android.newsfeedapp;

/**
 * Created by Prasoon Gupta on 12/23/2016.
 */
public class NewsList {
    private String section;
    private String title;
    private String weburl;

    public NewsList(String msection, String mtitle, String mweburl){
        section=msection;
        title=mtitle;
        weburl=mweburl;
    }

    public String gettitle(){return title;}
    public String getSection(){return section;}
    public String getWeburl(){return weburl;}
}
