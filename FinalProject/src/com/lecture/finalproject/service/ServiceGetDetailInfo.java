package com.lecture.finalproject.service;

import java.util.List;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelComment;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelHash;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelUser;

public class ServiceGetDetailInfo {
	private DaoTravlePlace db;
	
	public ServiceGetDetailInfo(){
		db = new DaoTravlePlace();
		
	}
	
	public List<ModelComment> getCommentList(int postNum){
		List<ModelComment> result = null;
		
		result = db.getCommentList(postNum);
		return result;
	}
	
	public ModelTravelPost getTravlePost(int postNum){
		ModelTravelPost result = null;
		
		result = db.getTravelPostOne(postNum);
		return result;
	}
	
	public List<ModelInformation> getInformationList(int postNum){
		List<ModelInformation> result = null;
		
		result = db.getInformationList(postNum);
		return result;
	}
	
	public List<ModelHash> getHashList(int postNum){
		List<ModelHash> result = null;
		
		result = db.getHashList(postNum);
		return result;	
	}
	
	public List<ModelImage> getImageList(int postNum){
		List<ModelImage> result = null;
		
		result = db.getImageList(postNum);
		return result;
	}
	
	public int getLikeCount(int postNum){
		int result = 0;
		
		result = db.getLikeCount(postNum);
		return result;
	}
	
	public ModelLocation getLocation(int postNum){
		ModelLocation result = null;
		
		result = db.getLocationOne(postNum);
		return result;
	}
	
	public List<ModelFeature> getFeatureList(int postNum){
		List<ModelFeature> result = null;
		
		result = db.getFeatureList(postNum);
		return result;
	}
	
	public ModelUser getWriter(int postNum){
		ModelTravelPost	temp = getTravlePost(postNum);
		
		ModelUser result = db.getUserInfo(temp.getUser_id());
		return result;
	}
	
	
	
	
}
