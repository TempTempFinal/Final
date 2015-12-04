package com.lecture.finalproject.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.spi.LoggerFactory;
import org.slf4j.Logger;

import com.lecture.finalproject.model.ModelComment;
import com.lecture.finalproject.model.ModelConcern;
import com.lecture.finalproject.model.ModelFeature;
import com.lecture.finalproject.model.ModelFrontTravlePost;
import com.lecture.finalproject.model.ModelHash;
import com.lecture.finalproject.model.ModelImage;
import com.lecture.finalproject.model.ModelInformation;
import com.lecture.finalproject.model.ModelLocation;
import com.lecture.finalproject.model.ModelTravelPost;
import com.lecture.finalproject.model.ModelUser;


public class DaoTravlePlace implements IDao{
    
  
    private static Connection connection = JDBCMannager.getInstance();
   
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
  			  result = new ModelInformation(rs.getString("travel_content"), rs.getInt("travelPost_no"));
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
		  				+ "(travelpost_tb  natural join location_tb) where " + str +
		  						" group by travelPost_no" + 
		  							" limit ?,?";
    	 
    	 pstmt = connection.prepareStatement(query);
    	 pstmt.setInt(1, (startPage - 1) * pageNum);
    	 pstmt.setInt(2, pageNum);
         rs = pstmt.executeQuery();
               
         while (rs.next()) {
       	  ModelFrontTravlePost one = new ModelFrontTravlePost();
   	  		String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
            
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
    		String query = "select count(travelPost_no) from hash_tb where hashTag like ? group by travelPost_no";
    		
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
		 
		 try{
			 String query = "select * from (select * from (select address, travelPost_no from location_tb) as a natural join (select travelPost_no, title, like_count, comment_count from travelpost_tb) as b where address like ? or title like ?) as c natural  join image_tb group by travelPost_no limit ?,?";
			 pstmt = connection.prepareStatement(query);
			 
			 pstmt.setString(1, "%"+searchWord+"%");
			 pstmt.setString(2, "%"+searchWord+"%");
			 pstmt.setInt(3, (startPage - 1) * pageNum);
			 pstmt.setInt(4, pageNum);
			  
			 rs = pstmt.executeQuery();

			 while (rs.next()) {
				 ModelFrontTravlePost one = new ModelFrontTravlePost();
				 String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
			       	
				 one.setImage_url(image_url);
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
	public List<ModelFrontTravlePost> getFrontTravlePostList(){
		// TODO Auto-generated method stub
		
		  List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		  
		  try{
    		  String query = "select image_url,travelPost_no, title,view_count, like_count, comment_count, address, latitude, longitude" +
    				  			" from image_tb natural join "
    				  				+ " (travelpost_tb  natural join location_tb)" +
    				  						" group by travelPost_no";
    		  
              st = connection.createStatement();
              rs =st.executeQuery(query);
              
              if(st.execute(query))
                  rs = st.getResultSet();

                
              while (rs.next()) {
            	  ModelFrontTravlePost one = new ModelFrontTravlePost();
                  
            	  String image_url = rs.getString("image_url").equals("null") == true ? "img/notFound.jpg" : rs.getString("image_url");
            	  double latitude = rs.getString("latitude").equals("null" ) == true ? 0 : Double.parseDouble(rs.getString("latitude"));
            	  double longitude = rs.getString("longitude").equals("null" ) == true ? 0 : Double.parseDouble(rs.getString("longitude"));
            	  
                  one.setImage_url(image_url);
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
            String query = "select count(user_id) from like_tb where travelPost_no = '" + travelPost_no + "';";
            st = connection.createStatement();
            rs =st.executeQuery(query);
            
            if(st.execute(query))
                rs = st.getResultSet();
              
            while (rs.next()) {
                result = rs.getInt(1);
            }
          
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
	    		ModelImage image = new ModelImage(rs.getString("image_url"),rs.getInt("travelPost_no"));
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
				ModelInformation information = new ModelInformation(rs.getString("travel_content"), rs.getInt("travelPost_no"));
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


}
