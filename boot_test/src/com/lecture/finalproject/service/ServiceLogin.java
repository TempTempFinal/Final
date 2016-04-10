package com.lecture.finalproject.service;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelUser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class ServiceLogin {
	
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
