package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    private static final int booklistloaderid=1;
    private static String resourceurl;
    public BookListAdapter bookListAdapter;
    private static final String LOG_CAT="Main Activity";
    private TextView emptyView;
    private ProgressBar progressBar;
    LinearLayout linearLayout;
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        linearLayout=(LinearLayout)findViewById(R.id.query);
        editText=(EditText)findViewById(R.id.keywords);
        TextView submitbutton=(TextView)findViewById(R.id.submitbutton);
        final Intent intent=new Intent(this,DataCollectingActivity.class);
        final CheckBox titlecheckbox=(CheckBox )findViewById(R.id.searchtitle);
        final CheckBox authorscheckbox=(CheckBox)findViewById(R.id.searchauthors);

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String requestedwords=editText.getText().toString();
                List<String> keywords=Arrays.asList(requestedwords.split(" "));

                StringBuilder stringBuilder=new StringBuilder();

                if(titlecheckbox.isChecked()){
                    stringBuilder.append("intitle:").append(keywords.get(0));
                    for(int i=1;i<=keywords.size()-1;i++){
                        stringBuilder.append("+").append("intitle:").append(keywords.get(i));
                    }
                    if(authorscheckbox.isChecked()){
                        for(int i=0;i<=keywords.size()-1;i++){
                            stringBuilder.append("+").append("inauthor:").append(keywords.get(i));
                        }
                    }
                }else {
                    if(authorscheckbox.isChecked()){
                        stringBuilder.append("inauthor:").append(keywords.get(0));
                        for(int i=1;i<=keywords.size()-1;i++){
                            stringBuilder.append("+").append("inauthor:").append(keywords.get(i));
                        }
                    }else{
                        stringBuilder.append(keywords.get(0));
                        for(int i=1;i<=keywords.size()-1;i++){
                            stringBuilder.append("+").append(keywords.get(i));
                        }
                    }
                }

                resourceurl="https://www.googleapis.com/books/v1/volumes?q="+stringBuilder.toString()+"&maxResults=10";
                Log.v(LOG_CAT,"\t"+resourceurl);


                intent.putExtra("Resourceurl",resourceurl);
                startActivity(intent);
            }
        });


    }

    /*public void pressedsubmit(View view){
        String requestedtext=editText.getText().toString();
        List<String> keywords= Arrays.asList(requestedtext.split(" "));

        resourceurl="https://www.googleapis.com/books/v1/volumes?q="+keywords+"&maxResults=10";
        Log.v(LOG_CAT,resourceurl);
        linearLayout.setVisibility(View.GONE);
        bookListAdapter=new BookListAdapter(this,new ArrayList<BooksList>());

        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(bookListAdapter);
        Log.e(LOG_CAT,"set the adapter for list");

        emptyView=(TextView)findViewById(R.id.emptyviewtext);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        listView.setEmptyView(emptyView);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if((networkInfo!=null)&&(networkInfo.isConnected())){
            progressBar.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(booklistloaderid,null,this).forceLoad();
        }else {
            emptyView.setText("NO INTERNET CONNECTION");
            progressBar.setVisibility(View.INVISIBLE);
        }
        return;
    }

    @Override
    public Loader<ArrayList<BooksList>> onCreateLoader(int id, Bundle args) {
        return new BookListLoader(this,resourceurl);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BooksList>> loader) {
        bookListAdapter.clear();
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BooksList>> loader, ArrayList<BooksList> data) {
        bookListAdapter.clear();
        progressBar.setVisibility(View.INVISIBLE);
        if(data!=null){
            bookListAdapter.addAll(data);
        }else{
            emptyView.setText("NO BOOKS FOUND");
        }
    }*/
}
