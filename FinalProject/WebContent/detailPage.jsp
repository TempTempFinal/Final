<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ page import="java.io.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="java.util.*"%>
<%@ page import="javax.servlet.http.*"%>
<%@ page import="java.sql.Connection"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.PreparedStatement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Timestamp"%>
<%@ page import="com.lecture.finalproject.dao.JDBCMannager"%>
<%@ page import="com.lecture.finalproject.dao.DaoTravlePlace"%>
<%@ page import="com.lecture.finalproject.model.ModelFrontTravlePost"%>
<%@ page import="com.lecture.finalproject.model.ModelCommentList"%>
<!DOCTYPE html>
<html>
<head>
<title>Detail Page</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/bootstrap-responsive.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet">
<style type="text/css">
body {
   width: 100%;
   height: 100%;
}
.container {
   margin: 0 auto;
   max-width: 1000px;
}
.container>hr {
   margin: 20px 0;
}
.picture {
   max-width: 64px;
   max-height: 64px;
}
.navbar .navbar-inner {
   padding: 0;
}
.navbar .nav {
   margin: 0;
   display: table;
   width: 100%;
}
.navbar .nav li {
   display: table-cell;
   width: 1%;
   float: none;
}
.navbar .nav li a {
   font-weight: bold;
   text-align: center;
   border-left: 1px solid rgba(255, 255, 255, .75);
   border-right: 1px solid rgba(0, 0, 0, .1);
}
.navbar .nav li:first-child a {
   border-left: 0;
   border-radius: 3px 0 0 3px;
}
.navbar .nav li:last-child a {
   border-right: 0;
   border-radius: 0 3px 3px 0;
}
.bpicture {
   margin: 0 auto;
   width: 640px;
}
.btnmargin {
   margin-left: 150px;
}
.jumbotron {
   margin: 40px 0;
   text-align: center;
}
.jumbotron h1 {
   font-size: 100px;
   line-height: 1;
}
section{
   width: 100%;
   min-width: 1024px;
}
#myDetailSection{
   background-color: #f3f4f5;
      min-height: 1500px;
    border: 1px solid white;
}
.myDetailcontainer{
   border : solid 1px #e6e6e6;
    margin-left: 250px;
    margin-right: 250px;
    margin-top: 50px;
    -webkit-border-radius:5px;
   -moz-border-radius:5px;
}
.myDetailcontainer .row{
    margin : 0;
    
}
.myDetailcontainer .col-1{
   text-align: center;
}
.detailTitle{
    font-size: xx-large;
    font-weight: bold;
}
.subTitle{
    font-size: 12px;
    color: #999;
}
.btnRow{
    
}
.titleRow{
border-radius: 5px 5px 0 0;
background-color:white;
    padding-top: 50px;
    padding-bottom: 30px;
}
.photoRow{
    min-height: 400px;
    background-color: #eee;
    height: auto;
}
.contentRow{
background-color:white;
height: auto;
border-radius: 5px;
}
.commentContainer ,.postCommentContainer{
   border : solid 1px #e6e6e6;
    margin-left: 250px;
    margin-right: 250px;
    margin-top: 8px;
    -webkit-border-radius:5px;
   -moz-border-radius:5px;
   background-color: #eee;
    height: auto;
}
.writerInfoContainer{
height: 100px;
    text-align: right;
         padding-right: 50px;
    padding-top: 20px;
}
.wirterInfo{
height: 100px;
}
.textContainer{
    padding: 50px;
    line-height: 2em;
}
.comment-form{
width: 80%;
    margin-left: 20px;
    margin-top: 20px;
    float: left;
}
.comment-sendBtn{
    margin-top: 20px;
    margin-right: 50px;
    width: 10%;
}
.sendBtn{
    height: 74px;
    width: 74px;
}
.postCommentContainer{
   margin-top: 8px;
   min-height: 300px;
}
.friendCommentTitle{
    margin: 20px;
    color: #b4b4b4;
}
.friendComment{
margin : 10px 10px 5px 10px;
}
.nav-container {background:url('images/nav_bg.jpg') repeat-x 0 0;
    background-color: white;
    min-width: 845px;
}
.f-nav {z-index:9999; position:fixed; top:0;
    height: 50px;
    position: fixed;
    border-bottom:  1px solid #e6e6e6;
    opacity: 0.9;
    }
     
