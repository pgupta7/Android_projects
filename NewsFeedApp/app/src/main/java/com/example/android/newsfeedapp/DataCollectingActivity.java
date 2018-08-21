package com.example.android.newsfeedapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import static android.R.id.empty;

public class DataCollectingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<NewsList>> {
    private final static String LOG_CAT=DataCollectingActivity.class.getSimpleName();
    private static final int newslistloaderid=1;
    private static String resourceurl;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager recyclerviewlayoutmanager;

    private TextView emptyview;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collecting);
        resourceurl=getIntent().getStringExtra("Resourceurl");
        progressBar=(ProgressBar) findViewById(R.id.progressbar);
        emptyview=(TextView)findViewById(R.id.emptyview);

        recyclerView=(RecyclerView)findViewById(R.id.list);
        recyclerviewlayoutmanager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerviewlayoutmanager);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if((networkInfo!=null)&&(networkInfo.isConnected())){
            progressBar.setVisibility(View.VISIBLE);
            getLoaderManager().initLoader(newslistloaderid,null,this).forceLoad();
        }else {
            emptyview.setText(R.string.no_internet_connection);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public Loader<ArrayList<NewsList>> onCreateLoader(int id, Bundle args) {
        return new NewsFeedLoader(this,resourceurl);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<NewsList>> loader) {

    }

    @Override
    public void onLoadFinished(Loader<ArrayList<NewsList>> loader, ArrayList<NewsList> data) {
        progressBar.setVisibility(View.INVISIBLE);
        if((data!=null)&&(!data.isEmpty())){
            recyclerView.setAdapter(new Recyclerviewadapterclass(data));
        }else {
            emptyview.setText(R.string.no_data_found);
        }
    }

}
