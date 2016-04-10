package com.lecture.finalproject.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelCheckIn;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.repository.SNSRepository;
import com.lecture.finalproject.service.InfoExtractor;
import com.lecture.finalproject.service.ServiceLogin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import twitter4j.Twitter;
import twitter4j.User;



/**
 * Servlet implementation class ProcessServlet
 */
@WebServlet("/ProcessServlet")
public class MapProcessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MapProcessServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		User user = null;
		Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
		ServiceLogin loger = new ServiceLogin();
		user = loger.getTwitterUser(twitter);
	
		InfoExtractor infoExtractor = new InfoExtractor();
		SNSRepository snsRepository = SNSRepository.getInstance();
		
		ModelUser modelUser = (ModelUser)request.getSession().getAttribute("userObject");
	  	int placeNum = Integer.parseInt(request.getParameter("placeNum"));
	  	int radius = Integer.parseInt(request.getParameter("radius")); //km
	  	double longitude = Double.parseDouble(request.getParameter("longitude"));
	  	double latitude = Double.parseDouble(request.getParameter("latitude"));
	
	  	System.out.println("userClick longitude : " + longitude); 	
	  	System.out.println("userClick latitude : " + latitude); 	
	  	
	  	List<ModelCheckIn> rangeInnerPlace = infoExtractor.getRangeInnerPlace(snsRepository,latitude, longitude, radius);  
	  	rangeInnerPlace = infoExtractor.getFriendVisitPlaceInRange(rangeInnerPlace, modelUser);
	  	
	  	//범위 안에있는것들 다가져옴 --> 그뒤 몇명 방문했는지로 sort해야한
		
	  	Collections.sort(rangeInnerPlace);
		
	  	List<ModelCheckIn> mostVisitedPlace = infoExtractor.getMostVisitedPlace(rangeInnerPlace, placeNum);
	  	
	  	DaoTravlePlace db = new DaoTravlePlace();
	  	
	  	mostVisitedPlace = db.updateTopkPlaceInfo(mostVisitedPlace);
	  	
	  	//db에 접근해서 해당정보를 가져온다
	   	
		JSONArray jsonArray = JSONArray.fromObject(mostVisitedPlace);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("places", jsonArray);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		PrintWriter out = response.getWriter();
		System.out.println(jsonObject);
		out.println(jsonObject);	
		
		
	}

}
