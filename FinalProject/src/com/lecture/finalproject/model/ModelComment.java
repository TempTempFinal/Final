package com.lecture.finalproject.model;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelComment {
    private static Logger logger = LoggerFactory.getLogger(ModelComment.class);
    
    private int comment_no             ;
    private String commentPost_date     ;
    private String content             ;
    private String user_id             ;
    private String image_url  ;
    private int travelPost_no          ;
	public ModelComment(int comment_no, String commentPost_date, String content, String user_id, String image_url,
			int travelPost_no) {
		super();
		this.comment_no = comment_no;
		this.commentPost_date = commentPost_date;
		this.content = content;
		this.user_id = user_id;
		this.image_url = image_url;
		this.travelPost_no = travelPost_no;
	}
	public ModelComment() {
		super();
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		ModelComment.logger = logger;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getCommentPost_date() {
		return commentPost_date;
	}
	public void setCommentPost_date(String commentPost_date) {
		this.commentPost_date = commentPost_date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
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
		return "ModelComment [comment_no=" + comment_no + ", commentPost_date=" + commentPost_date + ", content="
				+ content + ", user_id=" + user_id + ", image_url=" + image_url + ", travelPost_no=" + travelPost_no
				+ "]";
	} 
}
