package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utility.JsonReader;
import utility.do_admin_info;

/**
 * Servlet implementation class admin_rank
 */
@WebServlet("/admin_rank")
public class admin_Rank extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public admin_Rank() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID ;//userID
		JSONObject sourceJson ,ReJson;
		//BufferedReader reader = request.getReader();
		sourceJson = JsonReader.receivePost(request);
		
        userID = sourceJson.getString("userID");
		//return json
        System.out.println(userID);
        ReJson =new JSONObject();
        ReJson = do_admin_info.getAdminRank(Integer.parseInt(userID));
        
        
     // 设置响应头允许ajax跨域访问 
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // 星号表示所有的异域请求都可以接受 
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
		response.setCharacterEncoding("UTF-8");  
	    response.setContentType("application/json; charset=utf-8");  
	    PrintWriter out = null;  
	    try {  
	        out = response.getWriter();  
	        out.append(ReJson.toString());  
	       
	    } catch (IOException e) {  
	        e.printStackTrace();
	        
	    } finally {  
	        if (out != null) {  
	            out.close();  
	        }  
	    }  
	}

}
