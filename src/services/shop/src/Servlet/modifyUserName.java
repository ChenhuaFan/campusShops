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

//@WebServlet("/modifyUserName")
//�޸��̼���Ϣ
public class modifyUserName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public modifyUserName() {
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
        int id;
        String name = "";
        String type="";
        String shopkeeper="";
        String address="";
        String phoneNumber="";
        String picture="";
        
        String info[][] = null;
        
        try {
        	out = response.getWriter();
        	json = JsonReader.receivePost(request);
        //�õ�json���ص��ֶ���Ϣ
        	id = json.getInt("id");
        	name = json.getString("name");
        	type=json.getString("type");
        	shopkeeper=json.getString("shopkeeper");
        	address=json.getString("address");
        	phoneNumber=json.getString("phoneNumber");
        	picture=json.getString("picture");
        	
        	us = new UserService();
        	info = us.modifyUserName(id, name,type,shopkeeper,address,phoneNumber,picture);
        	if(info == null) {
        		//��ȡjson�쳣ʱ���ش�����Ϣ
    			JSONObject errorInfo = new JSONObject();
    			errorInfo.put("status", "false");
    			errorInfo.put("info", "can not found");
    			out.println(errorInfo);
        	} else {
        		//���û���Ϣ����json������
    			JSONObject userInfo = new JSONObject();
    			userInfo.put("shopId", Integer.parseInt(info[0][0]));
    			userInfo.put("shopName", info[0][1]);
    			userInfo.put("shopType", info[0][2]);
    			userInfo.put("shopkeeper", info[0][3]);
    			userInfo.put("shopAddress", info[0][4]);
    			userInfo.put("shopPhoneNumber", info[0][5]);
    			userInfo.put("shopPicture", info[0][7]);
    			out.println(userInfo);
        	}
        } catch(IOException e) {
        	e.printStackTrace();
        } catch(JSONException e) {
        	//��ȡjson�쳣ʱ���ش�����Ϣ
			JSONObject errorInfo = new JSONObject();
			errorInfo.put("status", "false");
			errorInfo.put("info", e.getMessage());
			out.println(errorInfo);
        }
	}

}