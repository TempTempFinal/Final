package com.lecture.finalproject.controller;

import java.io.IOException;
import java.util.ArrayList;
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
import com.lecture.finalproject.repository.FriendshipRepository;
import com.lecture.finalproject.repository.SNSRepository;
import com.lecture.finalproject.service.FriendsInfoHelper;
import com.lecture.finalproject.service.ServiceLogin;
import com.lecture.finalproject.service.ServiceTwitterParser;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Servlet implementation class MainController
 */


@WebServlet("/")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final String CONSUMER_KEY = "C7nrqY6FAqjhtOpg4knP7ZWk4"; //APP등록후받은consumer key
	final String CONSUMER_SECRET = " ZalhGXMVT02jP2mzPCNhjA8cDUTGN1sjvBb05YsoH42yy48pto"; //APP등록후받은consumer secret
	
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    
	    processRequest(request,response);
	    
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    processRequest(request,response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		HttpSession session = request.getSession();
	
		boolean isLogin = session.getAttribute("checkLogin") == null ? false : true;
		
		if(isLogin)
		{
			ServiceLogin serviceLogin = new ServiceLogin();
						
			Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
			User user = serviceLogin.getTwitterUser(twitter);
		
		
			//해당유저의 친구목록 singleton으로 저장해놓기
		
						
			request.setAttribute("isLogin", "1");
			request.setAttribute("name", user.getName());
			request.setAttribute("imageUrl", user.getProfileImageURL());	
		
			session.setAttribute("twitterUser", user);
			session.setAttribute("twitterUserId", user.getId());
		}
		
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/loginPage.jsp");
	    dispatcher.forward(request, response);
	    
	    

	}
   
}
