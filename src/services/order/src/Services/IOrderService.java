package Services;


public interface IOrderService {
	//创建一个订单
	public String[][] createOrder(int userID, int shopId, String orderContent, String Amount, String remark);
	//根据订单号查询订单
	public String[][] queryOrderByOrderNumber(String orderNumber, int index, int limit);
	//根据用户ID查看订单
	public String[][] queryOrderByUserId(int userId, int index, int limit);
	//根据商家ID查看订单
	public String[][] queryOrderByShopId(int shopId, int index, int limit);
	//根据状态查看订单
	public String[][] queryOrderByStatus(int status, int index, int limit);
	//改变订单状态
	public int modifyOrderStatus(int status, int orderId);
	//根据订单ID查询订单
	public String[][] queryOrderByOrderId(int orderId);
	//检查符合条件订单的数量
	public int getCountOfOrder(String duplicateField[]);
}
