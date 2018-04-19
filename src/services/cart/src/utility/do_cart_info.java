package utility;

import net.sf.json.JSONObject;

public class do_cart_info {
	
	
	public static JSONObject getCartInfo(String UserID) {
		JSONObject json =null;
		int userID = Integer.parseInt(UserID);
		try {
			String info = cartSQLControl.sqlQuery(userID);
			//System.out.println(info);
			json= JSONObject.fromObject(info); 
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return json;
		
	}
	
	
	public static boolean updateCartInfo(String UserID,String GoodsInfo) {
		
		int userID = Integer.parseInt(UserID);
		String info=null;
		try {
			info= cartSQLControl.sqlQuery(userID);
			System.out.println("do info 30 :"+info);
			
		}catch(Exception e) {
			//System.out.println("do info 34 : info 为空");
			info =null;
		}
	
		
		try {
			 
			if(info==null) {
				System.out.println("无id，插入");
				cartSQLControl.sqlInset(userID, GoodsInfo);
			}else {
				System.out.println("有id，更新");
				cartSQLControl.sqlUpdate(userID, GoodsInfo);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		return true;
	}
	
 
	
	
}
