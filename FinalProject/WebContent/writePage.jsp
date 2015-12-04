<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*" %>
<%@ page import="javax.servlet.*" %>
<%@ page import="java.util.*" %>
<%@ page import="javax.servlet.http.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
            }

            .container > hr {
            margin: 60px 0;
            }

            /* Customize the navbar links to be fill the entire space of the .navbar */
            .navbar .navbar-inner {
            padding: 0;
            }

      .navbar .nav {
        margin: 0;
        display: table;
        width: 100%;
    
      }
      .navbar .nav li {
        display: table-cell;
        width: 1%;
        float: none;
      }
      .navbar .nav li a {
        font-weight: bold;
        text-align: center;
        border-left: 1px solid rgba(255,255,255,.75);
        border-right: 1px solid rgba(0,0,0,.1);
      }
     .navbar .nav li:first-child a {
        border-left: 0;
        border-radius: 3px 0 0 3px;
      }
      .navbar .nav li:last-child a {
        border-right: 0;
        border-radius: 0 3px 3px 0;
      }
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
		if (count > 1) { // ?꾩옱 Div媛? ?먭컻 ?댁긽?대㈃
			var addedDiv = document.getElementById("added_" + (--count));
			// 留덉?留됱쑝濡? ?앹꽦?? Div?? ID瑜? ?듯빐 Div媛앹껜瑜? 媛??몄샂

			while ( addedDiv.hasChildNodes() )
		    {
			    addedDiv.removeChild( addedDiv.firstChild );       
			   }
		addedFormDiv.removeChild(addedDiv);	
		} else { // 留덉?留? Div留? ?⑥븘?덈떎硫?
			document.baseForm.reset(); // Div ?댁슜 ??젣
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



</script>
    </head>
  <body>
 

    <div class="container">
        <div class="masthead">
            <div class="navbar">
                <div class="navbar-inner navbar-fixed-top">
                    <div class="container">
                        <ul class="nav">
                            <li><a href="#"><i
                                    class="icon-search"></i>Search</a></li>
                            <li><a href="mainpage.html"><i
                                    class="icon-home"></i></a></li>
                            <li><a href="concernRecommendPage.html"><i
                                    class="icon-star"></i></a></li>
                            <li><a href="mainpage.html">SCABTRS</a></li>
                            <li><a href="mypage.html"><i
                                    class="icon-user"></i></a></li>
                            <li><a href="writepage.html"><i
                                    class="icon-pencil"></i></a></li>
                            <li><a href="#"><i
                                    class="icon-gift"></i></a></li>
                            <li><a href="#"><i
                                    class="icon-align-justify"></i></a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
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
        <form action="ServiceWritePage" method="post" enctype="multipart/form-data">
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
                                      
                                         <input type="button" name="file_1" class="file_input_hidden"  />
                                      <img src="map.png" class="file_input_img_btn">
                                </div>
                                    </div>
                            
                               
                            </div>
                        </div>
                     </div>
                      <div class="span7" >
                           <div id="selectedFiles">
                                 
							
                            </div>
                            
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
        </form>


    </div>


   
    <script src="http://code.jquery.com/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

   
    </body>
</html>