package com.lecture.finalproject.service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import com.lecture.finalproject.dao.Travelpost_db;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;

public class ServiceTravelParser {
    private static Logger logger = LoggerFactory.getLogger(ServiceTravelParser.class);
    public Travelpost_db tp_db = new Travelpost_db();
    public ModelTravelPost tp_M =new ModelTravelPost();
    public ModelLocation tp_L = new ModelLocation();
    public ModelImage img = new ModelImage();
    
    private String content;
    int no=0;
    String[] token;
    int no_add=0;
   
    private String make(String s)
    {
        return "'"+s+"'";
    }
    
    public void Parser(){
        
        try{
            new ServiceTravelParser().start();
            
        }catch (Exception e){
            e.printStackTrace();
        }
        tp_db.close();
    }
    
    private void start() throws Exception{
        
        URL url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=7vKWifdEnG3dyBZ2YSxA8ouVJFkoIhvF3jpUfIF7Av7d4mQopF%2B8BN9lXScuMQ4ejBY%2BlUR5fZMk64V1rvitLg%3D%3D &contentTypeId=&areaCode=&sigunguCode=&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=20&pageNo=1");
        URLConnection connection = url.openConnection();
        
        Document doc = parseXML(connection.getInputStream());
        NodeList descNodes = doc.getElementsByTagName("item");
        
        for(int i=0; i<descNodes.getLength();i++){
            
            for(Node node = descNodes.item(i).getFirstChild(); node!=null; node=node.getNextSibling()){ //ù��° �ڽ��� �������� ���������� ���� ������ ����
                
                
                if(node.getNodeName().equals("addr1"))
                {
                    
                 
                    tp_L.setAddress(node.getTextContent());
                    if(tp_L.getAddress()!=null)
                     {
                        no_add++;
                        System.out.println("<"+no_add+">");
                        token = tp_L.getAddress().split(" ");
                        tp_L.setCity1(token[0]);
                     
                    }
               }
                
                else if(node.getNodeName().equals("firstimage")){
                   img.setImage_url(node.getTextContent());
                	img.setImage_url(node.getTextContent());
                	}
                
                else if(node.getNodeName().equals("mapx")){
                    tp_L.setLatitude(node.getTextContent());
                    
                }
                else if(node.getNodeName().equals("mapy"))
                {
                    tp_L.setLongitude(node.getTextContent());
                    
                }
                else if(node.getNodeName().equals("overview"))
                {
                	content = node.getTextContent();
                	System.out.println(content);
                }
                
                else if(node.getNodeName().equals("title")){
                    no++;
                    tp_M.setTitle(node.getTextContent());
                    
                    System.out.println(no);
                    tp_db.insert("insert into travelpost_tb(travelPost_no,title,travelPost_date,view_count,like_count,comment_count,user_id) values("+no+","+make(tp_M.getTitle())+","+"now(),"+"0,0,0,"+make("khyunm91")+")");
                    tp_db.insert("insert into location_tb(city1,address,latitude,longitude,travelPost_no) values("+make(tp_L.getCity1())+","+make(tp_L.getAddress())+","+make(tp_L.getLatitude())+","+make(tp_L.getLongitude())+","+no+")");;
                    tp_db.insert("insert into image_tb(image_url, travelpost_no) values("+make(img.getImage_url())+","+no+")");
                   // tp_db.insert("insert into information_tb values('" + content + "'," + no +")");
                    tp_L.setAddress(null);
                    tp_L.setCity1(null);
                    tp_L.setCity2(null);
                    tp_L.setLongitude(null);
                    tp_L.setLatitude(null);
                    img.setImage_url(null);
                    System.out.println(content);
                }
            }
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