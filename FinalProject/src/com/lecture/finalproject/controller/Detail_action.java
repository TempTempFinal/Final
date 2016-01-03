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
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelTwitterWiget;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.FriendsInfoHelper;
import com.lecture.finalproject.service.ServiceGetDetailInfo;
import com.lecture.finalproject.service.ServiceInfoSynchronize;

import twitter4j.Twitter;
import twitter4j.User;

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
		DaoTravlePlace db = new DaoTravlePlace();
		String postNum = request.getParameter("travlePostNumber");
		HttpSession session = request.getSession();
		
		
		ModelUser user = db.getWriterInfo(Integer.parseInt(postNum));
		ModelTravelPost post = db.getTravelPostOne(Integer.parseInt(postNum));
		ModelInformation information = db.getInformation(Integer.parseInt(postNum));
		db.updateViewCount(Integer.parseInt(postNum));
		
		System.out.println(information);
		request.setAttribute("writer", user);
		request.setAttribute("post", post);
		request.setAttribute("information", information);
		
		boolean isLogin = session.getAttribute("checkLogin") == null ? false : true;
		request.setAttribute("isLogin", "false");
		
		if(isLogin){
			User twitterUser = null;
			Twitter twitter = null;
			
			twitterUser = (User)session.getAttribute("twitterUser");
			twitter = (Twitter)session.getAttribute("twitter");
			
			FriendsInfoHelper friendHelper = new FriendsInfoHelper(twitter,twitterUser);
			List<ModelTwitterWiget> wigets = friendHelper.getFriendWiget(post.getTitle());
			
			int likeState = db.getLikeState(Integer.parseInt(postNum), Long.toString(twitterUser.getId()));
			
			request.setAttribute("likeState", likeState);
			request.setAttribute("userID", twitterUser.getId());
			request.setAttribute("wigets", wigets);
			request.setAttribute("isLogin", "true");
		}
		
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
