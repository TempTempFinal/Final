/**
 * 
 */



var friendList;
var addFriendCount = 0;
var contextPath='<%=request.getContextPath()%>';

$(document).ready(function(){

	$('#showFriendBtn').on('click', function(){		
		$('#layerpop').modal();
		friendList = new Array();
		addFriendCount = 0;
		
		var parameter = "?method=getFrindAndFollowerList";
		console.log(parameter);
		loadXMLDoc1(parameter);	
	});
	
	$('#sendFriendBtn').on('click', function(){
		
		var parameter="";
		var i;
		for(i=0; i<friendList.length; i++){
			parameter = parameter + "&name=" + friendList[i];
			
		}		
		parameter = "?method=getFriendWeight" + parameter;
		console.log(parameter);
		loadXMLDoc2(parameter);
		$('#myModal').hide();
		
	});
	
})


function addFriend(btn){
	console.log("testest");
	console.log(btn);
	$(btn).attr("disabled","disabled")
	var friendScreenName = $(btn).parent().children('.friendScreenName').text();
	console.log(friendScreenName);
	friendList[addFriendCount++] = friendScreenName;
}

function loadXMLDoc1(parameter)
{
    var xmlhttp = new XMLHttpRequest();
  
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200){
    	  var obj = JSON.parse(xmlhttp.responseText);
    	  friendsListParse(obj);
      }
    }   
    console.log(parameter);
 
    xmlhttp.open("GET", "/boot_test/RestGetPeople" + parameter,true);
    xmlhttp.send();
}

function loadXMLDoc2(parameter)
{
    var xmlhttp = new XMLHttpRequest();
  
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200){
    	  var obj = JSON.parse(xmlhttp.responseText);
    	  friendsListParse2(obj);
      }
    }   
    console.log(parameter);
 
    xmlhttp.open("GET", "/boot_test/RestGetPeople" + parameter,true);
    xmlhttp.send();
}

function friendsListParse(obj)
{
	var size = 0;
	
	for(var i in obj.friendList)
		size++;
	$('#friendListMenu').empty();
	for(var i=0; i<size; i++){
		$img = $('<img/>').attr('src',obj.friendList[i].img_url);
		$name = $('<p></p>').addClass("friendScreenName").text(obj.friendList[i].screenName);
		$btn = $('<button></button>').attr('onClick','addFriend(this)').text('추가');
		$friend = $('<div></div>').append($img, $name,$btn);
		$('#friendListMenu').append($friend);
	}
}

function friendsListParse2(obj)
{
	var size = 0;
	
	for(var i in obj.groupConcernList)
		size++;
	
	$(".groupConcernContainer").empty();
	$(".groupConcernContainer").css('text-align','left');
	
	$rowInfo = $('<p></p>').addClass('rowInfo').text('다음을 주제로 여행을 떠나보세요!');
	$row = $('<div></div>').addClass('row');
	$(".groupConcernContainer").append($rowInfo);
	$(".groupConcernContainer").append($row);
	
	var path = location.pathname;
	
	path = path.substring(0,path.indexOf('/',1));
	
	console.log('1111111111111111111');
	for(var i=0; i<size; i++){
		$atag = $('<a></a>').attr('href', path + "/concern?searchWord=" + obj.groupConcernList[i]).text(obj.groupConcernList[i]).css('color','white');
		$concern = $('<div></div>').addClass('col-4').append($atag);
		
		if(obj.groupConcernList[i] == '낚시'){
	
			$concern.css('backgroundImage','url(concernImg/fishing.png)');
			$concern.css('backgroundSize','cover');}
		else if(obj.groupConcernList[i] == '단풍'){
			$concern.css('backgroundImage','url(concernImg/maple.jpg)');		
			$concern.css('backgroundSize','cover');}
		else if(obj.groupConcernList[i] == '등산'){
			console.log('등산');
			$concern.css('backgroundImage','url(concernImg/climing.jpg)');	
			$concern.css('backgroundSize','cover');}
		else if(obj.groupConcernList[i] == '레저'){

			$concern.css('backgroundImage','url(concernImg/leisure.jpg)');
			$concern.css('backgroundSize','cover');
		}
		else if(obj.groupConcernList[i] == '미식'){
			$concern.css('backgroundImage','url(concernImg/food.jpg)');	
			$concern.css('backgroundSize','cover');}
		else if(obj.groupConcernList[i] == '바다'){
			$concern.css('backgroundImage','url(concernImg/ocean.jpg)');	
			$concern.css('backgroundSize','cover');
		}
		else if(obj.groupConcernList[i] == '섬'){
			$concern.css('backgroundImage','url(concernImg/island.jpg)');		
			$concern.css('backgroundSize','cover');}
		else if(obj.groupConcernList[i] == '캠핑'){
			$concern.css('backgroundImage','url(concernImg/camping.jpg)');		
			$concern.css('backgroundSize','cover');}
		
		$('.groupConcernContainer .row').append($concern);
	}
}

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

