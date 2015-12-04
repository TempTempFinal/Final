package com.lecture.finalproject.model;

public class ModelFrontTravlePost {
	
	private String title;
	private int like_count;
	private int comment_count;
	private String image_url;
	private String address;
	
	public ModelFrontTravlePost(String title, int like_count, int comment_count, String image_url, String address) {
		super();
		this.title = title;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.image_url = image_url;
		this.address = address;
	}
	public ModelFrontTravlePost() {
		super();
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "ModelFrontTravlePost [title=" + title + ", like_count=" + like_count + ", comment_count="
				+ comment_count + ", image_url=" + image_url + ", address=" + address + "]";
	}
}
