package com.lecture.finalproject.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.ServiceInfoSynchronize;
import com.lecture.finalproject.service.pagingHelper;

import twitter4j.Twitter;
import twitter4j.User;

/**
 * Servlet implementation class MainController
 */

@WebServlet("/main")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
	private static Map commandHandlerMap = new HashMap();
	
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
	}
	
	
	
    public MainController() {
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
		DaoTravlePlace db = new DaoTravlePlace();
		List<ModelImage> topPost = db.getPopularLocationImage(5);
		int totalCount = db.getTravelPostCount();
		
		pagingHelper pager = new pagingHelper();
        pager.setPageNo(1);
        pager.setPageSize(9);	
        pager.setTotalCount(totalCount);
        
   
		request.setAttribute("topPost", topPost);
		request.setAttribute("paging", pager);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/mainPage.jsp");
	    dispatcher.forward(request, response);
	}

}
