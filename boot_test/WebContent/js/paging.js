function goPage(page){

	var parameter = "&startPage=" + page +  "&pageNum=9";
	
	parameter = methodCheck() + LocationChecke() + parameter;
	
	console.log(parameter);
	
	loadXMLDoc(parameter);
}

function LocationChecke(){
	
	var LocationParameter='';
	
	$('input:checkbox').map(function(index,element){
		if($(element).is(':checked')){
			LocationParameter = LocationParameter + "&location=" + escape(encodeURIComponent(element.value)); 
		}
	})
	
	return LocationParameter;
}

function methodCheck(){
	var standard = null;
	
	$('input:checkbox').map(function(index,element){
		if($(element).is(':checked')){
			standard = "?method=listByLocation";
			return;
		}
	})	
	
	$(':button').map(function(index,element){
		if($(element).hasClass('active')){
			standard =  "?method=listBySortedLocation&standard=" + $(element).text();
			return;
		}
	})

	if(standard == null)
		standard = "?method=listPost";
	
	console.log(standard);	
		
	return standard;
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
