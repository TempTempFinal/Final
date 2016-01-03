/**
 * 
 */







$(function(){	
	
		var temp;
		$('#search_input').keydown(function(evnet){
			temp = $(this).val();
			if(temp.substring(0,1) == '#')
				$('#hiddenMethod').val('listByHashTag');
			else
				$('#hiddenMethod').val('listBySeachWord');
		});
		
			var path = '<%=request.getContextPath()%>';

			$(".col-3").map(function(index,element){
				var temp = $(element).children('a');
				
				if($(temp).text() == '낚시'){
					
					$(element).css('backgroundImage','url(concernImg/fishing.png)');
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '단풍'){
					$(element).css('backgroundImage','url(concernImg/maple.jpg)');		
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '등산'){
					console.log('등산');
					$(element).css('backgroundImage','url(concernImg/climing.jpg)');	
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '레저'){
		
					$(element).css('backgroundImage','url(concernImg/leisure.jpg)');
					$(element).css('backgroundSize','cover');
				}
				else if($(temp).text() == '미식'){
					$(element).css('backgroundImage','url(concernImg/food.jpg)');	
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '바다'){
					$(element).css('backgroundImage','url(concernImg/ocean.jpg)');	
					$(element).css('backgroundSize','cover');
				}
				else if($(temp).text() == '섬'){
					$(element).css('backgroundImage','url(concernImg/island.jpg)');		
					$(element).css('backgroundSize','cover');
					}
				else if($(temp).text() == '캠핑'){
					$(element).css('backgroundImage','url(concernImg/camping.jpg)');		
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '축제'){
					$(element).css('backgroundImage','url(concernImg/fest.jpg)');		
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '역사'){
					$(element).css('backgroundImage','url(concernImg/history.jpg)');		
					$(element).css('backgroundSize','cover');}
				else if($(temp).text() == '도심'){
					$(element).css('backgroundImage','url(concernImg/city.jpg)');		
					$(element).css('backgroundSize','cover');}
			});
		})