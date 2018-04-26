package Dao;

import java.util.Map;

import Utils.sqlUtils;

public class OrderDao {
	public int createAnOrder(Map<String, String> orderMap) {
		//变量声明
		sqlUtils su = new sqlUtils();
		int line = 0;
		
		su.connect();
		line = su.insert("`order`", orderMap);
		su.close();
		
		return line;
	}
	public String[][] queryOrder(Map<String, String> orderMap, String[] demandArr, int index, int limit) {
		sqlUtils su = new sqlUtils();
		String orderInfo[][] = null;
		su.connect();
		orderInfo = su.select("`order`", orderMap, demandArr, index, limit);
		su.close();
		
		return orderInfo;
	}
	
	public int changeOrder(Map<String, String> updateInfoMap, int orderId) {
		sqlUtils su = new sqlUtils();
		int line = 0;
		su.connect();
		line = su.update("`order`", updateInfoMap, "orderId", orderId);
		su.close();
		
		return line;
	}
	public int getCount(String duplicateField[]) {
		sqlUtils su = new sqlUtils();
		int line = 0;
		su.connect();
		line = su.duplicateChecking("`order`", duplicateField);
		su.close();
		return line;
		
	}
}
