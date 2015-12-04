package com.lecture.finalproject.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelTravelPost {
    private static Logger logger = LoggerFactory
                                         .getLogger(ModelTravelPost.class);
    private int travelPost_no      ;
    private String title           ;
    private String travelPost_date ;
    private int view_count         ;
    private int like_count;
    private int comment_count;
    private String user_id  ;
	public ModelTravelPost(int travelPost_no, String title, String travelPost_date, int view_count, int like_count,
			int comment_count, String user_id) {
		super();
		this.travelPost_no = travelPost_no;
		this.title = title;
		this.travelPost_date = travelPost_date;
		this.view_count = view_count;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.user_id = user_id;
	}
	public ModelTravelPost() {
		super();
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		ModelTravelPost.logger = logger;
	}
	public int getTravelPost_no() {
		return travelPost_no;
	}
	public void setTravelPost_no(int travelPost_no) {
		this.travelPost_no = travelPost_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTravelPost_date() {
		return travelPost_date;
	}
	public void setTravelPost_date(String travelPost_date) {
		this.travelPost_date = travelPost_date;
	}
	public int getView_count() {
		return view_count;
	}
	public void setView_count(int view_count) {
		this.view_count = view_count;
	}
	public int getLike_count() {
		return like_count;
	}
	public void setLike_count(int like_count) {
		this.like_count = like_count;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	@Override
	public String toString() {
		return "ModelTravelPost [travelPost_no=" + travelPost_no + ", title=" + title + ", travelPost_date="
				+ travelPost_date + ", view_count=" + view_count + ", like_count=" + like_count + ", comment_count="
				+ comment_count + ", user_id=" + user_id + "]";
	}
   
    
}
