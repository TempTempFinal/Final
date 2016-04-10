package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.dao.Travelpost_db;
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelUser;

import JTCL.JTCLHelper;
import JTCL.TextCatDriver;
import Komoran.Preprocessor;
import twitter4j.PagableResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

public class ServiceInfoSynchronize {
	public Map<String,Double> concerns = null;

	public boolean timelineSync(Twitter twitter,User user){
		int result = 0;
		int result1 = 0;
		String str="";

		List<String> meaningWordList = null;	
		List<String> timelineList = null;
		

		ServiceTwitterParser parser = new ServiceTwitterParser();	
		DaoTravlePlace uploader = new DaoTravlePlace();
		Preprocessor processor = new Preprocessor();
		JTCLHelper concernHelper = new JTCLHelper();
		
		//전처리 후 의미있는 NNP, NNG list를 받아옴
		timelineList = parser.getMyTimeline(twitter,user);
		str = processor.timeLineToString(timelineList);
		str = processor.preProcess(str);
		meaningWordList = processor.getMeaningWord(str);

		//List를 JTCL에 넣어준다
		concerns = concernHelper.getConcern(meaningWordList);
		
		System.out.println(meaningWordList);
		
		System.out.println(concerns);
		for( String key : concerns.keySet()){
			result = uploader.insertConcern(new ModelConcern(key, Long.toString(user.getId())));
		}

		result1 = uploader.updateSyncState(Long.toString(user.getId()));

		if(result1 > 0)
			return true;
		return false;
	}
}
