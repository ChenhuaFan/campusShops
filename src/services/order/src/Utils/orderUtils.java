package Utils;

import java.util.Calendar;

public class orderUtils {
	
	//获得当前时间
	public String getTime() {
		Calendar c = Calendar.getInstance();
		String nowTime = c.getTime().toString();
		return nowTime;
	}
	
	//生成订单号
	public String getOrderNumber(int userId, int shopId) {
		Calendar c = Calendar.getInstance();
		String orderNumer = String.valueOf(c.getTimeInMillis())+String.valueOf(userId)+String.valueOf(shopId);
		return orderNumer;
	}
	
	//检查状态是否合法
	public boolean checkStatus(int status) {
		boolean flag = false;
		if(status == 1 || status == 2 || status == 3 || status == 4 || status == 5 || status == 6) {
			flag = true;
		}
		return flag;
	}
}
