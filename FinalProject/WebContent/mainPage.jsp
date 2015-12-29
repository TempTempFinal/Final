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
<!DOCTYPE html>
<html>
<head>
<title>Main page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
<style type="text/css">
body {
	height : 100%;
	width: 100%;
}

.debug {
		outline: 1px solid red;
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

.img-div{
	position:relative;
	display:inline;
	width:100%;
	height: auto;
}

.img-div img{
	position: relative;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	width: 100%;
	height: 200px;
}

.title-div{
	position: relative;
	font-weight: bold;
	margin-left: 10px;
	padding: 10px 0;
	    height: 60px;
}

.location-div{
	border-top: 1px solid #bebebe;
    padding: 10px;
        height: 40px;
}

.row-fluid .span4{
 	height: 356px;
 	margin: 1px;
 	padding: 25px 10px;
 }
 
 .row-fluid{
 	margin-top: 20px;
 }
 

.carousel-caption{
	margin-left: 300px;
	margin-top:100px;
	background-color: rgba(0,0,0,0.5);
	border: 0.5px solid #bebebe;
}

.content-container{
	margin: 30px;
	padding: 50px 0px;
}
.content-container h1{
	font-family: serif;
}

.content-container p{
	font-family: sans-serif;
	font-size: medium;
}

.content-container a{
    position: relative;
    border: 1px solid white;
    margin-left: 150px;
    font-size: large;
    padding: 5px 50px;
    color: white;
    bottom: -15px;
}

#categorySection{
	 padding: 0px 30px;
}

#contentSection{
	padding: 0px 250px;
}

.post-container {
	border: 1px solid #bebebe;
	border-radius: 10px;
	height: 340px;
}

.feedback-div{
	    margin-top: 10px;
}
#pageSection{
    padding: 30px;
    margin-top: 30px;
}

.img-div img{
    border-bottom: 1px solid #bebebe;
}
 

</style>
</head>


<body>
	<nav id="mainNav"> <jsp:include page="commonPage/nav.jsp"></jsp:include> </nav>
	
	
	<div id="myCarousel" class="carousel slide">
			<div class="carousel-inner">	
				<div class="item active">
					<img src="${pageContext.request.contextPath}/img/mainPhoto2.jpg"alt="">
					<div class="container">
						<div class="carousel-caption">
							<div class="content-container">
								<h1>홍콩보다 부산, 야경의<br>메카 더베이 101</h1><br>
								<p class="lead">홍콩보다 낭만적인 야경이 부산에 있다. 80층을 넘나드는 초고층 빌딩에서 쏟아내는 불빛이 밤하늘을 수놓고 해운대 밤바다에 넘실댄다. 눈앞에 펼쳐진 야경을 바라보며 맥주 한잔 기울이기 딱 좋은 부산의 겨울밤이 반짝반짝 깊어간다.</p>
								<a>More</a>
							</div>
						</div>
					</div>
				</div>

				<div class="item">
					<img
						src="${pageContext.request.contextPath}/img/mainPhoto1.jpg"
						alt="">
					<div class="container">
						<div class="carousel-caption">
							<div class="content-container">
								<h1>홍콩보다 부산, 야경의<br>메카 더베이 101</h1>
								<p class="lead">홍콩보다 낭만적인 야경이 부산에 있다. 80층을 넘나드는 초고층 빌딩에서 쏟아내는 불빛이 밤하늘을 수놓고 해운대 밤바다에 넘실댄다. 눈앞에 펼쳐진 야경을 바라보며 맥주 한잔 기울이기 딱 좋은 부산의 겨울밤이 반짝반짝 깊어간다.</p>
							</div>
						</div>
					</div>
				</div>

				<div class="item">
					<img
						src="${pageContext.request.contextPath}/img/mainPhoto3.jpg"
						alt="">
					<div class="container">
						<div class="carousel-caption">
							<h1>Example picture3</h1>
							<p class="lead">test picture</p>
						</div>
					</div>
				</div>
				
					<div class="item">
					<img
						src="${pageContext.request.contextPath}/img/mainPhoto4.jpg"
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
			<a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
		</div>

	
		<!-- Category -->
		<section id="categorySection">
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

		<!-- category -->
	
		</section>
	
		<!-- Example row of columns -->
		<section id="contentSection">

		<div class="row-fluid" id="post_list">
			<%
			DaoTravlePlace db = new DaoTravlePlace();
			
				List<ModelFrontTravlePost> postList = db.getFrontTravlePostList(1,9); 
				for(int i=0; i<postList.size(); i++){
					ModelFrontTravlePost post = postList.get(i);			
				%>
				<A href="${pageContext.request.contextPath}/detail?travlePostNumber=<%=post.getTravelPost_no()%>">
					<div class="span4">
						<div class="post-container">
							<div class ="img-div"><img src="<%=post.getImage_url()%>">
							</div>
							<div class ="title-div"><%=post.getTitle()%>
							</div>
							<div class ="location-div"><%=post.getAddress()%>
							</div>
							<div class ="feedback-div">
								<a class="active pull-right" href="#"><%=post.getComment_count()%><i class="icon-comment"></i></a>
								<a class="active pull-right" href="#"><%=post.getLike_count()%><i class="icon-heart"></i></a>
							</div>		
						</div>
					</div>		
				</A>
				<%
				}
				 %>	
		</div>
		</section>
		
		<section id="pageSection">
		<%@include file="commonPage/paging.jsp" %>
		</section>
		<footer id="mainFooter">
			<jsp:include page="commonPage/footer.jsp"></jsp:include>
		</footer>

	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/mainPage.js"></script>
	<script src="${pageContext.request.contextPath}/js/paging.js"></script>
	
</body>
</html>
