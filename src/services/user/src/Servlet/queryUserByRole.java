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

//@WebServlet("/queryUserByRole")
public class queryUserByRole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public queryUserByRole() {
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
        String role="";
        int index, lim;
        String info[][] = null;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	role = json.getString("role");
        	index = json.getInt("index");
        	lim = json.getInt("lim");
        	if(index < 0 || lim < 1) {
        		//获取json异常时返回错误信息
    			JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "the value of index or lim is not illegal");
    			out.println(errorInfo);
        	} else {
        		us = new UserService();
        		info = us.queryUserByRole(role, index, lim);
        		if(info == null) {
        			//获取json异常时返回错误信息
        			JSONObject errorInfo = new JSONObject();
        			errorInfo.put("status", "false");
        			errorInfo.put("info", "can not find any info");
        			out.println(errorInfo);
        		} else {
        			//JSON型数组,用于存放json对象,json对象存放用户信息
        			JSONObject jsonArr[] = new JSONObject[info.length];
        			for(int i = 0; i < info.length; i++) {
        				JSONObject userInfo = new JSONObject();
    					userInfo.put("userID", info[i][0]);
    					userInfo.put("userName", info[i][1]);
    					userInfo.put("email", info[i][2]);
    					userInfo.put("phone", info[i][3]);
    					userInfo.put("gender", info[i][4]);
    					userInfo.put("role", info[i][5]);
    					userInfo.put("headPortrait", info[i][6]);
    					jsonArr[i] = userInfo;
        			}
        			JSONObject userJson = new JSONObject();
        			userJson.put("user", jsonArr);
        			out.println(userJson);
        		}
        	}
        	
        } catch(IOException e) {
        	e.printStackTrace();
        } catch(JSONException e) {
        	//获取json异常时返回错误信息
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
