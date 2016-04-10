/**
 * 
 */
  
    var map;
    var geocoder;
    var index = 0;
    var marker = null;
    var places=[];
    var infowindows=[];
    var circle;
    var labelIndex = 0;
    var currentIndex;
    var timer;
    
    var maleNum;
	var femaleNum;
	var friendNum;

    function initMap() {	
      map = new google.maps.Map(document.getElementById('map'), {
    	center: {lat: 36.566666, lng: 128.716660},
        mapTypeId: google.maps.MapTypeId.TERRAIN,
        zoom : 7	
      });
      
      // This event listener will call addMarker() when the map is clicked.
      map.addListener('click', function(event) {
    	  addClickMarker(event.latLng);
      });
    }
    

    function addClickMarker(location){
        resetMarker();
    	 var newMarker = new google.maps.Marker({
            position: location,
            map: map,
          //  label: labels[labelIndex++ % labels.length],
            icon : "http://maps.google.com/mapfiles/kml/paddle/blu-blank.png",
          }); 	
  
    	
    	 var newCircle = new google.maps.Circle({
  	        strokeColor: '#FF0000',
  	        strokeOpacity: 0.8,
  	        strokeWeight: 2,
  	        fillColor: '#FF0000',
  	        fillOpacity: 0.35,
  	        map: map,
  	        center: location,
  	        radius: 0,
  	        index : index
  	      });
    	  
    	  	
    	 newMarker.addListener('click',function(e){
            	//initialize current pushed marker's index
          		//setupRangeValue(circles[currentIndex].getRadius());
          		
        	  });
      
    	 newCircle.addListener('bounds_changed', function(e){
  	    	
      	  });
    	 
    	 marker = newMarker;
    	 circle = newCircle;
	 
    }
    
   	function setupRangeValue(value){
 		var rangeWithCurMarker = document.getElementById('rangeForm');
 		rangeWithCurMarker.value = (value / 1000) - 1;
   	}
   	
   	function setMapOnAll(map) {
   		
   		for(var i=0; i<infowindows.length; i++){
   			places[i].setMap(map);
   			infowindows[i].close();
   		}
   		
   		marker.setMap(map);
   		circle.setMap(null);
   	}
    // Shows any markers currently in the array.
    function showMarkers() {
      setMapOnAll(map);
    }

   	// Removes the markers from the map, but keeps them in the array.
   	function clearMarkers() {
   	  setMapOnAll(null);
   	}
    
    // Deletes all markers in the array by removing references to them.
    function resetMarker() {
    	if(marker)
    		clearMarkers();
      markers = null;
      circles = null;
      places=[];
      infowindows=[];
    }
    
    function rangeChanged(value){
    	updateRadius(circle,value * 1000);
    	updateMapZoomTimer(map,value)
    	
    }
    
    function updateMapZoomTimer(map,value){
    	clearTimeout(timer);
    	timer = setTimeout(function(){updateMapZoom(map,value)},1000);
    }
    
    function updateMapZoom(map,value){
    	if(value == 1)map.setZoom(15);
    	else if(value == 2)map.setZoom(14);
    	else if(value >= 3 && value <= 5)map.setZoom(13);
    	else if(value <= 9)map.setZoom(12);
    	else if(value <= 18)map.setZoom(11);
    	else if(value <= 35)map.setZoom(10);
    	else if(value <= 70)map.setZoom(9);
    	else if(value <= 140)map.setZoom(8);
    	else if(value <= 200)map.setZoom(7);
    	
    	map.setCenter(marker.position);
    }
    
    function updateRadius(circle, rad){
    	  circle.setRadius(rad);
    }
        
    function onClickSubmitBtn(){
    	
    	if(marker == null){
    		alert("select market please");
    		return;
    	}
    	
    	var placeNum = document.getElementById('numberForm').value;
    	var radius = document.getElementById('rangeForm').value;
    	var longitude = marker.getPosition().lng();
    	var latitude = marker.getPosition().lat();
    	
    	if(placeNum == '' || radius == 0){
    		alert('fill the blank');
    		return;
    	}
    	
    	var parameter = '?placeNum=' + placeNum +
    					'&radius=' + radius +
    					'&longitude=' + longitude +
    					'&latitude=' + latitude;
    	
    	console.log("1111111");
    	loadXMLDoc(parameter);
    }
       
    function loadXMLDoc(parameter)
    {
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.onreadystatechange=function()
        {
          if (xmlhttp.readyState==4 && xmlhttp.status==200) {
        	  var obj = JSON.parse(xmlhttp.responseText);
        	  console.log(obj);
        	  drop(obj);
          }
        }
     
		xmlhttp.open("GET", "/boot_test/ProcessServlet" + parameter,true);
		xmlhttp.send();
    }
   
  
    
    function drop(obj) {
    	var size = 0;
    	
    	
    	for (i in obj.places) 
    	    size++;
    	
   
    	for (var i = 0; i < size; i++) {
    	    addMarkerWithTimeout(obj.places[i], i * 200);
    	}	
    }

	function addMarkerWithTimeout(targetPlace, timeout) {
		
	  window.setTimeout(function() {	  
	  

	  var placeMarker = getPlaceMarker(targetPlace);
	  var infowindow = getInfoWindow(targetPlace); 
	
	   
	   google.maps.event.addListener(placeMarker, 'mouseover', function() {
	      infowindow.open(map,placeMarker); //map and marker are the variables defined previously   
	   });
	   
	   google.maps.event.addListener(infowindow, 'mouseout', function() {
		    infowindow.close();
		  });

	   infowindows.push(infowindow);
	   places.push(placeMarker);
	  }, timeout);
	  
	}
    
	function getInfoWindow(targetPlace){
		
		var content = '<div id="iw_container">' +
			'<div class="iw_title">' + targetPlace.name + '</div>' + 
			'<div class="iw_content">' + 	
			makeInfoWindowFriendContent(targetPlace) + 
			makeInfoWindowTotalContent()+
		'<div class="linker"><a href=' + path + '/detail?travlePostNumber=' + targetPlace.place_no + '>더보기</a></div>'+
		
		'</div>' +
		'</div>';
		
	   var infowindow = new google.maps.InfoWindow({
		   content : content
	   });
	   
	   
	   google.maps.event.addListener(infowindow, 'domready', function() {

		   // Reference to the DIV which receives the contents of the infowindow using jQuery
		   var iwOuter = $('.gm-style-iw');

	
		   var iwBackground = iwOuter.prev();
		   
		   
		
		   // Remove the background shadow DIV
		   iwBackground.children(':nth-child(2)').css({'display' : 'none'});

		   // Remove the white background DIV
		   iwBackground.children(':nth-child(4)').css({'display' : 'none'});
		   
		   var iwCloseBtn = iwOuter.next();

		// Apply the desired e	ffect to the close button
		iwCloseBtn.css({
			right :'-2px',top:'29px'
		  });
		
		});
		
	   
	   return infowindow;
	}
	

	
	function makeInfoWindowFriendContent(targetPlace){
	
		var array_keys = new Array();
		var result = '';
		
		var nameTag;
		var imgUrl;
		var genderTag;
		
		var infoTag;
		var imgTag;

		maleNum = 0;
		femaleNum = 0;
		friendNum = 0;
		
		for(var key in targetPlace.user_id_list){
			array_keys.push(key);
			console.log(key);
			friendNum++;
		}
			
		for(var i=0; i<array_keys.length; i++){
			nameTag = '<li><b>user ID : </b> :' + array_keys[i] + '</li>';
			infoTag = '<div class="friendInfo"><ul>' + nameTag + '</ul></div>'
			result += '<div class="friend">'+ infoTag + '</div>';
		}
		
		/*
		for(var key in friendsInfo){
					
			if(friendsInfo[key].gender == 'male')
				maleNum++;
			else if(friendsInfo[key].gender == 'female')
				femaleNum++;
			
			nameTag = '<li><b>Name</b> :' + friendsInfo[key].name + '</li>';
			genderTag = '<li><b>Gender</b> :' + friendsInfo[key].gender + '</li>';
			infoTag = '<div class="friendInfo"><ul>' + nameTag + genderTag + '</ul></div>'
			
			imgUrl = friendsInfo[key].photo; 
			imgTag = '<div class="friendPhoto"><img src="' + imgUrl + '"/></div>';
			 
			result += '<div class="friend">'+ imgTag + infoTag + '</div>';
			
			nameTag = '<li><b>Name</b> :' + friendsInfo[key].name + '</li>';
			genderTag = '<li><b>Gender</b> :' + friendsInfo[key].gender + '</li>';
			infoTag = '<div class="friendInfo"><ul>' + nameTag + genderTag + '</ul></div>'
			
			imgUrl = friendsInfo[key].photo; 
			imgTag = '<div class="friendPhoto"><img src="' + imgUrl + '"/></div>';
			 
			result += '<div class="friend">'+ imgTag + infoTag + '</div>';
			
			nameTag = '<li><b>Name</b> :' + friendsInfo[key].name + '</li>';
			genderTag = '<li><b>Gender</b> :' + friendsInfo[key].gender + '</li>';
			infoTag = '<div class="friendInfo"><ul>' + nameTag + genderTag + '</ul></div>'
			
			imgUrl = friendsInfo[key].photo; 
			imgTag = '<div class="friendPhoto"><img src="' + imgUrl + '"/></div>';
			 
			result += '<div class="friend">'+ imgTag + infoTag + '</div>';
			
			
		}
		*/
		
		result = '<div class="friendsContent iwBasicContent">' + result + '</div>';
		
		
		return result;
	}
	
	function makeInfoWindowTotalContent(){
		return '<div class="totalInfo"><B>Total number : </b>' + friendNum  + '</div>';
	}
	
	function getPlaceMarker(targetPlace){
		var position = {lat: targetPlace.latitude, lng: targetPlace.longitude};
		
		 var placeMarker =   new google.maps.Marker({
		      position: position,
		      map: map,
		      animation: google.maps.Animation.DROP
		    });
		
		 return placeMarker;
	}
	
	
  
