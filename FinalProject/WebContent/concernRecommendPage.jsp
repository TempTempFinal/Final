<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost" %>


<!DOCTYPE html>
<html>
  <head>
    <title>ConcernRecommend Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
  	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet">
    <style type="text/css">
.debug {
	outline: 1px solid red;
}

body, html {
	width: 100%;
	height: 100%;
}

#mainNav {
	min-width: 1024px;
	width: 100%;
	height: 70px;;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 1030;
	background-color: white;
	border-top: 2px solid #1e356a;
}

#contentSection {
	padding: 0px 250px;
	margin-top: 100px;
}

.row-fluid .span4 {
	height: 356px;
	margin: 1px;
	padding: 25px 10px;
}

.row-fluid {
	margin-top: 20px;
}

.post-container {
	border: 1px solid #bebebe;
	border-radius: 10px;
	height: 340px;
}

.img-div {
	position: relative;
	display: inline;
	width: 100%;
	height: auto;
}

.img-div img {
	position: relative;
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	width: 100%;
	height: 200px;
}

.title-div {
	position: relative;
	font-weight: bold;
	margin-left: 10px;
	padding: 10px 0;
	height: 60px;
}

.location-div {
	border-top: 1px solid #bebebe;
	padding: 10px;
	height: 40px;
}

.feedback-div{
	    margin-top: 10px;
}

#pageSection {
	padding: 30px;
	margin-top: 30px;
}

#mainFooter {
	min-width: 1024px;
	width: 100%;
	height: auto;
	background-color: #505050;
	margin-top: 60px;
	height: 150px;
}
</style>
  </head>
    <body>
    <nav id="mainNav">
    	<jsp:include page="commonPage/nav.jsp"></jsp:include>  
    </nav>
    <section id="contentSection">
     <div class="row-fluid" id="post_list">
             
                    	<%
				List<ModelFrontTravlePost> posts = (List<ModelFrontTravlePost>) request.getAttribute("posts");

				for (int i = 0; i < posts.size(); i++) {
					ModelFrontTravlePost post = posts.get(i);
					%>
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
					<%
						}
					%>        
                </div>
    </section>
	<section id="pageSection">
		<%@include file="commonPage/paging.jsp"%>
	</section>
	<footer id="mainFooter">
		<jsp:include page="commonPage/footer.jsp"></jsp:include>
	</footer>


    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/concernPaging.js"></script>
    </body>
</html>