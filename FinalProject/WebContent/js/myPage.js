var friendList;
var addFriendCount = 0;
var contextPath='<%=request.getContextPath()%>';

$(document).ready(function(){

	$('#showFriendBtn').on('click', function(){		
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
	
	
	console.log("ggggggggggggggggggggggggggggg");
	console.log(obj);
	console.log(obj.groupConcernList[0])
	console.log(obj.groupConcernList[1])
	
	$('#groupConcern0 a').text("");
	$('#groupConcern1 a').text("");
	$('#groupConcern2 a').text("");
	$('#groupConcern3 a').text("");
	
	for(var i in obj.groupConcernList)
		size++;
	
	for(var i=0; i<size; i++){	
		$('#groupConcern' + i +' a').text(obj.groupConcernList[i]);
		$('#groupConcern' + i +' a').attr('href',getContextPath() +"/concern?searchWord=" + obj.groupConcernList[i]);
	}
}

function getContextPath(){
    var offset=location.href.indexOf(location.host)+location.host.length;
    var ctxPath=location.href.substring(offset,location.href.indexOf('/',offset+1));
    return ctxPath;
}

