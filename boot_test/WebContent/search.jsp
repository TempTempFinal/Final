<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% String isLogin = (String)request.getAttribute("isLogin"); %>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
<title>Insert title here</title>
<style type="text/css">

	body, html{
		height : 100%;
		width: 100%;
	}
	
	.debug{
		outline: 1px solid red;
	}

	
	
	section{
		min-width: 1024px;
	}
	
	#searchSection{
		min-width : 1024px;
		width : 100%;
		height:700	px;
		background-color: #F5F5F5;
	}
	
	.search-container{
	    padding:  100px 150px 150px 150px
	}
	
	#search_input{
	display: block;
    width: 100%;
    margin: 36px 0 46px 0;
    padding: 0;
    background: transparent;
    border: 0;
    font-size: 54px;
    font-weight: bold;
    line-height: 64px;
    color: #333;
	}
	
	::-webkit-input-placeholder { color:gray; }
::-moz-placeholder { color:gray; } /* firefox 19+ */
:-ms-input-placeholder { color:gray; } /* ie */
input:-moz-placeholder { color:gray;  }

a:link{color: white; text-decoration: none;}
a:VISITED {color: white; text-decoration: none;}	
a:HOVER {color: white; text-decoration: none;}
a:ACTIVE {color: white; text-decoration: none;}

.concern-list{
margin: 20px;
padding : 30px 0;
border-top: 1px solid #b4b4b4;  
height: 150px;
}

.concern-list .row{
	padding-left: 100px;
	padding-right: 100px;
	    height: 150px;
}

.concern-list .col-3{

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
    font-size: xx-large;
    margin-top: 30px;
    position: relative;
    color: white;
}

.recommend-title{
	font-size: large;
	color: #b4b4b4
}



	

</style>	
</head>
<body onload="document.form1.searchWord.focus();">

	<nav id="mainNav">
		<jsp:include page="commonPage/nav.jsp"></jsp:include>
	</nav>
	<section id="searchSection">
		<div class="search-container">
			<form  action="${pageContext.request.contextPath}/concern" method="get" name="form1"> 
								<input type="text" name="searchWord" placeholder="검색어를 입력해 주세요" id="search_input" style="width: 600px;height: 80px;" name="searchWord"	>
								<input type="hidden" name="method" value="originalSearch" id="hiddenMethod">
			</form>
		
			 <%-- <%if(isLogin.equalsIgnoreCase("true")){%> 
			 --%>	
				<p class="recommend-title"><a href="${pageContext.request.contextPath}/MapSetup">친구들 사이 인기있는 여행지 보기</a></p> 
				<p class="recommend-title"><b>${userName}</b>님 다음은 어떠신가요?</p>
            <%--  	<div class="concern-list">
             
	       			<div class="row">
          			   <c:forEach items="${concerns}" var="concern">
             				<div class="col-3"><A href="${pageContext.request.contextPath}/concern?searchWord=${concern.interest}&method=listByConcern">${concern.interest}</A></div>
             			</c:forEach>
              		   </div>
	           	</div>
  			   <%}%> --%>
		</div>
	</section>
	<footer id="mainFooter">
       <jsp:include page="commonPage/footer.jsp"></jsp:include>
    </footer>
	<script src="http://code.jquery.com/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/js/search.js"></script>

</body>
</html>