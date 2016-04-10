package com.lecture.finalproject.model;

public class ModelFrontTravlePost {
	
	
	private int travelPost_no      ;
	private String title;
	private int like_count;
	private int comment_count;
	private String image_url;
	private String address;
	private double senti_positive;
	private double senti_negative;
	private int search_count;
	private double pre_popularity;
	private double gap_popularity;
	private double latitude;
	private double longitude;
	public ModelFrontTravlePost(int travelPost_no, String title, int like_count, int comment_count, String image_url,
			String address, double senti_positive, double senti_negative, int search_count, double pre_popularity,
			double gap_popularity, double latitude, double longitude) {
		super();
		this.travelPost_no = travelPost_no;
		this.title = title;
		this.like_count = like_count;
		this.comment_count = comment_count;
		this.image_url = image_url;
		this.address = address;
		this.senti_positive = senti_positive;
		this.senti_negative = senti_negative;
		this.search_count = search_count;
		this.pre_popularity = pre_popularity;
		this.gap_popularity = gap_popularity;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public ModelFrontTravlePost() {
		super();
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
	public double getSenti_positive() {
		return senti_positive;
	}
	public void setSenti_positive(double senti_positive) {
		this.senti_positive = senti_positive;
	}
	public double getSenti_negative() {
		return senti_negative;
	}
	public void setSenti_negative(double senti_negative) {
		this.senti_negative = senti_negative;
	}
	public int getSearch_count() {
		return search_count;
	}
	public void setSearch_count(int search_count) {
		this.search_count = search_count;
	}
	public double getPre_popularity() {
		return pre_popularity;
	}
	public void setPre_popularity(double pre_popularity) {
		this.pre_popularity = pre_popularity;
	}
	public double getgap_popularity() {
		return gap_popularity;
	}
	public void setgap_popularity(double gap_popularity) {
		this.gap_popularity = gap_popularity;
	}
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
	@Override
	public String toString() {
		return "ModelFrontTravlePost [travelPost_no=" + travelPost_no + ", title=" + title + ", like_count="
				+ like_count + ", comment_count=" + comment_count + ", image_url=" + image_url + ", address=" + address
				+ ", senti_positive=" + senti_positive + ", senti_negative=" + senti_negative + ", search_count="
				+ search_count + ", pre_popularity=" + pre_popularity + ", gap_popularity=" + gap_popularity
				+ ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
	

}
