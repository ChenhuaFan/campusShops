package Services.ServiceImp;

import java.util.HashMap;
import java.util.Map;

import Services.IUserService;
import Utils.sqlUtils;

public class UserService implements IUserService {

	//用户登陆认证
	@Override
	public String[][] userLogin(String userName, String pw) {
		sqlUtils su = new sqlUtils();
		su.connect();
		Map<String, String> userAndPw = new HashMap<String, String>();
		userAndPw.put("userName", userName);
		userAndPw.put("pw", pw);
		String demandArr[] = {"userID","userName","role","headPortrait"};
		String infoArr[][] = su.select("user", userAndPw, demandArr, 1, 1);
		su.close();
		return infoArr;
	}

}
