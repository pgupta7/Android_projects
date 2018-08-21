package com.example.android.travelappwithlist;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Prasoon Gupta on 11/8/2016.
 */
public class AttractionAdapter extends ArrayAdapter<Attraction> {
    public AttractionAdapter(Activity context, ArrayList<Attraction> attractions){
        super(context,0,attractions);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listitemview=convertView;
        if(listitemview==null){
            listitemview= LayoutInflater.from(getContext()).inflate(R.layout.attractionlist,parent,false);
        }
        Attraction attraction=getItem(position);
        TextView mattractionname=(TextView)listitemview.findViewById(R.id.name);
        mattractionname.setText(attraction.getattractionname());

        TextView mattractionaddress=(TextView)listitemview.findViewById(R.id.address);
        mattractionaddress.setText(attraction.getAttractionaddress());
        TextView mattractionphone=(TextView)listitemview.findViewById(R.id.phonenumber);
        mattractionphone.setText(attraction.getPhonenumber());

        TextView mattractionhourstitle=(TextView)listitemview.findViewById(R.id.workinghourstitle);
        TextView mattractionhours=(TextView)listitemview.findViewById(R.id.workinghours);
        if(attraction.hasworkinghours()){
            mattractionhours.setText(attraction.getWorkinghours());
        }
        else {
            mattractionhours.setVisibility(View.GONE);
            mattractionhourstitle.setVisibility(View.GONE);
        }

        ImageView mattractionimage=(ImageView)listitemview.findViewById(R.id.attractionimage);

        if(attraction.hasimage()){
            mattractionimage.setImageResource(attraction.getImageresourceid());
        }
        else{
            mattractionimage.setVisibility(View.GONE);
        }

        RatingBar mattractionratings=(RatingBar)listitemview.findViewById(R.id.ratings);
        if(attraction.hasratings()){
            mattractionratings.setRating((float)attraction.getRatingsvalues());
        }else {
            mattractionratings.setVisibility(View.GONE);
        }

        TextView mattractionperimetertitle=(TextView)listitemview.findViewById(R.id.perimetertitle);
        TextView mattractionperimeter=(TextView)listitemview.findViewById(R.id.perimeter);
        if(attraction.hasperimetervalue()){
            mattractionperimeter.setText(""+attraction.getPerimetervalue()+"mi");
        }else {
            mattractionperimeter.setVisibility(View.GONE);
            mattractionperimetertitle.setVisibility(View.GONE);
        }

        return listitemview;

    }
}
