<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>geocoder</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js">
</script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false">
</script>
<script type="text/javascript">
	$(document).ready(function() {
	    var latlng = new google.maps.LatLng(37.5640, 126.9751);
	    var myOptions = {
	  	      zoom : 12,
	  	      center : latlng,
	  	      mapTypeId : google.maps.MapTypeId.ROADMAP
	  	}
	    var map = new google.maps.Map(document.getElementById("map_canvas"), myOptions);
	    var marker = new google.maps.Marker({
			position : latlng, 
    		map : map
    	});
	    
	    var geocoder = new google.maps.Geocoder();
	    
	    google.maps.event.addListener(map, 'click', function(event) {
	    	var location = event.latLng;
	    	geocoder.geocode({
	    		'latLng' : location
	    	}, function(results, status){
	    		if( status == google.maps.GeocoderStatus.OK ) {
	    			$('#add').val(results[0].formatted_address);
	    			$('#lat').val(results[0].geometry.location.lat());
	    			$('#lng').val(results[0].geometry.location.lng());
	    		}
	    		else {
	    			alert("Geocoder failed due to: " + status);
	    		}
	    	});
	    	if( !marker ) {
	    		marker = new google.maps.Marker({
	    			position : location, 
		    		map : map
		    	});
	    	}
	    	else {
	    		marker.setMap(null);
	    		marker = new google.maps.Marker({
	    			position : location, 
		    		map : map
		    	});
	    	}
	    	map.setCenter(location);
	    });
	    
	    $("#address").focusout(function(){
	    	var address = $(this).val();
	    	if( address != '') {
	    		geocoder.geocode({
					'address' : address
				}, function(results, status){
					if( status == google.maps.GeocoderStatus.OK ) {
						$('#lat').val(results[0].geometry.location.lat());
						$('#lng').val(results[0].geometry.location.lng());
						$('#add').val(results[0].formatted_address);
						map.setCenter(results[0].geometry.location);
						if( !marker ) {
				    		marker = new google.maps.Marker({
				    			position : results[0].geometry.location, 
					    		map : map
					    	});
				    	}
				    	else {
				    		marker.setMap(null);
				    		marker = new google.maps.Marker({
				    			position :  results[0].geometry.location, 
					    		map : map
					    	});
				    	}
					}
					else {
						alert("Geocoder failed due to: " + status);
					}
				});
	    	}
	    });
	});
	
 
 
    function send() {
       
        window.opener.document.myform.receiver.value = document.myform.sender.value;
        window.opener.document.myform.latreceiver.value = document.myform.latsender.value;
        window.opener.document.myform.lngreceiver.value = document.myform.lngsender.value;
        
        window.close();
    }

</script>
</head>
<body>
<center>
<h3 >여행지를 클릭하세요 !</h3>
 <input id="address" type="textbox" value="서울" ></input>
<input id="submit" type="button" value="여행지 찾기">
<form name="myform">
	<table border="1">
	
		<tr>
			<td colspan="2"><div id="map_canvas" style="width: 1000px; height: 380px;"></div></td>
		</tr>
		<tr>
			<th width="100">위도</th>
			<td><input type="text" id="lat" name="latsender" value="" /></td>
			<td></td>
		</tr>
		<tr>
			<th>경도</th>
			<td><input type="text" id="lng" name="lngsender" value="" /></td>
		</tr>
		<tr>	
			<th>주소</th>
			 
			<td><input type="text" id="add" name="sender" value="" size="50"/></td>
		</tr>
	</table>
	
      
	
  <input type="button" value="선택완료!" onclick="send()"/>
          </center>
        </form>
</body>
</html>
