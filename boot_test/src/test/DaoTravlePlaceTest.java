package test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.dao.JDBCMannager;
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelFrontTravlePost;
import com.lecture.finalproject.model.ModelHash;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.repository.SearchHelper;

import Komoran.Preprocessor;

public class DaoTravlePlaceTest {

	@Test
	public void test() {
		DaoTravlePlace one = new DaoTravlePlace();
		
		
		
		
		

		int a = one.getLikeState(1, "1493501562");
		
		System.out.println(a);
	}
	
    private static Connection connection = JDBCMannager.getInstance();
    private static SearchHelper searchHelper = SearchHelper.getInstance();
   
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
	
	@Test
	public void test1(){
    	
    	int result = 0;
    	
    	try{
    		
    		String query = "insert into user_tb value(?,?,?,?)";     // sql 쿼리
    		
    		pstmt = connection.prepareStatement(query);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.
    		pstmt.setString(1,"박정");
    		pstmt.setString(2,"박정");
    		pstmt.setString(3,"박정");
    		pstmt.setBoolean(4,true);
    		
    		result = pstmt.executeUpdate();
    	
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}


		
	}
}
