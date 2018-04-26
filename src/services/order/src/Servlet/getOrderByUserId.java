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

//@WebServlet("/getOrderByUserId")
public class getOrderByUserId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public getOrderByUserId() {
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
        int userId, index, limit;
        String info[][] = null;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	userId = json.getInt("userId");
        	index = json.getInt("index");
        	limit = json.getInt("limit");
        	os = new OrderService();
        	info = os.queryOrderByUserId(userId, index, limit);
        	if(info == null) {
        		JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "failed to get order");
    			out.println(errorInfo);
        	} else {
        		JSONObject jsonArr[] = new JSONObject[info.length];
        		for(int i = 0; i < info.length; i++) {
        			JSONObject orderInfo = new JSONObject();
	        		orderInfo.put("orderId", Integer.parseInt(info[i][0]));
	        		orderInfo.put("orderNumber", info[i][1]);
	        		orderInfo.put("generatedTime", info[i][2]);
	        		orderInfo.put("orderContent", info[i][3]);
	        		orderInfo.put("orderAmount", info[i][4]);
	        		orderInfo.put("orderStatus", info[i][5]);
	        		orderInfo.put("remark", info[i][6]);
	        		orderInfo.put("userId", Integer.parseInt(info[i][7]));
	        		orderInfo.put("shopId", Integer.parseInt(info[i][8]));
	        		jsonArr[i] = orderInfo;
        		}
        		JSONObject orderJson = new JSONObject();
        		orderJson.put("order", jsonArr);
    			out.println(orderJson);
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
			//userID不存在
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
