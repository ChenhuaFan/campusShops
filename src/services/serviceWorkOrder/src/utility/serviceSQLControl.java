package utility;

import java.sql.ResultSet;
import java.util.List;

import Dao.Aservice;
import Dao.serviceRecord;

public class serviceSQLControl {
	
	private serviceSQLControl() {}
	
	static Aservice sqlServiceQuery(int serviceID) {
		String sql ="select * from service where ServiceID='"+serviceID+"';";
		ResultSet rsSet =doSQL.sQuery(sql);
		Aservice servInfo = tools.getaServiceInfo(rsSet);

		return  servInfo;
	}
	
	static boolean sqlServiceInset(Aservice servInfo) {
		 //int ServiceID =servInfo.getServiceID();
		 int userID=servInfo.getUserID();
		 String Title=servInfo.getTitle();
		 String Description=servInfo.getDescription();
		 String StartDate=servInfo.getStartDate();
		 int Status=servInfo.getStatus();
		
		
		 String sql ="insert into service(userID,Title,Description,StartDate,Status)values('"+userID+"','"+Title+"','"+Description+"','"+StartDate+"','"+Status+"'); ";
		 doSQL.sInsert(sql);
		
		 return  true;
	}
	
	
	static boolean sqlServiceUpdate(int serviceID,int userID) {
		String sql ="update service set Status ="+1+" where ServiceID="+serviceID+" and UserID="+userID+"; ";
		doSQL.sUpdate(sql);
		
		return  true;
	}
	//慎用
	boolean sqlServiceDelete(int userID) {
		String sql ="delete * from service  where UserID='"+userID+"'); ";
		doSQL.sDelete(sql);
		
		return  true;
	}
	
	static List<serviceRecord> serviceRecordQuery(int serviceID ) {
		String sql ="select * from servicerecord where ServiceID='"+serviceID+"' ;";
		ResultSet rsSet =doSQL.sQuery(sql); 
		List<serviceRecord> sRecords = tools.getServiceRecords(rsSet);
		 

		return sRecords ;
	}
	
	static boolean sqlSRecordInset(serviceRecord sRecord) {
		 //int ServiceID =servInfo.getServiceID();
		 int ServiceID=sRecord.getServiceID();
		 int userID=sRecord.getUserID();
		 String content=sRecord.getContent();
		 String time=sRecord.getTime();
		  	
		 String sql ="insert into servicerecord(ServiceID,userID,content,StartDate)values('"+ServiceID+"','"+userID+"','"+content+"','"+time+"'); ";
		 doSQL.sInsert(sql);
		
		 return  true;
	}
}
