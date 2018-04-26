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

//@WebServlet("/deleteUser")
public class deleteUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public deleteUser() {
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
        int userID;
        String info[][] = null;
        
        try {
			out = response.getWriter();
			json = JsonReader.receivePost(request);
			userID = json.getInt("id");
			us = new UserService();
			info = us.deleteUser(userID);
			if(info == null) {
				//ID不存在
				JSONObject errorInfo = new JSONObject();
				errorInfo.put("status", "false");
				errorInfo.put("info", "the userID is not exist");
				out.println(errorInfo);
			} else {
				JSONObject userinfo = new JSONObject();
				userinfo.put("userID", Integer.parseInt(info[0][0]));
				userinfo.put("userName", info[0][1]);
				userinfo.put("role", info[0][2]);
				userinfo.put("isDelete", Integer.parseInt(info[0][3]));
				out.println(userinfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			//ID不合法
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
		} catch(NumberFormatException e) {
			//userID不存在
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
