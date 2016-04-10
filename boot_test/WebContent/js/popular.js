
$(function(){

	$('.navbar-nav a').on('click',function(event){
	
		$('.navbar-nav').children('li').removeClass('active');
		$(this).parent('li').addClass('active');
		
		var parameterTopList = "?method=popularTop&category=" + escape(encodeURIComponent($(this).text()));
		var parameterPopularList = "?method=popularList&category=" + escape(encodeURIComponent($(this).text())) + "&startPage=" + 1 + "&pageNum=9"
		console.log(parameterTopList);
		console.log(parameterPopularList);
		
		loadXMLDoc1(parameterTopList);
		loadXMLDoc2(parameterPopularList);
	});
})
	
function loadXMLDoc1(parameter)
{
    var xmlhttp = new XMLHttpRequest();

    
    xmlhttp.onreadystatechange=function(){
      if (xmlhttp.readyState==4 && xmlhttp.status==200) {
    	  var obj = JSON.parse(xmlhttp.responseText);
    	  console.log(obj);
    	  jsonObjectParse1(obj);
      }
    }
    
    console.log(parameter);
 
    xmlhttp.open("GET", "/boot_test/RestGetPost" + parameter,true);
    xmlhttp.send();
}

function jsonObjectParse1(obj){
	console.log("11111111111111111111");
	
	var size = 0;
	
	var path = location.pathname;
	path = path.substring(0,path.indexOf('/',1));
	
	for(var i in obj.posts)
		size++;

	$('#topPost-list').empty();
	for(var i=0; i<size; i++){
		//FIXME
		//$imgTag = $('<img></img>').html('<img src="' + obj.posts[i].image_url + '">');
		$spanTag = $('<span></span>').text(obj.posts[i].title);
		$postDivTag = $('<div></div>').addClass('post').html('<img src="' + obj.posts[i].image_url + '">');
		$postTitleTag = $('<div></div>').addClass('postTitle').append($spanTag);
		$atag = $('<a></a>').attr('href',path + "/detail?travlePostNumber=" + obj.posts[i].travelPost_no).append($postDivTag).append($postTitleTag);
		$popularContainerTag = $('<div></div>').addClass('popularPostContainer').append($atag);
		$liTag=$('<li></li>').append($popularContainerTag);
		$('#topPost-list').append($liTag);
	}
}




fn_rollToEx('slider', 'topPost-list');

	function fn_rollToEx(containerID, slideID){

		// 롤링할 객체를 변수에 담아둔다.
		var el = $('#'+containerID).find('#'+slideID);
		var lastChild;
		var speed = 3000;
		var timer = 0;

		el.data('prev', $('#'+containerID).find('.prev'));	//이전버튼을 data()메서드를 사용하여 저장한다.
		el.data('next', $('#'+containerID).find('.next'));	//다음버튼을 data()메서드를 사용하여 저장한다.
		el.data('size', el.children().outerWidth());		//롤링객체의 자식요소의 넓이를 저장한다.
		el.data('len', el.children().length);				//롤링객체의 전체요소 개수
		el.data('animating',false);

		el.css('width',el.data('size')*el.data('len'));		//롤링객체의 전체넓이 지정한다.

		//el에 첨부된 prev 데이타를 클릭이벤트에 바인드한다.
		el.data('prev').bind({
			click:function(e){
				e.preventDefault();
				movePrevSlide();
			}
		});

		//el에 첨부된 next 데이타를 클릭이벤트에 바인드한다.
		el.data('next').bind({
			click:function(e){
				e.preventDefault();
				moveNextSlide();
			}
		});

		function movePrevSlide(){
			if(!el.data('animating')){
				//롤링객체의 끝에서 요소를 선택하여 복사한후 변수에 저장한다.
				var lastItem = el.children().eq(-2).nextAll().clone(true);
				lastItem.prependTo(el);		//복사된 요소를 롤링객체의 앞에 붙여놓는다.
				el.children().eq(-2).nextAll().remove();	//선택된 요소는 끝에서 제거한다
				el.css('left','-'+(el.data('size')*1+'px'));	//롤링객체의 left위치값을 재설정한다.
			
				el.data('animating',true);	//애니메이션 중복을 막기 위해 첨부된 animating 데이타를 true로 설정한다.

				el.animate({'left': '0px'},'normal',function(){		//롤링객체를 left:0만큼 애니메이션 시킨다.
					el.data('animating',false);
				});
			}
			return false;
		}

		function moveNextSlide(){
			if(!el.data('animating')){
				el.data('animating',true);

				el.animate({'left':'-'+(el.data('size')*1)+'px'},'normal',function(){	//롤링객체를 애니메이션 시킨다.
					//롤링객체의 앞에서 요소를 선택하여 복사한후 변수에 저장한다.
					var firstChild = el.children().filter(':lt('+1+')').clone(true);
					firstChild.appendTo(el);	//복사된 요소를 롤링객체의 끝에 붙여놓는다.
					el.children().filter(':lt('+1+')').remove();	//선택된 요소를 앞에서 제거한다
					el.css('left','0px');	////롤링객체의 left위치값을 재설정한다.

					el.data('animating',false);
				});
			}
			return false;
		}

	}					