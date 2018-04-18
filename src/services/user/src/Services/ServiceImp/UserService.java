package Services.ServiceImp;

import java.util.HashMap;
import java.util.Map;

import Dao.UserDao;
import Services.IUserService;
import Utils.deleteEmptyEle;
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
			String demandArr[] = {"userID","userName","role","headPortrait", "isActive", "isDelete"};
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
			//isActive字段为0
			try {
				if(userInfo[0][4].equals("0")) {
					userInfo[0][0] = "freeze";
					return userInfo;
				} else if (userInfo[0][5].equals("1")) {//isDelete字段为1
					userInfo[0][0] = "deleted";
					return userInfo;
				} else {
					return userInfo;
				}
			} catch(NullPointerException e) {
				//未认证成功
				userInfo[0][0] = "wrong";
				return userInfo;
			}
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

	/*
	 * 用户特征字段合法性判断和查重
	 * 若字段格式不合法，返回2
	 * 若特征字段重复返回1
	 * 正常返回0
	 * */
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

	//更改用户名
	@Override
	public String[][] modifyUserName(int id, String userName) {
		//变量声明
		UserService us = null;
		UserDao ud = null;
		Map<String, String> userNameMap = null;
		String userInfo[][] = null;
		int result = 1, line;
		
		us = new UserService();
		//格式合法判断和查重
		result = us.duplicateCheck("userName", userName);
		if(result == 0) {
			userNameMap = new HashMap<String, String>();
			userNameMap.put("userName", userName);
			ud = new UserDao();
			//更新userName
			line = ud.updateInfo(userNameMap, id);
			//更新成功，返回用户身份信息
			if(line == 1) {
				userInfo = us.getIdentifyByID(id);
				return userInfo;
			}
		}
		return userInfo;
	}

	//更改用户密码
	@Override
	public String[][] modifyUserPassword(int id, String pw) {
		//变量声明
		UserService us = null;
		UserDao ud = null;
		sha256Util sha256 = null;
		Map<String, String> pwMap = null;
		String userInfo[][] = null;
		int line;
		
		//对密码进行sha256加密
		sha256 = new sha256Util();
		pw = sha256.getSHA256StrJava(pw);
		pwMap = new HashMap<String, String>();
		pwMap.put("pw", pw);
		
		ud = new UserDao();
		//更新密码
		line = ud.updateInfo(pwMap, id);
		//更新成功，返回用户身份信息
		if(line == 1) {
			us = new UserService();
			userInfo = us.getIdentifyByID(id);
			return userInfo;
		}
		return userInfo;
	}

	////更改用户角色
	@Override
	public String[][] modifyUserRole(int id, String role) {
		//变量声明
		UserService us = null;
		UserDao ud = null;
		regexStr reg = null;
		Map<String, String> roleMap = null;
		String userInfo[][] = null;
		int line;
		
		//判断role字段是否合法
		reg = new regexStr();
		if(reg.checkRole(role)) {
			roleMap = new HashMap<String, String>();
			roleMap.put("role", role);
			ud = new UserDao();
			line = ud.updateInfo(roleMap, id);
			//更新成功，返回身份信息
			if(line == 1) {
				us = new UserService();
				userInfo = us.getIdentifyByID(id);
				return userInfo;
			}
		}
		return userInfo;
	}

	//根据角色查询用户
	@Override
	public String[][] queryUserByRole(String role, int index, int lim) {
		//变量声明
		UserService us = null;
		UserDao ud = null;
		regexStr reg = null;
		deleteEmptyEle dee = null;
		String userInfo[][] = null;
		Map<String, String> roleMap = null;
		boolean flag = false;
		
		//判断role字段格式是否合法
		reg = new regexStr();
		flag = reg.checkRole(role);
		if(!flag) {
			return null;
		} else {
			String demandArr[] = {"userID", "userName", "email", "phone", "gender", "role", "headPortrait"};
			roleMap = new HashMap<String, String>();
			roleMap.put("role", role);
			//查询role类型的用户
			ud = new UserDao();
			userInfo = ud.queryUser(roleMap, demandArr, index, lim);
			
			//删除值为null的结果
			dee = new deleteEmptyEle();
			userInfo = dee.deleteEle(userInfo, demandArr.length);
		}
		return userInfo;
	}

	//删除用户
	@Override
	public String[][] deleteUser(int id) {
		//变量声明
		UserDao ud = null;
		Map<String, String> deleteMap = null;
		String userInfo[][] = null;
		int line;
		
		ud = new UserDao();
		deleteMap = new HashMap<String, String>();
		deleteMap.put("isDelete", "1");
		line = ud.updateInfo(deleteMap, id);
		if(line == 1) {
			String demandArr[] = {"userID", "userName", "role", "isDelete"};
			Map<String, String> idMap = new HashMap<String, String>();
			idMap.put("userID", String.valueOf(id));
			userInfo = ud.queryUser(idMap, demandArr, 1, 1);
			return userInfo;
		}
		return null;
	}

	//更改用户状态
	@Override
	public String[][] changeStatus(int id) {
		//变量声明
		UserDao ud = null;
		Map<String, String> statusMap = null;
		String userInfo[][] = null;
		int line;
		
		//判断ID是否存在
		String idFiled[] = {"userID", String.valueOf(id)};
		ud = new UserDao();
		line = ud.fieldRecheck(idFiled);
		if(line == 0) {//不存在的情况
			return userInfo;
		} else {//ID存在,查询用户当前status
			Map<String, String> idMap = new HashMap<String, String>();
			String demandArr[] = {"userID", "userName", "role", "isActive"};
			idMap.put("userID", String.valueOf(id));
			userInfo = ud.queryUser(idMap, demandArr, 1, 1);
			System.out.println(userInfo[0][3]);
			if(userInfo[0][3].equals("0")) {//用户当前为未激活状态
				statusMap = new HashMap<String, String>();
				statusMap.put("isActive", "1");
				line = ud.updateInfo(statusMap, id);
				if(line == 1) {
					userInfo[0][3] = "1";
					return userInfo;
				}
			} else if(userInfo[0][3].equals("1")) {//用户当前为激活状态
				statusMap = new HashMap<String, String>();
				statusMap.put("isActive", "0");
				line = ud.updateInfo(statusMap, id);
				if(line == 1) {
					userInfo[0][3] = "0";
					return userInfo;
				}
			}
		}
		
		return null;
	}

	//获得所有用户信息
	@Override
	public String[][] getAllUser(int index, int lim) {
		//变量声明
		UserDao ud = null;
		deleteEmptyEle dee = null;
		String userInfo[][] = null;
		Map<String, String> nullMap = null;
		
		String demandArr[] = {"userID", "userName", "email", "phone", "role", "gender", "isActive", "isDelete", "headPortrait"};
		ud = new UserDao();
		userInfo = ud.queryUser(nullMap, demandArr, index, lim);
		
		//删除值为null的结果
		dee = new deleteEmptyEle();
		userInfo = dee.deleteEle(userInfo, demandArr.length);
		
		return userInfo;
	}
}
