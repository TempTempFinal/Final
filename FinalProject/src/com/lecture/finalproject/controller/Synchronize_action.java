package com.lecture.finalproject.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.ServiceInfoSynchronize;
import com.lecture.finalproject.service.ServiceTwitterParser;

import twitter4j.Twitter;

/**
 * Servlet implementation class SNSprocessingController
 */
@WebServlet("/synchronize_action")

public class Synchronize_action extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Synchronize_action() {
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
		ModelUser user = (ModelUser) request.getSession().getAttribute("userObject"); //user가 있는지 없는지로 회원/비회원 구분
		ServiceInfoSynchronize synchronizer = new ServiceInfoSynchronize();
		String nextUrl = null;
			
		if(user != null) //회원 로그인
			if(!(synchronizer.isSynchronize(user))){//동기화 안되이으면
				Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");	
		
			
				if(synchronizer.synchronize(twitter))
					nextUrl = "/main";
				else
					nextUrl = "/error";
			}else
				nextUrl = "/main";
		else
			nextUrl = "/main";
		
		response.sendRedirect(request.getContextPath() + nextUrl);
	    
	}

}
