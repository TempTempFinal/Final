package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Point;
import com.lecture.finalproject.model.ModelCheckIn;
import com.lecture.finalproject.model.ModelCount;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.repository.SNSRepository;

import twitter4j.User;



public class InfoExtractor {
	
	private SNSRepository repository = SNSRepository.getInstance();
	
public List<ModelCheckIn> getRangeInnerPlace(SNSRepository snsRepository,double lat, double lng, int radius){
		
		List<ModelCheckIn> result = new ArrayList<ModelCheckIn>();
		
		RTree<ModelCheckIn, Point> checkinRtreeIndex = snsRepository.getRtree();
		List<Entry<ModelCheckIn, Point>> list = RtreeHelper.search(checkinRtreeIndex, Geometries.point(lng,lat), radius)
			                .toList().toBlocking().single();
		 
		for(Entry<ModelCheckIn, Point> place : list){
			result.add(place.value());
		}
		return result;
	}
	
	public List<ModelCheckIn> getFriendVisitPlaceInRange(List<ModelCheckIn> innerPlace, ModelUser modelUser){
		List<ModelCheckIn> result = new ArrayList<ModelCheckIn>();
		ModelCheckIn checkInTemp = null;
		boolean isFirst;
		
		
		for(ModelCheckIn checkin : innerPlace){
			
			isFirst = true;
			for(Long friend : modelUser.getFriend_list()){
				//갖고있다
				
				if((checkin.getUser_id_list().get(Long.toString(friend)) != null) && isFirst){
					checkInTemp = new ModelCheckIn();
					checkInTemp.setPlace_no(checkin.getPlace_no());
					checkInTemp.setLatitude(checkin.getLatitude());
					checkInTemp.setLongitude(checkin.getLongitude());
					checkInTemp.getUser_id_list().put(Long.toString(friend), null);
					isFirst = false;
					
					result.add(checkInTemp);
				}
				else if((checkin.getUser_id_list().get(Long.toString(friend)) != null && !isFirst))
					checkInTemp.getUser_id_list().put(Long.toString(friend), null);
			}
		}
		
		return result;
	}
	
	
	public List<ModelCheckIn> getMostVisitedPlace(List<ModelCheckIn> placeList, int k){
			
		List<ModelCheckIn> mostVisitedPlaces = new ArrayList<ModelCheckIn>();
		int count = 0;
		
		for(int i=0; i<placeList.size(); i++){		
			if(count == k)
				break;
			mostVisitedPlaces.add(placeList.get(i));
			count++;
		}
		return mostVisitedPlaces;
	}
}
