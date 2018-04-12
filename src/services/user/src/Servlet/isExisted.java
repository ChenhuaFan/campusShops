package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ServiceImp.UserService;
import net.sf.json.JSONObject;

//@WebServlet("/isExisted")
public class isExisted extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public isExisted() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("application/json;charset=utf-8");  
        // 设置响应头允许ajax跨域访问 
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // 星号表示所有的异域请求都可以接受 
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        //变量声明
        PrintWriter out = null;
        UserService us = null;
        int result = 1;
        String userName = request.getParameter("userName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String checkField[] = new String[2];
        //检查get方式提交了哪个字段
        if(userName != null) {
        	us = new UserService();
        	checkField[0] = "userName";
        	checkField[1] = userName;
        } else if(phone != null) {
        	us = new UserService();
        	checkField[0] = "phone";
        	checkField[1] = phone;
        } else if(email != null) {
        	us = new UserService();
        	checkField[0] = "email";
        	checkField[1] = email;
        }
        //result代表查重的结果,0代表无重复,非0代表有重复
        result = us.duplicateCheck(checkField);
        try {
        	out = response.getWriter();
	        if(result != 0) {
	        	JSONObject errorInfo = new JSONObject();
	        	errorInfo.put("status", 1);
	        	out.println(errorInfo);
	        } else {
	        	JSONObject errorInfo = new JSONObject();
	        	errorInfo.put("status", 1);
	        	out.println(errorInfo);
	        }
        } catch (IOException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        } finally {
        	out.flush();
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
