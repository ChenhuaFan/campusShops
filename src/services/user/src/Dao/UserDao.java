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
	public int insertUser(Map<String,String> infoMap) {
		//变量声明
		sqlUtils su = null;
		int line;
		//访问数据库,插入一条新记录
		su = new sqlUtils();
		su.connect();
		line = su.insert("user", infoMap);
		su.close();
		return line;
	}
}
