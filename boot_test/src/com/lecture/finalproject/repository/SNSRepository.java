package com.lecture.finalproject.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelCheckIn;
import com.lecture.finalproject.service.ServiceLogin;

import twitter4j.Twitter;
import twitter4j.User;



public class SNSRepository {
	

	private static SNSRepository repository;
	private static Map<Long,FriendshipRepository> friendshipRepositories;
	private static RTree<ModelCheckIn, Point> rTree;

	private SNSRepository(){
	
		friendshipRepositories = new HashMap<Long,FriendshipRepository>();
		rTree = RTree.minChildren(10).maxChildren(20).create();
		buildTree();
		System.out.println("tree build");
	}
	
	public static SNSRepository getInstance(){
		
		if(repository == null){
			
			synchronized (key1) {
				System.out.println("새로생성");
				repository = new SNSRepository();	
			}
		}
		return repository;
	}
	
	public void buildTree(){
		DaoTravlePlace db = new DaoTravlePlace();
		
		List<ModelCheckIn> checkInList = db.getCheckInList();
		
		for(ModelCheckIn checkIn : checkInList){
			rTree = rTree.add(checkIn, Geometries.point(checkIn.getLongitude(),checkIn.getLatitude()));
		}
	}
	
	public RTree<ModelCheckIn, Point> getRtree(){
		if(rTree == null)
			System.out.println("rTree is null");
		return rTree;
	}
	
	public FriendshipRepository getFriendRepository(Twitter twitter, User user){

		if(friendshipRepositories.get(user.getId()) == null)
			addFriendRepository(twitter,user);
		
		return friendshipRepositories.get(user.getId());
	}
	
	
	private void addFriendRepository(Twitter twitter, User user){
		
		//현재 메모리에 없다면	
			// 1.우선 DB를확인
			// 2.DB에도 없다면 새로만들어줘서 저장
		
		FriendshipRepository one = new FriendshipRepository();
		one.setModelUser(twitter, user);
	
		if(one != null)
			friendshipRepositories.put(Long.parseLong(one.getModelUser().getUser_id()),one);
	}

	
	

	private static Object key1 = new Object();
	
}
