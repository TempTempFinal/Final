package com.lecture.finalproject.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class SearchHelper {
	
	private static SearchHelper searchHelper;
	private Map<String,String> locationTable = null;
	
	private SearchHelper(){
		locationTable = new HashMap<String,String>();
		InitLocationTable();
	}
	
	private void InitLocationTable(){
		
		locationTable.put("서울", "서울%");
		locationTable.put("경기", "경기%");
		locationTable.put("경기도", "경기%");
		locationTable.put("충남", "충%남%");
		locationTable.put("충청남도", "충%남%");
		locationTable.put("충청북도", "충%북%");
		locationTable.put("충북", "충%북%");
		locationTable.put("충남", "충%남%");
		locationTable.put("전남", "전%남%");
		locationTable.put("전라남도", "전%남%");
		locationTable.put("전북", "전%북%");
		locationTable.put("전라북도", "전%북%");
		locationTable.put("전라도", "전%라%도%");
		locationTable.put("경북", "경%북%");
		locationTable.put("경상북도", "경%북%");
		locationTable.put("경남", "경%남%");
		locationTable.put("경상남도", "경%남%");
		locationTable.put("경상도", "경%상%도%");
		locationTable.put("강원도", "강원%");
		locationTable.put("강원", "강원%");
		locationTable.put("제주", "제주%");
		locationTable.put("제주도", "제주%");
	}
	
	public static SearchHelper getInstance(){
		
		if(searchHelper == null)
			searchHelper = new SearchHelper();
		
		return searchHelper;
	}
	
	public Map<String,String> getMap(){
		return locationTable;
	}
	
	public List<String> findLocationQuery(List<String> searchWord){
		List<String> locationName = new ArrayList<String>();
		
		for(int i=0; i<searchWord.size(); i++){
			
			for (Map.Entry<String, String> entry : locationTable.entrySet()) {
			    
				 if(entry.getKey().equals(searchWord.get(i))){
			        	searchWord.remove(i);
			        	locationName.add(entry.getValue());
			        	i--;
			        	break;
			        }
			}
		}
		
		return locationName;
	}
}