.nav {height:42px;}
.nav ul {list-style:none;}
.nav ul li {float:left; margin-top:6px; padding:6px;}
.nav ul li:first-child {padding-left:0;}
.nav ul li a:hover {text-decoration: underline;}
.like{color: #b4b4b4;font-weight: bold;}
.navBtn{
    margin-top: -5px;
}
</style>
</head>

<body>

   <jsp:useBean id="model"
      class="com.lecture.finalproject.model.ModelComment" />
   <jsp:setProperty property="*" name="model" />
   
   <nav>
      <jsp:include page="commonPage/nav.jsp"></jsp:include>
   </nav>
   <section id="myDetailSection">
      <div class="myDetailcontainer">
         <div class="nav-container">
            <div class="nav">
               <ul>
                  <li><button type="button" class="btn btn-default navBtn" ><span class="glyphicon glyphicon-heart"><span class="like"> 좋아요 | ${post.like_count}</span></button></span></li>
               </ul>
               <div class="clear"></div>
            </div>
         </div>
         <div class="row titleRow">
            <div class="col-1">
               <p class="detailTitle">${travlePost.title}</p>
            </div>
            <div class="col-1">
               <p class="subtitle"><b>${writer.user_id}</b>님이 작성 /${post.travelPost_date} /${post.view_count} VIEWS /${post.comment_count} 개의 댓글</p>
            </div>
         </div>
         <div class="row photoRow">
            <div class="col-1">
               <c:forEach items="${imageList}" var="imgEntry">
                  <br>
                  <img src="${imgEntry.image_url}" />
                  <br>
               </c:forEach>

            </div>
         </div>
         <div class="row contentRow">
            <div class="col-1 textContainer">
               <p>1박2일 국내여행 바다보러 속초 다녀왔어요~
날씨가 선선하니 좋다 보니까 이런 음식 저런 음식이 막 땡겨요 ㅋㅋㅋ
어제는 달달한 음식이 땡기다가 오늘은 느끼한게 땡기고
저녁되면 또 매운거 땡겨서 닭발에 소주 한잔 하고 ㅋㅋ 다이어트는 대체 언제쯤 :(?
가을 날씨 참 좋아하는데 사실 정말 눈 깜짝할 사이에 후딱
지나가버리잖아요.... 저는 항상 아쉽더라구요
그래서 이번 가을에는 아얘 제대로 여행을 다녀올까 해요 ㅋㅋㅋ
오래 기억될만한 추억을 만들면 좋을 것 같더라구요
여름이나 겨울에는 전혀 느낄 수 없는 분위기로다가요~~~~~
여행은 다녀와야겠다 마음 먹었는데 어디로 떠나야할지
막상 고민되신다면 ! 저는 1박2일 국내여행 속초를 추천합니다? !
동해바다만큼 맑고 예쁜 물은 없는 것 같아요 >_< ♥
1박2일 국내여행을 계획하는 분들~ 혹은 시간적 여유가 없으신
분들은 굳이 1박이 아닌 당일치기로 다녀오기에도 좋아요
속초해변 근처에 펜션도 많고 민박집도 많아서
당일로 머물기에도, 1박을 머물기에도 거뜬 없다는거 ㅎㅎㅎ?
1박2일 국내여행을 왔다면 속초의 유명한 음식 하나쯤은
꼭 맛보고 집가야하지 않겠어요??
2박3일, 3박4일 머물지 않아도 맛있는 맛집은 미리 알아보면
충분히 다녀올 수 있으니까요 !!!!!!!!!!!!!!!!
속초하면 여름철 별미인 물회가 되게 유명하쥬 ㅋㅋㅋㅋㅋ
해산물 자체가 워낙에 싱싱해서 아마 물회를 여기에서 처음 드셨다면
다른 곳에선 못드실 거라 조심스레 예상해봅니다..? ^ㅡ^...
물회에는 해산물의 7가지 종류가 들어간다고 해요
멍게 전복 오징어 골뱅이 해삼 등등 엇 벌써 까먹었네요..ㅋㅋㅋㅋㅋ
분명 설명으로도 듣고 직접 먹어보기까지 했는데 까머금
지금 아니면 못즐길 가을 1박2일 국내여행 지금 당장
계획하고 바로바로 실행해보자구용!!!!!?
사실 한번 미루다보면 점점 멀어지는게 사실...ㅠ ㅠ?
생각만 하기 보다는 바로 계획하고 바로 실행하는 습관을 갖는걸로 !날씨가 선선하니 좋다 보니까 이런 음식 저런 음식이 막 땡겨요 ㅋㅋㅋ
어제는 달달한 음식이 땡기다가 오늘은 느끼한게 땡기고
저녁되면 또 매운거 땡겨서 닭발에 소주 한잔 하고 ㅋㅋ 다이어트는 대체 언제쯤 :(?
가을 날씨 참 좋아하는데 사실 정말 눈 깜짝할 사이에 후딱
지나가버리잖아요.... 저는 항상 아쉽더라구요
그래서 이번 가을에는 아얘 제대로 여행을 다녀올까 해요 ㅋㅋㅋ
오래 기억될만한 추억을 만들면 좋을 것 같더라구요
여름이나 겨울에는 전혀 느낄 수 없는 분위기로다가요~~~~~
여행은 다녀와야겠다 마음 먹었는데 어디로 떠나야할지
막상 고민되신다면 ! 저는 1박2일 국내여행 속초를 추천합니다? !
동해바다만큼 맑고 예쁜 물은 없는 것 같아요 >_< ♥
1박2일 국내여행을 계획하는 분들~ 혹은 시간적 여유가 없으신
분들은 굳이 1박이 아닌 당일치기로 다녀오기에도 좋아요
속초해변 근처에 펜션도 많고 민박집도 많아서
당일로 머물기에도, 1박을 머물기에도 거뜬 없다는거 ㅎㅎㅎ?
1박2일 국내여행을 왔다면 속초의 유명한 음식 하나쯤은
꼭 맛보고 집가야하지 않겠어요??
2박3일, 3박4일 머물지 않아도 맛있는 맛집은 미리 알아보면
충분히 다녀올 수 있으니까요 !!!!!!!!!!!!!!!!
속초하면 여름철 별미인 물회가 되게 유명하쥬 ㅋㅋㅋㅋㅋ
해산물 자체가 워낙에 싱싱해서 아마 물회를 여기에서 처음 드셨다면
다른 곳에선 못드실 거라 조심스레 예상해봅니다..? ^ㅡ^...
물회에는 해산물의 7가지 종류가 들어간다고 해요
멍게 전복 오징어 골뱅이 해삼 등등 엇 벌써 까먹었네요..ㅋㅋㅋㅋㅋ
분명 설명으로도 듣고 직접 먹어보기까지 했는데 까머금
지금 아니면 못즐길 가을 1박2일 국내여행 지금 당장
계획하고 바로바로 실행해보자구용!!!!!?
사실 한번 미루다보면 점점 멀어지는게 사실...ㅠ ㅠ?
생각만 하기 보다는 바로 계획하고 바로 실행하는 습관을 갖는걸로 !</p>
            </div>
         </div>
         <div class="row">
            <div class="writerInfoContainer">
                  <p>${writer.user_id} 님이 작성</p>
                  <img src="${pageContext.request.contextPath}/${writer.img_url}"/>
            </div>
         </div>
      </div>
      
      <div class="commentContainer">
      <p class="friendCommentTitle">해당 여행지를 다녀온 <b>${writer.name}</b>님의 친구분들의 이야기!</p>

         <c:forEach items="${wigets}" var="wiget">
            <div class="friendComment">
               <blockquote class="twitter-tweet"
                  style="background-color: #FFF !important;">
                  <a href="https://twitter.com/${wiget.userId}/status/${wiget.mediaId}"></a>
               </blockquote>
            </div>
         </c:forEach>

      </div>
      <div class="postCommentContainer">
      
      
   <!-- TODO herehere herehere herehere herehere herehere herehere herehere herehere herehere herehere herehere herehere-->
      <div class="container">
         <div class="raw-fluid" id="comment_list">
            <div class="span12 pull-left">
               댓글<br> <br>
            </div>
            <%
                        DaoTravlePlace db = new DaoTravlePlace();
                        List<ModelCommentList> commentList = db.getModelCommentList(db.getTravelPostCount());
                        for (int i = 0; i < commentList.size(); i++) {
                            ModelCommentList coms = commentList.get(i);
                    %>

            <div class="cpicture span1 pull-left">
               <img src="<%=coms.getImage_url()%>" class="img-polaroid">
            </div>
            <div class="span8 pull-left">
               <dl>
                  <dt><%=coms.getUser_id()%></dt>
                  <dd><%=coms.getContent()%></dd>
               </dl>
            </div>
            <%
               }
            %>
   
      </div>
      </div>
      <%@include file="commonPage/paging.jsp"%>
         <div class="postComment">
            <form action="comment_save.jsp" method="post">
                    <div class="raw" id="post_list">
                        <div class="comment-form">
                            <textarea class="form-control" rows="3" name="content"></textarea>
                        </div>
                        <div class="comment-sendBtn pull-right">
                            <button type="submit" class="btn sendBtn">Send</button>
                        </div>
                    </div>
                </form>
         </div>   
      </div>   
   </section>
   <script src="http://code.jquery.com/jquery.js"></script>
   <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
   <script src="${pageContext.request.contextPath}/js/mainPage.js"></script>
   <script src="${pageContext.request.contextPath}/js/paging.js"></script>
   <script src="${pageContext.request.contextPath}/js/detail.js"></script>
   <script src='http://platform.twitter.com/widgets.js' charset='utf-8'></script>";
</body>
</html>