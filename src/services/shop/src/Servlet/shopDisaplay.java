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
public class shopDisaplay extends HttpServlet {
private static final long serialVersionUID = 1L;
	
    public shopDisaplay() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("application/json;charset=utf-8");  
        // ������Ӧͷ����ajax������� 
        response.setHeader("Access-Control-Allow-Origin", "*");  
        // �Ǻű�ʾ���е��������󶼿��Խ��� 
        response.setHeader("Access-Control-Allow-Methods", "GET,POST");
        
        //��������
        PrintWriter out = null;
        JSONObject json = null;
        UserService us = null;
        String allShopInfo[][] = null;
        int number,page;
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        	page = json.getInt("page");
        	number = json.getInt("number");
        	if(page < 0 || number < 1) {
        		//��ȡjson�쳣ʱ���ش�����Ϣ
    			JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "the value of index or lim is not illegal");
    			out.println(errorInfo);
        	} else {
        		us = new UserService();
        		allShopInfo = us.getAllShop(page, number);
        		if(allShopInfo == null) {
        			//��ȡjson�쳣ʱ���ش�����Ϣ
        			JSONObject errorInfo = new JSONObject();
        			errorInfo.put("status", "false");
        			errorInfo.put("info", "can not find any info");
        			out.println(errorInfo);
        		} else {
        			//JSON������,���ڴ��json����,json�������û���Ϣ
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
        		
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        } catch (JSONException e) {
			//��ȡjson�쳣ʱ���ش�����Ϣ
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
		} catch(NumberFormatException e) {
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
		}
	}

}
