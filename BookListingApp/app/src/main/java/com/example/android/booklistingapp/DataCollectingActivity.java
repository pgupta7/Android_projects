package com.example.android.booklistingapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class DataCollectingActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<BooksList>>{
    private static final int booklistloaderid=1;
    private static String resourceurl;
    public BookListAdapter bookListAdapter;
    private static final String LOG_CAT= DataCollectingActivity.class.getSimpleName();
    private TextView emptyView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collecting);

        //Bundle bundle=getIntent().getExtras();
        resourceurl=getIntent().getStringExtra("Resourceurl");
        Log.v(LOG_CAT,resourceurl);
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
        if(!data.isEmpty()){
            bookListAdapter.addAll(data);
        }else {
            emptyView.setText("NO BOOKS FOUND");
        }
    }
}
