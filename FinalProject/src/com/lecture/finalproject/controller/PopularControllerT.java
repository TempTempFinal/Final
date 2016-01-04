package com.lecture.finalproject.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelFrontTravlePost;
import com.lecture.finalproject.service.pagingHelper;

/**
 * Servlet implementation class PopularControllerT
 */
@WebServlet("/PopularControllerT")
public class PopularControllerT extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PopularControllerT() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DaoTravlePlace db = new DaoTravlePlace();
		pagingHelper pager = new pagingHelper();
        int totalCount = 0;
		
		List<ModelFeature> featureList = db.getFeatureGroupList();
		List<ModelFrontTravlePost> topPostList = db.getTopFrontTravelPostByAllCategory();
		List<ModelFrontTravlePost> popularPostList = db.getPopularFrontTravelPostByAllCategory(2, 9);
		totalCount = db.getTravelPostCount();
		
		pager.setPageNo(1);
        pager.setPageSize(9);	

		//맨처음 9개는 상단바에 위치하기때문에! 해당것을 빼기위해 9개를 빼준다!
        pager.setTotalCount(totalCount - 9);
		
		request.setAttribute("featureList", featureList);
		request.setAttribute("topPostList", topPostList);
		request.setAttribute("popularPostList", popularPostList);
		request.setAttribute("paging", pager);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/popularTemp.jsp");
		dispatcher.forward(request, response);
	}



}
