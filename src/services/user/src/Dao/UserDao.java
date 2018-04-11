package Dao;

import java.util.Map;

import Utils.sqlUtils;

public class UserDao {
	public String[][] queryUser(Map<String,String> offerMap, String demandArr[], int index, int lim) {
		//变量声明
		sqlUtils su = null;
		String userInfo[][] = null;
		//访问数据库
		su = new sqlUtils();
		su.connect();
		userInfo = su.select("user", offerMap, demandArr, index, lim);
		su.close();
		
		return userInfo;
	}
}
