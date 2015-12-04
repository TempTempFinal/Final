<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<div class="pagination pull-right">
<ul>
    <li><a href="javascript:goPage(${paging.firstPageNo})" class="first">처음 페이지</a></li>
    <li><a href="javascript:goPage(${paging.prevPageNo})" class="prev">이전 페이지</a></li>
    <li><span>
        <c:forEach var="i" begin="${paging.startPageNo}" end="${paging.endPageNo}" step="1">
            <c:choose>
                <c:when test="${i eq paging.pageNo}"><a href="javascript:goPage(${i})" class="choice">${i}</a></c:when>
                <c:otherwise><a href="javascript:goPage(${i})">${i}</a></c:otherwise>
            </c:choose>
        </c:forEach>
    </span></li>
   <li> <a href="javascript:goPage(${paging.nextPageNo})" class="next">다음 페이지</a></li>
   <li><a href="javascript:goPage(${paging.finalPageNo})" class="last">마지막 페이지</a></li>
</ul>
</div>

</body>
</html>