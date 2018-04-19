package utility;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
import utility.doSQL;

public class tools {
	
	//ResultSet to String
	 static String tGetString(ResultSet rsSet) {
		String rs =null;
		 try {
			while(rsSet.next()){
				rs+=rsSet.getString("Goods");					
			 }
			
			return rs;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	
		
	}
	 
	//ResultSet to int
		 static int tGetInt(ResultSet rsSet,String param) {
			int rs =-1;
			 try {
				while(rsSet.next()){
					rs=rsSet.getInt(param);					
				 }
				
				return rs;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
			return -1;
		}
 
	 
	static String getNowTime() {
		
		//使用Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
}
