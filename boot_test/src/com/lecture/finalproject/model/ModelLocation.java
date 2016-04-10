package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelLocation {
    private static Logger logger = LoggerFactory.getLogger(ModelLocation.class);
    
    private String city1                ;
    private String city2                ;
    private String address              ;
    private String latitude             ;
    private String longitude          ;
    private int travelPost_no        ;
    public ModelLocation(String city1, String city2, String address,
            String latitude, String longitude, int travelPost_no) {
        super();
        this.city1 = city1;
        this.city2 = city2;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.travelPost_no = travelPost_no;
    }
    public ModelLocation() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelLocation.logger = logger;
    }
    public String getCity1() {
        return city1;
    }
    public void setCity1(String city1) {
        this.city1 = city1;
    }
    public String getCity2() {
        return city2;
    }
    public void setCity2(String city2) {
        this.city2 = city2;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public int getTravelPost_no() {
        return travelPost_no;
    }
    public void setTravelPost_no(int travelPost_no) {
        this.travelPost_no = travelPost_no;
    }
    @Override
    public String toString() {
        return "ModelLocation [city1=" + city1 + ", city2=" + city2
                + ", address=" + address + ", latitude=" + latitude
                + ", longitude=" + longitude + ", travelPost_no="
                + travelPost_no + "]";
    }
  
}
