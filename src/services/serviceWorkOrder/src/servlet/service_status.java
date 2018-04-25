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
import utility.do_cart_info;
import utility.do_service_info;

/**
 * Servlet implementation class service_status
 */
//@WebServlet("/service_status")
public class service_status extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public service_status() {
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
		String serviceID,UserID ;
		JSONObject sourceJson ,ReJson=null;
		boolean flag = false;
		Aservice serv = null;
		try {
		sourceJson = JsonReader.receivePost(request);
        serviceID =sourceJson.getString("ServiceID");
        UserID =sourceJson.getString("UserID");
		//return json
        flag = true;
        ReJson = new JSONObject();
        
        serv = do_service_info.getServiceInfo(Integer.parseInt(serviceID));
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(flag == false) {
			ReJson = new JSONObject();
			ReJson.put("state",false );
        	ReJson.put("info", "wrong Json format");
		}
        else if(serv!=null) {
        	ReJson = new JSONObject();
        	ReJson.put("status",true);
        	ReJson.put("state",serv.getStatus());
        }else {
        	ReJson.put("state",false );
        	ReJson.put("info", "DataBase Error");
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
