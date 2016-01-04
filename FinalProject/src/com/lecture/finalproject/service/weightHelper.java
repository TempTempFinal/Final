package com.lecture.finalproject.service;

import java.util.List;

import com.lecture.finalproject.model.ModelFrontTravlePost;

public class weightHelper {
	
	public int setPostWeight(List<ModelFrontTravlePost> postList){
		int likeCount;
		int commentCount;
		double sentiment;
		double weight;
		int completeCount = 0;
		
		for(ModelFrontTravlePost postOne : postList){
			
			 likeCount = postOne.getLike_count();
			 commentCount = postOne.getComment_count();
			 sentiment = postOne.getSentiment();
			
			 weight = calWeight(likeCount,commentCount,sentiment);
			 postOne.setWeight(weight);
			 
			 completeCount++;
			 
			 if(completeCount == postList.size())
				 return 1;
		}
		
		return 0;
	}
	
	//TODO have to calculate Popular weight
	private double calWeight(int likeCount, int commentCount, double sentiment){
		
		double result;
		
		result = likeCount;
		
		return result;
	}

}
