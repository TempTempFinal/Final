package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelConcern {
    private static Logger logger = LoggerFactory.getLogger(ModelConcern.class);
    
    private String interest ;
    private String user_id  ;
    public ModelConcern(String interest, String user_id) {
        super();
        this.interest = interest;
        this.user_id = user_id;
    }
    public ModelConcern() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelConcern.logger = logger;
    }
    public String getInterest() {
        return interest;
    }
    public void setInterest(String interest) {
        this.interest = interest;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    @Override
    public String toString() {
        return "ModelConcern [interest=" + interest + ", user_id=" + user_id
                + "]";
    }
    
    
}
