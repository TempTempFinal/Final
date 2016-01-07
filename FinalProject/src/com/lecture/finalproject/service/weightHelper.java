package com.lecture.finalproject.service;

import java.util.List;

import com.lecture.finalproject.model.ModelFrontTravlePost;

public class weightHelper {
	
	public void setPostWeight(List<ModelFrontTravlePost> postList){
		int likeCount;
		int commentCount;
		double senti_positive;
		double senti_negative;
		double pre_popularity;
		int search_count;
	
		double newPopularity;
		
		for(ModelFrontTravlePost postOne : postList){
			
			 likeCount = postOne.getLike_count();
			 commentCount = postOne.getComment_count();
			 senti_positive = postOne.getSenti_positive();
			 senti_negative = postOne.getSenti_negative();
			 search_count = postOne.getSearch_count();
			 
			 pre_popularity = postOne.getPre_popularity();
			 
			 newPopularity= calPopularity(likeCount,commentCount,senti_positive, senti_negative, search_count);
			 
			 if((newPopularity - pre_popularity) > 0)
				 postOne.setgap_popularity(newPopularity - pre_popularity);
			 else
				 postOne.setgap_popularity(0);
		}
	}
	
	//TODO have to calculate Popular weight
	private double calPopularity(int likeCount, int commentCount, double senti_positive, double senti_negative, int search_count){
		
		double result = 0;
		
		result += (likeCount * 10);
		result += ((senti_positive * 10) * commentCount);
		result += ((senti_negative * 10) * commentCount);
		result += (search_count * 0.5);
		
		return result;
	}

}
