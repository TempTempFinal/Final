package com.lecture.finalproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lecture.finalproject.repository.SNSRepository;
import com.lecture.finalproject.service.ServiceLogin;

import twitter4j.Twitter;
import twitter4j.User;

/**
 * Servlet implementation class MapSetup
 */
@WebServlet("/MapSetup")
public class MapSetup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapSetup() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		User user = (User) request.getSession().getAttribute("twitterUser");
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
				
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/mapForTopK.jsp");
	    dispatcher.forward(request, response);
	    
	}



}
