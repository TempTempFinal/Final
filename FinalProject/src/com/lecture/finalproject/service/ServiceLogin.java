package com.lecture.finalproject.service;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelUser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class ServiceLogin {
	
	public boolean isExistMember(String user_id){
		int count = 0;	

		DaoTravlePlace db = new DaoTravlePlace();	
		count = db.getUserCount(user_id);
		
		if(count > 0)
			return true;
		
		return false;
	}
	
	public ModelUser getNewEnrollUser(User twitterUser){
		
		ModelUser user = new ModelUser();
		DaoTravlePlace db = new DaoTravlePlace();
		
		user.setUser_id(Long.toString(twitterUser.getId()));
		user.setName(twitterUser.getName());
		user.setImg_url(twitterUser.getProfileImageURL());
		user.setSync(false);
		
		db.insertUserInfo(user);
	
		return user;	
	}
	
	public ModelUser getAlreadyEnrollUser(User twitterUser){
		
		ModelUser user = null;
		DaoTravlePlace db = new DaoTravlePlace();
		
		user = db.getUserInfo(Long.toString(twitterUser.getId()));
		
		return user;	
	}
	
	public User getTwitterUser(Twitter twitter){

		User user = null;
		
		try {
			 user = twitter.verifyCredentials();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return user;
	}
}
