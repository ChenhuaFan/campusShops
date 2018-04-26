package utility;

 

import java.util.List;

import Dao.Aservice;
import Dao.serviceRecord;
import net.sf.json.JSONObject;

public class do_service_info {
	
	public static Aservice getServiceInfo(int ServiceID) {
		JSONObject json = new JSONObject();

		Aservice servInfo = serviceSQLControl.sqlServiceQuery(ServiceID);	 
		  
		
		return servInfo;
		
	}
	
	
	public static boolean updateServiceInfo(Aservice serv,String method) {
		if(method.equalsIgnoreCase("add")) {
			serviceSQLControl.sqlServiceInset(serv);
			
		}else if(method.equalsIgnoreCase("delete")){
			serviceSQLControl.sqlServiceUpdate(serv.getServiceID(),serv.getUserID());
			
		}
//		else if(method.equalsIgnoreCase("status")){
//			serviceSQLControl.sqlServiceQuery(serv.getServiceID());
//		}else if(method.equalsIgnoreCase("record")){
//			serviceSQLControl.sqlServiceQuery(serv.getServiceID());
//		}
	 
		return true;
	}
	public static boolean insertRecord(serviceRecord sRecord) {
		 
		serviceSQLControl.sqlSRecordInset(sRecord);
				 
//		else if(method.equalsIgnoreCase("status")){
//			serviceSQLControl.sqlServiceQuery(serv.getServiceID());
//		}else if(method.equalsIgnoreCase("record")){
//			serviceSQLControl.sqlServiceQuery(serv.getServiceID());
//		}
	 
		return true;
	}
	
	public static JSONObject getRecords(int ServiceID,int userID) {
		JSONObject jsonAll = new JSONObject();
		JSONObject jsonOne = new JSONObject();
		List<serviceRecord>  records  = serviceSQLControl.serviceRecordQuery(ServiceID );	 
		if(records==null||records.size()==0  )
			return null;
		for(int i = 0;i<records.size();i++) {
			serviceRecord record = records.get(i); 
			 
			jsonOne.put("ServiceID", record.getServiceID());
			jsonOne.put("UserID", record.getUserID());
			jsonOne.put("Description", record.getContent());
			jsonOne.put("Time", record.getTime());
			jsonAll.put(""+record.getID(), jsonOne);
		}
		
		return jsonAll;
		
	}
}
