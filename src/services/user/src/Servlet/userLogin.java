package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ServiceImp.UserService;
import net.sf.json.JSONException;
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

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		response.setContentType("application/json;charset=utf-8");  
        // 设置响应头允许ajax跨域访问 
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // 星号表示所有的异域请求都可以接受 
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        
        //变量声明
        PrintWriter out = null;
        JSONObject json = null;
        UserService us = null;
        String userName="";
        String pw = "";
        String info[][] = null;
        
        try {
        	out = response.getWriter();
	        json = JsonReader.receivePost(request);
	        //通过JSONObject获得username和pw
        	userName = json.getString("userName");
        	pw = json.getString("pw");
    		
    		us = new UserService();
    		//获得用户信息，存入info[][]数组
    		info = us.userLogin(userName, pw);
    		//防SQL注入
    		if(info == null) {
    			JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "the type of username is illegal");
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
        } catch(JSONException e) {
        	//通过JSONObject获得用户名密码失败异常
        	JSONObject errorInfo = new JSONObject();
        	errorInfo.put("status", "false");
        	errorInfo.put("info", "you did not type in userName or password");
        	out.println(errorInfo);
        } catch (IOException e) {
        	//IO异常
//			e.printStackTrace();
		} catch(NumberFormatException e) {
			//userID不存在
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", "wrong username or password");
			out.println(errorInfo);
		} finally {
        	out.flush();
        	out.close();
        }
	}
}
