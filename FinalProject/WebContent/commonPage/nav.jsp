<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
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
	    border-top: 2px solid #1e356a;
	
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

.myNav li span{
	font-size: large;
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
				<li><a href="${pageContext.request.contextPath}/PopularControllerT">인기</a>
			</ul>
		</div>
		<div class="myPull-right ">
			<ul class="myNav">
				<li id="mySearch-icon"><a href="${pageContext.request.contextPath}/search"><span class="glyphicon glyphicon-search"></span></a></li>
				<li><a href="${pageContext.request.contextPath}/group"><span class="glyphicon glyphicon-globe"></span></a></li>
				<li><a href="${pageContext.request.contextPath}/my"><span class="glyphicon glyphicon-user"></span></a></li>
				<li><a href="${pageContext.request.contextPath}/writePage"><span class="glyphicon glyphicon-pencil"></span></a></li>
			</ul>
		</div>
	</div>
</body>
</html>