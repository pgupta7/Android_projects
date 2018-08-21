package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Prasoon Gupta on 11/20/2016.
 */
public class QueryUtils{
    public static final String LOG_CAT=QueryUtils.class.getSimpleName();
    public QueryUtils(){

    }

    public static ArrayList<BooksList> extractbooklist(String resourceurl){
        URL murl=createUrl(resourceurl);
        String jsonresponse=makeHTTPrequest(murl);

        ArrayList<BooksList> booksList=new ArrayList<BooksList>();
        try {
            JSONObject bookjsonobject=new JSONObject(jsonresponse);
            if(bookjsonobject.has("items")){
                JSONArray jsonArray=bookjsonobject.getJSONArray("items");
                JSONArray authorjsonlist=null;      //authors list in json array format
                ArrayList<String> authorslist1;
                int i=0;
                JSONObject eachitem=null;
                JSONObject tempvar=null;
                while (i<jsonArray.length()){
                    authorslist1=new ArrayList<String>();
                    eachitem=(JSONObject) jsonArray.get(i);
                    tempvar=(JSONObject)eachitem.get("volumeInfo");
                    if(tempvar.has("authors")&&tempvar.has("title")){
                        authorjsonlist=tempvar.getJSONArray("authors");
                        //Log.v(LOG_CAT,"inside the loop, extracting the json object");
                        for (int j=0;j<authorjsonlist.length();j++) {
                            authorslist1.add(authorjsonlist.get(j).toString());
                        }
                        booksList.add(new BooksList(tempvar.getString("title"),authorslist1));
                        i++;
                    }else {
                        i++;
                    }

                }
            }
        }catch (JSONException e){
            Log.e(LOG_CAT,"trouble creating json object",e);
        }

        for (int i=0;i<booksList.size();i++){
            if(booksList.get(i).getAuthorlist().size()>0)
                Log.v(LOG_CAT,"authors list"+booksList.get(i).getAuthorlist().get(0));
        }

        return booksList;
    }

    private static URL createUrl(String resourceurl){
        URL url=null;
        try {
            url=new URL(resourceurl);
        }catch (MalformedURLException e){
            Log.e(LOG_CAT,"url not formed correctly",e);
        }
        return url;
    }

    private static String makeHTTPrequest(URL url){
        String jsonresponse="";
        if(url==null)
            return jsonresponse;
        HttpURLConnection urlConnection=null;
        InputStream inputStream=null;
        try {
            Log.v(LOG_CAT,"\t"+url);
            urlConnection=(HttpURLConnection)url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            Log.v(LOG_CAT,"\t"+urlConnection.getResponseCode());
            if(urlConnection.getResponseCode()==200){
                inputStream=urlConnection.getInputStream();
                jsonresponse=readfrominputstream(inputStream);
            }
        }catch (IOException e){
            Log.e(LOG_CAT,"bad connection",e);
        }finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
            if(inputStream!=null){
                try{
                    inputStream.close();
                }catch (IOException e){
                    Log.e(LOG_CAT,"trouble closing the input stream",e);
                }
            }
        }
        return jsonresponse;
    }

    private static String readfrominputstream(InputStream inputStream)throws IOException{
        StringBuilder output=new StringBuilder();
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line=bufferedReader.readLine();
            while (line!=null){
                output.append(line);
                line=bufferedReader.readLine();
            }
        }catch (IOException e){
            Log.e(LOG_CAT,"Unable to read from input stream",e);
        }
        return output.toString();
    }

    private static String extractjsonresponse(String jsonresponse){
        return jsonresponse.substring(1,500);
    }
}
