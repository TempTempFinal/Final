<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String isLogin = (String)request.getAttribute("isLogin"); %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet">
<title>Insert title here</title>
<style type="text/css">
body, html {
	height: 100%;
	width: 100%;
}

.debug {
	outline: 1px solid red;
}

section{
width: 100%;
	min-width: 1024px;
}

#topFriendSection {
	height: 350px;
	background-color: #F5F5F5;
}

#friendSelectSection{
	height: 50px;
	background-color: #ddd;
}

.search-container {
	padding: 50px 100px 150px 150px
}

.concern-list {
	margin: 20px;
	padding: 30px 0;
	border-top: 1px solid #b4b4b4;
	height: 150px;
}

.concern-list .row {
	height: 150px;
}

.concern-list .col-4 {
	width: 150px;
	height: 150px;
	-webkit-border-radius: 100px;
	-moz-border-radius: 100px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
	padding-top: 60px;
	background-size: cover;
}

.concern-list .col-4 a {
	font-weight: bold;
	font-size: large;
	margin-top: 30px;
	position: relative;
	color: white;
}

.recommend-title, .sub-title {
	font-size: large;
	color: #b4b4b4
}

.sub-title{
	padding-left: 150px;
}

.addFriendInfo{
	padding-left: 500px;
}

.addFriendContent{
	margin-top: 10px;
    font-size: larger;
        color: #b4b4b4;
}

.showFriendBtnContainer{
    padding-top: 8px;
}

#layerpop{
margin-left: 400;

}

#groupConcernSection{
	height: 300px;
	background-color: #F5F5F5;
}

.groupConcernContainer{
    width: 100%;
    height: 100%;
    text-align: center;
}

.readyGroupConcern{
font-size: large;
    color: #b4b4b4;
    font-weight: bold;
    padding-top: 100px;
}

.groupConcernContainer .row{
	margin-left: 0px;
	margin-right: 0px;
	margin-top: 50px;
	padding-left: 155px;
	

}

.groupConcernContainer .row .col-4{
	width: 150px;
	height: 150px;
	-webkit-border-radius: 100px;
	-moz-border-radius: 100px;
	margin-left: 50px;
	margin-right: 50px;
	text-align: center;
	padding-top: 60px;
	background-size: cover;
	    font-size: x-large;
	    color: white;
}

.rowInfo{
	font-size: large;
    color: #b4b4b4;
        padding-top: 30px;
    padding-left: 150px;
}

a:link{color: white; text-decoration: none;}
a:VISITED {color: white; text-decoration: none;}	
a:HOVER {color: white; text-decoration: none;}
a:ACTIVE {color: white; text-decoration: none;}

</style>

</head>
<body>

<nav id="mainNav">
		<jsp:include page="commonPage/nav.jsp"></jsp:include>
</nav>
<section id="topFriendSection" class="">
	<div class="search-container">	
			<%if(isLogin.equalsIgnoreCase("true")){%>
				<p class="recommend-title"><b>${userName}</b>님과 최근에 가장 많은 교류를한 친구들입니다.</p>
				<p class="sub-title">친구분들과 함께 여행을 떠나보시는건 어떨까요?</p>
             	<div class="concern-list">
             
	       			<div class="row">
          			   <c:forEach items="${topFriendList}" var="friend">
             				<div class="col-4" style="background-image: url('${friend.img_url}')"><A>${friend.name}</A></div>
             			</c:forEach>
              		   </div>
	           	</div>
  			 <%}%>
		</div>
</section>
<section id="friendSelectSection">
	<div class="span10 addFriendInfo">
		<p class="addFriendContent">오른쪽 버튼을 눌러 함께 여행할 친구를 추가해 보세요!</p>
	</div>
	
	<div class="span2 pull-right showFriendBtnContainer">
	<button class="btn btn-default" id="showFriendBtn"><i class="icon-user"></i> 친구추가</button><br/>
	    <!--  <a href="#myModal" role="button" class="btn" data-toggle="modal" id="showFriendBtn"><i class="icon-user"></i> Add friends</a>-->
	</div>
</section>
<section id="groupConcernSection">
	<div class="groupConcernContainer">
		<p class="readyGroupConcern">친구들과의 공통 관심사를 찾아 여행을 떠나봐요!</p>
	</div>
</section>

 <footer id="mainFooter">
       <jsp:include page="commonPage/footer.jsp"></jsp:include>
    </footer>


	<div class="modal fade" id="layerpop">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h3 id="myModalLabel">Add friends</h3>
				</div>
				<div class="modal-body">
					<p>friends list</p>
					<div id="friendListMenu"></div>
				</div>
				<div class="modal-footer">
					<button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
					<button class="btn btn-primary" id="sendFriendBtn">Save</button>
				</div>
			</div>
		</div>
	</div>


	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/group.js"></script>


</body>
</html>