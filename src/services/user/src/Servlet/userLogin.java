package Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ServiceImp.UserService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import Utils.JsonReader;

//@WebServlet("/userLogin")
public class userLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public userLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json;charset=utf-8");  
        // 设置响应头允许ajax跨域访问 
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // 星号表示所有的异域请求都可以接受 
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
        PrintWriter out = response.getWriter();  
  
        JSONObject json = JsonReader.receivePost(request);
        String userName = json.getString("userName");
        String pw = json.getString("pw");
        
//        System.out.println(json.getString("userName"));
        UserService us = new UserService();
        String info[][] = us.userLogin(userName, pw);
        if(info[0][0] == null) {
        	JSONObject errorInfo = new JSONObject();
        	errorInfo.put("status", "false");
        	errorInfo.put("info", "wrong username or password");
        	out.println(errorInfo);
//        	out.write("can not found any user");
        } else {
        	JSONObject userInfo = new JSONObject();
        	userInfo.put("userID", Integer.parseInt(info[0][0]));
        	userInfo.put("userName", info[0][1]);
        	userInfo.put("role", info[0][2]);
        	userInfo.put("headPortrait", info[0][3]);
        	out.println(userInfo);
//	        System.out.println(info[0][0]);
//	        System.out.println(info[0][1]);
//	        System.out.println(info[0][2]);
//	        System.out.println(info[0][3]);
//	        out.write("success");  
        }
        
//        System.out.println("1111");
        out.flush();  
      
	}

}
