package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelHash {
    private static Logger logger = LoggerFactory.getLogger(ModelHash.class);
    
    private String hashTag          ;
    private int travelPost_no    ;
    public ModelHash(String hashTag, int travelPost_no) {
        super();
        this.hashTag = hashTag;
        this.travelPost_no = travelPost_no;
    }
    public ModelHash() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelHash.logger = logger;
    }
    public String getHashTag() {
        return hashTag;
    }
    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }
    public int getTravelPost_no() {
        return travelPost_no;
    }
    public void setTravelPost_no(int travelPost_no) {
        this.travelPost_no = travelPost_no;
    }
    @Override
    public String toString() {
        return "ModelHash [hashTag=" + hashTag + ", travelPost_no="
                + travelPost_no + "]";
    }
   
}
