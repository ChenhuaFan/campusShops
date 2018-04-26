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
 * Servlet implementation class service_Delete
 */
//@WebServlet("/service_Delete")
public class service_Delete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public service_Delete() {
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
		String userID,ServiceID;//request.getParameter("userID");
		JSONObject sourceJson ,ReJson;
		boolean flag=false,sflag = false;
		
		 /**
         * 接收json
         */
		try {
			sourceJson = JsonReader.receivePost(request);
	        userID =sourceJson.getString("UserID");
	        ServiceID =sourceJson.getString("ServiceID");
	//        json =JSONObject.fromObject(goods);
	        Aservice serv = new Aservice();
	        serv.setServiceID(Integer.parseInt(ServiceID));
	        serv.setUserID(Integer.parseInt(userID));
	        sflag =true;
	        
	        //System.out.println(json);
	        flag = do_service_info.updateServiceInfo(serv, "delete");
		}catch(Exception e)
		{
			e.printStackTrace();
		}
        ReJson = new JSONObject();
        if(sflag ==false) {
        	ReJson.put("status", "false");
        	ReJson.put("info", "wrong Json format");
        }
        else if(flag) {
        	
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
