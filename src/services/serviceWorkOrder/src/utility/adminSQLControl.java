package utility;

import java.sql.ResultSet;

public class adminSQLControl {
	
	private adminSQLControl() {}
	
	
	static int sqlQuery(int AdminID) {
		String sql ="select AdminRank from administratorrank where AdminID='"+AdminID+"';";
		ResultSet rsSet =doSQL.sQuery(sql);
		int info = tools.tGetInt(rsSet,"AdminRank");

		return  info;
	}
	
	static boolean sqlInset(int AdminID,int AdminRank) {
		String sql ="insert into administratorrank(AdminID,AdminRank)values('"+AdminID+"','"+AdminRank+"'); ";
		doSQL.sInsert(sql);
		
		return  true;
	}
	
	static boolean sqlUpdate(int AdminID,int AdminRank) {
		String sql ="update administratorrank set AdminRank ='"+AdminRank+"' where AdminID='"+AdminID+"'); ";
		doSQL.sUpdate(sql);
		
		return  true;
	}
	
	static boolean sqlDelete(int AdminID) {
		String sql ="delete * from administratorrank  whereAdminID='"+AdminID+"'); ";
		doSQL.sDelete(sql);
		
		return  true;
	}
}
