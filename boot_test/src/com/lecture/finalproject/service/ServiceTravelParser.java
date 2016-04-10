package com.lecture.finalproject.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;

public class ServiceTravelParser {

  private String serviceKey = "7vKWifdEnG3dyBZ2YSxA8ouVJFkoIhvF3jpUfIF7Av7d4mQopF%2B8BN9lXScuMQ4ejBY%2BlUR5fZMk64V1rvitLg%3D%3D";
  private String[] contentTypeIds ={"12","28","25"};
  private int[] eachContentIdRowsArr={0,0,0,0};
  
  private int[] pageNumArr={680,128,57};
  
  private List<String> contentIdList;
  
  private DaoTravlePlace db;
  
  
  /*
   * nature : 12
   * leisureAndSport : 28
   * travelCourse : 25
   */
  
  public ServiceTravelParser() {
	super();
	contentIdList = new ArrayList<String>();
	db = new DaoTravlePlace();
}

  
 
  public void parseTravelPostList(){
	 
	Date dt = new Date();
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd, hh:mm:ss a"); 

	 
	
	 ModelTravelPost post = new ModelTravelPost();
	 ModelLocation location =  new ModelLocation();
	 ModelImage image =  new ModelImage();
	 int no = 1;
	 boolean isHaveImage1 = false;
	 
	 String[] token;
	  
	  try {
		  for(int i=0; i<contentTypeIds.length; i++){
			  //pageNum<=pageNumArr[i]
			  for(int pageNum=1; pageNum<=75; pageNum++){
			  
				URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?"
				  		+ "ServiceKey=" + serviceKey + "&contentTypeId=" + contentTypeIds[i] + "&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&"
				  		+ "MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=3&pageNo=" + pageNum);
			
				URLConnection connection = url.openConnection();      
			    Document doc = parseXML(connection.getInputStream());
			  
		        NodeList descNodes = doc.getElementsByTagName("item");
		        
		        for(int j=0; j<descNodes.getLength(); j++){
		        	
		        	//item 하나를 가져와서
		        	NodeList itemChildNodes = descNodes.item(j).getChildNodes();
		        	//item의 childnode의 길이를 구한뒤 하나하나 돌자!
		        	for(int q=0; q<itemChildNodes.getLength(); q++){
		        		
		        		//set location address and add city1
		        		if(itemChildNodes.item(q).getNodeName().equals("addr1")){
		        			location.setAddress(itemChildNodes.item(q).getTextContent());
		        			
		        		
		        			if(location.getAddress() !=null)
		                     {
		                        token = location.getAddress().split(" ");
		                        location.setCity1(token[0]);
		                    }
		        		}
		        		else if(itemChildNodes.item(q).getNodeName().equals("firstimage")){
		        				image.setImage_url(itemChildNodes.item(q).getTextContent());	  
		        				isHaveImage1 = true;
		        		}
		        		else if(itemChildNodes.item(q).getNodeName().equals("mapx")){
		        			location.setLongitude(itemChildNodes.item(q).getTextContent());
		        			
		        		}
		        		else if(itemChildNodes.item(q).getNodeName().equals("mapy")){
		        			location.setLatitude(itemChildNodes.item(q).getTextContent());

		        		}	else if(itemChildNodes.item(q).getNodeName().equals("contentid")){
		        			contentIdList.add(itemChildNodes.item(q).getTextContent());
		        			
		        			if(i == 0)
		        				eachContentIdRowsArr[1]++;
		        			else if(i == 1)
		        				eachContentIdRowsArr[2]++;
		        			else if(i == 2)
		        				eachContentIdRowsArr[3]++;
		        			
		        		}	else if(itemChildNodes.item(q).getNodeName().equals("title")){
		        			post.setTitle(itemChildNodes.item(q).getTextContent());		        			
		        		}
		        	}
		        	
		        	
		        	
		        	post.setComment_count(0);
		        	post.setLike_count(0);
		        	post.setTravelPost_date(sdf.format(dt).toString());
		        	post.setUser_id("khyunm91");
		        	post.setView_count(0);
		        	post.setTravelPost_no(no);
		        	
		        	location.setCity2("null");
		        	location.setTravelPost_no(no);
		        	
		        	image.setTravelPost_no(no);
		        	
		        	if(isHaveImage1 == false)
		        		image.setImage_url("img/readyImage.jpg");	        	
		        	isHaveImage1 = false;

		        	db.insertTravelPost(post);
		        	db.insertTravelLocation(location);
		        	db.insertTravelImage(image);
		        	
		        	no++;
			        }  
			  }
		  }
		  
		  eachContentIdRowsArr[2] += eachContentIdRowsArr[1];
		  eachContentIdRowsArr[3] += eachContentIdRowsArr[2];
		  
	  } catch (MalformedURLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }catch ( IOException e ) {
		  // TODO: handle exception
	  } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  
	  
	  System.out.println("test");
  }
  
  public void parseTravelPostInformation(){
	  
	  ModelInformation info = new ModelInformation();
	  PostUploaderHelper uploaderHelper = new PostUploaderHelper();
	  int subNo = 1;
	  String content;
	  
	  
	  try {
		  for(int i=0; i<contentTypeIds.length; i++){
			  for(int p=eachContentIdRowsArr[i]; p<eachContentIdRowsArr[i+1]; p++){
				  
				  URL url = new URL(" http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?"
						  + "ServiceKey=" + serviceKey + "&contentTypeId=" + contentTypeIds[i] + "&contentId=" + contentIdList.get(p) + "&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&"
						  + "defaultYN=Y&firstImageYN=Y&areacodeYN=Y&catcodeYN=Y&addrinfoYN=Y&mapinfoYN=Y&overviewYN=Y&transGuideYN=Y");

				  URLConnection connection = url.openConnection();      
				  Document doc = parseXML(connection.getInputStream());

				  NodeList descNodes = doc.getElementsByTagName("item");

				  for(int j=0; j<descNodes.getLength(); j++){

					  //item 하나를 가져와서
					  NodeList itemChildNodes = descNodes.item(j).getChildNodes();

					  //item의 childnode의 길이를 구한뒤 하나하나 돌자!
					  for(int q=0; q<itemChildNodes.getLength(); q++){
						  if(itemChildNodes.item(q).getNodeName().equals("overview")){
							  content = itemChildNodes.item(q).getTextContent();
							  
							  info.setTravel_content(content);
							  info.setTravelPost_no(subNo);
							  db.insertTravelInformation(info);
							  
							  uploaderHelper.extractOnePostFeature(content, subNo);
							  subNo++;
						  }
					  }  
					 
				  }
			  }
		  }	  
	  } catch (MalformedURLException e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }catch ( IOException e ) {
		  // TODO: handle exception
	  } catch (Exception e) {
		  // TODO Auto-generated catch block
		  e.printStackTrace();
	  }
	  
  }
  

	private Document parseXML(InputStream stream) throws Exception{
        
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        
        try{
            
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            
            doc = objDocumentBuilder.parse(stream);
            
        }catch(Exception ex){
            throw ex;
        }       
        
        return doc;
    }
    
}