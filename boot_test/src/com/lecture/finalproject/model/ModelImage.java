package com.lecture.finalproject.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelImage {
    private static Logger logger = LoggerFactory.getLogger(ModelImage.class);
    
    private String image_url  ;
    private String image_url2;
    private int travelPost_no    ;
    
 
    public ModelImage(String image_url, String image_url2, int travelPost_no) {
		super();
		this.image_url = image_url;
		this.travelPost_no = travelPost_no;
	}
	public ModelImage() {
        super();
    }
    public static Logger getLogger() {
        return logger;
    }
    public static void setLogger(Logger logger) {
        ModelImage.logger = logger;
    }
	public String getImage_url() {
        return image_url;
    }
    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
    public int getTravelPost_no() {
        return travelPost_no;
    }
    public void setTravelPost_no(int travelPost_no) {
        this.travelPost_no = travelPost_no;
    }
    @Override
    public String toString() {
        return "ModelImage [image_url=" + image_url + ", travelPost_no="
                + travelPost_no + "]";
    }
    
    
    
}
