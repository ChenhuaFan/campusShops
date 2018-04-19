package utility;

import java.sql.ResultSet;

public class cartSQLControl {
	private cartSQLControl() {}
	static String sqlQuery(String userID) {
		String sql ="select Goods from shopping_cart where userID='"+userID+"';";
		ResultSet rsSet =doSQL.sQuery(sql);
		String info = tools.tGetString(rsSet);

		return  info;
	}
	
	static boolean sqlInset(String userID,String Goods) {
		String sql ="insert into shopping_cart(userID,Goods)values('"+userID+"','"+Goods+"'); ";
		doSQL.sInsert(sql);
		
		return  true;
	}
	
	static boolean sqlUpdate(String userID,String Goods) {
		String sql ="update shopping_cart set Goods ='"+Goods+"' where userID='"+userID+"'); ";
		doSQL.sUpdate(sql);
		
		return  true;
	}
	
	static boolean sqlDelete(String userID,String Goods) {
		String sql ="delete * from shopping_cart  where userID='"+userID+"'); ";
		doSQL.sDelete(sql);
		
		return  true;
	}
}
