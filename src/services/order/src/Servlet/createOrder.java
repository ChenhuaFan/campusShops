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
        OrderService os = null;
        String orderContent, orderAmount, remark;
        int userId, shopId;
        String info[][] = null;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	try {
        		remark = json.getString("remark");
        	} catch (JSONException e) {
        		remark = "";
        	}
        	orderContent = json.getString("orderContent");
        	orderAmount = json.getString("orderAmount");
        	userId = json.getInt("userId");
        	shopId = json.getInt("shopId");
        	os = new OrderService();
        	info = os.createOrder(userId, shopId, orderContent, orderAmount, remark);
        	if(info == null) {
        		JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "failed to create order");
    			out.println(errorInfo);
        	} else {
        		JSONObject orderInfo = new JSONObject();
        		orderInfo.put("orderId", Integer.parseInt(info[0][0]));
        		orderInfo.put("orderNumber", info[0][1]);
        		orderInfo.put("generatedTime", info[0][2]);
        		orderInfo.put("orderContent", info[0][3]);
        		orderInfo.put("orderAmount", info[0][4]);
        		orderInfo.put("orderStatus", info[0][5]);
        		orderInfo.put("remark", info[0][6]);
        		orderInfo.put("userId", Integer.parseInt(info[0][7]));
        		orderInfo.put("shopId", Integer.parseInt(info[0][8]));
        		out.println(orderInfo);
        	}
        } catch (IOException e) {
        	//获取json异常时返回错误信息
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
        } catch (JSONException e) {
        	//获取json异常时返回错误信息
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
        } catch (NumberFormatException e) {
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
