package com.lecture.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.repository.SNSRepository;
import com.lecture.finalproject.service.FriendsInfoHelper;

import twitter4j.Twitter;
import twitter4j.User;

/**
 * Servlet implementation class GroupController
 */
@WebServlet("/group")
public class GroupController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GroupController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		FriendsInfoHelper friendInfo = null;
		int count = 0;
		
		
		String[] nameList = null;
		
		boolean isLogin = session.getAttribute("checkLogin") == null ? false : true;
		request.setAttribute("isLogin", "false");
		
		if(isLogin){
			User user = (User)session.getAttribute("twitterUser");
			Twitter twitter = (Twitter)session.getAttribute("twitter");
			
			
			friendInfo = new FriendsInfoHelper(twitter,user);
			nameList = friendInfo.getFriendAndFollowerNameList();
			Map<String, Float> friendNameAndWeight = friendInfo.getPureFriendWeight(nameList);
			List<ModelUser> topFriendList = null;
			
			topFriendList = friendInfo.getTopFriendList(friendNameAndWeight);
			
			
			request.setAttribute("topFriendList", topFriendList);
			request.setAttribute("userName", user.getName());
			request.setAttribute("isLogin", "true");
		}
		
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/group.jsp");
	    dispatcher.forward(request, response);
	}
	
	
	
	 
	
	

}
