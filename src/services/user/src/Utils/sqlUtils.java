package Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


public class sqlUtils {
	//配置文件对象
	Properties pro = new Properties();
	
	private String driver = null;
	private String sqlURL = null;
	private String user = null;
	private String pw = null;
	
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public sqlUtils() {
		try {
			//加载JDBC配置文件
			pro.load(sqlUtils.class.getResourceAsStream("/jdbc.properties"));
			
		} catch (IOException e) {
			String errorMsg="Failed to load jdbc config files!";
			System.out.println(errorMsg);
//			e.printStackTrace();
		}
	}
	public void connect() {
		//从配置文件获得驱动名，数据库地址，用户名和密码
		driver = pro.getProperty("driver");
		sqlURL = pro.getProperty("sqlURL");
		user = pro.getProperty("user");
		pw = pro.getProperty("pw");
		try {
			//加载MySql驱动
			Class.forName(driver);
			//连接数据库
			conn = DriverManager.getConnection(sqlURL, user, pw);
		} catch(ClassNotFoundException e1) {
			String errorMsg = "Failed to load jdbc driver!";
			System.out.println(errorMsg);
			e1.printStackTrace();
		} catch (SQLException e) {
			String errorMsg = "Failed to connect MySql!";
			System.out.println(errorMsg);
//			e.printStackTrace();
		}
	}
	public void close() {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(pst != null) {
				pst.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn != null) {
				conn.close();
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	//检查数据库是否有重复字段
	public int duplicateChecking(String tbName, String duplicateField[]) {
		int line = 0;
		String sql = "select count("+duplicateField[0]+") from "+tbName+" where "+duplicateField[0]+" = '"+duplicateField[1]+"'";
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			System.out.println(sql);
			while(rs.next()) {
				line = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return line;
	}
	
	/*
	 * tbName:表名
	 * Map<String,String> offerMap:供查询的字段名和数据
	 * demandArr[]:需要获得数据的字段
	 * index:当前位置
	 * lim:返回的条数
	 * */
	public String[][] select(String tbName, Map<String,String> offerMap, String demandArr[], int index, int lim) {
		String sql_part_1 = "";
		String sql_part_2 = "";
		String sql = "";
		
		int length = demandArr.length;
		if(length == 0) {
			sql_part_1 = "*";
		} else {
			for(int i = 0; i < length-1; i++) {
				sql_part_1 += demandArr[i]+", ";
			}
			sql_part_1 += demandArr[length-1];
		}
		
		Set<String> keySet = offerMap.keySet();
		int size = keySet.size();
		if(index != 1 || lim != 1) {
			lim += index-1;
			//sql_part_2第一种情况:select * from tb_name where userID>=1 and userID<=10
			sql_part_2 = " where userID >= "+index+" and userID <= "+lim;
		} else {
			if(size == 0) {
				//sql_part_2第二种情况:select * from tb_name
				sql_part_2 = "";
			} else {
				sql_part_2=" where ";
				//sql_part_2第三种情况:select * from tb_name where key_1 = value_1 and key_2 = value_2
				for(String key : keySet) {
					if(key == "userID") {
						//针对key的字段为int型时
						sql_part_2 += key+"="+offerMap.get(key)+" and ";
					} else {
						//针对key的字段为varchar型时
						sql_part_2 += key+"="+"'"+offerMap.get(key)+"' and ";
					}
				}
				sql_part_2 = sql_part_2.substring(0, sql_part_2.length()-5);
			}
		}
		//拼接sql语句
		sql = "select "+sql_part_1+" from "+tbName+sql_part_2;
		System.out.println(sql);
		//需要返回的数据数组
		String returnArr[][] = new String[lim][length];
		try {
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			int i = 0;
			while(rs.next()) {
				for(int j = 0; j < length; j++) {
					returnArr[i][j]=rs.getString(j+1);
				}
				i++;
				
//			System.out.println("id:"+rs.getString(1)+"\nphone:"+rs.getString(2)+"\nemail:"+rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnArr;
	}
	
	public int insert(String tbName, Map<String, String> infoMap) {
		String sql_part_1 = "";
		String sql_part_2 = "";
		String sql = "";
		//拼接sql语句
		Set<String> keySet = infoMap.keySet();
		sql_part_1 += " (";
		sql_part_2 += " (";
		for(String key : keySet) {
			sql_part_1 += key+", ";
			sql_part_2 += "'"+infoMap.get(key)+"', ";
		}
		sql_part_1 = sql_part_1.substring(0,sql_part_1.length()-2);
		sql_part_2 = sql_part_2.substring(0,sql_part_2.length()-2);
		sql_part_1+=")";
		sql_part_2+=")";
		sql = "insert into "+tbName+sql_part_1+" values"+sql_part_2;
		
		try {
			pst = conn.prepareStatement(sql);
			pst.execute();
		} catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		return 1;
	}
	
	public int update(String tbName, Map<String, String> updateInfo, String indexField, int id) {
		//UPDATE Person SET FirstName = 'Fred' WHERE LastName = 'Wilson' 
		String sql_part_1 = "";
		String sql_part_2 = "";
		String sql = "";
		int line = 0;
		
		Set<String> keySet = updateInfo.keySet();
		for(String key : keySet) {
			sql_part_1 += " "+key+" = "+"'"+updateInfo.get(key)+"',";
		}
		sql_part_1 = sql_part_1.substring(0, sql_part_1.length()-1);
		sql_part_2 = " "+indexField+" = "+id;
		
		sql = "update "+tbName+" set"+sql_part_1+" where"+sql_part_2;
		System.out.println(sql);
		try {
			pst = conn.prepareStatement(sql);
			line = pst.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
		return line;
		
	}
//	public static void main(String[] args) {
//		sqlUtils su = new sqlUtils();
//		su.connect();
//		String checkField[] = {"userName","Liaray"};
//		int line = su.duplicateChecking("user", checkField);
//		System.out.println(line);
//		检查sql语句拼接
//		Map<String, String> offerMap = new HashMap<String, String>();
//		offerMap.put("userName", "Liaray");
//		offerMap.put("pw", "123456");
//		offerMap.put("phone", "18155925262");
//		offerMap.put("email", "1107325513@qq.com");
//		offerMap.put("gender", "男");
//		su.update("user", offerMap, "userID", 1);
//		String demandArr[] = {"userID","phone","email","pw"};
//		String infoArr[][] =  su.select("user",offerMap, demandArr,1 ,2);
//		for(String[] info_arr : infoArr) {
//			for(String info : info_arr) {
//				System.out.println(info);
//			}
//		}
//	}
}
