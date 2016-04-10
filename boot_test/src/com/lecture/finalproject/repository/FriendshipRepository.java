package com.lecture.finalproject.repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.ServiceTwitterParser;

import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class FriendshipRepository{

	private Twitter twitter;
	private User user;
	private ModelUser modelUser;

	public FriendshipRepository(){
		
	}
	
	
	public void setModelUser(Twitter twitter, User user){
		
		
		this.twitter = twitter;
		this.user = user;
		DaoTravlePlace db = new DaoTravlePlace();
		
		
		modelUser = getModelUserFromDB(user.getId());
		
	
	
		
		//디비에 저장ㅇ
		//해당 유저가DB에 없는경우 해당 유저와 친구정보를 DB와 Repository에 저장
		
		
		if(modelUser == null)
		{
		
			modelUser = new ModelUser();
			ServiceTwitterParser twitterParser = new ServiceTwitterParser();
			
			modelUser.setUser_id(Long.toString(user.getId()));
			modelUser.setName(user.getName());
			modelUser.setImg_url(user.getProfileImageURL());
			modelUser.setSync(false);
			List<User> friends = twitterParser.getFriendsList(twitter);
			
			for(User one : friends){
				modelUser.getFriend_list().add(one.getId());
			}
			
			db.insertUserInfo(modelUser);
			
			for(Long friend : modelUser.getFriend_list())
				db.insertFriendship(Long.parseLong(modelUser.getUser_id()), friend);
		}
	}
	

	//이걸로 해서 친구목록 저장해서 가져오면 친구기능은 끝!
	//있다면 디비에서 친구록도 함께 가져오
	
	public ModelUser getModelUserFromDB(Long userId){
		DaoTravlePlace db = new DaoTravlePlace();
		ModelUser temp = null;

			
		int count = db.getUserCount(Long.toString(userId));
		
		if(count > 0){
			temp = db.getUserInfo(userId);
			temp.setFriend_list(db.getFriendship(userId));
			modelUser = temp;
		}
		
		return modelUser;
	}
	
	public ModelUser getModelUser(){
		if(modelUser == null)
			System.out.println("modelUser is Null");
		return modelUser;
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
