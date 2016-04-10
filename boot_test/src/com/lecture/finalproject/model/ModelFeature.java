package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelFeature {
    private static Logger logger = LoggerFactory.getLogger(ModelFeature.class);
    
    private String feature          ;
    private int travelPost_no    ;
    
    public ModelFeature(String feature, int travelPost_no) {
        super();
        this.feature = feature;
        this.travelPost_no = travelPost_no;
    }
    public ModelFeature() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelFeature.logger = logger;
    }
    public String getFeature() {
        return feature;
    }
    public void setFeature(String feature) {
        this.feature = feature;
    }
    public int getTravelPost_no() {
        return travelPost_no;
    }
    public void setTravelPost_no(int travelPost_no) {
        this.travelPost_no = travelPost_no;
    }
    @Override
    public String toString() {
        return "ModelFeature [feature=" + feature + ", travelPost_no="
                + travelPost_no + "]";
    }
    
    
}
