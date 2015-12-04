package REST;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
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
			int longitude = Integer.parseInt(request.getParameter("longitude"));
			int latitude = Integer.parseInt(request.getParameter("latitude"));
			
		
			
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
	
}
