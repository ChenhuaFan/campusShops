package Services.ServiceImp;

import java.util.HashMap;
import java.util.Map;

import Dao.UserDao;
import Services.IUserService;
import Utils.regexStr;
import Utils.sha256Util;

public class UserService implements IUserService {

	//用户登陆认证
	@Override
	public String[][] userLogin(String userName, String pw) {
		//变量声明
		Map<String, String> userAndPw = null;
		UserDao ud = null;
		String userInfo[][] = null;
		sha256Util sha256 = null;
		regexStr reg = null;
		
		reg = new regexStr();
		//userName字段防sql注入
		if(reg.checkUserName(userName)) {
			String demandArr[] = {"userID","userName","role","headPortrait"};
			String pw_sha256="";
			//用户密码sha256加密
			sha256 = new sha256Util();
			pw_sha256 = sha256.getSHA256StrJava(pw);
			//构建Map
			userAndPw = new HashMap<String, String>();
			userAndPw.put("userName", userName);
			userAndPw.put("pw", pw_sha256);
			//调用UserDao层
			ud = new UserDao();
			userInfo = ud.queryUser(userAndPw, demandArr, 1, 1);
			return userInfo;
			
		} else {
			return null;
		}
	}

	//用户注册服务
	@Override
	public String[][] userRegister(String userName, String pw, String email, String phone, String gender) {
		//变量声明
		sha256Util sha256 = null;
		UserDao ud = null;
		UserService us = null;
		regexStr reg = null;
		//存储用户注册的信息
		Map<String, String> infoMap = null;
		//用户身份信息
		String userInfo[][] = null;
		String pw_sha256="";
		int count = 0;
		
		reg = new regexStr();
		//检查用户名邮箱和手机号是否合法
		if(!(reg.checkUserName(userName) && reg.checkEmail(email) && reg.checkPhone(phone))) {
			return null;
		} else {
			//用户名是否存在
			
			//实例化自身对象
			us = new UserService();
			//对userName字段查重
			count = us.duplicateCheck("userName", userName);
			if(count > 0) {
				//如果user字段重复,直接返回
				return userInfo;
			} else {
				//对用户密码进行sha256加密
				sha256 = new sha256Util();
				pw_sha256 = sha256.getSHA256StrJava(pw);
				//加信息存入map中
				infoMap = new HashMap<String, String>();
				infoMap.put("userName", userName);
				infoMap.put("pw", pw_sha256);
				//email非空判断
				if(email != "") {
					infoMap.put("email", email);
				}
				infoMap.put("phone", phone);
				infoMap.put("gender", gender);
				
				//调用UserDao
				ud = new UserDao();
				ud.insertUser(infoMap);
				
				//实例化自己的对象,获得用户身份信息
				userInfo = us.userLogin(userName, pw);
				return userInfo;
			}
			
		}
		
	}

	//用户特征字段查重
	@Override
	public int duplicateCheck(String key, String value) {
		//变量声明
		UserDao ud = null;
		regexStr reg = null;
		int count = 0;
		boolean flag = false;
		String checkField[] = {key, value};
		if(key == "userName") {
			reg = new regexStr();
			flag = reg.checkUserName(value);
			if(!flag) {
				return 2;
			}
		} else if(key == "phone") {
			reg = new regexStr();
			flag = reg.checkPhone(value);
			if(!flag) {
				return 2;
			}
		} else if(key == "email") {
			reg = new regexStr();
			flag = reg.checkEmail(value);
			if(!flag) {
				return 2;
			}
		} else {
			reg = new regexStr();
			
		}
		
		//调用UserDao
		ud = new UserDao();
		count = ud.fieldRecheck(checkField);
		
		return count;
	}

	//得到用户详细信息
	@Override
	public String[][] getUserDetails(int id) {
		//变量声明
		Map<String, String> userIDMap = null;
		UserDao ud = null;
		String detailInfo[][] = null;
		
		//存储用户ID
		userIDMap = new HashMap<String, String>();
		userIDMap.put("userID", String.valueOf(id));
		String demandArr[] = {"userID", "userName", "email", "phone", "gender", "role", "headPortrait"};
		ud = new UserDao();
		detailInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
		
		return detailInfo;
	}

	//得到用户简略信息
	@Override
	public String[][] getUserBrief(int id) {
		//变量声明
		Map<String, String> userIDMap = null;
		UserDao ud = null;
		String briefInfo[][] = null;
		
		
		//存储用户ID
		userIDMap = new HashMap<String, String>();
		userIDMap.put("userID", String.valueOf(id));
		String demandArr[] = {"userID", "userName", "gender", "role", "headPortrait"};
		ud = new UserDao();
		briefInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
		
		return briefInfo;
		
	}

	//更改用户基本信息
	@Override
	public String[][] modifyEssentialInfo(int id, String email, String phone, String gender, String headPortrait) {
		//变量声明
		Map<String, String> updateMap = null;
		String userInfo[][] = null;
		UserDao ud = null;
		UserService us = new UserService();
		regexStr reg = new regexStr();
		
		updateMap = new HashMap<String, String>();
		//是否提交了email字段
		if(email != "") {
			if(us.duplicateCheck("email", email) == 0) {
				updateMap.put("email", email);
				
			}
		}
		//是否提交了phone字段
		if(phone != "") {
			if(us.duplicateCheck("phone", phone) == 0) {
				updateMap.put("phone", phone);
			}
		}
		//是否提交了gender字段
		if(gender != "") {
			if(reg.checkGender(gender)) {
				updateMap.put("gender", gender);
			}
		}
		//是否提交了headPortrait字段
		if(headPortrait != "") {
			System.out.println("..");
			updateMap.put("headPortrait", headPortrait);
		}
		//若前台无任何信息修改
		if(updateMap.isEmpty()) {
			
			return userInfo;
		} 
		ud = new UserDao();
		//更新成功
		if(ud.updateInfo(updateMap, id) > 0) {
			userInfo = us.getIdentifyByID(id);
			return userInfo;
		}
		return userInfo;
	}

	//根据ID获得认证信息
	@Override
	public String[][] getIdentifyByID(int id) {
		//变量声明
			Map<String, String> userIDMap = null;
			UserDao ud = null;
			String identifyInfo[][] = null;
			//存储用户ID
			userIDMap = new HashMap<String, String>();
			userIDMap.put("userID", String.valueOf(id));
			String demandArr[] = {"userID", "userName", "role", "headPortrait"};
			ud = new UserDao();
			identifyInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
			
			return identifyInfo;
	}

}
