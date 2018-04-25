package Services.ServiceImp;

import java.util.HashMap;
import java.util.Map;

import Dao.OrderDao;
import Services.IOrderService;
import Utils.orderUtils;

public class OrderService implements IOrderService {

	@Override
	public String[][] createOrder(int userId, int shopId, String orderContent, String orderAmount, String remark) {
		//变量声明
		Map<String, String> orderMap = null;
		OrderDao od = null;
		orderUtils ou = null;
		OrderService os = null;
		String orderInfo[][] = null;
		
		
		//获得当前时间，生成订单号
		ou = new orderUtils();
		String orderNumber = ou.getOrderNumber(userId, shopId);
		String generatedTime = ou.getTime();
		//订单信息存入map
		orderMap = new HashMap<String, String>();
		orderMap.put("orderNumber", orderNumber);
		orderMap.put("generatedTime", generatedTime);
		orderMap.put("orderContent", orderContent);
		orderMap.put("orderAmount", orderAmount);
		orderMap.put("orderStatus", "1");
		if(remark != "") {
			orderMap.put("remark", remark);
		}
		orderMap.put("userId", String.valueOf(userId));
		orderMap.put("shopId", String.valueOf(shopId));
		od = new OrderDao();
		//插入新的订单信息
		od.createAnOrder(orderMap);
		
		//返回订单信息
		os = new OrderService();
		orderInfo = os.queryOrderByOrderNumber(orderNumber, 1, 1);
		
		return orderInfo;
	}

	//根据订单号查询订单
	@Override
	public String[][] queryOrderByOrderNumber(String orderNumber, int index, int limit) {
		//变量声明
		String orderInfo[][] = null;
		Map<String, String> orderNumberMap = new HashMap<String, String>();
		OrderDao od = null;
		
		od = new OrderDao();
		String demandArr[] = {"orderId", "orderNumber", "generatedTime", "orderContent", "orderAmount", "orderStatus", "remark" ,"userId", "shopId"};
		orderNumberMap.put("orderNumber", orderNumber);
		orderInfo = od.queryOrder(orderNumberMap, demandArr, index, limit);
		
		return orderInfo;
	}

	//根据用户ID查看订单
	@Override
	public String[][] queryOrderByUserId(int userId, int index, int limit) {
		//变量声明
		OrderDao od = null;
		OrderService os = null;
		
		String orderInfo[][] = null;
		Map<String, String> userIdMap = new HashMap<String, String>();
		String duplicateField[] = {"userId", String.valueOf(userId)};
		os = new OrderService();
		int count = os.getCountOfOrder(duplicateField);
		if(count <= index) {
			return orderInfo;
		}
		if(count-index < limit) {
			limit = count-index;
		}
		
		od = new OrderDao();
		String demandArr[] = {"orderId", "orderNumber", "generatedTime", "orderContent", "orderAmount", "orderStatus", "remark" ,"userId", "shopId"};
		userIdMap.put("userId", String.valueOf(userId));
		orderInfo = od.queryOrder(userIdMap, demandArr, index, limit);
		
		return orderInfo;
	}

	//根据商家ID查看订单
	@Override
	public String[][] queryOrderByShopId(int shopId, int index, int limit) {
		//变量声明
		OrderDao od = null;
		OrderService os = null;
		String orderInfo[][] = null;
		Map<String, String> shopIdMap = new HashMap<String, String>();
		String duplicateField[] = {"shopId", String.valueOf(shopId)};
		os = new OrderService();
		//将符合shopId状态的订单数和index&limit比较
		int count = os.getCountOfOrder(duplicateField);
		if(count <= index) {
			return orderInfo;
		}
		if(count-index < limit) {
			limit = count-index;
		}
		
		od = new OrderDao();
		String demandArr[] = {"orderId", "orderNumber", "generatedTime", "orderContent", "orderAmount", "orderStatus", "remark" ,"userId", "shopId"};
		shopIdMap.put("shopId", String.valueOf(shopId));
		orderInfo = od.queryOrder(shopIdMap, demandArr, index, limit);
		
		return orderInfo;
	}

	//根据状态查看订单
	@Override
	public String[][] queryOrderByStatus(int orderStatus, int index, int limit) {
		//变量声明
		String orderInfo[][] = null;
		Map<String, String> statusMap = new HashMap<String, String>();
		OrderDao od = null;
		OrderService os = null;
		orderUtils ou = new orderUtils();
		//检查status数值是否合法
		if(!ou.checkStatus(orderStatus)) {
			return orderInfo;
		}
		//将符合status状态的订单数和index&limit比较
		String duplicateField[] = {"orderStatus", String.valueOf(orderStatus)};
		os = new OrderService();
		int count = os.getCountOfOrder(duplicateField);
		if(count <= index) {
			return orderInfo;
		}
		if(count-index < limit) {
			limit = count-index;
		}
		od = new OrderDao();
		String demandArr[] = {"orderId", "orderNumber", "generatedTime", "orderContent", "orderAmount", "orderStatus", "remark" ,"userId", "shopId"};
		statusMap.put("orderStatus", String.valueOf(orderStatus));
		orderInfo = od.queryOrder(statusMap, demandArr, index, limit);
		
		return orderInfo;
	}

	@Override
	public int modifyOrderStatus(int status, int orderId) {
		//变量声明
		Map<String, String> statusMap = new HashMap<String, String>();
		OrderDao od = null;
		orderUtils ou = null;
		
		ou = new orderUtils();
		if(!ou.checkStatus(status)) {
			return 0;
		}
		
		od = new OrderDao();
		int line = od.changeOrder(statusMap, orderId);
		return line;
	}

	//根据订单ID查询订单
	@Override
	public String[][] queryOrderByOrderId(int orderId) {
		//变量声明
		String orderInfo[][] = null;
		Map<String, String> orderIdMap = new HashMap<String, String>();
		OrderDao od = null;
		
		od = new OrderDao();
		String demandArr[] = {"orderId", "orderNumber", "generatedTime", "orderContent", "orderAmount", "orderStatus", "remark" ,"userId", "shopId"};
		orderIdMap.put("orderId", String.valueOf(orderId));
		od.queryOrder(orderIdMap, demandArr, 1, 1);
		
		return orderInfo;
	}

	//获得符合条件的订单数量
	@Override
	public int getCountOfOrder(String duplicateField[]) {
		OrderDao od = new OrderDao();
		int line = 0;
		line = od.getCount(duplicateField);
		return line;
	}

	

}
