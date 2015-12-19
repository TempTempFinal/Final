package com.lecture.finalproject.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import OpinionMining.OpinionMining;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.dao.Travelpost_db;
import com.lecture.finalproject.model.ModelComment;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.PostUploaderHelper;

/**
 * Servlet implementation class ServiceCommentPage
 */
@WebServlet("/ServiceCommentPage")
public class ServiceCommentPage extends HttpServlet {
   private static final long serialVersionUID = 1L;
   private DaoTravlePlace db;
   ModelComment com = new ModelComment();
   ModelUser user = new ModelUser();
   ModelTravelPost post = new ModelTravelPost();
   ModelInformation info = new ModelInformation();
   Travelpost_db tp_db = new Travelpost_db();

   private String make(String s) {
      return "'" + s + "'";
   }

   /**
    * @see HttpServlet#HttpServlet()
    */
   public ServiceCommentPage() {
      super();
      // TODO Auto-generated constructor stub
   }

   protected void processRequest(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {

   }

   /**
    * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doGet(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      // response.getWriter().append("Served at:
      // ").append(request.getContextPath());
   }

   /**
    * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
    *      response)
    */
   protected void doPost(HttpServletRequest request, HttpServletResponse response)
         throws ServletException, IOException {
      // TODO Auto-generated method stub
      request.setCharacterEncoding("UTF-8");
      ServletContext context = getServletContext();

      String contents = request.getParameter("commentArea");

      int num;
      int commentNum = 1;
      double senti = 0; // sentiment
      double bodySenti = 0;
      PostUploaderHelper hashExtracter = new PostUploaderHelper();
      com.setContent(contents);

      OpinionMining sentiwordnet = new OpinionMining();
      sentiwordnet.create("resource/dictionary.txt");
      sentiwordnet.calculate(make(com.getContent()));
      senti = sentiwordnet.getSentiment();
      bodySenti = bodySenti * (commentNum-1) + senti / commentNum;
      
      num = tp_db.findTravelPost_no(post.getTitle());
      tp_db.insert(
            "insert into comment_tb(comment_no,commentPost_date,content, user_id,image_url,travelPost_no,sentiment) value("
                  + commentNum + "," + "now()," + make(com.getContent()) + "," + make("khyunm91") + ","
                  + make(user.getImg_url()) + "," + num + "," + senti + ")");
      tp_db.update("update comment_tb set travelSentiment='" + bodySenti + "'");

      commentNum++;

      hashExtracter.extractHashTagTest(contents, num);
      hashExtracter.extractOnePostFeature(contents, num);

      RequestDispatcher dispatcher = request.getRequestDispatcher("/detailPage.jsp");
      dispatcher.forward(request, response);
   }
}