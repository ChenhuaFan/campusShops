package Services;


public interface IUserService {
	
	//用户登陆服务
	public String[][] userLogin(String userName, String pw);
	
	//用户注册服务
	public String[][] userRegister(String userName, String pw, String email, String phone, String gender);
	
	//用户特征字段查重
	public int duplicateCheck(String key, String value);
	//得到用户详细信息
	public String[][] getUserDetails(int id);
	
	//得到用户简略信息
	public String[][] getUserBrief(int id);
	
	//更改用户基本信息
	public String[][] modifyEssentialInfo(int id, String email, String phone, String gender, String headPortrait);
	
	//根据ID获得认证信息
	public String[][] getIdentifyByID(int id);
	
	//更改用户名
	public String[][] modifyUserName(int id, String userName);
	
	//更改用户密码
	public String[][] modifyUserPassword(int id, String pw);
	
	//更改用户角色
	public String[][] modifyUserRole(int id, String role);
	//删除商家
	public String[][] deleteUser(int id);
	
	//根据角色查询用户
	public String[][] queryUserByRole(String role, int index, int lim);


	public String[][] modifyUserName(int id, String name,  String type, String shopkeeper, String address,
			String phoneNumber,String picture);

	public String[][] getAllShop(int index, int lim);

	public String[][] getAllShop(int index, int lim, int id);

	String[][] shopAdd(String name, String type, String shopkeeper, String address, String phoneNumber, int rank,
			String picture, int isOpen);

	String[][] getIdentifyByID(String name);
}