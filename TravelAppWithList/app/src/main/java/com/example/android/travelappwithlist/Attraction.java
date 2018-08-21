package com.example.android.travelappwithlist;

/**
 * Created by Prasoon Gupta on 11/8/2016.
 */
public class Attraction {
    private String attractionname;
    private String attractionaddress;
    private String phonenumber;
    private String workinghours="";
    private static int NO_IMAGE_PROVIDED=-1;
    private int imageresourceid=NO_IMAGE_PROVIDED;
    private static double NO_RATINGS_PROVIDED=-1;
    private double ratingsvalues=NO_RATINGS_PROVIDED;
    private static double NO_PERIMETER_PROVIDED=-1;
    private double perimetervalue=NO_PERIMETER_PROVIDED;
    public Attraction(String mattractionname, String mattractionaddress, String mphonenumber, String mhours){
        attractionname=mattractionname;
        attractionaddress=mattractionaddress;
        phonenumber=mphonenumber;
        workinghours=mhours;
    }

    public Attraction(String mattractionname, String mattractionaddress, String mphonenumber, String mhours, int mimageresourceid){
        attractionname=mattractionname;
        attractionaddress=mattractionaddress;
        phonenumber=mphonenumber;
        workinghours=mhours;
        imageresourceid=mimageresourceid;
    }

    public Attraction(String mattractionname, String mattractionaddress, String mphonenumber, String mhours,double mratings){
        attractionname=mattractionname;
        attractionaddress=mattractionaddress;
        phonenumber=mphonenumber;
        workinghours=mhours;
        ratingsvalues=mratings;
    }

    public Attraction(String mattractionname, String mattractionaddress, String mphonenumber,double mperimetervalue,String mhours){
        attractionname=mattractionname;
        attractionaddress=mattractionaddress;
        phonenumber=mphonenumber;
        workinghours=mhours;
        perimetervalue=mperimetervalue;
    }

    public String getattractionname(){
        return attractionname;
    }
    public String getAttractionaddress(){
        return attractionaddress;
    }
    public String getPhonenumber(){
        return phonenumber;
    }

    public String getWorkinghours(){
        return workinghours;
    }
    public boolean hasworkinghours(){
        if(workinghours.isEmpty()){
            return false;
        }
        return true;
    }

    public int getImageresourceid(){
        return imageresourceid;
    }
    public boolean hasimage(){
        if(imageresourceid==NO_IMAGE_PROVIDED){
            return false;
        }
        return true;
    }

    public double getRatingsvalues(){
        return ratingsvalues;
    }
    public boolean hasratings(){
        if(ratingsvalues==NO_RATINGS_PROVIDED){
            return false;
        }
        return true;
    }
    public double getPerimetervalue(){
        return perimetervalue;
    }
    public boolean hasperimetervalue(){
        if(perimetervalue==NO_PERIMETER_PROVIDED){
            return false;
        }
        return true;
    }
}
