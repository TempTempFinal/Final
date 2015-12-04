package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelLike {
    private static Logger logger = LoggerFactory.getLogger(ModelLike.class);
    
    private String user_id      ;
    private int travelPost_no   ;
    private String like_check   ;
    public ModelLike(String user_id, int travelPost_no, String like_check) {
        super();
        this.user_id = user_id;
        this.travelPost_no = travelPost_no;
        this.like_check = like_check;
    }
    public ModelLike() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelLike.logger = logger;
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
    public String getLike_check() {
        return like_check;
    }
    public void setLike_check(String like_check) {
        this.like_check = like_check;
    }
    @Override
    public String toString() {
        return "ModelLike [user_id=" + user_id + ", travelPost_no="
                + travelPost_no + ", like_check=" + like_check + "]";
    }

}
