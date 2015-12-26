<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	boolean islogin = (String)request.getAttribute("isLogin") == null? false : true; %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>travel recommend</title>

    <!-- Bootstrap Core CSS -->
    <link href="http://ironsummitmedia.github.io/startbootstrap-landing-page/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="http://ironsummitmedia.github.io/startbootstrap-landing-page/css/landing-page.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/awesome.css" rel="stylesheet">
	<link href="${pageContext.request.contextPath}/css/grid.css" rel="stylesheet">
    <!-- Custom Fonts -->
    <link href="http://ironsummitmedia.github.io/startbootstrap-landing-page/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="http://fonts.googleapis.com/css?family=Lato:300,400,700,300italic,400italic,700italic" rel="stylesheet" type="text/css">
 	<link href="${pageContext.request.contextPath}/font/loginFont.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css">
    <link href='https://fonts.googleapis.com/css?family=Kaushan+Script' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Droid+Serif:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700' rel='stylesheet' type='text/css'>
   	<style type="text/css">
   		.debug {
			outline: 1px solid red;
		}
		
		.intro-message{
			padding-top: 15%;
		}
		
		.intro-message ul{
			padding: 60px;
		}
		
		.intro-header{
			background: url('${pageContext.request.contextPath}/img/background.jpg') no-repeat center center;
		}
		
		.trans-background{
			 background-color: transparent !important;
			 background-image: none !important;
		}
		
		.border-none{
			border: none;
		}
		

		.font-white{
			color: white;
		}
		section{
			background-color: white;
		}
		footer{
			background-color: #696969;
		}
		
		.col-md-4 img{
			width: 300px;
			height: 150px;
		}
		
		.team-member img{
			width: 275px;
			height: 275px;
		}
		
		#mainFooter {
		min-width: 1024px;
		width: 100%;
		height: auto;
		background-color: #505050;
	}
		
		a:link{color: white;}
		a:VISITED {color: white;}
		a:HOVER {color: white;}
		a:ACTIVE {color: blue;}
		

   	
   	
   	
   	</style>
   
   

</head>

<body>
    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-fixed-top topnav trans-background border-none" role="navigation">
        <div class="container topnav">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand topnav" href="#" style="color: white;">The Real travel</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul id="loginNav" class="nav navbar-nav navbar-right">
                  <li>
                  	<a href="<%=request.getContextPath()%>/signin" style="color: white;font-weight: bold;">로그인</a>
                  </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>
    
    


    <!-- Header -->
    <a name="about"></a>
    <div class="intro-header">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>나의 취향에 맞춰 떠나는 여행 </h1>
             <h3>대한민국 이곳 저곳, 진짜 여행 스토리</h3>
          
                        <ul  class="list-inline intro-social-buttons">      
                            <li id="loginBtn">
                                <a href="<%=request.getContextPath()%>/signin" class="btn btn-default btn-lg trans-background font-white" style="border: 4px solid white">
                                	<i class="fa fa-twitter fa-fw whitFont" ></i>
                                	 <span class="network-name">Twitter로 로그인</span>
                                </a>
                            </li>
                            
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    <!-- /.intro-header -->

    <!-- service -->
     <section id="services">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Services</h2>
                    <h3 class="section-subheading text-muted"></h3>
                </div>
            </div>
            <div class="row text-center">
                <div class="col-md-4">
        
                        <img  src="${pageContext.request.contextPath}/img/service1.png">
     
                    <h4 class="service-heading">사용자의 분석을 통한 추천 </h4>
                    <p class="text-muted">SNS의 Time line의 분석, 사용자의 관심사 확보를 통한 맞춤형 여행지 추천</p>
                </div>
                <div class="col-md-4">
                  <img  src="${pageContext.request.contextPath}/img/service2.png">
     
                    <h4 class="service-heading">친구와  ‘함께 가기’ 기능
                    </h4>
                    <p class="text-muted">상황정보의 분석을 통해  랭킹 알고리즘을 적용하여 집단의 맞춤형 여행지 추천
                    </p>
                </div>
                <div class="col-md-4">
                    <img  src="${pageContext.request.contextPath}/img/service3.png"	>
     
                    <h4 class="service-heading">다양한 추천방식을 통한 만족도 향상
