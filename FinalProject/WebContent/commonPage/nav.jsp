<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Responsive Auto Show/Hide Toggle Menu Demo</title>

<link rel="stylesheet" href="css/slidestyle.css" />
</head>

<body>
   <div class="nav_wrapper">
      <a href="#search_box" class="btn" id="search">&#9740;</a>
      <nav class="navbar navbar-default navbar-fixed-top">
         <div class="container">
            <div class="navbar-header">
               <a class="navbar-brand" href="#">Project name</a>
            </div>
            <div id="navbar" class="navbar-collapse collapse">
               <ul class="nav">
                  <li onClick="textMenu();"><i class="icon-search"></i>Search</li>
                  <li><a href="${pageContext.request.contextPath}/main"><i
                        class="icon-home"></i>Main</a></li>
                  <li><a href="${pageContext.request.contextPath}/concern"><i
                        class="icon-star"></i>Concern</a></li>
                  <li><a href="${pageContext.request.contextPath}/main">Real
                        Travel</a></li>
                  <li><a href="${pageContext.request.contextPath}/my"><i
                        class="icon-user"></i>MyPage</a></li>
                  <li><a
                     href="${pageContext.request.contextPath}/writePage.jsp"><i
                        class="icon-pencil"></i>WritePage</a></li>
                  <li><a href="#"><i class="icon-gift"></i></a></li>
                  <li><a href="#"><i class="icon-align-justify"></i></a></li>
               </ul>
            </div>
         </div>
      </nav>
      <form action="${pageContext.request.contextPath}/concern" method="get">
         <input type="text" name="searchWord"> <input type="submit"
            value="search">
      </form>
   </div>
   <script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
   <script src="/js/slidescript.js"></script>

</body>
</html>