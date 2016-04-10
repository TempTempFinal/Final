package com.lecture.finalproject.model;

public class ModelHometown {
	
	private int user_id;
	private double latitude;
	private double longitude;
	public ModelHometown(int user_id, double latitude, double longitude) {
		super();
		this.user_id = user_id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	public ModelHometown() {
		super();
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
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
		return "ModelHometown [user_id=" + user_id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}
	
}
