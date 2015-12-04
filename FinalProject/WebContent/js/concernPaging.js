/**
 * concern에 대한 paging처리
 */
function goPage(page){
	
	var url = location.href;
	var subValue = "=";
	var iValue = url.indexOf(subValue); 
	var searchWord = url.substring(iValue+1, url.length);
	
	var method = classifyMetohd(searchWord);
	
	var parameter="?method=" + method + "&searchWord=" + encodeURIComponent(searchWord) + "&startPage=" + page + "&pageNum=9";
	
	console.log(parameter);
	
	loadXMLDoc(parameter);
}

function classifyMetohd(searchWord){
	
	var temp = searchWord.substring(0,1);
	
	if(temp == "#")
		return "listByHashTag";
	else
		return "listBySeachWord";
}


function loadXMLDoc(parameter)
{
	var xmlhttp = new XMLHttpRequest();

	xmlhttp.onreadystatechange=function(){
		if (xmlhttp.readyState==4 && xmlhttp.status==200){
			var obj = JSON.parse(xmlhttp.responseText);

			jsonObjectParse(obj);
		}
	}   
	 
	xmlhttp.open("GET", "/boot_test/RestGetPost" + parameter,true);
	xmlhttp.send();
}


function jsonObjectParse(obj){
	
	var size = 0;
	
	for(var i in obj.posts)
		size++;
	
	$('#post_list').empty();
	for(var i=0; i<size; i++){
		$divWrapper = $('<div></div>').addClass('col-3');
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



