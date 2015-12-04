package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lecture.finalproject.model.ModelUser;

import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class FriendsInfoHelper {
	
	private Twitter twitter;
	private User myUser;
	private float retweet;
	private float comment;
	private float mention;
	private float like;

	public FriendsInfoHelper(Twitter twitter, User myUser){
		this.twitter = twitter;	
		this.myUser = myUser;
		retweet = 0;
		comment = 0;
		mention = 0;
		like = 0;
	}
	
	public List<ModelUser> getFriendAndFollowerList(){
		
		List<ModelUser> friendList = new ArrayList<ModelUser>(0);
		
		   try {
	            long cursor = -1;
	            PagableResponseList<User> pagableFollowings;
	            do {
	            	
	                pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
	                for (User user : pagableFollowings) {
	                	ModelUser friend = new ModelUser(Long.toString(user.getId()), user.getName(), user.getProfileImageURL(),user.getScreenName(),false);
	                	
	                	friendList.add(friend); // ArrayList<User>
	                }
	            } while ((cursor = pagableFollowings.getNextCursor()) != 0);

	        } catch (TwitterException e) {
	        	System.out.println(e.getMessage());
	        }
		   
		   return friendList;
	}
		

	public Map<String, Float> getFriendsWeight(String[] friendNames) {
		
		Map<String, Float> friendsNameAndWeight = new HashMap<String, Float>();
		float weight;
		
		try {
			ResponseList<User> friendUserObject = twitter.lookupUsers(friendNames); // 나의 친구들의 user객체를 얻어옴
			for(User user : friendUserObject){
				
				System.out.println("Friend's Name " + user.getName());
				friendsNameAndWeight.put(user.getName(), (float) 0);
				if(user.getStatus() != null){
					
					List<Status> friendStatusess = twitter.getUserTimeline(user.getId()); //나의 timeline에서 해당 user가 적어놓은 timeline을 가져옴/statusess 하나는 timeline 하나를 의미
			
							//친구가 나에게 접촉한경우의 weight
					 for (Status status3 : friendStatusess) {
					 System.out.println(status3);
						 	checkWeight(status3,user,myUser);
					}
					 weight = getTotalWeight();
					 System.out.println(weight);
					 
					 //나의 타임라인에서 친구와 관련된걸 뽑아내자
					 List<Status> myStatusess = twitter.getUserTimeline(myUser.getId());
						
					 for(Status status3 : myStatusess){
						 System.out.println(status3); 
						 	checkWeight(status3,myUser,user);
					 }
					 weight = getTotalWeight();
					 System.out.println(weight);
					 
					friendsNameAndWeight.put(user.getName(), weight);
				}
				
				retweet = 0;
				comment = 0;
				mention = 0;
				like = 0;
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());	
		}
		return friendsNameAndWeight;
	}
	
	private float getTotalWeight(){
		float result = 0;	
		result = retweet + comment + mention + like;
		
		return result;
	}
	
	private void checkWeight(Status inputTimeline, User myUser,User friendUser){
		
		if(isComment(inputTimeline,friendUser,myUser)){
			comment += checkCommentWeight(inputTimeline);
			checkOtherFavortieWeight(inputTimeline);
			System.out.println("iscomment");
		}
		else if(isRetweet(inputTimeline, friendUser,myUser)){
			retweet += checkRetweetWeight(inputTimeline);
			checkReTweetFavoriteWeight(inputTimeline);
			System.out.println("isReweet");
		}else if(isMention(inputTimeline, friendUser,myUser)){
			mention += checkMentionWeight(inputTimeline);
			checkOtherFavortieWeight(inputTimeline);
			System.out.println("isMention");
		}
		//그냥 자기혼자 글을 쓴경우에는 넘어간다.
	}
	
	private void checkReTweetFavoriteWeight(Status inputTimeline){
		
		boolean isEqualLike = false;
			
		if(inputTimeline.getQuotedStatus().isFavorited())
			isEqualLike = true;
		
		if(inputTimeline.isFavorited())
		{
			if(isEqualLike)
				like += 0.2;
			else
				like += 0.1;
		}
	}
	
	private void checkOtherFavortieWeight(Status inputTimeline){
		if(inputTimeline.isFavorited())
			like += 0.1;
	}
	
	private float checkRetweetWeight(Status inputTimeline){
		float result = 0;
		
		if(inputTimeline.getQuotedStatus().getMediaEntities().length != 0){
			if(inputTimeline.getQuotedStatus().getMediaEntities()[0].getExpandedURL().contains("photo"))
				result += 0.8;
			else if(inputTimeline.getQuotedStatus().getMediaEntities()[0].getExpandedURL().contains("video"))
				result += 1.0;
		}else
			result += 0.5;
		
		result *= getTimeWeight(inputTimeline);
		
		return result;
	}
	
	private float checkCommentWeight(Status inputTimeline){
		float result = (float) 0.5; 
		
		result *= getTimeWeight(inputTimeline);
	
		return result;
	}
	
	private float checkMentionWeight(Status inputTimeline){
		float result = 0;
		
		if(inputTimeline.getMediaEntities().length != 0){
			if(inputTimeline.getMediaEntities()[0].getExpandedURL().contains("photo"))
				result += 1.0;
			else if(inputTimeline.getMediaEntities()[0].getExpandedURL().contains("video"))
				result += 1.2;
		}else
			result += 0.7;
		
		result *= getTimeWeight(inputTimeline);
		
		return result;
	}
	
	
		
	private boolean isRetweet(Status inputTimeline, User friendUser, User myUser){
		if(inputTimeline.getQuotedStatusId() != -1)
			if(inputTimeline.getQuotedStatus().getUser().getScreenName().equalsIgnoreCase(friendUser.getScreenName()))
				return true;
		return false;
	}
	
	private boolean isComment(Status inputTimeline, User friendUser, User myUser){
		if(inputTimeline.getInReplyToStatusId() != -1){
			//나에대한 답글인지.
			//만약 이글이 나(friendUser)에게 다는 글이라면
			if(inputTimeline.getInReplyToUserId() == friendUser.getId())
				return true;
		}
		
		return false;
	}
	
	private boolean isMention(Status inputTimeline, User friendUser, User myUser){
		if(inputTimeline.getInReplyToStatusId() == -1){
			if(inputTimeline.getInReplyToScreenName() == null)
				return false;
			if(inputTimeline.getInReplyToScreenName().equalsIgnoreCase(friendUser.getScreenName()))
				return true;
		}
		return false;
	}
	
	private Map<String,Float> getWeightRatio(Map<String, Float> friendsWeight, String[] friendNames){
		
		Map<String,Float> resultWeight = new HashMap<String,Float>();
		float totalWeight = 0;
		
		try {
			ResponseList<User> friendUserObject = twitter.lookupUsers(friendNames); // 나의 친구들의 user객체를 얻어옴
			
			for(User user : friendUserObject){
				totalWeight += friendsWeight.get(user.getName());
			}
			
			for(User user : friendUserObject){
				resultWeight.put(user.getName(), (friendsWeight.get(user.getName()) / totalWeight) * 100);
			}
			
		}catch(TwitterException e){
			System.out.println(e.getMessage());
		}
		
		return resultWeight;
	}
	
	private double getTimeWeight(Status inputTimeline){
		
		int subWeeks = 0;
		double weight = 0;
		Date currentTime = new Date();
		Date makeTime = inputTimeline.getCreatedAt();
		
		//Date으로 받고 calendar로 지정해줘야 날자를 가져올 수 있다.
		Calendar currentCalendar = Calendar.getInstance();
		Calendar makeCalendar = Calendar.getInstance();
		
		currentCalendar.setTime(currentTime);
		makeCalendar.setTime(makeTime);
		
		int tempYearSub = currentCalendar.get(Calendar.YEAR) - makeCalendar.get(Calendar.YEAR);
		int tempMonthSub = (currentCalendar.get(Calendar.MONTH) + (tempYearSub * 12)) - makeCalendar.get(Calendar.MONTH);
		
		subWeeks = ((currentCalendar.get(Calendar.DAY_OF_MONTH) + (tempMonthSub * 30)) - makeCalendar.get(Calendar.DAY_OF_MONTH));
		
		
		if(subWeeks >= 0 && subWeeks <= 2)
			weight = 1;
		else if(subWeeks <=4)
			weight = 0.8;
		else if(subWeeks <= 8)
			weight = 0.6;
		else if(subWeeks <= 24)
			weight = 0.4;
		else if(subWeeks <= 52)
			weight = 0.3;
		else 
			weight = 0.1;
		return weight;
	}
}
