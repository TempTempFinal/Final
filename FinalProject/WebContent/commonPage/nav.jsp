<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div class="masthead">
		<div class="navbar">
			<div class="navbar-inner navbar-fixed-top">
				<div class="container">
					<ul class="nav">
						<li><a href="#"><i class="icon-search"></i>검색</a>
							<form action="${pageContext.request.contextPath}/concern" method="get">
								<input type="text" name="searchWord">
								<input type="submit" value="search">
							</form>
						</li>
						<li><a
							href="${pageContext.request.contextPath}/main">Real Travel</a></li>
						<li><a href="${pageContext.request.contextPath}/my"><i class="icon-user"></i>나의 페이지</a></li>
						<li><a
							href="${pageContext.request.contextPath}/writePage.jsp"><i
								class="icon-pencil"></i>글 작성</a></li>
					</ul>
				</div>
			</div>
		</div>

</body>
</html>