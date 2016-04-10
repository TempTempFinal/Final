package com.lecture.finalproject.model;

import java.util.HashMap;
import java.util.Map;

public class ModelCheckIn  implements Comparable<ModelCheckIn>{
	private int place_no;
	private String name;
	private double latitude;
	private double longitude;
	private Map<String,Boolean> user_id_list;
	
	
	
	public ModelCheckIn(){
		user_id_list = new HashMap<String,Boolean>();
	}
	
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getPlace_no() {
		return place_no;
	}
	public void setPlace_no(int place_no) {
		this.place_no = place_no;
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
	public Map<String, Boolean> getUser_id_list() {
		return user_id_list;
	}
	public void setUser_id_list(Map<String, Boolean> user_id_list) {
		this.user_id_list = user_id_list;
	}
	
	@Override
	public int compareTo(ModelCheckIn o) {
		// TODO Auto-generated method stub
		if(this.user_id_list.size() > o.user_id_list.size())
			return 1;
		else if(this.user_id_list.size() < o.user_id_list.size())
			return -1;
		else
			return 0;
	}
	
	
	
	

}