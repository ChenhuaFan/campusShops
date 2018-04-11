package Services;

public interface IUserService {
	//用户登陆服务
	public String[][] userLogin(String userName, String pw);
	//用户注册服务
	public String[][] userRegister(String userName, String pw, String email, String phone, String gender);
}
