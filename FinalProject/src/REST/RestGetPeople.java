package REST;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lecture.finalproject.model.ModelUser;
import com.lecture.finalproject.service.FriendsInfoHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import twitter4j.PagableResponseList;
import twitter4j.ResponseList;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;

/**
 * Servlet implementation class RestGetPeople
 */
@WebServlet("/RestGetPeople")
public class RestGetPeople extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RestGetPeople() {
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
		
		HttpSession session = request.getSession();
		Twitter twitter = (Twitter)session.getAttribute("twitter");
		User user = (User)session.getAttribute("twitterUser");		
		String method = request.getParameter("method");
		
		List<Map<String, Double>> friendsNameAndWeight = null;
		FriendsInfoHelper friendHelper = new FriendsInfoHelper(twitter, user);
		List<String> finalgroup = null;
		List<ModelUser> listFriends = null;
	
		if(method.equalsIgnoreCase("getFrindAndFollowerList")){
			listFriends = friendHelper.getFriendAndFollowerList();
		}
		if(method.equalsIgnoreCase("getFriendWeight")){
			String[] friendNames = decoder(request.getParameterValues("name"), request.getParameterValues("name").length);
			
			friendsNameAndWeight = friendHelper.getFriendsWeight(friendNames);
			finalgroup = friendHelper.groupConcern(friendNames);
			for(int i=0;i<friendsNameAndWeight.size();i++)
				System.out.println(friendsNameAndWeight.toString());
			for(int i=0;i<finalgroup.size();i++)
				System.out.println("gg"+finalgroup.get(i));
			
		}
		
		JSONArray FrinedJsonArray = JSONArray.fromObject(listFriends);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("friendList", FrinedJsonArray);
		
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);
		PrintWriter out = response.getWriter();
		
		out.println(jsonObject);	
	}
	
	
	
	private String[] decoder(String[] friendsName, int length)
	{
		
		String[] result = new String[length];
			
		try {
		for(int i=0; i<length; i++)
				result[i] = URLDecoder.decode(friendsName[i],"UTF-8");	
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	
}
