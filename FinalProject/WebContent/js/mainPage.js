
/*
 * New Or 지역별로 버튼을 눌럿을 경우 pageing 처리 해주는부분
 * 
 */

var latitude;
var longitude;

$(document).ready(function(){
	$('input:checkbox').change(function(){
		var parameter = "";
	
		parameter = getCheckedLocationParameter("listByLocation&startPage=1&pageNum=9");
		
		
	    console.log(parameter);
		loadXMLDoc(parameter);
	})
	
	$(':button').on('click', function(event){
		console.log("value=" + this.innerText);
		var clickBtn = this.innerText;
		var parameter = "";
		
		if(clickBtn == "location"){
			getLocation();
		}
		else{
			if(clickBtn == 'Like'){
				console.log("good1");
				parameter = getCheckedLocationParameter('listBySortedLocation&standard=like&startPage=1&pageNum=9');
			}else if(clickBtn == 'New'){
				parameter = getCheckedLocationParameter('listBySortedLocation&standard=new&startPage=1&pageNum=9');
			}else if(clickBtn =="Comment"){
				parameter = getCheckedLocationParameter('listBySortedLocation&standard=comment&startPage=1&pageNum=9');
			}
			console.log("parameter = " + parameter);
			loadXMLDoc(parameter);
		}
	})
});


function getLocation() {
    // check if Geolocation is supported
    if (navigator.geolocation) { //현재 사용 browser에서 해당 object를 지원한다
        navigator.geolocation.getCurrentPosition(showPosition, showError);
        // if successful, passes a coordinate object to showPosition
        // if failed, calls showError to handle errors
    } else {
    	console.log("Geolocation is not supported by this browser.");
    }
}

function showPosition(position) {
    // displays the Latitude and Longitude
  
    latitude = position.coords.latitude.toFixed(2);
    longitude = position.coords.longitude.toFixed(2);
 
    var parameter = '?method=listByBoundaryLocation&latitude=' + latitude + '&longitude=' + longitude + '&startPage=1&pageNum=9';
    console.log(parameter); 
    
    loadXMLDoc(parameter);
}

function showError(error) {
    switch(error.code) {
        case error.PERMISSION_DENIED:
           console.log("User denied the request for Geolocation."); break;
        case error.POSITION_UNAVAILABLE:
        	console.log(x.innerHTML = "Location information is unavailable."); break;
        case error.TIMEOUT:
        	console.log(x.innerHTML = "The request to get user location timed out."); break;
    }
}




function getCheckedLocationParameter(method)
{
	var parameter="";

	if($('input:checked').length == 0)
		parameter = "?method=" + method;
		
	$('input:checked').map(function(index,element){
		console.log("element value = " + element.value )
		if(index == 0)
			parameter = "?method=" + method + "&location=" + escape(encodeURIComponent(element.value));
		else
			parameter = parameter + "&location=" + escape(encodeURIComponent(element.value));
	})
	
	return parameter;
}

function loadXMLDoc(parameter)
{
    var xmlhttp = new XMLHttpRequest();

    
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200) {
    	  var obj = JSON.parse(xmlhttp.responseText);
    	  console.log(obj);
    	  jsonObjectParse(obj);
      }
    }
    
    console.log(parameter);
 
    xmlhttp.open("GET", "/boot_test/RestGetPost" + parameter,true);
    xmlhttp.send();
}

function jsonObjectParse(obj){
	
	var size = 0;
	
	for(var i in obj.posts)
		size++;
	
	$('#post_list').empty();
	for(var i=0; i<size; i++){
		$divWrapper = $('<div></div>').addClass('span4');
		$divWrapper.append($('<div></div>').addClass('img-div debug').html('<img src="' + obj.posts[i].image_url + '">'));
		$divWrapper.append($('<div></div>').addClass('title-div debug').text(obj.posts[i].title));
		$divWrapper.append($('<div></div>').addClass('location-div debug').text(obj.posts[i].address));
		
		$pWrapper = $('<div></div>').addClass('debug');
		$pWrapper.append($('<a></a>').addClass('active pull-right').text(obj.posts[i].comment_count).append($('<i></i>').addClass('icon-comment')));
		$pWrapper.append($('<a></a>').addClass('active pull-right').text(obj.posts[i].like_count).append($('<i></i>').addClass('icon-heart')));
		
		$divWrapper.append($pWrapper);
		
		$('#post_list').append($divWrapper);
	
	}
}



