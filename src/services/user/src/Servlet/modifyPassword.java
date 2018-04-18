package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ServiceImp.UserService;
import Utils.JsonReader;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

//@WebServlet("/modifyPassword")
public class modifyPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public modifyPassword() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");  
        // 设置响应头允许ajax跨域访问 
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // 星号表示所有的异域请求都可以接受 
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        
        //变量声明
        PrintWriter out = null;
        JSONObject json = null;
        UserService us = null;
        int id;
        String pw = "";
        String info[][] = null;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	id = json.getInt("id");
        	pw = json.getString("pw");
        	us = new UserService();
        	info = us.modifyUserPassword(id, pw);
        	if(info == null) {
        		//获取json异常时返回错误信息
    			JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "the userID is not exist");
    			out.println(errorInfo);
        	} else {
        		//将用户信息存入json对象中
    			JSONObject userInfo = new JSONObject();
    			userInfo.put("userID", Integer.parseInt(info[0][0]));
    			userInfo.put("userName", info[0][1]);
    			userInfo.put("role", info[0][2]);
    			userInfo.put("headPortrait", info[0][3]);
    			out.println(userInfo);
        	}
        } catch(IOException e) {
        	e.printStackTrace();
        } catch(JSONException e) {
        	//获取json异常时返回错误信息
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
        } catch(NumberFormatException e) {
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
		} finally {
			out.flush();
			out.close();
		}
	}

}
