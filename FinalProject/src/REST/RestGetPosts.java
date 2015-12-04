package REST;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelFrontTravlePost;



/**
 * Servlet implementation class RestGetPostList
 */
@WebServlet("/RestGetPost")
public class RestGetPosts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestGetPosts() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		DaoTravlePlace db = new DaoTravlePlace();
		List<ModelFrontTravlePost> posts = null;

		
		if(method.equals("listPost")){
			posts = db.getFrontTravlePostList(Integer.parseInt(request.getParameter("startPage")), Integer.parseInt(request.getParameter("pageNum")));
		}
		else if(method.equals("listByLocation")){		
			String[] location = decoder(request.getParameterValues("location"), request.getParameterValues("location").length);
			posts = db.getFrontTravlePostListByLocation(location, Integer.parseInt(request.getParameter("startPage")), Integer.parseInt(request.getParameter("pageNum")));
		}
		else if(method.equals("listBySortedLocation")){
			int length = request.getParameterValues("location") == null ? 0 :  request.getParameterValues("location").length;
			String[] location = decoder(request.getParameterValues("location"), length);
			String standard = request.getParameter("standard");
			posts = db.getFrontTravlePostListBySortedLocation(location, standard,Integer.parseInt(request.getParameter("startPage")), Integer.parseInt(request.getParameter("pageNum")));
		}	
		else if(method.equals("listByBoundaryLocation")){
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			List<ModelFrontTravlePost> tempPost = db.getFrontTravlePostList();
			List<ModelFrontTravlePost> nearPosts = getNearPostList(tempPost,latitude,longitude);
			posts = getNearPagedPostList(nearPosts,Integer.parseInt(request.getParameter("startPage")), Integer.parseInt(request.getParameter("pageNum")));
		}
		else if(method.equals("listBySeachWord")){
			String searchWord = URLDecoder.decode(request.getParameter("searchWord"),"UTF-8");
			System.out.println(searchWord);
			posts = db.getFrontTravlePostBySearchWord(searchWord,Integer.parseInt(request.getParameter("startPage")), Integer.parseInt(request.getParameter("pageNum")));
		}
		else if(method.equals("listByHashTag")){
			String hashTag = URLDecoder.decode(request.getParameter("searchWord"),"UTF-8");
			posts = db.getFrontTravlePostByHashTag(hashTag, Integer.parseInt(request.getParameter("startPage")), Integer.parseInt(request.getParameter("pageNum")));
		}
		JSONArray jsonArray = JSONArray.fromObject(posts);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("posts", jsonArray);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);
		PrintWriter out = response.getWriter();
		out.println(jsonObject);	
	}

	
	private String[] decoder(String[] parameter, int length)
	{
		
		String[] result = new String[length];
			
		try {
		for(int i=0; i<length; i++)
				result[i] = URLDecoder.decode(parameter[i],"UTF-8");	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	private List<ModelFrontTravlePost> getNearPagedPostList(List<ModelFrontTravlePost> nearPost, int startPage, int pageNum){
		List<ModelFrontTravlePost> pagedPost = new ArrayList<ModelFrontTravlePost>();
		
		for(int i= (startPage - 1) * pageNum; i < (startPage * pageNum) - 1; i++){
			if(nearPost.size() == i)
				break;
			pagedPost.add(nearPost.get(i));			
		}
		
		return pagedPost;
	}
	
	private List<ModelFrontTravlePost> getNearPostList(List<ModelFrontTravlePost> inputPost, double myLatitude, double myLongitude){
		List<ModelFrontTravlePost> result = new ArrayList<ModelFrontTravlePost>();
		
		for(int i=0 ;i<inputPost.size(); i++){
			//해당 여행지가 3000m내에 있다면
			if(calDistance(myLatitude,myLongitude,inputPost.get(i).getLatitude(),inputPost.get(i).getLongitude()) <= 3000)
				result.add(result.get(i));
		}
		
		return result;
	}
	
	public double calDistance(double lat1, double lon1, double lat2, double lon2){  
	    
	    double theta, dist;  
	    theta = lon1 - lon2;  
	    dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))   
	          * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));  
	    dist = Math.acos(dist);  
	    dist = rad2deg(dist);  
	      
	    dist = dist * 60 * 1.1515;   
	    dist = dist * 1.609344;    // 단위 mile 에서 km 변환.  
	    dist = dist * 1000.0;      // 단위  km 에서 m 로 변환  
	  
	    return dist;  
	}  
	  
	    // 주어진 도(degree) 값을 라디언으로 변환  
	private double deg2rad(double deg){  
	    return (double)(deg * Math.PI / (double)180d);  
	}  
	  
	    // 주어진 라디언(radian) 값을 도(degree) 값으로 변환  
	private double rad2deg(double rad){  
	    return (double)(rad * (double)180d / Math.PI);  
	} 

	
}
