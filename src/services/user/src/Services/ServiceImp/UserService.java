package Services.ServiceImp;

import java.util.HashMap;
import java.util.Map;

import Dao.UserDao;
import Services.IUserService;

public class UserService implements IUserService {

	//用户登陆认证
	@Override
	public String[][] userLogin(String userName, String pw) {
		//变量声明
		Map<String, String> userAndPw = null;
		UserDao ud = null;
		String infoArr[][] = null;
		String demandArr[] = {"userID","userName","role","headPortrait"};
		//构建Map
		userAndPw = new HashMap<String, String>();
		userAndPw.put("userName", userName);
		userAndPw.put("pw", pw);
		//调用UserDao层
		ud = new UserDao();
		infoArr = ud.queryUser(userAndPw, demandArr, 1, 1);
		return infoArr;
	}

}
