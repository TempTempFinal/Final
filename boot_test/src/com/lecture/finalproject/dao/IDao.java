package com.lecture.finalproject.dao;

import java.util.List;
import java.util.Map;

import com.lecture.finalproject.model.ModelCheckIn;
import com.lecture.finalproject.model.ModelComment;
import com.lecture.finalproject.model.ModelCommentList;
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelFrontTravlePost;
import com.lecture.finalproject.model.ModelHash;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelUser;



public interface IDao {
	
	public abstract ModelUser getUserInfo(String user_id);
	
	public abstract int insertUserInfo(ModelUser user);
	
	public abstract int getUserCount(String user_id);
	
	public abstract ModelUser getWriterInfo(int travelPost_no);
	
	public abstract int getTravelPostCount();
	
	public List<ModelFrontTravlePost> getFrontTravlePostList();
	//front Travle List 가져오기
	public abstract List<ModelFrontTravlePost> getFrontTravlePostList(int startPage, int pageNum);
	//front Travle List 위치기반
	public abstract List<ModelFrontTravlePost> getFrontTravlePostListByLocation(String[] location, int startPage, int pageNum);
	 //front Travle sorted List 위치 기반
	public List<ModelFrontTravlePost> getFrontTravlePostListBySortedLocation(String[] location, String standard,int startPage, int pageNum);
	//front Travle List Id기반
	public List<ModelFrontTravlePost> getFrontTravlePostListById(String user_id);
	//search word로 찾기
    public abstract List<ModelFrontTravlePost> getFrontTravlePostBySearchWord(String searchWord, int startPage, int pageNum);
    //관심사로 찾기
    public abstract List<ModelFrontTravlePost> getFrontTravelPostByConcern(String concern, int startPage, int pageNum);
    //hash로 찾기
    public abstract List<ModelFrontTravlePost> getFrontTravlePostByHashTag(String hashTag, int startPage, int pageNum);
    
    public abstract int getCountTravlePostBySearchWord(String searchWord);
    public abstract int getCountTravlePostByHashTagWrod(String searchWord);
    public abstract int getCountTravlePostByConcern(String conern);
    
    
	
	//여행지 List, 여행지 하나 가져오기

    public abstract List<ModelTravelPost> getTravelPostList(String user_id);
    public abstract ModelTravelPost getTravelPostOne(int travelPost_no);
    
    
    public abstract ModelLocation getLocationOne(int travelPost_no);
    
    public abstract List<ModelImage> getImageList(int travelPost_no);
    
    public abstract List<ModelComment> getCommentList(int travelPost_no);
    
    public abstract int getLikeCount(int travelPost_no);
    
    
    //여행지에 대한 설명 정보 
    public abstract List<ModelInformation> getInformationList(int travelPost_no);
    public abstract ModelInformation getInformation(int travelPost_no);
    public abstract List<ModelInformation> getPostInformationTotalList();
    
    
    public abstract List<ModelHash> getHashList(int travelPost_no);
    public abstract List<ModelFeature> getFeatureList(int travelPost_no);
    
    
    //관심사 동기화 
    public abstract List<ModelConcern> getConcernList(String user_id);
    public abstract int insertConcern(ModelConcern concernList);
    public abstract int updateSyncState(String user_id);
    
    
    //인기 여행지 사진 가져오기
    public abstract List<ModelImage> getPopularLocationImage(int count);
    
    
    //여행지 feature 넣기
    public abstract int insertPostFeature(ModelFeature feature);
    public abstract int insertPostHash(ModelHash hashTag);
    
    //comment 긍부정
    public abstract List<ModelCommentList> getModelCommentList(int travelPost_no);
    public abstract int insertCommentList(ModelComment comentList);
    
    //초기 여행지 setting
    public abstract int insertTravelPost(ModelTravelPost post);
    public abstract int insertTravelLocation(ModelLocation location);
    public abstract int insertTravelImage(ModelImage image);
    public abstract int insertTravelInformation(ModelInformation info);
    
    
    //popular page
    public abstract List<ModelFeature> getFeatureGroupList();
    public abstract List<ModelFrontTravlePost> getTopFrontTravelPostByCategory(String category);
    public abstract List<ModelFrontTravlePost> getTopFrontTravelPostByAllCategory();
    
    public abstract List<ModelFrontTravlePost> getPopularFrontTravelPostByAllCategory(int startPage, int pageNum);
    public abstract List<ModelFrontTravlePost> getPopularFrontTravelPostByCategory(String category, int startPage, int pageNum);
    
    
    //update page
    public abstract int updateViewCount(int travelPost_no);
    //update like
    public abstract int updateLikeCount(int travelPost_no);
    public abstract int updateLikeMinusCount(int travelPost_no);
    public abstract int insertLikePerosn(int travelPost_no, String userID);
    
    
    public abstract int insertCheckin(int travelPost_no, String userID,double latitude, double longitude); //나중에 다시한번 확
    public abstract int deleteCheckin(int travelPost_no, String userID);
    
    
    public abstract int removeLikePerson(int travelPost_no, String userID);
    
    public abstract int getLikeState(int travelPost_no, String userID);
    public abstract int getVisitState(int travelPost_no, String userID);
    
    
    public abstract List<Long> getFriendship(String user_id);
    public abstract List<ModelCheckIn> getCheckInList();   //수정요망ㅇㅇ
    
    public abstract long[] getFriendsList(String user_id);  //수정요망ㅇ
    public abstract int insertFriendship(String user_id, long friendship); //수정요망ㅇ
    
    public abstract List<ModelCheckIn> updateTopkPlaceInfo(List<ModelCheckIn> topkList);
    
    public abstract int test(double user_id, double friend_id);



    
    
    
}
