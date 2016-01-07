package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelInformation {
    private static Logger logger = LoggerFactory
                                         .getLogger(ModelInformation.class);
    
    private String travel_content ;
    private int travelPost_no;
    private double senti_positive;
    private double senti_negative;
    private double popularity;
    private int search_count;
    
    public ModelInformation(String travel_content, int travelPost_no, double senti_positive, double senti_negative, double popularity, int search_count) {
        super();
        this.travel_content = travel_content;
        this.travelPost_no = travelPost_no;
        this.senti_positive = senti_positive;
        this.senti_negative = senti_negative;
        this.popularity = popularity;
        this.search_count = search_count;
    }
    public ModelInformation() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelInformation.logger = logger;
    }
    public String getTravel_content() {
        return travel_content;
    }
    public void setTravel_content(String travel_content) {
        this.travel_content = travel_content;
    }
    public int getTravelPost_no() {
        return travelPost_no;
    }
    public void setTravelPost_no(int travelPost_no) {
        this.travelPost_no = travelPost_no;
    }
    public double getSentiPositive() {
        return senti_positive;
    }
    public void setSentiPositive(double senti_positive) {
        this.senti_positive = senti_positive;
    }
    public double getSentiNegative() {
        return senti_negative;
    }
    public void setSentiNegative(double senti_negative) {
        this.senti_negative = senti_negative;
    }
    public double getPopularity() {
        return popularity;
    }
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }
    public int getSearchCount() {
        return search_count;
    }
    public void setSearchCount(int search_count) {
        this.search_count = search_count;
    }
    
    @Override
    public String toString() {
        return "ModelInformation [travel_content=" + travel_content
                + ", travelPost_no=" + travelPost_no
                + ", senti_positive=" + senti_positive 
                + ", senti_negative=" + senti_negative 
                + ", popularity=" + popularity 
                + ", search_count=" + search_count + "]";
    }
}