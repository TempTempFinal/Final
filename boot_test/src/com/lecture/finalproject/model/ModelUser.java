package com.lecture.finalproject.model;

import java.util.ArrayList;
import java.util.List;

import twitter4j.User;


public class ModelUser {
 
    private String user_id ;
    private String name;
    private String img_url;
    private String screenName;
    private boolean sync;
    private List<Long> friend_list;
    
    
    public ModelUser(String user_id, String name, String img_url, boolean sync) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.img_url = img_url;
		this.sync = sync;
	   	this.friend_list = new ArrayList<Long>();
	   }
    
    
    
    public ModelUser(String user_id, String name, String img_url, String screenName, boolean sync) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.img_url = img_url;
		this.screenName = screenName;
		this.sync = sync;
	   	this.friend_list = new ArrayList<Long>();
	   }

	public ModelUser(){
		this.friend_list = new ArrayList<Long>();
    }
    
    public boolean isSync() {
		return sync;
	}
	public void setSync(boolean sync) {
		this.sync = sync;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public List<Long> getFriend_list() {
		return friend_list;
	}
	public void setFriend_list(List<Long> friend_list) {
		this.friend_list = friend_list;
	}
	@Override
	public String toString() {
		return "ModelUser [user_id=" + user_id + ", name=" + name + ", img_url=" + img_url + ", screenName="
				+ screenName + ", friend_list=" + friend_list + "]";
	}

   
    

}
