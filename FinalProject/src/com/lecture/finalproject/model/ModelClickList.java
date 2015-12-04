package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelClickList {
    private static Logger logger = LoggerFactory
                                         .getLogger(ModelClickList.class);
    
    private String user_id     ;      
    private int travelPost_no  ;      
    private int clickCount     ;
    public ModelClickList(String user_id, int travelPost_no, int clickCount) {
        super();
        this.user_id = user_id;
        this.travelPost_no = travelPost_no;
        this.clickCount = clickCount;
    }
    public ModelClickList() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelClickList.logger = logger;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public int getTravelPost_no() {
        return travelPost_no;
    }
    public void setTravelPost_no(int travelPost_no) {
        this.travelPost_no = travelPost_no;
    }
    public int getClickCount() {
        return clickCount;
    }
    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
    @Override
    public String toString() {
        return "ModelClickList [user_id=" + user_id + ", travelPost_no="
                + travelPost_no + ", clickCount=" + clickCount + "]";
    }      

    
    
}
