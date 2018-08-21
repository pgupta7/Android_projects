package com.example.android.travelappwithlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Historicalsites extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        ArrayList<Attraction> historicalsites=new ArrayList<Attraction>();
        historicalsites.add(new Attraction(getString(R.string.historicalsite1_name),getString(R.string.historicalsite1_address),getString(R.string.historicalsite1_phone),getString(R.string.historicalsite1_hrs),R.drawable.carter_g_woodson_home));
        historicalsites.add(new Attraction(getString(R.string.historicalsite2_name),getString(R.string.historicalsite2_address),getString(R.string.historicalsite2_phone),getString(R.string.historicalsite2_hrs),R.drawable.sewall_belmont_house));
        historicalsites.add(new Attraction(getString(R.string.historicalsite3_name),getString(R.string.historicalsite3_address),getString(R.string.historicalsite3_phone),getString(R.string.historicalsite3_hrs),R.drawable.us_capital));
        historicalsites.add(new Attraction(getString(R.string.historicalsite4_name),getString(R.string.historicalsite4_address),getString(R.string.historicalsite4_phone),getString(R.string.historicalsite4_hrs),R.drawable.dc_preservation_league));
        historicalsites.add(new Attraction(getString(R.string.historicalsite5_name),getString(R.string.historicalsite5_address),getString(R.string.historicalsite5_phone),getString(R.string.historicalsite5_hrs),R.drawable.fords_theater));

        AttractionAdapter historicalsitesadapter=new AttractionAdapter(this,historicalsites);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(historicalsitesadapter);

    }
}
