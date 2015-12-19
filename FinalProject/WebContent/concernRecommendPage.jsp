<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String isLogin = (String)request.getAttribute("isLogin"); %>
<!DOCTYPE html>
<html>
  <head>
    <title>ConcernRecommend Page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet">
    <style type="text/css">
    	a:LINK{
    	}
    	a:VISITED{
    		color: blue;
    	}
    	a:HOVER{
    		text-decoration: none;
    	}
    	a:ACTIVE{
    	}
    
        body {
        width:100%;
        height:100%;
        padding-top: 20px;
        padding-bottom: 60px;
        }
        
        .container {
        margin: 0 auto;
        max-width: 1000px;
        }

        .container > hr {
        margin: 20px 0;
        }
            .picture{
        max-width:64px;
        max-height:64px;
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

        .bpicture{
        margin: 0 auto;
        width:640px;
        }

        .btnmargin{
        margin-left:150px;
        }
        
        .jumbotron {
        margin: 40px 0;
        text-align: center;
        }
                
        .jumbotron h1 {
        font-size: 100px;
        line-height: 1;
        }
        
        .img-div{
	position:relative;
	display:inline;
	width:100%;
	height: auto;
}

.img-div img{
	position: relative;
	border-radius : 10px;
	width: 100%;
	height: 200px;
}

.title-div{
	position: relative;
	display: inline;
	margin-left: 10%;
	font-weight: bold;
}

.location-div{
	margin-top: 10%;
	border-bottom : 1px solid gray;
}

.row .col-3{
 	border: 1px solid gray;
 	border-radius : 10px;
 	height: 356px;
 	width : 300px;
 	margin: 10px;
 }
 
 .row{
 	margin-top: 20px;
 	margin-left: 40px;
 }
 
 section{
	width : 100%;
 }
 
.temp{
 height: 100px;
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






 
 .debug {
		outline: 1px solid red;
}
        
        </style>
  </head>
    <body>
        <div class="container">
          	<%@include file="commonPage/nav.jsp" %>
            <br>
            <section class="temp "></section>
            
            <%if(isLogin.equalsIgnoreCase("true")){%>
             	<section class="concern-list">
	       			<div class="row">
          			   <c:forEach items="${concerns}" var="concern">
             				<div class="col-3"><A href="${pageContext.request.contextPath}/concern?searchWord=${concern.interest}">${concern.interest}</A></div>
             			</c:forEach>
              		   </div>
	           	</section>
  			 <%}%>
             
            
      
            <div class="container">
                <div class="row" id="post_list">
                    
                    	<%
				List<ModelFrontTravlePost> posts = (List<ModelFrontTravlePost>) request.getAttribute("posts");

				for (int i = 0; i < posts.size(); i++) {
					ModelFrontTravlePost post = posts.get(i);
					%>
					<div class="col-3">
								<div class ="img-div debug"><img src="<%=post.getImage_url()%>">
								</div>
								<div class ="title-div debug"><%=post.getTitle()%>
								</div>
								<div class ="location-div debug"><%=post.getAddress()%>
								</div>
								<div class ="feedback-div debug">
									<a class="active pull-right" href="#"><%=post.getComment_count()%><i class="icon-comment"></i></a>
									<a class="active pull-right" href="#"><%=post.getLike_count()%><i class="icon-heart"></i></a>
								</div>		
								
							</div>		
					<%
						}
					%>        
                </div>
            </div>
            <hr>
          	<%@include file="commonPage/paging.jsp" %>
        </div>
            <div class="container">
            <hr>
            <div class="footer">
                    <p>Social Context Awareness Based Travel Recommendation System</p>
            </div>
        </div> 
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/concernPaging.js"></script>
    </body>
</html>