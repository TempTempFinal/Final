package com.lecture.finalproject.model;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ModelUser {
    private static Logger logger = LoggerFactory.getLogger(ModelUser.class);

    private String user_id ;
    private String name;
    private String img_url;
    private String screenName;
    public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	private boolean sync;
    
	
	
	public ModelUser(String user_id, String name, String img_url, String screenName, boolean sync) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.img_url = img_url;
		this.screenName = screenName;
		this.sync = sync;
	}

	public ModelUser(String user_id, String name, String img_url, boolean sync) {
		super();
		this.user_id = user_id;
		this.name = name;
		this.img_url = img_url;
		this.sync = sync;
	}
	
	public ModelUser() {
		super();
	}
	public static Logger getLogger() {
		return logger;
	}
	public static void setLogger(Logger logger) {
		ModelUser.logger = logger;
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
	public boolean isSync() {
		return sync;
	}
	public void setSync(boolean sync) {
		this.sync = sync;
	}
	@Override
	public String toString() {
		return "ModelUser [user_id=" + user_id + ", name=" + name + ", img_url=" + img_url + ", sync=" + sync + "]";
	}
	
	
   
    
    
    
    
}
