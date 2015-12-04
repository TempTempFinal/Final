package com.lecture.finalproject.service;

import java.util.ArrayList;
import java.util.List;

import twitter4j.User;

public interface IServiceSNSParser {
	
	   public abstract List<Object> getFriendsList (User user);
	    

}
