package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelInformation {
    private static Logger logger = LoggerFactory
                                         .getLogger(ModelInformation.class);
    
    private String travel_content ;
    private int travelPost_no     ;
    public ModelInformation(String travel_content, int travelPost_no) {
        super();
        this.travel_content = travel_content;
        this.travelPost_no = travelPost_no;
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
    @Override
    public String toString() {
        return "ModelInformation [travel_content=" + travel_content
                + ", travelPost_no=" + travelPost_no + "]";
    }
    
    
}
