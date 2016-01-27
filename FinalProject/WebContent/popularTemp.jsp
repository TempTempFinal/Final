<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<style type="text/css">


.category-Nav{
    height: 45px;
    background-color: #f3f4f5;
}

.navbar-default{
    min-height: 45px;
    height: 45px;
    margin-bottom: 0px;
    
}

section{
    background-color: #f3f4f5;
    min-width: 1024px;
    width: 100%;
   
}

.postContainer{
    height: auto;
}

.topPostListContainer{

}
.popularPostContainer{
    padding: 0px 10px;
    width: 100%;
}

.post{
	border: 1px solid #bebebe;
	border-radius: 10px;
	

}

.post img{
	border-radius: 10px;
	width: 318px;
    height: 213px;
  	visibility : visible;
  	display : block;
}

.postTitle{
    position: relative;
    top: -40px;
    left: 20px;
    font-size: large;
    color: white;
    font-weight: bold;
}

.topPostTitle, .popularPostTitle{

    position: relative;
    font-size: x-large;
    color: #666;
    font-weight: bold;
    top: 20px;
    padding-left: 80px;
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
	border-bottom: 1px solid #bebebe;
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

.post-container {
	border: 1px solid #bebebe;
	border-radius: 10px;
	height: 340px;
}

.feedback-div{
	    margin-top: 10px;
}


.row-fluid .span4{
 	height: 356px;
 	margin: 1px;
 	padding: 25px 10px;
 	
    
 	
 }
 
 .row-fluid{
 	margin-top: 20px;
 	padding-left: 200px;
    padding-right: 150px;
 }

#pageSection{
    padding: 30px;
    height: 100px;
}


.slide-wrap {position:relative; width:1040px; height:450px; overflow:hidden;    padding-top: 40px;
    
    margin-left: 145px;
    left: auto;
}
	ul.slide-list{ position:absolute; left;0px; list-style: none; width: 6600px; padding-left: 0px;}
		ul.slide-list li {position:relative; float:left; width:340px; height:240px;}	
		#slider{position:relative;min-width: 1024px;width: 100%; height: 300px;}
		.active li{padding-top: 12px;}
		

	a#prev2 {position:absolute; left:100px; top:135px; z-index:20; font-size: xx-large;}
	a#next2 {position:absolute; right:100px; top:135px; z-index:20; font-size: xx-large;}



</style>

</head>
<body>

<nav>
<jsp:include page="commonPage/nav.jsp"></jsp:include>
</nav>
<nav class="navbar navbar-default" style="min-height: 45px;margin-bottom: 0px;">
  <div class="container-fluid">
    <div>
      <ul class="nav navbar-nav">
      	<li class="active"><a href="#">All</a></li>
		<c:forEach items="${featureList}" var="featureItem">
			    <li><a href="#">${featureItem.feature}</a></li>
		</c:forEach>
      </ul>
    </div>
  </div>
</nav>
<section class="postContainer">
	<div class="topPostTitle"><span>Top Place</span></div>
	<div class="topPostListContainer">
			<div id="slider">
				<a href="#" id="prev2" class="prev glyphicon glyphicon-chevron-left"></a>
				<div class="slide-wrap">
					<ul id="topPost-list" class="slide-list mt15">
						<c:forEach items="${topPostList}" var="topPost">
							<li>
									<div class="popularPostContainer">
										<A href="${pageContext.request.contextPath}/detail?travlePostNumber=${topPost.travelPost_no}">
											<div class="post">
												<img src="${topPost.image_url}" >												
											</div>
											<div class="postTitle">
												<span>${topPost.title}</span>
											</div>
										</a>
									</div>
										
							</li>
						</c:forEach>
					</ul>
				</div>
				<a href="#" id="next2" class="next glyphicon glyphicon-chevron-right"></a>
			</div>
		</div>
</section>
<section class="postContainer2">
	<div class="popularPostTitle"><span>Popular Place</span></div>
	<div class="row-fluid" id="post_list">
		<c:forEach items="${popularPostList}" var="popularPost">
			<A href="${pageContext.request.contextPath}/detail?travlePostNumber=${popularPost.travelPost_no}">
				<div class="span4">
					<div class="post-container">
						<div class="img-div">
							<img src="${popularPost.image_url}">
						</div>
						<div class="title-div">${popularPost.title}
						</div>
						<div class="location-div">${popularPost.address}
						</div>
						<div class="feedback-div">
							<a class="active pull-right" href="#">${popularPost.comment_count}<i class="icon-comment"></i></a> 
							<a class="active pull-right" href="#">${popularPost.like_count}<i class="icon-heart"></i></a>
						</div>
					</div>
				</div>
			</A>		
		</c:forEach>
	</div>
</section>
<section id="pageSection">
	<%@include file="commonPage/paging.jsp" %>
</section>
<footer id="mainFooter">
	<jsp:include page="commonPage/footer.jsp"></jsp:include>
</footer>
	









<script src="http://code.jquery.com/jquery.js"></script>
<script src="${pageContext.request.contextPath}/js/popular.js"></script>
<script src="${pageContext.request.contextPath}/js/popularPaging.js"></script>



</body>
</html>