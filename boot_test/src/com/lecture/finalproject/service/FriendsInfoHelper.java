package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lecture.finalproject.model.ModelTwitterWiget;
import com.lecture.finalproject.model.ModelUser;

import JTCL.JTCLHelper;
import JTCL.TextCatDriver;
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
	
	
	
	public List<ModelUser> getTopFriendList(Map<String,Float> friendNameAndWeight){
		
		List<ModelUser> topFriendList = new ArrayList<ModelUser>();
		List<String> tempList = new ArrayList<String>();
		
		int count = 0;
		
		Iterator it = sortByValue(friendNameAndWeight).iterator();
		
		//top4명의 screen name을 얻어온다
        while(it.hasNext()){
        	if(count == 4)
        		break;
        	
            String temp = (String) it.next();
            tempList.add(temp);
            
	        count++;
	    }
        
        try{
        	ResponseList<User> friendUserObject = twitter.lookupUsers(tempList.toArray(new String[tempList.size()])); // 나의 친구들의 user객체를 얻어옴
        	ModelUser temp; 
        
        	for(User user : friendUserObject){
        		temp = new ModelUser();
        		temp.setImg_url(user.getProfileImageURL());
        		temp.setName(user.getName());
        		temp.setScreenName(user.getScreenName());
        		temp.setUser_id(Long.toString(user.getId()));
        		topFriendList.add(temp);
			}
        }catch(TwitterException e){
        	System.out.println(e.getMessage());
        }
        
		return topFriendList;
	}
	
	
	
	 private  List sortByValue(final Map map){
	        List<String> list = new ArrayList();
	        list.addAll(map.keySet());
	         
	        Collections.sort(list,new Comparator(){
	             
	            public int compare(Object o1,Object o2){
	                Object v1 = map.get(o1);
	                Object v2 = map.get(o2);
	                 
	                return ((Comparable) v1).compareTo(v2);
	            }
	             
	        });
	        Collections.reverse(list); // 주석시 오름차순
	        return list;
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
	
	public String[] getFriendAndFollowerNameList(){
		List<String> tempList = new ArrayList<String>();
		
		try {
			long cursor = -1;
			PagableResponseList<User> pagableFollowings;
			do {
				pagableFollowings = twitter.getFriendsList(twitter.getId(), cursor);
				for (User user : pagableFollowings) {
					tempList.add(user.getScreenName());	
				}
			} while ((cursor = pagableFollowings.getNextCursor()) != 0);

		} catch (TwitterException e) {
			System.out.println(e.getMessage());
		}
		
		String[] nameList = tempList.toArray(new String[tempList.size()]);
		
		return nameList;
	}
	
	public List groupConcern(String[] friendNames){
		List <Map<String,Double>> weightPlusConcerns = getFriendsWeight(friendNames);
		List<String> concerns = new ArrayList();
		List<Double> weights = new ArrayList();
		List<String> tempC = new ArrayList();
		List<Double> tempW = new ArrayList();
		ServiceInfoSynchronize myConcern = new ServiceInfoSynchronize();

		myConcern.timelineSync(twitter,myUser);
		
		
		
		Map<String,Double> mC = myConcern.concerns;
		ArrayList tempConcerns = new ArrayList();
		ArrayList groupConcerns = new ArrayList();
		Set mykey = mC.keySet();
		
		System.out.println(mC.size());
		
		
		for (Iterator iterator = mykey.iterator(); iterator.hasNext();) {
			String keyName = (String) iterator.next();
			Double valueName = mC.get(keyName);
			mC.replace(keyName, valueName);

			concerns.add(keyName);
			weights.add(valueName);
		}
		
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
		
		for(int i=0;i<concerns.size();i++)
		{
			System.out.println("그룹관심사" + concerns.get(i)+"/ " + Double.parseDouble(weights.get(i).toString()));
		}
		int count = 0;

		//String sk[] = new String[10];
		Boolean on[] = new Boolean[100];
		//List<Boolean> on = null;
		//int skindex[] = new int[10];
		//int s[] = new int[8];
		double temp=0;
		

		for (int j = 0; j < concerns.size(); ++j) {  
			for (int k = 0; k < concerns.size(); ++k) {
				if (concerns.get(j).toString().equals(concerns.get(k).toString())) {
					count++;
					temp=temp+Double.parseDouble(weights.get(k).toString());
					if (count > 1) { 
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
		double sum=0;
		for(int i=0;i<tempW.size();i++)
		{
			System.out.println(tempC.get(i).toString() + " : " +tempW.get(i).toString());
			sum+=Double.parseDouble(tempW.get(i).toString());
			tempConcerns.add(tempC.get(i).toString());
		}
		ArrayList<Integer> index = new ArrayList();
		sortWeights(tempW,index);
		
		double var = sum/tempW.size();
		double dev = Math.sqrt(var);
		double sum22 =0;
		double sum3 =0;
		double sum4 =0;
		double sum5 =0;
		double dev2n3;
		double dev3n4;
		double dev4n5;
		int picknum=0;
		if(tempW.size()<=4)
			picknum=tempW.size();
		else if(tempW.size()>4)
		{
			
		for(int i=0;i<2;i++)
			sum22+= Double.parseDouble(tempW.get(i).toString());
		for(int i=0;i<3;i++)
			sum3+= Double.parseDouble(tempW.get(i).toString());
		dev2n3 = (sum22/2)-(sum3/3);
		sum3=0;
	
		
		for(int i=0;i<3;i++)
			sum3+= Double.parseDouble(tempW.get(i).toString());
		for(int i=0;i<4;i++)
			sum4+= Double.parseDouble(tempW.get(i).toString());
		
		dev3n4 = (sum3/3)-(sum4/4);
		sum4=0;
		for(int i=0;i<4;i++)
			sum4+= Double.parseDouble(tempW.get(i).toString());
		for(int i=0;i<5;i++)
			sum5+= Double.parseDouble(tempW.get(i).toString());
		
		dev4n5 = (sum4/4)-(sum5/5);
		
		 picknum = TextCatDriver.pickdev(dev2n3,dev3n4,dev4n5,dev);
			System.out.println(picknum);
		}	
		
		
		for(int i=0;i<picknum;i++)
		{
			if(Double.parseDouble(tempW.get(i).toString())!=0.0)
				groupConcerns.add(tempC.get(index.get(i)).toString());
			
			System.out.println("최종관심사" + groupConcerns);
		}
		return groupConcerns;
	}
	
	private void sortWeights(List<Double> iList, List<Integer> index)
	{
		 double itmp=0;
		 for(int i = 0 ; i < iList.size(); i++){
	            for(int j = 0; j < i ; j++) {
	                if(iList.get(i) <= iList.get(j)){
	                    itmp = iList.get(i);
	                    iList.set(i, iList.get(j));
	                    iList.set(j, itmp);
	                }
	            }
	        }
		 
		 for(int i=0;i<iList.size();i++)
		       index.add(i);


	}



	public Map<String, Float> getPureFriendWeight(String[] friendNames){
		
		Map<String, Float> friendsNameAndWeight = new HashMap<String, Float>();
		float weight;
		
		try {
			ResponseList<User> friendUserObject = twitter.lookupUsers(friendNames); // 나의 친구들의 user객체를 얻어옴
			for(User user : friendUserObject){
				
				System.out.println("Friend's Name " + user.getName());
				friendsNameAndWeight.put(user.getScreenName(), (float) 0);
				if(user.getStatus() != null){
					
					List<Status> friendStatusess = twitter.getUserTimeline(user.getId()); //나의 timeline에서 해당 user가 적어놓은 timeline을 가져옴/statusess 하나는 timeline 하나를 의미
							//친구가 나에게 접촉한경우의 weight
					 for (Status status3 : friendStatusess) {
						 	checkWeight(status3,user,myUser);
					}
					 weight = getTotalWeight();
	
					 
					 //나의 타임라인에서 친구와 관련된걸 뽑아내자
					 List<Status> myStatusess = twitter.getUserTimeline(myUser.getId());
						
					 for(Status status3 : myStatusess){
						 	checkWeight(status3,myUser,user);
					 }
					 weight = getTotalWeight();
			
					 
					friendsNameAndWeight.put(user.getScreenName(), weight);
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
			//	System.out.println(groupConcern(friendNames).toString());
		//	System.out.println(groupConcern(friendNames).size());
		//	for(int i=0;i<3;i++)
			//	System.out.println(i+1 +"�쐞 媛먯젙 : " + groupConcern(friendNames).get(i));
			return fc;
		}

		private float getTotalWeight(){
			float result = 0;	
			result = retweet + comment + mention + like;

			return result;
		}
		
		public List<ModelTwitterWiget> getFriendWiget(String post_title){
			
			List<ModelTwitterWiget> result = new ArrayList<ModelTwitterWiget>();
			
			try {
				ResponseList<User> friendUserObject = twitter.lookupUsers(getFriendAndFollowerNameList()); // �굹�쓽 移쒓뎄�뱾�쓽 user媛앹껜瑜� �뼸�뼱�샂
				for(User user : friendUserObject){

					if(user.getStatus() != null){

						List<Status> friendStatusess = twitter.getUserTimeline(user.getId()); //�굹�쓽 timeline�뿉�꽌 �빐�떦 user媛� �쟻�뼱�넃�� timeline�쓣 媛��졇�샂/statusess �븯�굹�뒗 timeline �븯�굹瑜� �쓽誘�

						for (Status status3 : friendStatusess) {
							
							if(status3.getText().contains(post_title)){
								ModelTwitterWiget wiget = new ModelTwitterWiget();
								
								wiget.setUserId(Long.toString(user.getId()));
								wiget.setMediaId(Long.toString(status3.getId()));
								wiget.setCreateTime(status3.getCreatedAt().toString());
								wiget.setLikeCount(status3.getFavoriteCount());
								
								System.out.println(wiget);
								result.add(wiget);
								//wiget.setMediaType(status3.getM);
							}	
						}
					}
				}
			} catch (TwitterException e) {
				// TODO Auto-generated catch block
				System.out.println(e.getMessage());	
			}
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
