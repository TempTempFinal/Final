/**
 * 
 */


	function goPage(page){
		
		var parameter = getParameter(page);

		console.log(parameter);
		loadXMLDoc2(parameter);		
	}

	function getParameter(page){
		
		var category = document.getElementsByClassName('active')[0].childNodes[0].innerHTML;
		var parameter = "?method=popularList&category=" + escape(encodeURIComponent(category)) + "&startPage=" + page + "&pageNum=9"
		
		return parameter;
	}


	function loadXMLDoc2(parameter)
	{
		var xmlhttp = new XMLHttpRequest();

		xmlhttp.onreadystatechange=function(){
			if (xmlhttp.readyState==4 && xmlhttp.status==200){
				var obj = JSON.parse(xmlhttp.responseText);

				jsonObjectParse2(obj);
			}
		}   
		 
		xmlhttp.open("GET", "/boot_test/RestGetPost" + parameter,true);
		xmlhttp.send();
	}


	function jsonObjectParse2(obj){
		
		console.log("22222222222222222222");
		
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

	
	


