<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost" %>
<%@ page import="com.lecture.finalproject.model.ModelConcern" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String isLogin = (String)request.getAttribute("isLogin"); %>
<!DOCTYPE html>
<html>
    <head>
        <title>My page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet">
        <style type="text/css">

            body {
            padding-top: 20px;
            padding-bottom: 60px;
            }

            /* Custom container */
            .container {
            margin: 0 auto;
            max-width: 1000px;
            }

            .container > hr {
            margin: 60px 0;
            }

            .picture{
            max-width:64px;
            max-height:64px;
            }
             /* Customize the navbar links to be fill the entire space of the .navbar */
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
            border-left: 1px solid rgba(255,255,255,.75);
            border-right: 1px solid rgba(0,0,0,.1);
            }

            .navbar .nav li:first-child a {
            border-left: 0;
            border-radius: 3px 0 0 3px;
            }

            .navbar .nav li:last-child a {
            border-right: 0;
            border-radius: 0 3px 3px 0;
            }
            
            .debug{
            outline: 1px solid red;
            }
            
            
.concern-list{
margin: 20px;
padding-bottom : 30px;
border-bottom: 1px solid #b4b4b4;
}

.concern-list .row{
	padding-left: 100px;
	padding-right: 100px;
	
}

.concern-list .col-3{

	background-color: #b4b4b4;
	width: 150px;
	height: 150px;
	-webkit-border-radius:100px;
	-moz-border-radius:100px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
	padding-top: 60px;
}

.concern-list .col-3 a{
    font-weight: bold;
    font-size: 50px;
    margin-top: 30px;
    position: relative;
}

.row .col-3{
 	border: 1px solid gray;
 	border-radius : 10px;
 	height: 90px;
 	width : 150px;
 	margin: 20px;
 	float :left;
 }
 
 .row{
 	margin-top: 20px;
 }
            
        </style>
    </head>

    <body>
 
        <div class="container" style="padding-top: 100px">
        
        <%@include file="commonPage/nav.jsp" %>
            <br>
            <div class="raw-fluid">
                <!-- profile -->
                <div class="span1 pull-left">
                    <img src="${pageContext.request.contextPath}/img/defaulUser.png" class="img-polaroid" id="my_img">

                </div>
                <div class="span7 pull-left">
                    <dl>
                        <dt id="my_name">로그인 해주세요  </dt>
                    </dl>
                </div>
                <!-- modal on/off -->
                <div class="span2 pull-right"><br>
                    <a href="#myModal" role="button" class="btn" data-toggle="modal" id="showFriendBtn"><i class="icon-user"></i> Add friends</a>
                </div>
            
            </div>
            <hr>
            
            <!-- category -->
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne">
                        My Category</a>

					<%
						if(isLogin.equalsIgnoreCase("true")) {
					%>
					<section class="concern-list">	
						<div class="row">
						<div class="col-3" id="groupConcern0"><A href=""></A></div>
						<div class="col-3" id="groupConcern1"><A href=""></A></div>
						<div class="col-3" id="groupConcern2"><A href=""></A></div>
						<div class="col-3" id="groupConcern3"><A href=""></A></div>
						</div>
					</section>
					<%
						}
					%>
				</div>
                </div>
            </div>
            
           <!--TODO 내가 올린 글 -->
            </div>
         <%@include file="commonPage/paging.jsp" %>
            <br>
            <hr>
            
        </div>
        <!-- modal -->
        <div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-header">
                <h3 id="myModalLabel">Add friends</h3>
            </div>
            <div class="modal-body" >
                <p>friends list</p>
                	<div id="friendListMenu"></div>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary" id="sendFriendBtn">Save</button>
            </div>
        </div>
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/myPage.js"></script>
   
    <script type="text/javascript">
	 
			var user_img = '<%=(String)request.getAttribute("userImage")%>';
			var userName = '<%=(String)request.getAttribute("userName")%>';
			
		if(user_img !== "" && userName !== ""){
			$("#my_img").attr("src",user_img);
			$("#my_name").empty().text(userName);
		}
		
    </script>
    </body>
</html>