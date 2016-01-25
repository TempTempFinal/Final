<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Write page</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/bootstrap-responsive.min.css" rel="stylesheet"/>
        <style type="text/css">
            body {
            padding-top: 20px;
            padding-bottom: 60px;
        }
        
        
            /* Custom container */
            .container {
            margin: 0 auto;
            max-width: 1000px;
                margin-top: 40px;
            }

            .container > hr {
            margin: 60px 0;
            }

            /* Customize the navbar links to be fill the entire space of the .navbar */
            
    .textwidth{
    width:500px;
    }

    .textheight{
    height:50px;
    }
    
    .file_input_div {
    position: relative;
    width: 80px;
    height: 50px;
    overflow: hidden;
}

.file_input_img_btn {
    padding: 0 0 0 5px;
}

.file_input_hidden {
    font-size: 29px;
    position: absolute;
    right: 0px;
    top: 0px;
    opacity: 0;
    filter: alpha(opacity = 0);
    -ms-filter: alpha(opacity = 0);
    cursor: pointer;
}
        </style>
        
        <script
    src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

<script type="text/javascript">
	var count = 1;
	var selDiv = "";
	var cnt=0;
    var text="";
    var latitude ="";
	function addForm() {
		var addedFormDiv = document.getElementById("pre_set");
	    
		var addedDiv = document.createElement("div"); 
		addedDiv.id = "added_" + count;
		  
		addedDiv.innerHTML = document.getElementById("12").innerHTML; 
			
		   // $("#files" + count.toString()).val();
	  	    
		    
		addedFormDiv.appendChild(addedDiv); 
				
		  count++;
		  
		
		
	}
    function lat()
    {
    	
    }
	
	 function winopen(){
	        win = window.open('map.jsp', 'ot', 'width=500px, height=500px');
	       
	    }
	    function winmessage(msg){
	        var ab=win.document.getElementById('ab');
	       alert(ab);
	    }
	    function winclose(){
	        win.close();
	    }

	function delForm() {

		var addedFormDiv = document.getElementById("pre_set");
		if (count > 1) { 
			var addedDiv = document.getElementById("added_" + (--count));
		
			while ( addedDiv.hasChildNodes() )
		    {
			    addedDiv.removeChild( addedDiv.firstChild );       
			   }
		addedFormDiv.removeChild(addedDiv);	
		} else { 
			document.baseForm.reset();
		}
	}

	function previewFile() {
		 
		var preview = document.getElementById('image'); //selects the query named img
		var file = document.querySelector('input[type=file]').files[0]; //sames as here
		var reader = new FileReader();

		reader.onloadend = function() {
			preview.src = reader.result;
		}

		if (file) {
			reader.readAsDataURL(file); //reads the data as a URL
		} else {
			preview.src = "";
		}
	}





var storedFiles = [];

$(document).ready(function() {
	 $("#files").on("change",handleFileSelect);
         
	    
	    selDiv = $("#selectedFiles").attr('id',function(){
	    	return this.id + cnt;
	    });
	  

   $("#myForm").on("submit", handleForm);

    
    $("body").on("click", ".selFile", removeFile);
});


    
function handleFileSelect(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);
    var text = e.target.text;
    filesArr.forEach(function(f) {          

        if(!f.type.match("image.*")) {
            return;
        }
        storedFiles.push(f);
        
        var reader = new FileReader();
        reader.onload = function (e) {
               var html = "<div><img src=\"" + e.target.result + "\" data-file='"+f.name+"' class='selFile' title='Click to remove'>" + f.name + "<br clear=\"left\"/></div>";
           selDiv.append(html);
            
          
            
        }
        reader.readAsDataURL(f); 
    });
    
}


 
function handleForm(e) {
    e.preventDefault();
    var data = new FormData();
    
    for(var i=0, len=storedFiles.length; i<len; i++) {
        data.append('files', storedFiles[i]);   
    }
    
    
 
    
    
    var xhr = new XMLHttpRequest();
   xhr.open('GET', 'handler.cfm', true);
    
    xhr.onload = function(e) {
        if(this.status == 200) {
            console.log(e.currentTarget.responseText);  
            alert(e.currentTarget.responseText + ' items uploaded.');
        }
    }
    
    xhr.send(data);
}
   
