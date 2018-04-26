package DataBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.sql.*;
import config.defaultSets;


public class Mysql {
	private static Connection conn = null;
	
	private Connection getInstance(){
		
		Connection conn =null;
		try {
			String url = defaultSets.getInstance().getURL();
			String name = defaultSets.getInstance().getUSER();
			String password = defaultSets.getInstance().getPASSWORD();
			Class.forName(defaultSets.getInstance().getDriverloads());
			
			conn= DriverManager.getConnection(url,name,password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
		
	public static Connection  getConn(){
		try {
			if(conn == null||conn.isClosed()){
				Mysql.conn = new Mysql().getInstance();	 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 	
		return Mysql.conn;
	}
	public static void main(String args[]) throws SQLException{
		Connection con = Mysql.getConn();
		Statement stat = con.createStatement();
		String sql ="select * from Service";
		
		ResultSet rs = stat.executeQuery(sql);
		while(rs.next()){
			System.out.println(rs.getString(1));
		}
	}
}
