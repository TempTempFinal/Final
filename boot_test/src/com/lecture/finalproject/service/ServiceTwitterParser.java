package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import twitter4j.PagableResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class ServiceTwitterParser{
	
	public List<User> getFriendsList(Twitter twitter) {
		// TODO Auto-generated method stub
		
		List<User> listFriends = new ArrayList<User>();
	
		 try {
	            // get friends
	            long cursor = -1;
	            PagableResponseList<User> pagableFollowings;
	            do {
	                pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
	                for (User user : pagableFollowings) {
	                    listFriends.add(user); // ArrayList<User>
	                }
	            } while ((cursor = pagableFollowings.getNextCursor()) != 0);

	            // get followers
	           

	        } catch (TwitterException e) {
	           	System.out.println(e.getMessage());
	        }
			 
		return listFriends;
	}
	 
	public List<User> getFollowersList(Twitter twitter)
	{
		List<User> listFollowers = new ArrayList<User>();
		
		try {
			long cursor = -1;
			PagableResponseList<User> pagableFollowers;
			do {
				pagableFollowers = twitter.getFollowersList(twitter.getId(), cursor);
				for (User user : pagableFollowers) {
					listFollowers.add(user); // ArrayList<User>
				}
			} while ((cursor = pagableFollowers.getNextCursor()) != 0);
		} catch (TwitterException e) {
			System.out.println(e.getMessage());
		}
		
		return listFollowers;	
	}

	public List<String> getMyTimeline(Twitter twitter,User user)
	{
		List<Status> list = null;
		List<String> timelineList = new ArrayList<String>();
		
		try {
			list = twitter.getUserTimeline(); 		
			
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("타임라인 계정:"+user.getScreenName());
		
		for(Status status : list) {	
			timelineList.add(status.getText());
		}
		return timelineList;
	}
}

