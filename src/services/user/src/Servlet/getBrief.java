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

//@WebServlet("/getBrief")
public class getBrief extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getBrief() {
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
        String briefInfo[][] = null;
        int id;
        
        try {
        	out = response.getWriter();
			json = JsonReader.receivePost(request);
			id = json.getInt("id");
			us = new UserService();
			briefInfo = us.getUserBrief(id);
			if(briefInfo == null) {
				JSONObject errorInfo = new JSONObject();
				errorInfo.put("status", "false");
				errorInfo.put("info", "the user id is not exist");
				out.println(errorInfo);
			} else {
				JSONObject userInfo = new JSONObject();
    			userInfo.put("userID", Integer.parseInt(briefInfo[0][0]));
    			userInfo.put("userName", briefInfo[0][1]);
    			userInfo.put("gender", briefInfo[0][2]);
    			userInfo.put("role", briefInfo[0][3]);
    			userInfo.put("headPortrait", briefInfo[0][4]);
    			out.println(userInfo);
			}
		} catch (IOException e) {
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
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
