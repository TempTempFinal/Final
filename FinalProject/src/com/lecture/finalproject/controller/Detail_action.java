package com.lecture.finalproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.ServiceGetDetailInfo;
import com.lecture.finalproject.service.ServiceInfoSynchronize;

import twitter4j.Twitter;

/**
 * Servlet implementation class GetDetailInfo_action
 */
@WebServlet("/detail")
public class Detail_action extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Detail_action() {
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
		String postNum = request.getParameter("travlePostNumber");
		ServiceGetDetailInfo collector = new ServiceGetDetailInfo();
		
		request.setAttribute("hashList",collector.getHashList(Integer.parseInt(postNum)));
		request.setAttribute("featureList", collector.getFeatureList(Integer.parseInt(postNum)));
		request.setAttribute("informationList", collector.getInformationList(Integer.parseInt(postNum)));
		request.setAttribute("imageList", collector.getImageList(Integer.parseInt(postNum)));
		request.setAttribute("location", collector.getLocation(Integer.parseInt(postNum)));
		request.setAttribute("commentList", collector.getCommentList(Integer.parseInt(postNum)));
		request.setAttribute("travlePost", collector.getTravlePost(Integer.parseInt(postNum)));
		request.setAttribute("writer", collector.getWriter(Integer.parseInt(postNum)));
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/detailPage.jsp");
		dispatcher.forward(request, response);
	}
}
