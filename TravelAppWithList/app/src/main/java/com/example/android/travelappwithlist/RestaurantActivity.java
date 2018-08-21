package com.example.android.travelappwithlist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class RestaurantActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attraction);

        ArrayList<Attraction> attractions=new ArrayList<Attraction>();
        attractions.add(new Attraction(getString(R.string.restaurant1_name),getString(R.string.restaurant1_address),getString(R.string.restaurant1_phone),getString(R.string.restaurant1_hrs),Float.parseFloat(getString(R.string.restaurant1_ratings))));
        attractions.add(new Attraction(getString(R.string.restaurant2_name),getString(R.string.restaurant2_address),getString(R.string.restaurant2_phone),getString(R.string.restaurant2_hrs),Float.parseFloat(getString(R.string.restaurant2_ratings))));
        attractions.add(new Attraction(getString(R.string.restaurant3_name),getString(R.string.restaurant3_address),getString(R.string.restaurant3_phone),getString(R.string.restaurant3_hrs),Float.parseFloat(getString(R.string.restaurant3_ratings))));
        attractions.add(new Attraction(getString(R.string.restaurant4_name),getString(R.string.restaurant4_address),getString(R.string.restaurant4_phone),getString(R.string.restaurant4_hrs),Float.parseFloat(getString(R.string.restaurant4_ratings))));
        attractions.add(new Attraction(getString(R.string.restaurant5_name),getString(R.string.restaurant5_address),getString(R.string.restaurant5_phone),getString(R.string.restaurant5_hrs),Float.parseFloat(getString(R.string.restaurant5_ratings))));

        AttractionAdapter attractionAdapter=new AttractionAdapter(this,attractions);
        ListView listView=(ListView)findViewById(R.id.list);
        listView.setAdapter(attractionAdapter);
    }
}
