package Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Services.ServiceImp.OrderService;
import Utils.JsonReader;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

//@WebServlet("/modifyOrderStatus")
public class modifyOrderStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public modifyOrderStatus() {
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
        OrderService os = null;
        int status, orderId;
        int modifyResult = 0;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	status = json.getInt("status");
        	orderId = json.getInt("orderId");
        	os = new OrderService();
        	modifyResult = os.modifyOrderStatus(status, orderId);
        	if(modifyResult == 0) {
        		JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "failed to modify");
    			out.println(errorInfo);
        	} else {
        		JSONObject info = new JSONObject();
        		info.put("status", "success");
        		out.println(info);
        	}
        } catch (IOException e) {
        	JSONObject errorInfo = new JSONObject();
        	errorInfo.put("status", "false");
        	errorInfo.put("info", "system error,you can not do anything except watching it");
        	out.println(errorInfo);
        } catch (JSONException e) {
        	JSONObject errorInfo = new JSONObject();
        	errorInfo.put("status", "false");
        	errorInfo.put("info", "the type of input is incorrect");
        	out.println(errorInfo);
        } catch(NumberFormatException e) {
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
		} catch(NullPointerException e) {
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
