package Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserDao {
	public boolean getUser(String username, String password) throws Exception {
	    String USER = "root";
	    String PASSWORD = "123456";
    	String URL = "jdbc:mysql://localhost:3306/campusshops?useUnicode=true&characterEncoding=utf8&useSSL=true";
        Class.forName("com.mysql.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select userName, pw from user");
        while(rs.next()){
            System.out.println(rs.getString("userName")+" 年龄："+rs.getString("pw"));
        }
		return false;
	}
}
