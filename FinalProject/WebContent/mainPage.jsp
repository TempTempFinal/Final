<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>

<%@ page import="com.lecture.finalproject.dao.DaoTravlePlace" %>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost" %>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.*"%>
<%	boolean islogin = (String)request.getAttribute("isLogin") == null? false : true; %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Main page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
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

.debug {
		outline: 1px solid red;
}

.container>hr {
	margin: 60px 0;
}

/* Main marketing message and sign up button */
/* Carousel */
.carousel {
	margin-bottom: 60px;
}

.carousel .container {
	position: relative;
	z-index: 9;
}

.carousel-control {
	height: 80px;
	margin-top: 0;
	font-size: 120px;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
	background-color: transparent;
	border: 0;
	z-index: 10;
}

.carousel .item {
	height: 500px;
}

.carousel img {
	position: absolute;
	top: 0;
	left: 0;
	min-width: 100%;
	height: 500px;
}

.carousel-caption {
	background-color: transparent;
	position: static;
	max-width: 550px;
	padding: 0 20px;
	margin-top: 200px;
}

.carousel-caption h1, .carousel-caption .lead {
	margin: 0;
	line-height: 1.25;
	color: #fff;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .4);
}

.carousel-caption .btn {
	margin-top: 10px;
}
/* Carousel End */
.jumbotron .lead {
	font-size: 24px;
	line-height: 1.25;
}

.jumbotron .btn {
	font-size: 21px;
	padding: 14px 24px;
}
/* Supporting marketing content */
.marketing {
	margin: 60px 0;
}

.marketing p+h4 {
	margin-top: 28px;
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

.row-fluid .span4{
 	border: 1px solid gray;
 	border-radius : 10px;
 	height: 356px;
 	margin: 1px;
 }
 
 .row-fluid{
 	margin-top: 20px;
 }
 
 

</style>
</head>


<body>
	<div class="container">
		<%@include file="commonPage/nav.jsp" %>
			<!-- /.navbar -->
			<!-- /Carousel area -->
		</div>
		<div id="myCarousel" class="carousel slide">
			<div class="carousel-inner">

				<div class="item active">
					<img
						src="${pageContext.request.contextPath}/img/temp.jpg"alt="">
					<div class="container">
						<div class="carousel-caption">
							<h1>test Picture1</h1>
							<p class="lead">test picture</p>
						</div>
					</div>
				</div>

				<div class="item">
					<img
						src="http://postfiles12.naver.net/20141224_75/kyg91kr_1419407677824VXD1f_JPEG/20141223-IMG_0106.jpg?type=w1"
						alt="">
					<div class="container">
						<div class="carousel-caption">
							<h1>Example picture2</h1>
							<p class="lead">test picture</p>
						</div>
					</div>
				</div>

				<div class="item">
					<img
						src="http://postfiles7.naver.net/20150106_262/kyg91kr_1420552730089rgoFA_JPEG/20150101-IMG_0082.jpg?type=w1"
						alt="">
					<div class="container">
						<div class="carousel-caption">
							<h1>Example picture3</h1>
							<p class="lead">test picture</p>
						</div>
					</div>
				</div>
			</div>
			<a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
			<a class="right carousel-control" href="#myCarousel"
				data-slide="next">&rsaquo;</a>
		</div>

		<!-- Category -->
		<div class="accordion" id="accordion2">
			<div class="accordion-group">
				<div class="accordion-heading">
					<a class="accordion-toggle" data-toggle="collapse"
						data-parent="#accordion2" href="#collapseOne"> Locaton
						Category </a>
				</div>
				<div id="collapseOne" class="accordion-body collapse">
					<div class="accordion-inner raw-fluid">
						<div class="span2">
							<input type="checkbox" name=location value="서울"> 
							Seoul
						</div>
						<div class="span2">
							<input type="checkbox" name=location value="경기도">
							Kyonggi
						</div>
						<div class="span2">
							<input type="checkbox" name=location value="충청">
							Chungcheong
						</div>
						<div class="span2">
							<input type="checkbox" name=location value="경상">
							Gyeongsang
						</div>
						<div class="span2">
							<input type="checkbox" name=location value="강원">
							Gangwon
						</div>
						<div class="span2">
							<input type="checkbox" name=location value="전라">
							Jeolla
						</div>
						<div class="span2">
							<input type="checkbox" name=location value="제주">
							Jeju
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- category -->

		<div class="row-fluid">
			<div class="span12">
				<div class="btn-group pull-right" data-toggle="buttons-radio">
					<button type="button" class="btn">New</button>
					<button type="button" class="btn">Like</button>
					<button type="button" class="btn">Comment</button>
					<button type="button" class="btn">location</button>
					
				</div>
			</div>
		</div>
	
		<!-- Example row of columns -->
		<div class="row-fluid" id="post_list">
			<%
			DaoTravlePlace db = new DaoTravlePlace();
			
				List<ModelFrontTravlePost> postList = db.getFrontTravlePostList(1,9); 
				for(int i=0; i<postList.size(); i++){
					ModelFrontTravlePost post = postList.get(i);
				%>
				
					<div class="span4">
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
		
		<%@include file="commonPage/paging.jsp" %>


	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/mainPage.js"></script>
	<script src="${pageContext.request.contextPath}/js/paging.js"></script>
	
</body>
</html>
