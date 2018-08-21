package com.example.android.travelappwithlist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Intent restaurantactivityintent=new Intent(this,RestaurantActivity.class);
        final Intent museumactivityintent=new Intent(this,MeuseumActivity.class);
        final Intent historicalsitesintent=new Intent(this,Historicalsites.class);
        final Intent parksactivityintent=new Intent(this,ParksActivity.class);

        TextView restaurantsactivity=(TextView)findViewById(R.id.restaurants);
        restaurantsactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(restaurantactivityintent);
            }
        });

        TextView museumactivity=(TextView)findViewById(R.id.museums);
        museumactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(museumactivityintent);
            }
        });

        final TextView historicalsitesactivity=(TextView)findViewById(R.id.historicalsites);
        historicalsitesactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(historicalsitesintent);
            }
        });

        final TextView parksactivity=(TextView)findViewById(R.id.parks);
        parksactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(parksactivityintent);
            }
        });
    }
}
