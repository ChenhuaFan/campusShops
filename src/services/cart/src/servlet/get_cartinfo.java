package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import utility.JsonReader;
import utility.do_cart_info;

/**
 * Servlet implementation class get_cartinfo
 */
//@WebServlet("/get_cartinfo")
public class get_cartinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public get_cartinfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userID=null ;
		JSONObject sourceJson ,ReJson=null;
		boolean flag =false;
		try {
			sourceJson = JsonReader.receivePost(request);
	        
			  
	        userID =sourceJson.getString("UserID");
	        //System.out.println(userID);
	        flag = true;
	        ReJson = do_cart_info.getCartInfo(userID);
		}catch (Exception e) {
			e.printStackTrace();
		}
       
		
		//return json
 
		if(flag ==false) {
			ReJson =new JSONObject();
        	ReJson.put("status", false);
        	ReJson.put("info", "wrong Json format");
		}
		else if(ReJson==null||ReJson.isEmpty()) {
        	ReJson =new JSONObject();
        	ReJson.put("status", false);
        	ReJson.put("info", "null info");
        	
        }
        
        
        
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
