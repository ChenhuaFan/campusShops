package utility;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import Dao.Aservice;
import Dao.serviceRecord;
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
	 //get serviceinfo
	static Aservice getaServiceInfo(ResultSet rsSet) {
		Aservice aservice = new Aservice();
		 try {
			while(rsSet.next()){
				aservice.setDescription(rsSet.getString("Description"));		
				aservice.setServiceID(rsSet.getInt("ServiceID"));
				aservice.setStartDate(rsSet.getString("StartDate"));
				aservice.setTitle(rsSet.getString("Title"));
				aservice.setUserID(rsSet.getInt("UserID"));
				aservice.setStatus(rsSet.getInt("Status"));
			 }//
		 
			return aservice;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}
		 
		 return null;
		
	}
	
	//get serviceReccord
	static List<serviceRecord> getServiceRecords(ResultSet rsSet) {
		List<serviceRecord> Records = new ArrayList<serviceRecord>();
		serviceRecord sRecord;
		try {
			while(rsSet.next()){
				sRecord = new serviceRecord();
				sRecord.setID(rsSet.getInt("ID"));	
				//System.out.println("id "+sRecord.getID());
				sRecord.setContent(rsSet.getString("content"));		
				sRecord.setServiceID(rsSet.getInt("ServiceID"));
				sRecord.setTime(rsSet.getString("time"));
				sRecord.setUserID(rsSet.getInt("userID"));
				Records.add(sRecord);
			 }//
		 
			return Records;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}
		 
		 return null;
		
	}
	 
	static String getNowTime() {
		
		//使用Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
}
