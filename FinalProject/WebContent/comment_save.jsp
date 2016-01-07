<%@ page language="java" contentType="text/html; charset=utf-8"
   import="java.sql.*" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="com.lecture.finalproject.dao.DaoTravlePlace"%>
<%@ page import="com.lecture.finalproject.dao.Travelpost_db"%>
<%@ page import="com.lecture.finalproject.model.ModelComment"%>
<%@ page import="com.lecture.finalproject.model.ModelInformation"%>
<%@ page import="com.lecture.finalproject.model.ModelTravelPost"%>
<%@ page import="com.lecture.finalproject.model.ModelUser"%>
<%@ page import="com.lecture.finalproject.service.PostUploaderHelper"%>
<%@ page import="com.lecture.finalproject.dao.JDBCMannager"%>
<%@ page import="java.sql.Timestamp"%>

<%
request.setCharacterEncoding("utf-8");
    %>
<jsp:useBean id="commentList"
   class="com.lecture.finalproject.model.ModelComment">
   <jsp:setProperty name="commentList" property="*" />
</jsp:useBean>
<%
    commentList.setCommentPost_date(new Timestamp(System.currentTimeMillis()));
    DaoTravlePlace comdb = new DaoTravlePlace();
    comdb.insertCommentList(commentList);
    response.sendRedirect("detailPage.jsp");

    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>