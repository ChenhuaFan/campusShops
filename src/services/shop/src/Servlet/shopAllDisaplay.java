package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ServiceImp.UserService;
import Utils.JsonReader;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

//@WebServlet("/deleteUser")
public class shopAllDisaplay extends HttpServlet {
private static final long serialVersionUID = 1L;
	
    public shopAllDisaplay() {
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
        String allShopInfo[][] = null;
        int id;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	id = json.getInt("id");
        		us = new UserService();
        		allShopInfo = us.getAllShop(1, 1,id);
        		if(allShopInfo == null) {
        			//获取json异常时返回错误信息
        			JSONObject errorInfo = new JSONObject();
        			errorInfo.put("status", "false");
        			errorInfo.put("info", "can not find any info");
        			out.println(errorInfo);
        		} else {
        			//JSON型数组,用于存放json对象,json对象存放用户信息
        			JSONObject jsonArr[] = new JSONObject[allShopInfo.length];
        			for(int i = 0; i < allShopInfo.length; i++) {
        				JSONObject userInfo = new JSONObject();
    					userInfo.put("shopID", Integer.parseInt(allShopInfo[i][0]));
    					userInfo.put("shopName", allShopInfo[i][1]);
    					userInfo.put("shopRank", allShopInfo[i][2]);
    					userInfo.put("shopPicture", allShopInfo[i][3]);
    					userInfo.put("isDelete", allShopInfo[i][4]);
    					userInfo.put("isopen", allShopInfo[i][5]);
    					jsonArr[i] = userInfo;
        			}
        			JSONObject userJson = new JSONObject();
        			userJson.put("shop", jsonArr);
        			out.println(userJson);
        		}
        } catch(IOException e) {
        	e.printStackTrace();
        } catch(JSONException e) {
        	//获取json异常时返回错误信息
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
        }
	}

}
