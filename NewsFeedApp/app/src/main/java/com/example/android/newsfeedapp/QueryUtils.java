package com.example.android.newsfeedapp;

import android.support.v4.app.LoaderManager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by Prasoon Gupta on 12/21/2016.
 */
public class QueryUtils {
    public static final String LOG_CAT=QueryUtils.class.getSimpleName();
    public QueryUtils(){}

    public static final ArrayList<NewsList> extractnewslist(String resourceurl){
        String sectionname="";
        String webtitle="";
        String weburl="";
        URL url=null;
        try {
            url=new URL(resourceurl);
        }catch (MalformedURLException e){
            Log.e(LOG_CAT,"url was not formed correctly",e);
        }
        ArrayList<NewsList> newsList=new ArrayList<NewsList>();
        try {
            String jsonresponse=makeHTTPrequest(url);
            JSONObject jsonObject=new JSONObject(jsonresponse);
            if(jsonObject.has("response")){
                JSONObject jsonObject1=jsonObject.getJSONObject("response");
                if(jsonObject1.has("results")){
                    JSONArray jsonArray=jsonObject1.getJSONArray("results");
                    for(int i=0;i<=jsonArray.length();i++){
                        JSONObject jsonObject2=(JSONObject) jsonArray.get(i);
                        if(jsonObject2.has("sectionName")){
                            sectionname=jsonObject2.getString("sectionName");
                        }
                        if(jsonObject2.has("webTitle")){
                            webtitle=jsonObject2.getString("webTitle");
                        }
                        if(jsonObject2.has("webUrl")){
                            weburl=jsonObject2.getString("webUrl");
                        }
                        newsList.add(new NewsList(sectionname,webtitle,weburl));
                    }
                }
            }
        }catch (Exception e){
            Log.e(LOG_CAT,"trouble creating json object");
        }
        return newsList;
    }

    private static String makeHTTPrequest(URL url){
        String jsonresponse="";
        if(url==null){
            return jsonresponse;
        }

        HttpURLConnection httpURLConnection=null;
        InputStream inputStream=null;

        try {
            httpURLConnection=(HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(1000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
            if(httpURLConnection.getResponseCode()==200){
                inputStream=httpURLConnection.getInputStream();
                jsonresponse=readfrominputstream(inputStream);
            }

        }catch (Exception e){
            Log.e(LOG_CAT,"bad connection",e);
        }finally {
            if(httpURLConnection!=null){
                httpURLConnection.disconnect();
            }
            if(inputStream!=null){
                try {
                    inputStream.close();
                }catch (IOException e){
                    Log.e(LOG_CAT,"trouble closing the inputstream",e);
                }
            }
        }
        return jsonresponse;
    }

    public static final String readfrominputstream(InputStream inputStream){
        StringBuilder stringBuilder=new StringBuilder();
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String templine=bufferedReader.readLine();
            while (templine!=null){
                stringBuilder.append(templine);
                templine=bufferedReader.readLine();
            }
        }catch (Exception e){
            Log.e(LOG_CAT,"Unable to read from inputstream",e);
        }
        return stringBuilder.toString();
    }
}
