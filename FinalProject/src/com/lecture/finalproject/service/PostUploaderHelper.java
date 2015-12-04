package com.lecture.finalproject.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.dao.JDBCMannager;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelHash;
import com.lecture.finalproject.model.ModelInformation;

import JTCL.JTCLHelper;
import Komoran.Preprocessor;

public class PostUploaderHelper {
	
    private DaoTravlePlace db;
    private Preprocessor pc;
    private JTCLHelper jtcl;
	
    public PostUploaderHelper(){
    	db = new DaoTravlePlace();
    	pc = new Preprocessor();
    	jtcl = new JTCLHelper();
    }
    
    
    //모든 여행지 글을 가져와서 특징정보를 update 시켜준다
    public void extractAllPostFeature(){
    	
    	String tempKeyword;
    	List<String> keywordList = null; 
    	Map<String,Double> features = null;
    	
    	
    	List<ModelInformation> infoList = db.getPostInformationTotalList();
    	
    	for(int i=0; i<infoList.size(); i++){
    		
    		tempKeyword = pc.preProcess(infoList.get(i).getTravel_content());
    		keywordList = pc.getMeaningWord(tempKeyword);
    		
    		features = jtcl.getFeature(keywordList);
    		
    		for(String key : features.keySet()){
    			db.insertPostFeature(new ModelFeature(key, infoList.get(i).getTravelPost_no()));  			
    		}
  
    	}	
    }
    
    public void extractOnePostFeature(String input, int travlePost_no){
    	String tempKeyword;
    	List<String> keywordList = null; 
    	

    		tempKeyword = pc.preProcess(input);
    		keywordList = pc.getMeaningWord(tempKeyword);

    		Map<String,Double> features = null;
        	
    		
    		features = jtcl.getFeature(keywordList);
    		
    		for(String key : features.keySet()){
    			db.insertPostFeature(new ModelFeature(key, travlePost_no));  			
    		}
    }
       
    //해당 여행지으 글과 번호를 받아와서 Hash Tag 객체를 마들어서 List로 리턴해준다.
    public void extractHashTagTest(String input, int travlePost_no) {
     
    	List<ModelHash> hashTagList = new ArrayList<ModelHash>();
  
        Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
        Matcher m = p.matcher(input);
        String extractHashTag = null;
     
        //해쉬태그 추출
        while(m.find()) {
        extractHashTag = sepcialCharacter_replace(m.group());
     
	        if(extractHashTag != null) {
	        	db.insertPostHash(new ModelHash(extractHashTag,travlePost_no));
	           // System.out.println("최종 추출 해시태그 :: {}" + extractHashTag);
	        }
        }
    }
     
    private String sepcialCharacter_replace(String str) {
        str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ","");
     
        if(str.length() < 1) {
        return null;
        }
     
        return str;
    }
    
    

}