package com.lecture.finalproject.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;


public class ServiceURLConnectionDebuger {

	private String requestURL = "http://openapi.naver.com/search"
			+ "?key=8152d7942053a2eff881b1fe42cb5723"
			+ "&query=한옥마을"
			+ "&display=10"
			+ "&start=1"
			+ "&sort=sim"
			+ "&target=blog";
	
	private String tempURL = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=7vKWifdEnG3dyBZ2YSxA8ouVJFkoIhvF3jpUfIF7Av7d4mQopF%2B8BN9lXScuMQ4ejBY%2BlUR5fZMk64V1rvitLg%3D%3D &contentTypeId=&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=20&pageNo=1";
	public void test(){
		
		try{
		URL url = new URL(tempURL);
		URLConnection connection = url.openConnection();
		
		InputStreamReader isr = new InputStreamReader(connection.getInputStream());
		BufferedReader   br = new BufferedReader(isr);
	
	     String buf = null;
         while(true)
         {
             buf = br.readLine();
             if(buf == null) break;
             System.out.println(buf);
         }
		
		
		//NodeList descNodes = doc.getFirstChild()
		/*
		NodeList descNodes = doc.getElementsByTagName("link");
	      
		for(int i=1; i<descNodes.getLength(); i++){
			System.out.println(descNodes.item(i).getFirstChild());
		}
		*/
		}catch(Exception e){
			System.out.println(e.getMessage());
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