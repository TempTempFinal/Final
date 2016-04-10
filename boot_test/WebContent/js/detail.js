/**
 * 
 */

				


$(function(){
	
	if(likeState == 1){
		$('#like-btn .glyphicon-heart').css('color','red');
	}
	
	if(visitState == 1)
		$('#like-btn .glyphicon-pushpin').css('color','blue').text("방문하였습니다");
		
	
	
	$('#like-btn').click(function(event){
		var parameter = "?method=likeUpdate&travelPost_no=" + travelPost_no + "&userID=" + userName;
		
		
		loadXMLDocForLikeUpdate(parameter);
	});
	
	$('#visit-btn').click(function(event){
		var parameter = "?method=visitUpdate&travelPost_no=" + travelPost_no + "&userID=" + userName + "&latitude=" + latitude + "&longitude=" + longitude;
		loadXMLDocForVisitUpdate(parameter);
	});
	
	
	
	
})
function loadXMLDocForVisitUpdate(parameter)
{
    var xmlhttp = new XMLHttpRequest();
  
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200){
    	  var obj = JSON.parse(xmlhttp.responseText);
    	  parseVisitUpdate(obj);
      }
    }   
    console.log(parameter);
 
    xmlhttp.open("GET", "/boot_test/RestUpdateInfo" + parameter,true);
    xmlhttp.send();
}

function parseVisitUpdate(obj){
	$('#visit-btn .visit').text(' 다녀온 여행지 등록 ');
	if(obj.isVisit == 1){
		$('#visit-btn .glyphicon-pushpin').css('color','blue');
	}
	else if(obj.isVisit == 0)
		$('#visit-btn .glyphicon-pushpin').css('color','');
	
}


function loadXMLDocForLikeUpdate(parameter)
{
    var xmlhttp = new XMLHttpRequest();
  
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200){
    	  var obj = JSON.parse(xmlhttp.responseText);
    	  parseLikeUpdate(obj);
      }
    }   
    console.log(parameter);
 
    xmlhttp.open("GET", "/boot_test/RestUpdateInfo" + parameter,true);
    xmlhttp.send();
}

function parseLikeUpdate(obj){
	$('#like-btn .like').text(' 좋아요 | ' + obj.likeCount);
	if(obj.likeState == 1){
		$('#like-btn .glyphicon-heart').css('color','red');
	}
	else if(obj.likeState == 0)
		$('#like-btn .glyphicon-heart').css('color','');
	
}





var nav = $('.nav-container');
 
$(window).scroll(function () {
    if ($(this).scrollTop() > 136) {
        nav.addClass("f-nav");
    } else {
        nav.removeClass("f-nav");
    }
});