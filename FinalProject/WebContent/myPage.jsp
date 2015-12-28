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
body, html {
	width : 100%;
	height: 100%;
}


/* Custom container */
.container {
	margin: 0 auto;
	max-width: 1000px;
}

.container>hr {
	margin: 60px 0;
}

.picture {
	max-width: 64px;
	max-height: 64px;
}

.debug {
	outline: 1px solid red;
}

.concern-list {
	margin: 20px;
	padding-bottom: 30px;
	border-bottom: 1px solid #b4b4b4;
}

.concern-list .row {
	padding-left: 100px;
	padding-right: 100px;
}

.concern-list .col-3 {
	background-color: #b4b4b4;
	width: 150px;
	height: 150px;
	-webkit-border-radius: 100px;
	-moz-border-radius: 100px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
	padding-top: 60px;
}

.concern-list .col-3 a {
	font-weight: bold;
	font-size: 50px;
	margin-top: 30px;
	position: relative;
}

.row .col-3 {
	border: 1px solid gray;
	border-radius: 10px;
	height: 90px;
	width: 150px;
	margin: 20px;
	float: left;
}

.row {
	margin-top: 20px;
}

.temp{
    height: 50px;
    width: 100%;
    background-color: #ddd;
    margin-top: 20px;
}

.myContainerTwo{
	background-color: #F5F5F5;
    height: 330px;
}

.contentContainer{
    height: 330px;
}

.pagingContainer{
    height: 30px;
    padding-right: px;
}

.myProfileContainer{
    margin-top: 80px;
}
</style>
    </head>

    <body>
		<nav id="mainNav">
			<jsp:include page="commonPage/nav.jsp"></jsp:include>
		</nav>
 
        <div class="container myProfileContainer">
            <div class="row-fluid">
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
               
            </div>
        </div>
        <div class="temp">
        
        </div>
        <div class ="myContainerTwo">
        	<div class="contentContainer">
        
        	</div>
        	<div class="pagingContainer">
        		<div class="row-fluid">  
			        <section id="pageSection">
					<%@include file="commonPage/paging.jsp"%>
					</section>
        		</div>
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