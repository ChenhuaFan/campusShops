package servlet;

import java.io.BufferedReader;
import java.io.IOException;
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
 * Servlet implementation class change_cartinfo
 */
//@WebServlet("/change_cartinfo")
public class change_cartinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public change_cartinfo() {
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
		
		
		
		
		String userID,GoodsInfo;//request.getParameter("userID");
		JSONObject sourceJson ,ReJson;
		boolean flag = false,sflag = false;
		 /**
         * 接收json
         */
		try {
			sourceJson = JsonReader.receivePost(request);
	        userID =sourceJson.getString("UserID");
	        GoodsInfo = sourceJson.getString("Goods");
	        
	        
	//        json =JSONObject.fromObject(goods);
	        
	        sflag = true;
	        //System.out.println(json);
	        flag = do_cart_info.updateCartInfo(userID,GoodsInfo);
		}catch(Exception e) {
			e.printStackTrace();
		}
        
        ReJson = new JSONObject();
        if(sflag==false) {
        	ReJson.put("status", "false");
        	ReJson.put("info", "wrong Json format");
        }else if(flag) {
        	
        	ReJson.put("status", "true");
        	
        }else {
        	ReJson.put("status", "false");
        	ReJson.put("info", "database error");
        }
        //System.out.println(json);
        
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
