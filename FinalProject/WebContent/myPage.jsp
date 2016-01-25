<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="com.lecture.finalproject.dao.DaoTravlePlace"%>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost"%>
<%@ page import="com.lecture.finalproject.model.ModelConcern"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String isLogin = (String)request.getAttribute("isLogin"); %>
<!DOCTYPE html>
<html>
<head>
<title>My page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css"
	rel="stylesheet">
<style type="text/css">
body, html {
	width: 100%;
	height: 100%;
}

.post-container {
	border: 1px solid #bebebe;
	border-radius: 10px;
	height: 340px;
}

.row-fluid .span4 {
	height: 300px;
	margin: 1px;
	padding: 25px 10px;
}

/* Custom container */
.container {
	margin: 0 auto;
	max-width: 1000px;
}

.title-div {
	position: relative;
	font-weight: bold;
	margin-left: 10px;
	padding: 10px 0;
	height: 60px;
}

#pageSection {
	padding: 30px;
	margin-top: 30px;
}

.location-div {
	border-top: 1px solid #bebebe;
	padding: 10px;
	height: 40px;
}

.post-container {
	border: 1px solid #bebebe;
	border-radius: 10px;
	height: 340px;
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

.img-div img {
	position: relative;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border-bottom: 1px solid #bebebe;
	width: 100%;
	height: 200px;
}

.row {
	margin-top: 20px;
}

.temp {
	height: 50px;
	width: 100%;
	background-color: #ddd;
	margin-top: 20px;
}

.myContainerTwo {
	background-color: #F5F5F5;
	height: 330px;
}

.contentContainer {
	height: 330px;
}

.pagingContainer {
	height: 30px;
	padding-right: px;
}

.myProfileContainer {
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
				<img src="${pageContext.request.contextPath}/img/defaulUser.png"
					class="img-polaroid" id="my_img">

			</div>
			<div class="span7 pull-left">
				<dl>
					<dt id="my_name">로그인 해주세요</dt>
				</dl>
			</div>
			<!-- modal on/off -->

		</div>
	</div>
	<div class="temp"></div>
	<div class="myContainerTwo">
		<div class="contentContainer">

			<div class="row-fluid" id="post_list">
				<%
			DaoTravlePlace db = new DaoTravlePlace();
				String ab = (String)request.getAttribute("userId");
				System.out.println(ab);
				List<ModelFrontTravlePost> postList = db.getUserTravlePostList(1,3,(String)request.getAttribute("userId")); 
				for(int i=0; i<postList.size(); i++){
					ModelFrontTravlePost post = postList.get(i);			
				%>
				<A
					href="${pageContext.request.contextPath}/detail?travlePostNumber=<%=post.getTravelPost_no()%>">
					<div class="span4">
						<div class="post-container">
							<div class="img-div">
								<img src="<%=post.getImage_url()%>">
							</div>
							<div class="title-div"><%=post.getTitle()%>
							</div>
							<div class="location-div"><%=post.getAddress()%>
							</div>
							<div class="feedback-div">
								<a class="active pull-right" href="#"><%=post.getComment_count()%><i
									class="icon-comment"></i></a> <a class="active pull-right" href="#"><%=post.getLike_count()%><i
									class="icon-heart"></i></a>
							</div>
						</div>
					</div>
				</A>
				<%
				}
				 %>
			</div>
		</div>
		<div class="pagingContainer">
			<div class="row-fluid">
				<section id="pageSection">
					<%@include file="commonPage/paging.jsp"%>
				</section>

				<section id="pageSection">
					<%@include file="commonPage/paging.jsp"%>
				</section>
				<footer id="mainFooter">
					<jsp:include page="commonPage/footer.jsp"></jsp:include>
				</footer>
			</div>
		</div>

	</div>






	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/myPage.js"></script>
	<script src="${pageContext.request.contextPath}/js/paging.js"></script>
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