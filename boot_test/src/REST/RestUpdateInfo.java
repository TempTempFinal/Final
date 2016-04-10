package REST;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lecture.finalproject.dao.DaoTravlePlace;
import com.lecture.finalproject.model.ModelFrontTravlePost;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class RestUpdateInfo
 */
@WebServlet("/RestUpdateInfo")
public class RestUpdateInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestUpdateInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		DaoTravlePlace db = new DaoTravlePlace();
		
		int travelPost_no = Integer.parseInt(request.getParameter("travelPost_no"));
		String userID = request.getParameter("userID");
		
		Map<String, Integer> resultMap = new HashMap<String, Integer>();
		int likeCount = 0;
		
		if(method.equals("likeUpdate")){
			int result = db.insertLikePerosn(travelPost_no, userID);
			
			if(result >= 1){
				db.updateLikeCount(travelPost_no);
				resultMap.put("likeState", 1);
			}
			else{
				db.updateLikeMinusCount(travelPost_no);
				db.removeLikePerson(travelPost_no, userID);
				resultMap.put("likeState", 0);
			}
			
			likeCount = db.getLikeCount(travelPost_no);
			resultMap.put("likeCount", likeCount);
			
		}
		else if(method.equals("visitUpdate")){
			int result = 0;
			
			if(db.deleteCheckin(travelPost_no, userID) <= 0){
				result = db.insertCheckin(travelPost_no, userID,Double.parseDouble(request.getParameter("latitude")), Double.parseDouble(request.getParameter("longitude")));
			}
			
			resultMap.put("isVisit", result);
		}
	
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		System.out.println(jsonObject);
		PrintWriter out = response.getWriter();
		out.println(jsonObject);
		
	}



}
