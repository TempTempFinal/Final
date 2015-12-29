/**
 * concern에 대한 paging처리
 */
function goPage(page){
	
	var url = location.search;
	var searchWord = getParameter("searchWord");
	var temp = url.split("&");

	var parameter = "?searchWord=" + encodeURIComponent(searchWord) + "&" + temp[1] + "&startPage=" + page + "&pageNum=9";
	console.log(parameter);

	loadXMLDoc(parameter);
}

getParameter = function(name){
	    search=location.search;
	    if(!search){
	        //파라미터가 하나도 없을때
	        document.write("에러 출력 텍스트");
	        return false;
	    }
	 
	    search=search.split("?");
	    data=search[1].split("=");
	    if(search[1].indexOf(name)==(-1) || data[0]!=name){
	        //해당하는 파라미터가 없을때.
	        return "없어!";
	        return;
	    }
	    if(search[1].indexOf("&")==(-1)){
	        //한개의 파라미터일때.
	        data=search[1].split("=");
	        return data[1];
	    }else{
	    //여러개의 파라미터 일때.
	    data=search[1].split("&"); //엠퍼센트로 자름.
	    for(i=0;i<=data.length-1;i++){
	        l_data=data[i].split("=");
	        if(l_data[0]==name){
	            return l_data[1];
	            break;
	        }else continue;
	        }
	    }
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
	
	var path = location.pathname;
	path = path.substring(0,path.indexOf('/',1));
	
	for(var i in obj.posts)
		size++;

	$('#post_list').empty();
	for(var i=0; i<size; i++){
		$divWrapper = $('<div></div>').addClass('span4');
		$postContainer = $('<div></div>').addClass('post-container');
		$postContainer.append($('<div></div>').addClass('img-div ').html('<img src="' + obj.posts[i].image_url + '">'));
		$postContainer.append($('<div></div>').addClass('title-div ').text(obj.posts[i].title));
		$postContainer.append($('<div></div>').addClass('location-div ').text(obj.posts[i].address));
		
		$pWrapper = $('<div></div>');
		$pWrapper.append($('<a></a>').addClass('active pull-right').text(obj.posts[i].comment_count).append($('<i></i>').addClass('icon-comment')));
		$pWrapper.append($('<a></a>').addClass('active pull-right').text(obj.posts[i].like_count).append($('<i></i>').addClass('icon-heart')));
		
		$postContainer.append($pWrapper);
		$divWrapper.append($postContainer);
		
		$atag = $('<a></a>').attr('href',path + "/detail?travlePostNumber=" + obj.posts[i].travelPost_no)
		$atag.append($divWrapper);
		
		$('#post_list').append($atag);
	
	}
}


