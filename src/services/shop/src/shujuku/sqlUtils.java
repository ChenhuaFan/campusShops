package shujuku;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class sqlUtils {
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
}
