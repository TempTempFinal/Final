<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.lecture.finalproject.dao.JDBCMannager"%>
<%@ page import="com.lecture.finalproject.dao.DaoTravlePlace"%>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost"%>
<%@ page import="com.lecture.finalproject.model.ModelCommentList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Detail Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
<style type="text/css">
body {
   width: 100%;
   height: 100%;
   padding-top: 20px;
   padding-bottom: 60px;
}

.container {
   margin: 0 auto;
   max-width: 1000px;
}

.container>hr {
   margin: 20px 0;
}

.picture {
   max-width: 64px;
   max-height: 64px;
}

.navbar .navbar-inner {
   padding: 0;
}

.navbar .nav {
   margin: 0;
   display: table;
   width: 100%;
}

.navbar .nav li {
   display: table-cell;
   width: 1%;
   float: none;
}

.navbar .nav li a {
   font-weight: bold;
   text-align: center;
   border-left: 1px solid rgba(255, 255, 255, .75);
   border-right: 1px solid rgba(0, 0, 0, .1);
}

.navbar .nav li:first-child a {
   border-left: 0;
   border-radius: 3px 0 0 3px;
}

.navbar .nav li:last-child a {
   border-right: 0;
   border-radius: 0 3px 3px 0;
}

.bpicture {
   margin: 0 auto;
   width: 640px;
}

.btnmargin {
   margin-left: 150px;
}

.jumbotron {
   margin: 40px 0;
   text-align: center;
}

.jumbotron h1 {
   font-size: 100px;
   line-height: 1;
}
</style>
</head>

<body>
   <jsp:useBean id="model"
      class="com.lecture.finalproject.model.ModelComment" />
   <jsp:setProperty property="*" name="model" />

   <div class="container">
      <%@include file="commonPage/nav.jsp"%>
      <br>
      <div class="raw-fluid">
         <div class="jumbotron">
            <h1>${travlePost.title}</h1>
         </div>
      </div>

      <div class="container">
         <div class="bpicture">
            <c:forEach items="${imageList}" var="imgEntry">
               <br>
               <img src="${imgEntry.image_url}" />
               <br>
            </c:forEach>
         </div>
         <div class="bpicture">

            <c:forEach items="${informationList}" var="informationEntry">
               <br>${informationEntry.travel_content}<br>
               <Br>
            </c:forEach>

            <br> <br> <br>
         </div>
      </div>

      <div class="container">
         <div class="raw-fluid">
            <div class="btnmargin span3">
               <button class="btn" type="button">
                  <i class="icon-heart"></i> Like
               </button>
            </div>
            <div class="span2 pull-right">
               <dl>
                  <dt>${wrtier.user_id}</dt>
                  <dd>
                     <img src="${writer.img_url}" />
                  </dd>
               </dl>
            </div>
            <div class="span1 pull-right">
               <img src="img/ibdxkw9g3wkjklk5pftx.jpg" class="img-polaroid">
               <br> <br>
            </div>
         </div>
      </div>

      <form action="ServiceCommentPage" method="post"
         enctype="multipart/form-data">
         <div class="container">
            <div class="raw-fluid" id="post_list">
               <div class="span9">
                  <textarea class="form-control" rows="3" name="commentArea"></textarea>
               </div>
               <div class="span2 pull-right">
                  <button type="submit" class="btn">Send</button>
               </div>
            </div>
         </div>
         
      </form>

      <hr>
      <div class="container">
         <div class="raw-fluid" id="comment_list">
         <div class="span12 pull-left">
               Friend Articles<br> <br>
            </div>
            <%
         DaoTravlePlace db_friend = new DaoTravlePlace();
         List<ModelCommentList> commentFriendList = db_friend.getModelCommentList(1);
         for(int i=0; i<commentFriendList.size(); i++){
            ModelCommentList fcoms = commentFriendList.get(i);
         %>

            
            <div class="cpicture span1 pull-left">
               <img src="img/x8ahhkk1af66fvc9nniw.jpg" class="img-polaroid">
            </div>
            <div class="span8 pull-left">
               <dl>
                  <dt>Friend ID</dt>
                  <dd>Friend Comment</dd>
               </dl>
            </div>
            <%
            }
             %>
         </div>
         <br> <br>
      </div>
      <%@include file="commonPage/paging.jsp"%>
      <hr>
      <div class="container">
         <div class="raw-fluid" id="comment_list">
         <div class="span12 pull-left">
               Comment<br> <br>
            </div>
         <%
         DaoTravlePlace db = new DaoTravlePlace();
         List<ModelCommentList> commentList = db.getModelCommentList(1);
         for(int i=0; i<commentList.size(); i++){
            ModelCommentList coms = commentList.get(i);
         %>
            
            <div class="cpicture span1 pull-left">
               <img src="<%=coms.getImage_url()%>" class="img-polaroid">
            </div>
            <div class="span8 pull-left">
               <dl>
                  <dt><%=coms.getUser_id()%></dt>
                  <dd><%=coms.getContent()%></dd>
               </dl>
            </div>
            <%
            }
             %>
         </div>
      </div>
      <%@include file="commonPage/paging.jsp"%>
      <hr>

   </div>
   <!-- modal -->
   <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
      aria-labelledby="myModalLabel" aria-hidden="true">
      <div class="modal-header">
         <h3 id="myModalLabel">Add frieds</h3>
      </div>
      <div class="modal-body">
         <p>friends list</p>
      </div>
      <div class="modal-footer">
         <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
         <button class="btn btn-primary">Save</button>
      </div>
   </div>
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/mainPage.js"></script>
   <script src="${pageContext.request.contextPath}/js/paging.js"></script>
</body>
</html>