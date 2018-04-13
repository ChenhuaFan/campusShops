package Services;

public interface IUserService {
	//用户登陆服务
	public String[][] userLogin(String userName, String pw);
	//用户注册服务
	public String[][] userRegister(String userName, String pw, String email, String phone, String gender);
	//用户特征字段查重
	/*
	 * checkField[]长度为2
	 * checkField[0]为字段名
	 * checkField[1]为字段值
	 * */
	public int duplicateCheck(String key, String value);
	//得到用户详细信息
	public String[][] getUserDetails(int id);
	
	//得到用户简略信息
	public String[][] getUserBrief(int id);
}
