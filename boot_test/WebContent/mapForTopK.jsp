<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <style>
.debug{
outline: 1px solid red;
}

html, body {
  height: 100%;
  margin: 0;
  padding: 0;
}
#map {
  height: 100%;
}

#controller{
    position: fixed;
    min-height: 40px;
    width: 100%;
    background: yellow;
    bottom: 0px;
    background: rgba(255, 255, 0, 0.5);
}

#controller ul{
    padding-left: 100px;
	margin: 0px 0px;
	
}

#controller ul li{
	list-style-type: none;
	display: inline;
	padding: 5px 10px;
	float: left;
}

.button{
    width: 60px;
    height: 36px;
    background-color: white;
    border: 1px solid gray;
}

.font{
    font-weight: bold;
    font-size: medium;
    	top: 6px;
    position: relative;
}

.output{
	color: red;
    position: relative;
    bottom: 14px;	
}

.inline{
	display: inline;
}

.range{
	width: 400px;
	position: relative;
	height: 30px;
}

.default-form{
	height: 30px;
	width: 50px;
}

.top{
	top: -14px;
}

#iw_container{
	max-width: 200px;
}

#iw_container .iw_content{
	padding: 5px;
	background: white;
	position: relative;
	bottom: 5px;
	border-radius: 0 0 2px 2px;
}

#iw_container .iw_title{
font-family: 'Open Sans Condensed', sans-serif !important;
   padding: 5px !important;
   background-color: #48b5e9 !important;
   color: white !important;
   border-radius: 2px 2px 0 0 !important;
    text-align: center !important;
    font-size: 15px !important;
    font-weight: bold !important;
 }
 
 .gm-style-iw {
         top: 22px !important;
    position: relative !important;
    left: 63px !important;
}
 
 .iwBasicContent{
   font-size: 10px;
   max-width: 200px;
   
 }
 
 .friendsContent{
  	height: 100px;
    margin-bottom: 5px;
        max-height: 90px;
    overflow-y: auto;
    overflow-x: hidden;
 }
 
 .friendsContent .friend{
 	height: 45px;
    padding: 1px 2px 0px 2px;
     border-top: 0.5px solid gray;
 }
 
 .placeContent{
 	margin: 5px 0px 10px 0px;
 	   
 }
 
 .friendsContent .friendPhoto{
 width: 20%;
    float: left;
    padding-top: 3px;
 }
 .friendsContent ul{
 padding-left: 20px;
 list-style: none;
    padding-left: 10px;
 }
 
 .friendsContent .friendInfo{
     width: 80%;
    float: left;
 }
 
 
 .friendsMoreInfo{
 	padding-top: 75px;
 }
 
.totalInfo{
    font-size: 10px;
    padding: 5px;
    font-style: italic;
}

  
  
    </style>
 
    
  </head>
  <body>
 
    <div id="map"></div>
    <div id="controller">
    	<ul>
    		<li> 	
    			<span class="font" style="top: auto;">K (# of result) : </span>
    			<input type="number" class="default-form" id="numberForm">&nbsp; &nbsp; &nbsp; &nbsp; 
 				</li>
 				<li>
 				<span class="font">Query radius : </span>
 				</li>
 				<li>
 				<form oninput="x.value=parseInt(rangeForm.value)">
 				<input type="range" class="range" name="radius" onchange="rangeChanged(this.value)"  step="1" value="1" max="200" id="rangeForm">	
 				<span class="font"><output name="x" for="rangeForm" class="output" >0</output><span class="font output top" >&nbsp;(Km)</span></span>
 				</form>
 				</li>
 				<li>
 				<input type="button" value="reset" class="button" onclick="resetMarker()">
 				</li>
 				<li>
 				<input type="button" value="submit" class="button" onclick="onClickSubmitBtn()">
   			</li>
	    		
    	</ul>
    </div>
 
    <script async defer
        src="https://maps.googleapis.com/maps/api/js?v=3.20&key=&signed_in=true&callback=initMap&language=en"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>  
    <script src="${pageContext.request.contextPath}/js/map.js"></script>
	<script>
		var path = '<%=request.getContextPath()%>';
	</script>
       
  </body>
</html>