function removeFile(e) {
    var file = $(this).data("file");
    for(var i=0;i<storedFiles.length;i++) {
        if(storedFiles[i].name === file) {
            storedFiles.splice(i,1);
            break;
        }
    }
    $(this).parent().remove();
}

function recieve(){
    var txt = "<font color='red'>자식창에서 받아온 값</font>";
    document.getElementById("process").innerHTML = txt;
    document.myform.receiver.value = newWindow.document.myform.sender.value;        
}




</script>
    </head>
  <body>
 	<nav id="mainNav">
 		<jsp:include page="commonPage/nav.jsp"></jsp:include>
 	</nav>
 	
 	

    <div class="container">
    
        <br>
        <div class="container">
            <div class="raw-fluid">
                <div class="span10 offset2">
                    <select class="span2">
                        <option>1</option>
                        <option>2</option>
                        <option>3</option>
                        <option>4</option>
                        <option>5</option>
                    </select> <br>
                </div>
            </div>
        </div>
        <form name="myform" action="ServiceWritePage" method="post" enctype="multipart/form-data">
        <div class="container">
            <div class="raw-fluid">
                <div class="span10 offset2">
                    <input class="textwidth textheight" type="text"
                        placeholder="Title" name = "istitle"> <br>
                </div>
               
            </div>
        </div>

        <br>
              <div class="container-fluid" id="pre_set">        
             <div class="row-fluid" id="12">
          
            <div class="row-fluid" >
                <div class="span2">
                    <div class="accordion"  >
                        <div class="accordion-group">
                           
                                <div class="accordion-inner raw-fluid" >

                               <div class="file_input_div" >
                               <input type="file"  class="file_input_hidden" id="files" name="file" multiple>
                               <img src="picture.png" class="file_input_img_btn">                                   
                                </div>
                                                                 
                                <div class="file_input_div">
                                    
                                    <script>
                                    function openNewWindow(){
                                        newWindow = window.open("map.jsp", "newWindow", "height=200, width=400, resizable=yes");        
                                    }
                                   


                                    </script>
                                         <input type="button" name="file_1" class="file_input_hidden" onclick="openNewWindow()"/>
                                      <img src="map.png" class="file_input_img_btn">
                                     
                                </div>
                                    </div>
                            
                               
                            </div>
                        </div>
                     </div>
                      <div class="span7" >
                           <div id="selectedFiles">
                                 
							
                            </div>
                             <input  type="text" name="receiver" class="textwidth textheight"  placeholder="Location-위치표시를 클릭하세요 !" >
                              <input  type="hidden" name="latreceiver" size="0">
                               <input  type="hidden" name="lngreceiver" size="0">
                            <textarea class="textwidth" name="istext" rows="10"  id ="abc"></textarea>
                            
                            </button>
                    </div>
                

            </div>
        </div>
            </div>
            <div class="container">
                <div class="raw-fluid">
                    <div class="span10 offset5">
                        <input type="hidden" name="count" value="0">
                        <button class="btn btn-large align-center"
                            type="button" onclick="addForm()">+</button>
                          
                        <button class="btn btn-large align-center" type="button" onclick="delForm()">
                            <i class="icon-trash"></i>
                        </button>

                    </div>
                </div>
                <br>
                <br>
                 <br>
                 <br>
                <br>
                 <br>
                <center>
   
                
          <input type="submit" >
          </center>
          
                 <dt id="my_name">로그인 해주세요  </dt>
		<input type="hidden" id="my_id" name="userid_Num">

          
        </form>

  

    </div>

  
       
   
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

 <script type="text/javascript">
	 
			var user_id = '<%=(String)request.getAttribute("userId")%>';
			var userName = '<%=(String)request.getAttribute("userName")%>';
			
		if(user_id!== "" && userName !== "null"){
			$("#my_id").empty().val(user_id)
			$("#my_name").empty().text(userName);
		}
		
	    </script>
    </body>
</html>