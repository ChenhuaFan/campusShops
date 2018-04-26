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

//@WebServlet("/getAllUser")
public class getAllUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public getAllUser() {
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
        String allUserInfo[][] = null;
        int index,lim;
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
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
        		allUserInfo = us.getAllUser(index, lim);
        		if(allUserInfo == null) {
        			//获取json异常时返回错误信息
        			JSONObject errorInfo = new JSONObject();
        			errorInfo.put("status", "false");
        			errorInfo.put("info", "can not find any info");
        			out.println(errorInfo);
        		} else {
        			//JSON型数组,用于存放json对象,json对象存放用户信息
        			JSONObject jsonArr[] = new JSONObject[allUserInfo.length];
        			for(int i = 0; i < allUserInfo.length; i++) {
        				JSONObject userInfo = new JSONObject();
    					userInfo.put("userID", Integer.parseInt(allUserInfo[i][0]));
    					userInfo.put("userName", allUserInfo[i][1]);
    					userInfo.put("email", allUserInfo[i][2]);
    					userInfo.put("phone", allUserInfo[i][3]);
    					userInfo.put("role", allUserInfo[i][4]);
    					userInfo.put("gender", allUserInfo[i][5]);
    					userInfo.put("isActive", Integer.parseInt(allUserInfo[i][6]));
    					userInfo.put("isDelete", Integer.parseInt(allUserInfo[i][7]));
    					userInfo.put("headPortrait", allUserInfo[i][8]);
    					jsonArr[i] = userInfo;
        			}
        			JSONObject userJson = new JSONObject();
        			userJson.put("user", jsonArr);
        			out.println(userJson);
        		}
        		
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (JSONException e) {
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
