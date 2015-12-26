<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

	<link href="${pageContext.request.contextPath}/font/loginFont.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
    
<style type="text/css">

a:link{color: #737373;}
a:VISITED {color: #737373;}	
a:HOVER {color: #737373; text-decoration: none;}
a:ACTIVE {color: #737373;}
		
body{
	margin: 0px;
}
.debug {
	outline: 1px solid red;
	
}

.myContainer{
	width: 100%;
	height: 69px;
	border-bottom: 1px solid #ccc;
	
}

.myPull-right {
	float: right !important;
	display: inline;
	margin: 20px 30px 0 0;
}

.myPull-left{
	float: left !important;
	display: inline;
	margin: 20px 0 0 30px;
}

.myNav li{
	display: inline;
	padding: 0 10px;
}

#mySearch-icon{
	margin-right: 40px;
	border-right: 1px solid gray;
}

#myLogo{
	    font-family: "Kaushan Script", "Helvetica Neue", Helvetica, Arial, cursive;
	    font-weight: bold;
	    font-size: x-large;  
	    color: 	#1e356a;
}



		
		

</style>

</head>

<body>
	<div class="myContainer">
		<div class="myPull-left ">
			<ul class="myNav">
				<li id="myLogo" style="margin-right: 20px;">The Real travel</li>
				<li><a href="${pageContext.request.contextPath}/main">홈</a>
				<li><a href="#">인기</a>
			</ul>
		</div>
		<div class="myPull-right ">
			<ul class="myNav">
				<li id="mySearch-icon"><a href="${pageContext.request.contextPath}/search"><i class="icon-search"></i></a></li>
				<li><a href="${pageContext.request.contextPath}/my"><i class="icon-user"></i></a></li>
				<li><a href="${pageContext.request.contextPath}/writePage.jsp"><i class="icon-pencil"></i></a></li>
			</ul>
		</div>
	</div>
</body>
</html>