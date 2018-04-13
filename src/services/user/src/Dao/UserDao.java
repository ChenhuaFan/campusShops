package Dao;

import java.util.Map;

import Utils.sqlUtils;

public class UserDao {
	//查询用户信息
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
	//插入一条用户信息
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
	
	//检查字段是否重复
	public int fieldRecheck(String duplicateField[]) {
		//变量声明
		sqlUtils su = null;
		int count = 0;
		//检查是否有重复字段
		su = new sqlUtils();
		su.connect();
		count = su.duplicateChecking("user", duplicateField);
		su.close();
		//返回查到的行数
		return count;
	}
	
	//更新信息
	public int updateInfo(Map<String, String> updateMap, int userID) {
		//变量声明
		sqlUtils su = null;
		int count = 0;
		su = new sqlUtils();
		su.connect();
		//更新
		count = su.update("user", updateMap, "userID", userID);
		su.close();
		//更新的行数
		return count;
		
	}
}
