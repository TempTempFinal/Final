package com.lecture.finalproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelConcern;

import twitter4j.User;

/**
 * Servlet implementation class SearchController
 */
@WebServlet("/search")
public class SearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		DaoTravlePlace db = new DaoTravlePlace();
		List<ModelConcern> concerns = null;
		
		boolean isLogin = session.getAttribute("checkLogin") == null ? false : true;
		request.setAttribute("isLogin", "false");
	
		if(isLogin){
			User user = null;
			
			String userId = ((Long)session.getAttribute("twitterUserId")).toString();
			
			user = (User)session.getAttribute("twitterUser");
			concerns = db.getConcernList(userId);
			
			request.setAttribute("userName", user.getName());
			request.setAttribute("concerns", concerns);
			request.setAttribute("isLogin", "true");
			
		}
		
		
	
		 RequestDispatcher dispatcher = request.getRequestDispatcher("/search.jsp");
		 dispatcher.forward(request, response);
	}



}
