package utility;

import net.sf.json.JSONObject;

public class do_cart_info {
	
	
	public static JSONObject getCartInfo(String userID) {
		JSONObject json =null;
		try {
			String info = cartSQLControl.sqlQuery(userID);	 
			json= JSONObject.fromObject(info); 
		}catch(Exception e) {
			return null;
		}
		
		return json;
		
	}
	
	
	public static boolean updateCartInfo(String userID,String GoodsInfo) {
		
		 
		try {
			String info = cartSQLControl.sqlQuery(userID);
			String Goods = GoodsInfo.toString();
			
			if(info.equals("")||info==null) {
				cartSQLControl.sqlInset(userID, GoodsInfo);
			}else {
				cartSQLControl.sqlUpdate(userID, GoodsInfo);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
			
		}
		
		return true;
	}
	
 
	
	
}
