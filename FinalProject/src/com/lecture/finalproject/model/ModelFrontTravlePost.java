package com.lecture.finalproject.model;

public class ModelFrontTravlePost {
	
	
	private int travelPost_no      ;
	private String title;
	private int like_count;
	private int comment_count;
	private String image_url;
	private String address;
	public int getTravelPost_no() {
		return travelPost_no;
	}
	public void setTravelPost_no(int travelPost_no) {
		this.travelPost_no = travelPost_no;
	}
	private double latitude;
	private double longitude;
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public ModelFrontTravlePost(int travelPost_no, String title, int like_count, int comment_count, String image_url,
			String address, double latitude, double longitude) {
		super();
		this.travelPost_no = travelPost_no;
		this.title = title;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.image_url = image_url;
		this.address = address;
		this.latitude = latitude;
		this.longitude = longitude;
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
				+ comment_count + ", image_url=" + image_url + ", address=" + address + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
