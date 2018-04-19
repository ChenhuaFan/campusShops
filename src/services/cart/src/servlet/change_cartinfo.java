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
		
		 /**
         * 接收json
         */
		sourceJson = JsonReader.receivePost(request);
        userID =sourceJson.getString("UserID");
        GoodsInfo = sourceJson.getString("Goods");
        
        
//        json =JSONObject.fromObject(goods);
        
        
        //System.out.println(json);
        boolean flag = do_cart_info.updateCartInfo(userID,GoodsInfo);
        
        ReJson = new JSONObject();
        if(flag) {
        	
        	ReJson.put("status", "true");
        	ReJson.put("info", "success option");
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
