package com.lecture.finalproject.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;




import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mysql.jdbc.Connection;

public class JDBCMannager {
    private static Logger logger = LoggerFactory.getLogger(JDBCMannager.class);
    
    private static Connection  connection = null;
    
    private JDBCMannager(){};
    
    public static synchronized Connection getInstance() {

        if(connection == null) {
       
        	try{
        		Class.forName("com.mysql.jdbc.Driver");
        		connection = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/finalproject?useSSL=false&useUnicode=yes&amp;characterEncoding=UTF-8&amp;autoReconnect=true","root","root");
        	}catch (ClassNotFoundException e) {
        		System.out.println("드라이버를 찾을수없습니다 : "+e);
        	}
    		catch(SQLException e){
    			System.out.println(e.getMessage());
    		}           
        }
        
        return connection; 
    }
    
    
}
