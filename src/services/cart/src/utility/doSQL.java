package utility;

import java.util.*;
import java.sql.*;

import DataBase.Mysql;


public class doSQL {
	
	private doSQL() {}
	
	
	//query  info
	static ResultSet  sQuery(String sql) {
		ResultSet result=null;
		//String[] info =null;
		Connection conn = Mysql.getConn();
		Statement stat;
		try {
			stat = conn.createStatement();
		
			result =  stat.executeQuery(sql);
			/*while(result.next()){
							
					info = new String[]{ result.getString("Goods") };			 					
			}*/
		} catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		} 
		
		return result;
	}
	
	//insert   info
	static boolean sInsert(String sql) {
	 
		//String[] info =null;
		Connection conn = Mysql.getConn();
		Statement stat;
		try {
			stat = conn.createStatement();
			 
		
			stat.execute(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		} 
		
		return true;
	}
	
	//update   info
	static boolean sUpdate(String sql) {
		
		Connection conn = Mysql.getConn();
		Statement stat;
		try {
			stat = conn.createStatement(); 
		
			stat.executeUpdate(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		} 
		return true;
	}
	
	//delete shopping_cart info
	static boolean sDelete(String sql) {
		Connection conn = Mysql.getConn();
		Statement stat;
		try {
			stat = conn.createStatement(); 
		
			stat.execute(sql);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		} 
		return true;
	}
}
