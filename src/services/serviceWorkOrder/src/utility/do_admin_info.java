package utility;

import net.sf.json.JSONObject;

public class do_admin_info {
	public static JSONObject getAdminRank(int userID) {
		JSONObject json = new JSONObject();

		int  rank = adminSQLControl.sqlQuery(userID);	 
		 
		json.put("rank", rank);
		
		return json;
		
	}
	
}
