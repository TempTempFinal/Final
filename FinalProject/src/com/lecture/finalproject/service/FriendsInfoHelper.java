package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lecture.finalproject.model.ModelUser;

import JTCL.JTCLHelper;
import Komoran.Preprocessor;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import twitter4j.*;

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
	
	public List groupConcern(String[] friendNames){
		List <Map<String,Double>> weightPlusConcerns = getFriendsWeight(friendNames);
		List concerns = new ArrayList();
		List weights = new ArrayList();
		List tempC = new ArrayList();
		List tempW = new ArrayList();
		List groupConcerns = new ArrayList();
		for(int i=0;i<weightPlusConcerns.size();i++)
		{

			Set key = weightPlusConcerns.get(i).keySet();

			for (Iterator iterator = key.iterator(); iterator.hasNext();) {
				String keyName = (String) iterator.next();
				Double valueName = weightPlusConcerns.get(i).get(keyName);
				weightPlusConcerns.get(i).replace(keyName, valueName);

				concerns.add(keyName);
				weights.add(valueName);
			}
		}

		int count = 0;

		//String sk[] = new String[10];
		Boolean on[] = new Boolean[100];
		//List<Boolean> on = null;
		//int skindex[] = new int[10];
		//int s[] = new int[8];
		double temp=0;
		

		for (int j = 0; j < concerns.size(); ++j) {  //寃��궗
			for (int k = 0; k < concerns.size(); ++k) {
				if (concerns.get(j).toString().equals(concerns.get(k).toString())) {
					count++;
					temp=temp+Double.parseDouble(weights.get(k).toString());
					if (count > 1) {  //以묐났 �젣嫄�
						on[k] = true;
						;
					}
					else{
						on[k]=false;
						
					}
				}
			}
			if (on[j] == false) {
				tempW.add(temp);
				tempC.add(concerns.get(j).toString());
				//System.out.println(sk[j]+" : " + s[j]);
			}
			count = 0;
			
			temp=0;
		}
		for(int i=0;i<tempW.size();i++)
		{
			//System.out.println(tempC.get(i).toString() + " : " +tempW.get(i).toString());
			groupConcerns.add(tempC.get(i).toString());
		}
			
		
		return groupConcerns;
	}






		


		public List<Map<String, Double>> getFriendsWeight(String[] friendNames) {

			Map<String, Float> friendsNameAndWeight = new HashMap<String, Float>();
			float weight;
			List fc = new ArrayList();
			int count = 0;
			String str = "";
			Map<String, Double> Friendsconcerns = new HashMap<String,Double>();
			List<String> meaningWordList = null;
			Preprocessor processor = new Preprocessor();
			JTCLHelper concernHelper = new JTCLHelper();
			String fstr = "";

			try {
				ResponseList<User> friendUserObject = twitter.lookupUsers(friendNames); // �굹�쓽 移쒓뎄�뱾�쓽 user媛앹껜瑜� �뼸�뼱�샂
				for(User user : friendUserObject){

					System.out.println("?Friend's Name " + user.getName());
					System.out.println(count++);
					friendsNameAndWeight.put(user.getName(), (float) 0);

					if(user.getStatus() != null){

						List<Status> friendStatusess = twitter.getUserTimeline(user.getId()); //�굹�쓽 timeline�뿉�꽌 �빐�떦 user媛� �쟻�뼱�넃�� timeline�쓣 媛��졇�샂/statusess �븯�굹�뒗 timeline �븯�굹瑜� �쓽誘�

						//移쒓뎄媛� �굹�뿉寃� �젒珥됲븳寃쎌슦�쓽 weight
						for (Status status3 : friendStatusess) {
							//System.out.println(status3);
							fstr+=status3.getText();
							checkWeight(status3,user,myUser);
						}
						
						// System.out.println(weight);

						//�굹�쓽 ���엫�씪�씤�뿉�꽌 移쒓뎄�� 愿��젴�맂嫄� 戮묒븘�궡�옄
						List<Status> myStatusess = twitter.getUserTimeline(myUser.getId());

						for(Status status3 : myStatusess){
								
							
							//System.out.println(status3); 
							checkWeight(status3,myUser,user);
						}
						weight = getTotalWeight();
						//System.out.println(weight);
						weight = getTotalWeight();
						str = processor.preProcess(fstr);
						fstr = "";
						meaningWordList = processor.getMeaningWord(str);
						Friendsconcerns = concernHelper.getConcern(meaningWordList);

						friendsNameAndWeight.put(user.getName(), weight);
						//double a = friendsNameAndWeight.get(user.getName());

						Set key = Friendsconcerns.keySet();

						for (Iterator iterator = key.iterator(); iterator.hasNext();) {
							String keyName = (String) iterator.next();
							Double valueName = Friendsconcerns.get(keyName);
							
							Friendsconcerns.replace(keyName, valueName*weight);
							
						}

						



					}
					fc.add(Friendsconcerns);
					//System.out.println(Friendsconcerns.toString());
					
				
					
					retweet = 0;
					comment = 0;
					mention = 0;
					like = 0;
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());	
			}
			//for(int i=0;i<groupConcern(friendNames).size();i++)
				//System.out.println(groupConcern(friendNames).toString());
			//System.out.println(groupConcern(friendNames).size());
			//for(int i=0;i<3;i++)
				//System.out.println(i+1 +"�쐞 媛먯젙 : " + groupConcern(friendNames).get(i));
			return fc;
		}

		private float getTotalWeight(){
			float result = 0;	
			result = retweet + comment + mention + like;

			return result;
		}
		/*
	public List<Map<String, Double>> getFriendsConcern(String[] friendNames)
	{
		//Map<String, Double> friendsNameAndWeight = new HashMap<String, Double>();
		ArrayList fc = new ArrayList();
		String str = "";
		Map<String, Double> Friendsconcerns = new HashMap<String,Double>();
		List<String> meaningWordList = null;
		Preprocessor processor = new Preprocessor();
		JTCLHelper concernHelper = new JTCLHelper();
		String fstr = "";
		int  j=0;
		try {
			ResponseList<User> friendUserObject = twitter.lookupUsers(friendNames); // �굹�쓽 移쒓뎄�뱾�쓽 user媛앹껜瑜� �뼸�뼱�샂
			for(User user : friendUserObject){

				//System.out.println("Friend's Name " + user.getName());

				if(user.getStatus() != null){

					List<Status> friendStatusess = twitter.getUserTimeline(user.getId()); //�굹�쓽 timeline�뿉�꽌 �빐�떦 user媛� �쟻�뼱�넃�� timeline�쓣 媛��졇�샂/statusess �븯�굹�뒗 timeline �븯�굹瑜� �쓽誘�

					//移쒓뎄媛� �굹�뿉寃� �젒珥됲븳寃쎌슦�쓽 weight
					for (Status status3 : friendStatusess) {
						//System.out.println(status3);
						fstr+=status3.getText();

					}
					str = processor.preProcess(fstr);
					fstr = "";
					meaningWordList = processor.getMeaningWord(str);
					Friendsconcerns = concernHelper.getConcern(meaningWordList);

					fc.add(Friendsconcerns);

				}


			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
		}



		return fc;
	}
		 */

		private void checkWeight(Status inputTimeline, User myUser,User friendUser){

			if(isComment(inputTimeline,friendUser,myUser)){
				comment += checkCommentWeight(inputTimeline);
				checkOtherFavortieWeight(inputTimeline);
				//System.out.println("iscomment");
			}
			else if(isRetweet(inputTimeline, friendUser,myUser)){
				retweet += checkRetweetWeight(inputTimeline);
				checkReTweetFavoriteWeight(inputTimeline);
				//System.out.println("isReweet");
			}else if(isMention(inputTimeline, friendUser,myUser)){
				mention += checkMentionWeight(inputTimeline);
				checkOtherFavortieWeight(inputTimeline);
				//System.out.println("isMention");
			}
			//洹몃깷 �옄湲고샎�옄 湲��쓣 �벖寃쎌슦�뿉�뒗 �꽆�뼱媛꾨떎.
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
				//�굹�뿉���븳 �떟湲��씤吏�.
				//留뚯빟 �씠湲��씠 �굹(friendUser)�뿉寃� �떎�뒗 湲��씠�씪硫�
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
				ResponseList<User> friendUserObject = twitter.lookupUsers(friendNames); // �굹�쓽 移쒓뎄�뱾�쓽 user媛앹껜瑜� �뼸�뼱�샂

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

			//Date�쑝濡� 諛쏄퀬 calendar濡� 吏��젙�빐以섏빞 �궇�옄瑜� 媛��졇�삱 �닔 �엳�떎.
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
