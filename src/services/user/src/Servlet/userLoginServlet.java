package Servlet;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import Utils.JsonReader;

@WebServlet("/userLoginServlet")
public class userLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public userLoginServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");  
		  
        /** 设置响应头允许ajax跨域访问 **/  
        response.setHeader("Access-Control-Allow-Origin", "*");  
        /* 星号表示所有的异域请求都可以接受， */  
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");  
  
        Writer out = response.getWriter();  
  
  
        JSONObject json = JsonReader.receivePost(request);  
        System.out.println(json);  
        System.out.println("1111");
        out.write("success");  
        out.flush();  
      
	}

}