</h4>
                    <p class="text-muted">사용자들의 선호도, 인기도, 거리 요소를 고려한 추천을 통해 만족도 향상
</p>
                </div>
            </div>
        </div>
    </section>

    <!-- /.content-section-a -->
    <section id="team" class="bg-light-gray">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <h2 class="section-heading">Our Team Member</h2>
                    <h3 class="section-subheading text-muted"></h3>
                </div>
            </div>
            <div class="row">
                <div class="col-4">
                    <div class="team-member">
                        <img src="${pageContext.request.contextPath}/img/park.jpg" class="img-responsive img-circle" alt="">
                        <h4>박 정 호</h4>
                        <p class="text-muted">Software Engineer<br>Gachon Univ</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-4">
                    <div class="team-member">
                        <img src="${pageContext.request.contextPath}/img/kimhyun.png" class="img-responsive img-circle" alt="">
                        <h4>김 현 민 </h4>
                        <p class="text-muted">Software Engineer<br>Gachon Univ</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="col-4">
                    <div class="team-member">
                        <img src="img/team/3.jpg" class="img-responsive img-circle" alt="">
                        <h4>김 영 근</h4>
                        <p class="text-muted">Software Engineer<br>Gachon Univ</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div> 
                 <div class="col-4">
                    <div class="team-member">
                        <img src="img/team/3.jpg" class="img-responsive img-circle" alt="">
                        <h4>박 승 철</h4>
                        <p class="text-muted">Software Engineer<br>Gachon Univ</p>
                        <ul class="list-inline social-buttons">
                            <li><a href="#"><i class="fa fa-twitter"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-facebook"></i></a>
                            </li>
                            <li><a href="#"><i class="fa fa-linkedin"></i></a>
                            </li>
                        </ul>
                    </div>
                </div> 
            </div>
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2 text-center">
                    <p class="large text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Aut eaque, laboriosam veritatis, quos non quis ad perspiciatis, totam corporis ea, alias ut unde.</p>
                </div>
            </div>
        </div>
    </section>
 
    <footer id="mainFooter">
       <jsp:include page="commonPage/footer.jsp"></jsp:include>
    </footer>

	<!-- jQuery -->
    <script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
    <script src="http://ironsummitmedia.github.io/startbootstrap-landing-page/js/jquery.js"></script>
  
    <!-- Bootstrap Core JavaScript -->
    <script src="http://ironsummitmedia.github.io/startbootstrap-landing-page/js/bootstrap.min.js"></script>
    
    <!-- plug in -->
    <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/loginPlugin.js"></script>
    <script src="${pageContext.request.contextPath}/js/loginPlugin2.js"></script>
    <!-- custom -->

	<!-- login/logout -->
	<%if(islogin){ %>
	

	<script type="text/javascript">
		
		var userImg = '<%=(String) request.getAttribute("imageUrl")%>';
		var userName = '<%=(String) request.getAttribute("name")%>';
		var path = '<%=request.getContextPath()%>';

		console.log(path);
		$(function() {
			console.log("인증2");
			console.log(path);

			$('#loginBtn a').attr('href')

			$('#loginBtn a').attr('href', path + "/synchronize_action");
			$('#loginBtn span').text('시작하기');

			var target = $('#loginNav');

			target.map(function(index, element) {
				console.log(element);
				$(element).empty();
			});

			for (var i = 0; i < 2; i++) {

				var anchor = document.createElement('a');
				$('<li>').append(anchor).appendTo('#loginNav');

			}

			$('#loginNav a').map(
					function(index, element) {
						console.log(element);
						if (index === 0) {
							$('<img>').attr({
								src : userImg,
								height : 25,
							}).appendTo(element)

							$('<span>').css("margin-left", "15px").css("color",
									"white").text(userName).appendTo(element);
						} else if (index === 1) {
							$('<span>').text('로그아웃').css("color", "white")
									.appendTo(element);
							$(this).attr('href', path + '/logout');
						}
					});

		})
	</script>
<%}%>

</body>

</html>
