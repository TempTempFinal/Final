package com.lecture.finalproject.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

import com.lecture.finalproject.model.ModelComment;
import com.lecture.finalproject.model.ModelCommentList;
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
import com.lecture.finalproject.service.weightHelper;

import Komoran.Preprocessor;


public class DaoTravlePlace implements IDao{
    
  
    private static Connection connection = JDBCMannager.getInstance();
    private static SearchHelper searchHelper = SearchHelper.getInstance();
    Preprocessor komoran = new Preprocessor();
   
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement pstmt = null;
    
    
    @Override
    public int getTravelPostCount() {
    	
    	int result = 0;
    	
    	try{
    		String query = "select count(*) from travelpost_tb";
    		pstmt = connection.prepareStatement(query);
    		
    		rs = pstmt.executeQuery();
    		rs.next();
    		
    		result = rs.getInt("count(*)");
    			
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    	
    	return result;
    }
    
    public ModelTravelPost getUserWritePost(String user_id) {
    	ModelTravelPost result = null;
    	
    	try{
    		String query = "select *from traelpost_tb where user_id = ?";
    		
    		pstmt = connection.prepareStatement(query);
    		pstmt.setString(2, user_id);
    		  while (rs.next()) {
    			  result = new ModelTravelPost(rs.getInt("travelPost_no"), rs.getString("title"), rs.getString("travelPost_date"), rs.getInt("view_count"), rs.getInt("like_count"), rs.getInt("comment_count"),rs.getString("user_id"));
              }
    		
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    	
    	
    	return result;
    }
    	
    	
    
    
    @Override
    public ModelTravelPost getTravelPostOne(int travelPost_no) {
    	// TODO Auto-generated method stub
    	ModelTravelPost result = null;
    	
    	try{
    		String query = "select * from travelpost_tb where travelPost_no = ?";
    		
    		pstmt = connection.prepareStatement(query);
    		pstmt.setInt(1, travelPost_no);
    		
			rs = pstmt.executeQuery();
				
    		while (rs.next()) {
  			  result = new ModelTravelPost(rs.getInt("travelPost_no"), rs.getString("title"), rs.getString("travelPost_date"), rs.getInt("view_count"), rs.getInt("like_count"), rs.getInt("comment_count"),rs.getString("user_id"));
            }   			
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    	
    	return result;
    }
    
    @Override
    public ModelInformation getInformation(int travelPost_no) {
    	ModelInformation result = null;
    	
    	try{
    		String query = "select * from information_tb where travelPost_no = ?";
    		
    		pstmt = connection.prepareStatement(query);
    		pstmt.setInt(1, travelPost_no);
    		
    		rs = pstmt.executeQuery();
    		
    		while (rs.next()) {
  			  result = new ModelInformation(rs.getString("travel_content"), rs.getInt("travelPost_no"), rs.getDouble("travelSentiment"));
            }
    		
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    	
    	return result;
    }
    
    @Override
    public int updateSyncState(String user_id) {
    	
    	int result = 0;
    	
    	try{
    		String query = "UPDATE user_tb SET sync=? where user_id =?";
    		
    		pstmt = connection.prepareStatement(query);
    		pstmt.setInt(1, 1);
    		pstmt.setString(2, user_id);
    				
    		result = pstmt.executeUpdate();
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    	
    	return result;
    }
    
    
    @Override
    public ModelUser getUserInfo(String user_id) {
    	
    	ModelUser result = null;
    	
    	try{
    		String query = "select * from user_tb where user_id = '" + user_id + "'";
    		
    		st = connection.createStatement();
    		rs = st.executeQuery(query);
    		
    		  while (rs.next()) {
    			  result = new ModelUser(rs.getString("user_id"),rs.getString("name"),rs.getString("img_url"),rs.getBoolean("sync"));
              }
    		
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    	
    	
    	return result;
    }
    
    @Override
    public int insertUserInfo(ModelUser user) {
    	// TODO Auto-generated method stub
    	
    	int result = 0;
    	
    	try{
    		
    		String query = "insert into user_tb value(?,?,?,?)";     // sql 쿼리
    		pstmt = connection.prepareStatement(query);                          // prepareStatement에서 해당 sql을 미리 컴파일한다.
    		pstmt.setString(1,user.getUser_id());
    		pstmt.setString(2,user.getName());
    		pstmt.setString(3,user.getImg_url());
    		pstmt.setBoolean(4,user.isSync());
    		
    		result = pstmt.executeUpdate();
    	
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}

    	return result;
    }
    
    @Override
    public int getUserCount(String user_id) {
    	// TODO Auto-generated method stub
    	
    	int result = 0;
    	
    	try{
    		String query = "select count(*) from user_tb where user_id = ?";
    		
    		pstmt = connection.prepareStatement(query);
    		pstmt.setString(1, user_id);
    		
    		rs = pstmt.executeQuery();
    		rs.next();
    		result = rs.getInt(1);
    		
    	}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
    		
      	return result;
      	
    }
    
    @Override
    public List<ModelFrontTravlePost> getFrontTravlePostListByLocation(String[] location,int startPage, int pageNum) {
    	// TODO Auto-generated method stub
    	
		String str="";
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		
		for(int i=0;i<location.length; i++)
			if(i == 0)
				str = str + "city1 like '" + location[i] +"%'";
			else
				str = str + " or " +  "city1 like '" + location[i] +"%'";
		
		
		try{
    	 String query = "select image_url,travelPost_no, title,view_count, like_count, comment_count, address" +
		  			" from image_tb natural join "
		  				+ "(travelpost_tb  natural join location_tb) where " + str+
		  						" group by travelPost_no" + 
		  							" limit ?,?";
    	 
    	 pstmt = connection.prepareStatement(query);
    	 pstmt.setInt(1, (startPage - 1) * pageNum);
    	 pstmt.setInt(2, pageNum);
         rs = pstmt.executeQuery();
               
         while (rs.next()) {
       	  ModelFrontTravlePost one = new ModelFrontTravlePost();
   	  		String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
   	  		one.setTravelPost_no(rs.getInt("travelPost_no"));
   	  		one.setImage_url(image_url);
             one.setAddress(rs.getString("address"));
             one.setComment_count(rs.getInt("comment_count"));
             one.setLike_count(rs.getInt("like_count"));
             one.setTitle(rs.getString("title"));
             result.add(one);
         }
       
     } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      
     }
    	
    	
	return result;
    }
    
    @Override
    public List<ModelFrontTravlePost> getFrontTravlePostListById(String user_id) {
    	// TODO Auto-generated method stub
    	String str="";
    	List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
    	
    	try{

    		String query = "select image_url,travelPost_no, title,view_count, like_count, comment_count, address" +
    				" from image_tb natural join" +
    				" ((select * from travelpost_tb where user_id = '" + user_id +"') as a  natural join location_tb)" + 
    				" group by travelPost_no";

    		System.out.println(query);
    		st = connection.createStatement();
    		rs =st.executeQuery(query);
    		if(st.execute(query))
    			rs = st.getResultSet();

    		while (rs.next()) {
    			ModelFrontTravlePost one = new ModelFrontTravlePost();
    		  	String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
              
    			one.setImage_url(image_url);
    			one.setTravelPost_no(rs.getInt("travelPost_no"));
    			one.setAddress(rs.getString("address"));
    			one.setComment_count(rs.getInt("comment_count"));
    			one.setLike_count(rs.getInt("like_count"));
    			one.setTitle(rs.getString("title"));
    			result.add(one);
    		}

    	} catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();

    	}
    	return result;
    }
    
    @Override
    public List<ModelFrontTravlePost> getFrontTravlePostListBySortedLocation(String[] location, String standard, int startPage, int pageNum) {
    	// TODO Auto-generated method stub
    	
		String str="";
		
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		
		for(int i=0;i<location.length; i++)
			if(i == 0)
				str = str + " where city1 like '" + location[i] +"%'";
			else
				str = str + " or " +  "city1 like '" + location[i] +"%'";
		
		if(standard.equalsIgnoreCase("new"))
			standard = "travelPost_date";
		else if(standard.equalsIgnoreCase("Like"))
			standard = "like_count";
		else if(standard.equalsIgnoreCase("Comment"))
			standard = "comment_count";
		
		try{
		
    	 String query = "select image_url,travelPost_no, title,view_count, like_count, comment_count, address" +
		  			" from image_tb natural join "
		  				+ "(travelpost_tb  natural join location_tb)" + str +
		  						" group by travelPost_no" +
		  							" ORDER BY " + standard + " DESC" +
		  								" limit ?,?";
    	 
    	 System.out.println(query);
    	 pstmt = connection.prepareStatement(query);
    	 pstmt.setInt(1, (startPage - 1) * pageNum);
    	 pstmt.setInt(2, pageNum);
    	 rs = pstmt.executeQuery();
  
         while (rs.next()) {
       	  ModelFrontTravlePost one = new ModelFrontTravlePost();
       	  String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
       	
             one.setImage_url(image_url);
             one.setTravelPost_no(rs.getInt("travelPost_no"));
             one.setAddress(rs.getString("address"));
             one.setComment_count(rs.getInt("comment_count"));
             one.setLike_count(rs.getInt("like_count"));
             one.setTitle(rs.getString("title"));
             result.add(one);
         }
       
     } catch (SQLException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
      
     }
    	
    	
	return result;
    }
    
    @Override
    public int getCountTravlePostByHashTagWrod(String searchWord) {
    	int result = 0;
    	
    	try{
    		String query = "select count(travelPost_no) from hash_tb where hashTag like ?";
    		
    		pstmt = connection.prepareStatement(query);
    		
    		pstmt.setString(1, "%"+searchWord+"%");
    
    		rs = pstmt.executeQuery();
    		rs.next();
    		
    		result = rs.getInt("count(travelPost_no)");
    	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         
        }
    	
    	return result;
    }
    
    @Override
    public int getCountTravlePostByConcern(String conern) {
    	// TODO Auto-generated method stub
    	
int result = 0;
    	
    	try{
    		String query = "select count(travelPost_no) from feature_tb where feature like ?";
    		
    		pstmt = connection.prepareStatement(query);
    		
    		pstmt.setString(1, conern);
    
    		rs = pstmt.executeQuery();
    		rs.next();
    		
    		result = rs.getInt("count(travelPost_no)");
    	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         
        }
    	
    	return result;
    }
    
    @Override
    public int getCountTravlePostBySearchWord(String searchWord) {
    	int result = 0;
    	
    	try{
    		String query = "select count(travelPost_no) from (select address, travelPost_no from location_tb) as a natural join (select title, travelPost_no from travelpost_tb) as b where address like ? or title like ?";
    		pstmt = connection.prepareStatement(query);
    		
    		pstmt.setString(1, "%"+searchWord+"%");
    		pstmt.setString(2, "%"+searchWord+"%");
    		
    		rs = pstmt.executeQuery();
    		rs.next();
    		
    		result = rs.getInt("count(travelPost_no)");
    	} catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         
        }
    	
    	return result;
    }
    
    //TODO 수정해야댐!! Hashtag에 관한걸로!
    @Override
    public List<ModelFrontTravlePost> getFrontTravlePostByHashTag(String hashTag, int startPage, int pageNum) {
    	
    	 List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
    	 
    	 try{
			 String query = "select * from" +
						" (select * from"+
								" (select * from"	+
									" (select travelPost_no from hash_tb where hashTag like ?) as a"+
									" natural join "+
									" (select travelPost_no, title, like_count, comment_count from travelpost_tb) as b) as c"+
								" natural join"+
								" (select travelPost_no, address from location_tb) as d) as e"+
							" natural join "+
							" (select travelPost_no, image_url from image_tb) as f"+
							" limit ?,?";
							
					 
					 
			 pstmt = connection.prepareStatement(query);
			 
			 pstmt.setString(1, "%"+hashTag+"%");
			 pstmt.setInt(2, (startPage - 1) * pageNum);
			 pstmt.setInt(3, pageNum);
			 rs = pstmt.executeQuery();

			 while (rs.next()) {
				 ModelFrontTravlePost one = new ModelFrontTravlePost();
				 String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
			       	
				 one.setImage_url(image_url);
				 one.setTravelPost_no(rs.getInt("travelPost_no"));
	             one.setAddress(rs.getString("address"));
	             one.setComment_count(rs.getInt("comment_count"));
	             one.setLike_count(rs.getInt("like_count"));
	             one.setTitle(rs.getString("title"));
	             result.add(one);
			 }

	    	}catch(SQLException e){
	    		System.out.println(e.getMessage());
	    	}
	    	
	    	return result;
    }
    
	@Override
	public List<ModelFrontTravlePost> getFrontTravlePostBySearchWord(String searchWord, int startPage, int pageNum) {
		// TODO Auto-generated method stub
		 List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		 List<String> searchKeyword = new ArrayList<String>();
		 List<String> searchLocation = null;
		
		 String temp = komoran.preProcess(searchWord);
		 searchKeyword = komoran.getMeaningWord(temp);	
		 searchLocation = searchHelper.findLocationQuery(searchKeyword);
		 
		 String locationQuery = getLocationQuery(searchLocation);
		 String searchAndQuery = getSearchAndQuery(searchKeyword);
		 String searchOrQuery = getSearchOrQuery(searchKeyword);
		 
		 try{
			 String query = "select * from" + 
					 		" ((select * from"+
					 		" (select travelPost_no, title, like_count, comment_count "+
					 		" from travelpost_tb "+ searchAndQuery + ") as a"+
					 		" natural join "+
					 		" (select * from image_tb) as b)"+
					 		" union"+
					 		" (select * from"+
					 		" (select travelPost_no, title, like_count, comment_count "+
					 		" from travelpost_tb "+ searchAndQuery + ") as a"+
					 		" natural join "+
					 		" (select * from image_tb) as b)) as c"+
					 		" natural join"+
					 		" (select address, travelPost_no from location_tb " + locationQuery + ") as d" +
					 		" limit ?,?";
					 
			 pstmt = connection.prepareStatement(query);
			 
			 pstmt.setInt(1, (startPage - 1) * pageNum);
			 pstmt.setInt(2, pageNum);
			  
			 rs = pstmt.executeQuery();

			 while (rs.next()) {
				 ModelFrontTravlePost one = new ModelFrontTravlePost();
				 String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
			       	
				 one.setImage_url(image_url);
				 one.setTravelPost_no(rs.getInt("travelPost_no"));
	             one.setAddress(rs.getString("address"));
	             one.setComment_count(rs.getInt("comment_count"));
	             one.setLike_count(rs.getInt("like_count"));
	             one.setTitle(rs.getString("title"));
	             result.add(one);
			 }

	    	}catch(SQLException e){
	    		System.out.println(e.getMessage());
	    	}
	    	
	    	return result;	
	}
	
	private String getLocationQuery(List<String> searchLocation){
		StringBuffer query = new StringBuffer();
		
		for(int i=0; i<searchLocation.size(); i++){
			if(i == 0)
				query.append(" where address like '" + searchLocation.get(i) + "'");
			else
				query.append(" or address like '" + searchLocation.get(i) +"'");
		}
		return query.toString();
	}
	
	private String getSearchAndQuery(List<String> searchKeyword){
		StringBuffer query = new StringBuffer();
		
		for(int i=0; i<searchKeyword.size(); i++){
			if(i == 0)
				query.append(" where title like '%" + searchKeyword.get(i) + "%'");
			else
				query.append(" and address like '%" + searchKeyword.get(i) +"%'");
		}
		return query.toString();
	}
	
	private String getSearchOrQuery(List<String> searchKeyword){
		StringBuffer query = new StringBuffer();
		
		for(int i=0; i<searchKeyword.size(); i++){
			if(i == 0)
				query.append(" where title like '" + searchKeyword.get(i) + "'");
			else
				query.append(" or address like '" + searchKeyword.get(i) +"'");
		}
		return query.toString();
	}
	
	
    
    @Override //startPage 시작 page : 1부터 시작, Pagenum : page당 갯수
    public List<ModelFrontTravlePost> getFrontTravlePostList(int startPage, int pageNum) {
    	
    	  List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
    	  
    	  try{
    		  String query = "select image_url,travelPost_no, title,view_count, like_count, comment_count, address" +
    				  			" from image_tb natural join "
    				  				+ "(travelpost_tb  natural join location_tb)" +
    				  						" group by travelPost_no" +
    				  							" limit ?,?";
    		  pstmt = connection.prepareStatement(query);
    		  pstmt.setInt(1, (startPage - 1) * pageNum);
    		  pstmt.setInt(2, pageNum);
    		  
    		  rs = pstmt.executeQuery();
                
              while (rs.next()) {
            	  ModelFrontTravlePost one = new ModelFrontTravlePost();
                  
            	  String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
            	
            	  System.out.println(image_url);
                  one.setImage_url(image_url);
                  one.setTravelPost_no(rs.getInt("travelPost_no"));
                  one.setAddress(rs.getString("address"));
                  one.setComment_count(rs.getInt("comment_count"));
                  one.setLike_count(rs.getInt("like_count"));
                  one.setTitle(rs.getString("title"));
                  result.add(one);
              }
            
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
           
          }
          return result;
    }
    
    public List<ModelFrontTravlePost> getUserTravlePostList(int startPage, int pageNum, String user_id) {
    	
  	  List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
  	  
  	  try{
  		  String query = "select image_url,travelPost_tb.travelPost_no, title,view_count, like_count, comment_count, address" +
  				  			" from image_tb natural join "
  				  				+ "(travelpost_tb  natural join location_tb)" +
  				  							"where user_id='" + user_id + "'";
  		  pstmt = connection.prepareStatement(query);
  		 
  		  
  		  rs = pstmt.executeQuery();
              
            while (rs.next()) {
          	  ModelFrontTravlePost one = new ModelFrontTravlePost();
                
          	  String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
          	
          	  System.out.println(image_url);
                one.setImage_url(image_url);
                one.setTravelPost_no(rs.getInt("travelPost_no"));
                one.setAddress(rs.getString("address"));
                one.setComment_count(rs.getInt("comment_count"));
                one.setLike_count(rs.getInt("like_count"));
                one.setTitle(rs.getString("title"));
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         
        }
        return result;
  }
    
    
    @Override
	public List<ModelFrontTravlePost> getFrontTravlePostList() {
		// TODO Auto-generated method stub
		
		  List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		  
		  try{
    		  String query = "select image_url,travelPost_no, title,view_count, like_count, comment_count, address, latitude, longitude" +
    				  			" from image_tb natural join "
    				  				+ "(travelpost_tb  natural join location_tb)" +
    				  						" group by travelPost_no";
    		  pstmt = connection.prepareStatement(query);
    		  rs = pstmt.executeQuery();
                
              while (rs.next()) {
            	  ModelFrontTravlePost one = new ModelFrontTravlePost();
                  
            	  String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
            	  double latitude = rs.getString("latitude").equals("null") == true ? 0 : Double.parseDouble(rs.getString("latitude"));
            	  double longitude = rs.getString("longitude").equals("null") == true ? 0 : Double.parseDouble(rs.getString("longitude"));
            	  
                  one.setImage_url(image_url);
                  one.setTravelPost_no(rs.getInt("travelPost_no"));
                  one.setAddress(rs.getString("address"));
                  one.setComment_count(rs.getInt("comment_count"));
                  one.setLike_count(rs.getInt("like_count"));
                  one.setTitle(rs.getString("title"));
                  one.setLatitude(latitude);
                  one.setLongitude(longitude);
                  result.add(one);
              }
            
          } catch (SQLException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
           
          }
          return result;
	}

  
    @Override
    public List<ModelTravelPost> getTravelPostList(String user_id) {
        // TODO Auto-generated method stub
      
        List<ModelTravelPost> result = new ArrayList<ModelTravelPost>();
        
        try {
            String query = "select * from travelpost_tb where user_id = '" + user_id + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelTravelPost one = new ModelTravelPost();
                
                one.setTravelPost_no(rs.getInt("travelPost_no"));
                one.setTitle(rs.getString("title"));
                one.setTravelPost_date(rs.getString("travelPost_date"));
                one.setView_count(rs.getInt("view_count"));           
                one.setLike_count(rs.getInt("like_count"));
                one.setComment_count(rs.getInt("comment_count"));
                one.setUser_id(rs.getString("user_id"));
                
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
         
        }
        return result;
    }
    
    @Override
    public ModelLocation getLocationOne(int travelPost_no) {
        // TODO Auto-generated method stub
        ModelLocation result = null;
        
        try {
            String query = "select * from location_tb where travelPost_no ='" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                result = new ModelLocation();
                
                result.setCity1(rs.getString("city1"));
                result.setCity2(rs.getString("city2"));
                result.setAddress(rs.getString("address"));
                result.setLatitude(rs.getString("latitude"));
                result.setLongitude(rs.getString("longitude"));
                result.setTravelPost_no(rs.getInt("travelPost_no"));
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          
        }
        return result;
        
    }
    

    @Override
    public List<ModelImage> getImageList(int travelPost_no) {
    
        List<ModelImage> result = new ArrayList<ModelImage>();
        
        try {
            String query = "select * from image_tb where travelPost_no = '" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelImage one  = new ModelImage();
                
                one.setImage_url(rs.getString("image_url"));
                one.setTravelPost_no(rs.getInt("travelPost_no"));       
                
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
       
        }
        return result;
    }
    
    @Override
    public List<ModelComment> getCommentList(int travelPost_no) {
        // TODO Auto-generated method stub
    
        List<ModelComment> result = new ArrayList<ModelComment>();
        
        try {
            String query = "select * from comment_tb where travelPost_no= '" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelComment one  = new ModelComment();
                
                one.setComment_no(rs.getInt("comment_no"));
                one.setCommentPost_date(rs.getString("commentPost_date"));
                one.setContent(rs.getString("content"));
                one.setUser_id(rs.getString("user_id"));  
                one.setImage_url(rs.getString("image_url"));
                one.setTravelPost_no(rs.getInt("travelPost_no"));       
                
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
      
        }
        return result;
    }
    
    @Override
    public int getLikeCount(int travelPost_no) {
        // TODO Auto-generated method stub
        
        int result = 0;
        
        try {
            String query = "select like_count from travelpost_tb where travelPost_no = " + travelPost_no +";";
          
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next())
                  result = rs.getInt("like_count");
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }	
        return result;
    }
    
    @Override
    public List<ModelInformation> getInformationList(int travelPost_no) {

        List<ModelInformation> result = new ArrayList<ModelInformation>();
        
        try {
            String query = "select * from information_tb where travelPost_no = '" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelInformation one = new ModelInformation();
                
                one.setTravel_content(rs.getString("travel_content"));
                one.setTravelPost_no(rs.getInt("travelPost_no"));
                
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

        }
    
        return result;
    }
    
    @Override
    public List<ModelHash> getHashList(int travelPost_no) {
        // TODO Auto-generated method stub

        List<ModelHash> result = new ArrayList<ModelHash>();
        
        try {
            String query = "select * from hash_tb where travelPost_no =  '" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelHash one = new ModelHash();
                
                one.setHashTag(rs.getString("hashTag"));
                one.setTravelPost_no(rs.getInt("travelPost_no"));
    
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
    
        }
    
        return result;
    
    }
    
    @Override
    public List<ModelFeature> getFeatureList(int travelPost_no) {
        // TODO Auto-generated method stub

        List<ModelFeature> result = new ArrayList<ModelFeature>();
        
        try {
            String query = "select * from feature_tb where travelPost_no= '" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelFeature one = new ModelFeature();
                
                one.setFeature(rs.getString("feature"));
                one.setTravelPost_no(rs.getInt("travelPost_no"));
    
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
       
        }
    
        return result;
        
    }
    
    @Override
    public List<ModelConcern> getConcernList(String user_id) {
        // TODO Auto-generated method stub
        
        List<ModelConcern> result = new ArrayList<ModelConcern>();
        
        try {
            String query = "select * from concern_tb where user_id ='" + user_id + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                ModelConcern one = new ModelConcern();
                
                one.setInterest(rs.getString("interest"));
                one.setUser_id(rs.getString("user_id"));
    
                result.add(one);
            }
          
        } catch (SQLException e) {
            // TODO Auto-generated catch block
        	e.printStackTrace();
 
        }
    
        return result;
    }
	    
    
	 @Override
	public int insertConcern(ModelConcern concern) {
		// TODO Auto-generated method stub
		 
		 int result = 0;
		 
		 try{
			 String query = "insert into concern_tb values(?,?)";
				 
			 pstmt = connection.prepareStatement(query);
			 pstmt.setString(1, concern.getInterest());
			 pstmt.setString(2,concern.getUser_id());
			 
			 result = pstmt.executeUpdate();
		
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
		 
		return result;
	}
	 
	@Override
	public List<ModelImage> getPopularLocationImage(int count) {
		// TODO Auto-generated method stub
		List<ModelImage> result = new ArrayList<ModelImage>();
		
		try{
			 String query = "select * from (select travelPost_no from travelpost_tb order by like_count desc limit ?) as a natural join image_tb group by travelPost_no";
			 pstmt = connection.prepareStatement(query);
			 pstmt.setInt(1, count);
			
			
	    	rs = pstmt.executeQuery();
	    		
	    	while (rs.next()) {
	    		ModelImage image = new ModelImage();
	    		image.setTravelPost_no(rs.getInt("travelPost_no"));
	    		image.setImage_url(rs.getString("image_url"));
	    		result.add(image);
	    	}
			 
		 }catch(SQLException e){
			 System.out.println(e.getMessage());
		 }
		 
		return result;
	}
	

	@Override
	public List<ModelInformation> getPostInformationTotalList() {
		
		List<ModelInformation> result = new ArrayList<ModelInformation>();
		
		try{
			String query = "select * from information_tb";
			pstmt = connection.prepareStatement(query);
			
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ModelInformation information = new ModelInformation(rs.getString("travel_content"), rs.getInt("travelPost_no"), rs.getDouble("sentiment"));
	    		result.add(information);
	    	}
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}

		return result;
	}
	
	
	@Override
	public int insertPostFeature(ModelFeature feature) {
		
		int result = 0;
		
		try{
			String query = "insert into feature_tb values(?,?)";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, feature.getTravelPost_no());
			pstmt.setString(2, feature.getFeature());
			
			result = pstmt.executeUpdate();
	
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	@Override
	public int insertPostHash(ModelHash hashTag) {
		
		int result = 0;
		
		try{
			String query = "insert into hash_tb values(?,?)";
		
			pstmt = connection.prepareStatement(query);
			
			pstmt.setString(1, hashTag.getHashTag());
			pstmt.setInt(2, hashTag.getTravelPost_no());
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}

	@Override
	   public List<ModelCommentList> getModelCommentList(int travelPost_no) {
	      // TODO Auto-generated method stub

	      List<ModelCommentList> result = new ArrayList<ModelCommentList>();

	      try {
	         String query = "select comment_no, commentPost_tb, content, user_id, image_url, travelPost_no, title, travelPost_data, view_count, like_count, comment_count"
	               + " from comment_tb natural join "
	               + " group by travelPost_no" + " limit ?,?";
	         
	         pstmt = connection.prepareStatement(query);
	         pstmt.setInt(1, travelPost_no);
	         
	         rs = pstmt.executeQuery(query);

	         while (rs.next()) {
	            ModelCommentList one = new ModelCommentList();

	            String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg"
	                  : rs.getString("image_url");

	            System.out.println(image_url);
	            one.setComment_no(rs.getInt("comment_no"));
	            one.setCommentPost_date(rs.getString("commentPost_date"));
	            one.setContent(rs.getString("content"));
	            one.setUser_id(rs.getString("user_id"));
	            one.setImage_url(image_url);
	            one.setTravelPost_no(rs.getInt("travelPost_no"));
	            one.setSentiment(rs.getDouble("sentiment"));
	            result.add(one);
	            
	            return result;
	         }

	      } catch (SQLException e) {
	         // TODO Auto-generated catch block
	         e.printStackTrace();

	      }
	      return result;
	   }
	   
	   @Override
	   public int insertCommentList(ModelComment commentList) {
	      // TODO Auto-generated method stub

	      int result = 0;

	      try {
	         String query = "insert into concern_tb values(?,?,?,?,?,?,?)";
	         pstmt = connection.prepareStatement(query);
	         pstmt.setInt(1, commentList.getComment_no());
	         pstmt.setString(2, commentList.getCommentPost_date());
	         pstmt.setString(3, commentList.getContent());
	         pstmt.setString(4, commentList.getUser_id());
	         pstmt.setString(5, commentList.getImage_url());
	         pstmt.setInt(6, commentList.getTravelPost_no());
	         pstmt.setDouble(7, commentList.getSentiment());
	         
	         rs = pstmt.executeQuery();
	      } catch (SQLException e) {
	         System.out.println(e.getMessage());
	      }
	      return result;
	   }

	   @Override
	public ModelUser getWriterInfo(int travelPost_no) {
		// TODO Auto-generated method stub
		
		   ModelUser user = null;

		   String query;
		   
		   try{
			   query = "select * from user_tb where user_id = (select user_id from travelpost_tb where travelPost_no = ?)";
			   pstmt = connection.prepareStatement(query);
			   pstmt.setInt(1, travelPost_no);
			   rs = pstmt.executeQuery();
			  
			   while (rs.next()) {
				   	user = new ModelUser();
		       
		            String image_url = rs.getString("img_url").equals("null") == true ? "img/notFound.jpg"
		                  : rs.getString("img_url");

		            user.setImg_url(image_url);
		            user.setName(rs.getString("name"));
		            user.setUser_id(rs.getString("user_id"));
		         }

	
		   }catch(SQLException e){
			   System.out.println(e.getMessage());
		   }
		   return user;
	}
	   
	 @Override
	public int insertTravelImage(ModelImage image) {
	 int result = 0;
		 
		 String query;
		 
		 try{
			 query = "insert into image_tb(image_url, travelpost_no)"
					 +" values(?,?)";
			 
			   pstmt = connection.prepareStatement(query);
			   
			   pstmt.setString(1, image.getImage_url());
			   pstmt.setInt(2, image.getTravelPost_no());
			  
			   result = pstmt.executeUpdate();
		 }catch(SQLException e){
			   System.out.println(e.getMessage());
		   }
		 
		return result;

	}
	 
	 @Override
	public int insertTravelPost(ModelTravelPost post) {
		// TODO Auto-generated method stub
		 
		 int result = 0;
		 
		 String query;
		 
		 try{
			 query = "insert into travelpost_tb(travelPost_no,title,travelPost_date,view_count,like_count,comment_count,user_id)"
					 +" values(?,?,?,?,?,?,?)";
			 
			   pstmt = connection.prepareStatement(query);
			   pstmt.setInt(1, post.getTravelPost_no());
			   pstmt.setString(2, post.getTitle());
			   pstmt.setString(3, post.getTravelPost_date());
			   pstmt.setInt(4, post.getView_count());
			   pstmt.setInt(5, post.getLike_count());
			   pstmt.setInt(6, post.getComment_count());
			   pstmt.setString(7, post.getUser_id());
   
			   result = pstmt.executeUpdate();
			  
		 }catch(SQLException e){
			   System.out.println(e.getMessage());
		   }
		 
		return result;
	}
	 
	 @Override
	public int insertTravelLocation(ModelLocation location) {
		// TODO Auto-generated method stub
		 int result = 0;
		 
		 String query;
		 
		 try{
			 query = "insert into location_tb(city1,address,latitude,longitude,travelPost_no)"
					 +" values(?,?,?,?,?)";
			 
			   pstmt = connection.prepareStatement(query);
			   
			   pstmt.setString(1, location.getCity1());
			   pstmt.setString(2, location.getAddress());
			   pstmt.setString(3, location.getLatitude());
			   pstmt.setString(4, location.getLongitude());
			   pstmt.setInt(5, location.getTravelPost_no());
			   
			   result = pstmt.executeUpdate();
			  
		 }catch(SQLException e){
			   System.out.println(e.getMessage());
		   }
		 
		return result;
	}
	 
	@Override
	public int insertTravelInformation(ModelInformation info) {
		// TODO Auto-generated method stub
		
		int result = 0;

		String query;

		try{
			query = "insert into information_tb(travel_content,travelPost_no)"
					+ " values(?,?)";

			pstmt = connection.prepareStatement(query);

			pstmt.setString(1, info.getTravel_content());
			pstmt.setInt(2, info.getTravelPost_no());

			result = pstmt.executeUpdate();

		}catch(SQLException e){
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	@Override
	public List<ModelFrontTravlePost> getFrontTravelPostByConcern(String concern, int startPage, int pageNum) {
		// TODO Auto-generated method stub
		 
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>(); 
		String query;
		
		try{
			query = "select * from" +
					" (select * from" +
					" (select * from"+
					" (select travelPost_no from feature_tb where feature = ?) as a"+
					" natural join"+
					" (select travelPost_no, address from location_tb) as b) as c"+
					" natural join"+
					" (select travelPost_no, image_url from image_tb) as d) as e"+
					" natural join"+
					" (select travelPost_no, title, like_count, comment_count from travelpost_tb) as f"+
					" limit ?,?";

			pstmt = connection.prepareStatement(query);
			 
			 pstmt.setString(1, concern);
			 pstmt.setInt(2, (startPage - 1) * pageNum);
			 pstmt.setInt(3, pageNum);
			 rs = pstmt.executeQuery();

			 while (rs.next()) {
				 ModelFrontTravlePost one = new ModelFrontTravlePost();	
				 one.setImage_url(rs.getString("image_url"));
				 one.setTravelPost_no(rs.getInt("travelPost_no"));
	             one.setAddress(rs.getString("address"));
	             one.setComment_count(rs.getInt("comment_count"));
	             one.setLike_count(rs.getInt("like_count"));
	             one.setTitle(rs.getString("title"));
	             result.add(one);
			 }

	    	}catch(SQLException e){
	    		System.out.println(e.getMessage());
	    	}
	    	
	    	return result;
	}
	
	@Override
	public List<ModelFeature> getFeatureGroupList() {
	
		List<ModelFeature> result = new ArrayList<ModelFeature>();
		ModelFeature temp = null;
		
		try{
			String query = "select feature from feature_tb group by feature";
			
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next())
			{
				temp = new ModelFeature();
				temp.setFeature(rs.getString("feature"));
				result.add(temp);
			}
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
			
		return result;
	}
	
	@Override
	public List<ModelFrontTravlePost> getTopFrontTravelPostByCategory(String category) {
	
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		ModelFrontTravlePost post = null;
		
		try{
			
			String query = "select * from" +
					" (select * from" +
					" (select * from"+
					" (select travelPost_no from feature_tb where feature = ?) as a"+
					" natural join"+
					" (select * from image_tb) as b) as c"+
					" natural join"+
					" (select travelPost_no, address, longitude, latitude from location_tb) as d) as e"+
					" natural join "+
					" (select travelPost_no, title, like_count, comment_count from travelpost_tb) as f"+
					" order by like_count desc limit 0,9";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, category);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				post = new ModelFrontTravlePost();
	   	  		
				String image_url = rs.getString("image_url").equals("null") == true ? "img/readyImage.jpg" : rs.getString("image_url");
	   	  		post.setTravelPost_no(rs.getInt("travelPost_no"));
	   	  		post.setImage_url(image_url);
	            post.setAddress(rs.getString("address"));
	            post.setLatitude(Double.parseDouble(rs.getString("latitude")));
	            post.setLongitude(Double.parseDouble(rs.getString("longitude")));
	            post.setComment_count(rs.getInt("comment_count"));
	            post.setLike_count(rs.getInt("like_count"));
	            post.setTitle(rs.getString("title"));
	            result.add(post);
			}
			
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
			
		return result;
		
	}
	
	@Override
	public List<ModelFrontTravlePost> getTopFrontTravelPostByAllCategory() {
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		ModelFrontTravlePost post = null;
		
		try{
			
			String query = "select * from" +
					" (select * from" +
					" (select * from image_tb) as a"+
					" natural join"+
					" (select travelPost_no, address, longitude, latitude from location_tb) as b) as c"+
					" natural join"+
					" (select travelPost_no, title, like_count, comment_count from travelpost_tb) as d"+
					" order by like_count desc limit 0,9";
		
			
			pstmt = connection.prepareStatement(query);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				post = new ModelFrontTravlePost();
	   	  		
				String image_url = rs.getString("image_url").equals("null") == true ? "img/readyImage.jpg" : rs.getString("image_url");
	   	  		post.setTravelPost_no(rs.getInt("travelPost_no"));
	   	  		post.setImage_url(image_url);
	            post.setAddress(rs.getString("address"));
	            post.setLatitude(Double.parseDouble(rs.getString("latitude")));
	            post.setLongitude(Double.parseDouble(rs.getString("longitude")));
	            post.setComment_count(rs.getInt("comment_count"));
	            post.setLike_count(rs.getInt("like_count"));
	            post.setTitle(rs.getString("title"));
	            result.add(post);
			}
			
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
			
		return result;
	}
	
	@Override
	public List<ModelFrontTravlePost> getPopularFrontTravelPostByAllCategory(int startPage, int pageNum) {
		
		List<ModelFrontTravlePost> temp = new ArrayList<ModelFrontTravlePost>();
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		weightHelper wHelper = new weightHelper();
		ModelFrontTravlePost post = null;
		
		
		try{
			
			String query = "select * from" +
					" (select * from" +
					" (select * from" +
					" (select * from image_tb) as a"+
					" natural join"+
					" (select travelPost_no, senti_positive, senti_negative, search_count, popularity from information_tb) as b) as c"+
					" natural join"+
					" (select travelPost_no, address, longitude, latitude from location_tb) as d) as e"+
					" natural join"+
					" (select travelPost_no, title, like_count, comment_count from travelpost_tb) as f";
		
			
			pstmt = connection.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				post = new ModelFrontTravlePost();
	   	  		
				String image_url = rs.getString("image_url").equals("null") == true ? "img/readyImage.jpg" : rs.getString("image_url");
	   	  		post.setTravelPost_no(rs.getInt("travelPost_no"));
	   	  		post.setImage_url(image_url);
	            post.setAddress(rs.getString("address"));
	            post.setLatitude(Double.parseDouble(rs.getString("latitude")));
	            post.setLongitude(Double.parseDouble(rs.getString("longitude")));
	            post.setComment_count(rs.getInt("comment_count"));
	            post.setLike_count(rs.getInt("like_count"));
	            post.setTitle(rs.getString("title"));
	            post.setSenti_negative(rs.getDouble("senti_negative"));
	            post.setSenti_positive(rs.getDouble("senti_positive"));
	            post.setSearch_count(rs.getInt("search_count"));
	            post.setPre_popularity(rs.getDouble("popularity"));
	            
	            temp.add(post);
			}
			
			//여기서 sorting해서 가져오자
			wHelper.setPostWeight(temp);
			temp.sort(new weightDscCompare());	
							
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		if(temp.size() < (startPage-1) * pageNum)
			return result;
	
		for(int i=(startPage-1) * pageNum; i<pageNum * startPage; i++)
			result.add(temp.get(i));
			
			
		return result;
	}
	
	static class weightDscCompare implements Comparator<ModelFrontTravlePost> {
		 
		@Override
		public int compare(ModelFrontTravlePost arg0, ModelFrontTravlePost arg1) {
			// TODO Auto-generated method stub
			if(arg0.getgap_popularity() > arg1.getgap_popularity())
				return -1;
			else if(arg0.getgap_popularity() < arg1.getgap_popularity())
				return 1;
			else
				return 0;
		}
	}
		
	@Override
	public List<ModelFrontTravlePost> getPopularFrontTravelPostByCategory(String category, int startPage, int pageNum) {
		List<ModelFrontTravlePost> temp = new ArrayList<ModelFrontTravlePost>();
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		weightHelper wHelper = new weightHelper();
		ModelFrontTravlePost post = null;
		
		
		try{
			
			String query = "select * from" +
					" (select * from" +
					" (select * from" +
					" (select * from"+
					" (select * from feature_tb where feature = ?) as a"+
					" natural join"+
					" (select travelPost_no, senti_positive, senti_negative, search_count, popularity from information_tb) as b) as c"+
					" natural join"+
					" (select * from image_tb) as d) as e"+
					" natural join"+
					" (select travelPost_no, address, longitude, latitude from location_tb) as f) as g"+
					" natural join"+
					" (select travelPost_no, title, like_count, comment_count from travelpost_tb) as h";
			
		
			
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				post = new ModelFrontTravlePost();
	   	  		
				String image_url = rs.getString("image_url").equals("null") == true ? "img/readyImage.jpg" : rs.getString("image_url");
	   	  		post.setTravelPost_no(rs.getInt("travelPost_no"));
	   	  		post.setImage_url(image_url);
	            post.setAddress(rs.getString("address"));
	            post.setLatitude(Double.parseDouble(rs.getString("latitude")));
	            post.setLongitude(Double.parseDouble(rs.getString("longitude")));
	            post.setComment_count(rs.getInt("comment_count"));
	            post.setLike_count(rs.getInt("like_count"));
	            post.setTitle(rs.getString("title"));
	            post.setSenti_negative(rs.getDouble("senti_negative"));
	            post.setSenti_positive(rs.getDouble("senti_positive"));
	            post.setSearch_count(rs.getInt("search_count"));
	            post.setPre_popularity(rs.getDouble("popularity"));
	            temp.add(post);
			}
			
			//여기서 sorting해서 가져오자
			wHelper.setPostWeight(temp);
			temp.sort(new weightDscCompare());	

		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		if(temp.size() < (startPage-1) * pageNum)
			return result;
	
		for(int i=(startPage-1) * pageNum; i<pageNum * startPage; i++)
			result.add(temp.get(i));
			
		return result;
	}

	@Override
	public int updateViewCount(int travelPost_no) {
		int result = 0;
		
		try{
			String query = "UPDATE travelpost_tb SET view_count = view_count+1 WHERE travelPost_no = ?";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, travelPost_no);
			
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		return result;
	}
	
	@Override
	public int updateLikeCount(int travelPost_no) {
		int result = 0;
		
		try{
			String query = "update travelpost_tb set like_count = like_count+1 where travelPost_no = ?";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, travelPost_no);
			
			result = pstmt.executeUpdate();
	
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		return result;
	}
	
	@Override
	public int updateLikeMinusCount(int travelPost_no) {
	int result = 0;
		
		try{
			String query = "update travelpost_tb set like_count = like_count - 1 where travelPost_no = ?";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setInt(1, travelPost_no);
			
			result = pstmt.executeUpdate();
	
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		return result;
	}
	
	@Override
	public int insertLikePerosn(int travelPost_no, String userID) {
		int result = 0;
		
		try{
			String query = "insert into like_tb values(?,?)";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userID);
			pstmt.setInt(2, travelPost_no);
	
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		return result;
	}
	
	@Override
	public int removeLikePerson(int travelPost_no, String userID) {
	int result = 0;
		
		try{
			String query = "DELETE FROM like_tb WHERE travelPost_no = ? and user_id = ?";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setString(2, userID);
			pstmt.setInt(1, travelPost_no);
	
			result = pstmt.executeUpdate();
			
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		return result;
	}
	
	@Override
	public int getLikeState(int travelPost_no, String userID) {
		
		int result = 0;
		
		try{
			String query = "select count(*) from like_tb where user_id = ? and travelPost_no = ?";
			
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, userID);
			pstmt.setInt(2, travelPost_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next())
				result = rs.getInt("count(*)");
		}catch(SQLException e){
    		System.out.println(e.getMessage());
    	}
		
		return result;
	}
	
	
	
	
}
