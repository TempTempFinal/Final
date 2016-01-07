package com.lecture.finalproject.controller;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.ArrayList;
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
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFrontTravlePost;
import com.lecture.finalproject.repository.SearchHelper;
import com.lecture.finalproject.service.pagingHelper;

import Komoran.Preprocessor;

/**
 * Servlet implementation class MainController
 */

@WebServlet("/concern")
public class ConcernRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static SearchHelper searchHelper = null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		searchHelper = SearchHelper.getInstance();
	}
    public ConcernRecommendController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("UTF-8"); 
		HttpSession session = request.getSession();
		
		String checkHash;
		DaoTravlePlace db = new DaoTravlePlace();
		List<ModelFrontTravlePost> posts = null;
		List<ModelConcern> concerns = null;
		List<String> searchKeyword = new ArrayList<String>();
		
		
		pagingHelper pager = new pagingHelper();
		int totalCount = 0;
		
		String searchWord = new String(request.getParameter("searchWord") .getBytes("8859_1"), "UTF-8"); 
		String searchMethod = request.getParameter("method");
		
		//일반검색인경우 (일반검색, hashTag이용)
		if(searchMethod.equals("listBySeachWord")){
			posts = db.getFrontTravlePostBySearchWord(searchWord,1,9);
			totalCount = db.getCountTravlePostBySearchWord(searchWord);
			
		}
		else if(searchMethod.equals("listByHashTag")){
			searchWord = searchWord.substring(1); //delete #
			 posts = db.getFrontTravlePostByHashTag(searchWord,1,9);		
			 totalCount = db.getCountTravlePostByHashTagWrod(searchWord)	; //해당 키우드로 검색했을떄의 총갯수
			
		}
		//관심사 검색인경우
		else if(searchMethod.equals("listByConcern")){
			posts = db.getFrontTravelPostByConcern(searchWord,1,9);		
			totalCount = db.getCountTravlePostByConcern(searchWord)	;	
		}
		
		
		
		
		
	
		//hash Tag 검색인경우
	
			
		    pager.setPageNo(1);
			pager.setPageSize(9);	
			pager.setTotalCount(totalCount);
		
		request.setAttribute("posts", posts);
		request.setAttribute("paging", pager);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/concernRecommendPage.jsp");
   	    dispatcher.forward(request, response);
	}
    
 
 
}
