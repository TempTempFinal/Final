/**
 * 
 */

				


$(function(){
	
	if(likeState == 1){
		$('#like-btn .glyphicon-heart').css('color','red');
		
	}
	
	$('#like-btn').click(function(event){
		var parameter = "?method=likeUpdate&travelPost_no=" + travelPost_no + "&userID=" + userName;
		
		console.log(parameter);
		loadXMLDocForLikeUpdate(parameter);
	});
})

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