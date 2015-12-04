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

public class ServiceHashExtractHelper {
	
    private DaoTravlePlace db;
    private Preprocessor pc;
    private JTCLHelper jtcl;
	
    public ServiceHashExtractHelper(){
    	db = new DaoTravlePlace();
    	pc = new Preprocessor();
    	jtcl = new JTCLHelper();
    }
    
    public void updatePostFeature(){
    	
    	String tempKeyword;
    	List<String> keywordList = null; 
    	Map<String, Double> feature = null;
    	ModelFeature feat = null;
    	
    	List<ModelInformation> infoList = db.getPostInformationTotalList();
    	
    	for(int i=0; i<infoList.size(); i++){
    		
    		tempKeyword = pc.preProcess(infoList.get(i).getTravel_content());
    		keywordList = pc.getMeaningWord(tempKeyword);
    		feature = jtcl.getFeature(keywordList);
    		
    		for(String key : feature.keySet()){
    			db.insertPostFeature(new ModelFeature(key, infoList.get(i).getTravelPost_no()));
        		
    		}
    			
    		
    		
    	}
    	
    }
    
    public void updateHashTag(String content, int travlePost_no){
    	
    	List<ModelHash> hashTagList = null;
    	
    	
    	
    	
    	
    }
    
    public List<ModelHash> extractHashTagTest(String input, int travlePost_no) {
     
    	List<ModelHash> hashTagList = new ArrayList<ModelHash>();
    	/*
    	String test ="나는 어딘가에서 #테스트 포를 #가#나다#라라라$ #배$#%@ #443##fefef";
        String test1 ="#아무개가 세미나에 참여했다.";
        String test2 ="#아무개? 이 캐릭터는 누구냐?";
        String test3 ="#작두#망토 어때요?";
        String test4= "말도안돼#니가$정말#그 사람이었다니 말야##이상하군!! 정말";
     */
        Pattern p = Pattern.compile("\\#([0-9a-zA-Z가-힣]*)");
        Matcher m = p.matcher(input);
        String extractHashTag = null;
     
        while(m.find()) {
        extractHashTag = sepcialCharacter_replace(m.group());
     
	        if(extractHashTag != null) {
	        	hashTagList.add(new ModelHash(extractHashTag,travlePost_no));
	           // System.out.println("최종 추출 해시태그 :: {}" + extractHashTag);
	        }
        }
        
        return hashTagList;
    }
     
    private String sepcialCharacter_replace(String str) {
        str = StringUtils.replaceChars(str, "-_+=!@#$%^&*()[]{}|\\;:'\"<>,.?/~`） ","");
     
        if(str.length() < 1) {
        return null;
        }
     
        return str;
    }
    
    

}