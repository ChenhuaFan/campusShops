package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Utils.JsonReader;
import net.sf.json.JSONObject;

//@WebServlet("/createOrder")
public class createOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public createOrder() {
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
        String orderContent, orderAmount, remark;
        int userId;
        try {
        	json = JsonReader.receivePost(request);
        	orderContent = json.getString("orderContent");
        	orderAmount = json.getString("orderAmount");
        	remark = json.getString("remark");
        	userId = json.getInt("userId");
        } catch (IOException e) {
        	//获取json异常时返回错误信息
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
        }
		
	}

}
