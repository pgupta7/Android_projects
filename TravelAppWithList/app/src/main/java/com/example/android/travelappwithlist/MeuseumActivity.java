package com.example.android.travelappwithlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Attr;

import java.util.ArrayList;

public class MeuseumActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        ArrayList<Attraction> museums=new ArrayList<Attraction>();
        museums.add(new Attraction(getString(R.string.museum1_name),getString(R.string.museum1_address),getString(R.string.museum1_phone),getString(R.string.museum1_hrs)));
        museums.add(new Attraction(getString(R.string.museum2_name),getString(R.string.museum2_address),getString(R.string.museum2_phone),getString(R.string.museum2_hrs)));
        museums.add(new Attraction(getString(R.string.museum3_name),getString(R.string.museum3_address),getString(R.string.museum3_phone),getString(R.string.museum3_hrs)));
        museums.add(new Attraction(getString(R.string.museum4_name),getString(R.string.museum4_address),getString(R.string.museum4_phone),getString(R.string.museum4_hrs)));
        museums.add(new Attraction(getString(R.string.museum5_name),getString(R.string.museum5_address),getString(R.string.museum5_phone),getString(R.string.museum5_hrs)));
        museums.add(new Attraction(getString(R.string.museum6_name),getString(R.string.museum6_address),getString(R.string.museum6_phone),getString(R.string.museum6_hrs)));

        AttractionAdapter museumadapter=new AttractionAdapter(this,museums);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(museumadapter);
    }

}
