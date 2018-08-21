package com.example.android.travelappwithlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ParksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        ArrayList<Attraction> parkslist=new ArrayList<Attraction>();
        parkslist.add(new Attraction(getString(R.string.parksite1_name),getString(R.string.parksite1_address),getString(R.string.parksite1_phone),Double.parseDouble(getString(R.string.parksite1_perimeter)),getString(R.string.parksite1_hrs)));
        parkslist.add(new Attraction(getString(R.string.parksite2_name),getString(R.string.parksite2_address),getString(R.string.parksite2_phone),Double.parseDouble(getString(R.string.parksite2_perimeter)),getString(R.string.parksite2_hrs)));
        parkslist.add(new Attraction(getString(R.string.parksite3_name),getString(R.string.parksite3_address),getString(R.string.parksite3_phone),Double.parseDouble(getString(R.string.parksite3_perimeter)),getString(R.string.parksite3_hrs)));
        parkslist.add(new Attraction(getString(R.string.parksite4_name),getString(R.string.parksite4_address),getString(R.string.parksite4_phone),getString(R.string.parksite4_hrs)));
        parkslist.add(new Attraction(getString(R.string.parksite5_name),getString(R.string.parksite5_address),getString(R.string.parksite5_phone),getString(R.string.parksite5_hrs)));

        AttractionAdapter parksadapter=new AttractionAdapter(this,parkslist);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(parksadapter);
    }
}
