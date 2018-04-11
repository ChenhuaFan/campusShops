package Utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
					sql_part_2 += key+"="+"'"+offerMap.get(key)+"' and ";
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
//	public static void main(String[] args) {
//		sqlUtils su = new sqlUtils();
//		su.connect();
//		//检查sql语句拼接
//		Map<String, String> offerMap = new HashMap<String, String>();
//		offerMap.put("userName", "Liaray");
//		offerMap.put("pw", "123456");
//		String demandArr[] = {"userID","phone","email","pw"};
//		String infoArr[][] =  su.select("user",offerMap, demandArr,1 ,2);
//		for(String[] info_arr : infoArr) {
//			for(String info : info_arr) {
//				System.out.println(info);
//			}
//		}
//	}
}
