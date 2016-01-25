package com.lecture.finalproject.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.dao.Travelpost_db;
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.S3Upload;
import com.lecture.finalproject.service.PostUploaderHelper;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import JTCL.JTCLHelper;
import Komoran.Preprocessor;

import com.amazonaws.AmazonClientException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
/**
 * Servlet implementation class ServiceWirtePage
 */
@WebServlet("/ServiceWritePage")
public class ServiceWritePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoTravlePlace db;
	ModelImage img = new ModelImage();
	ModelInformation info = new ModelInformation();
	ModelTravelPost post = new ModelTravelPost();
	Travelpost_db tp_db = new Travelpost_db();
	ModelLocation loca = new ModelLocation();
	ModelUser user = new ModelUser();

	private String make(String s)
	{
		return "'"+s+"'"; 
	}   
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServiceWritePage() {
		super();

	}

	// TODO Auto-generated constructor stub


	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		request.setCharacterEncoding("UTF-8");
		ServletContext context = getServletContext();


		S3Upload s3 = new S3Upload();
		String path = context.getRealPath("/");
		String encType =  "UTF-8";
		int sizeLimit = 20*1024*1024;

		MultipartRequest multi = new MultipartRequest(request, path, sizeLimit, encType, new DefaultFileRenamePolicy());

		String title = multi.getParameter("istitle");
		String contents = multi.getParameter("istext");
		String uploadFileName = multi.getFilesystemName("file");
		File file = multi.getFile("file");
		String lat = multi.getParameter("latreceiver");
		String lng = multi.getParameter("lngreceiver");
		String address = multi.getParameter("receiver");
		String user_id = multi.getParameter("userid_Num");
		String city1=null;
		
		String city2=null;
		int num;
		if(address !=null)
		{
			String[] result = address.split(" ");
			if(result.length>=3)
			{
				if(result[0].equalsIgnoreCase("대한민국"))
				{
					city1=result[1];
					city2=result[2];
				}
				else
				{
					city1=result[0];
					city2=result[1];
				}
			}
		}

		PostUploaderHelper hashExtracter = new PostUploaderHelper();

		
		info.setTravel_content(contents);
		post.setTitle(title);
		loca.setAddress(address);
		loca.setLatitude(lat);
		loca.setLongitude(lng);
		loca.setCity1(city1);
		loca.setCity2(city2);
		user.setUser_id(user_id);
		//TODO 나중에 수정해야할 부분, title중복시 에러 , 
		
		System.out.println(user.getUser_id());
		//System.out.println(loca.getAddress());
		tp_db.insert("insert into travelpost_tb(title,travelPost_date,user_id) values("+make(post.getTitle())+","+"now(),"+make(user.getUser_id())+")");
		num = tp_db.findTravelPost_no(post.getTitle());
		tp_db.insert("insert into information_tb(travel_content,travelpost_no) values("+make(info.getTravel_content())+","+num+")");



		
	
		tp_db.insert("insert into location_tb(city1,city2,address,latitude,longitude,travelpost_no) values("+make(loca.getCity1())+","+make(loca.getCity2())+"," +make(loca.getAddress())+","+make(loca.getLatitude())+","+make(loca.getLongitude())+","+num+")");


		//해당 글의 Hash Tag와 특징 정보를 추출하여 해당 TravlePost_date 번호의 DB에 저장.

		hashExtracter.extractHashTagTest(contents, num);
		hashExtracter.extractOnePostFeature(contents, num);


		if(file != null){					
			s3.fileUpload("team2tr", file);
			img.setImage_url(s3.getFileURL("team2tr", uploadFileName));		
			tp_db.insert("insert into image_tb(image_url,travelpost_no) values("+make(img.getImage_url())+","+num+")");		
		}else{
			tp_db.insert("insert into image_tb values("+make("null") + num + "')");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/writePage.jsp");
		dispatcher.forward(request, response);
	}
	



}
