package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.Aservice;
import net.sf.json.JSONObject;
import utility.JsonReader;
import utility.do_service_info;

/**
 * Servlet implementation class service_Records
 */
//@WebServlet("/service_Records")
public class service_Records extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public service_Records() {
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
		String serviceID,userID;
		JSONObject sourceJson ,ReJson=null;
		boolean flag = false;
		try {
		sourceJson = JsonReader.receivePost(request);
        serviceID =sourceJson.getString("ServiceID");
        userID=sourceJson.getString("UserID");
		//return json
        //大json 套小 json
         
       // try {
        flag= true;
        ReJson = do_service_info.getRecords(Integer.parseInt(serviceID), Integer.parseInt(userID));
        }catch(Exception e) {
        	e.printStackTrace();
        }
        if(flag == false) {
        	ReJson = new  JSONObject();
        	ReJson.put("status", "false");
        	ReJson.put("info", "wrong Json Format");
        }	
        if(ReJson == null||ReJson.isEmpty()) {
        	ReJson = new  JSONObject();
        	ReJson.put("status", "false");
        	ReJson.put("info", "Database Error");
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
