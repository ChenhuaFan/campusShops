package Utils;


public class regexStr {
	
	///(.*?((select)|(from)|(count)|(delete)|(update)|(drop)|(truncate)).*?){2,}/i
	
	//用户名是否合法
	public boolean checkUserName(String str) {
		boolean flag = false;
		flag = str.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");
		return flag;
	}
	//验证电子邮箱
	public boolean checkEmail(String email) {
		boolean flag = false;
		flag = email.matches("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");
		return flag;
	}
	
	//验证手机号
	public boolean checkPhone(String phone) {
		boolean flag = false;
		flag = phone.matches("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
		return flag;
	}
	
	//验证数字
	public boolean checkNum(int num) {
		boolean flag = false;
		flag = String.valueOf(num).matches("^[0-9]*$ ");
		return flag;
	}
	
	
	public boolean checkHeadPortrait(String headPortrait) {
		boolean flag = false;
		flag = headPortrait.matches("^[a-zA-Z0-9_\\\\u4e00-\\\\u9fa5]+$");
		return flag;
	}
	
	//检查性别
	public boolean checkGender(String gender) {
		boolean flag = false;
		if(gender.equals("男") || gender.equals("女")) {
			flag = true;
		}
		return flag;
	}
	
	public boolean checkRole(String role) {
		boolean flag = false;
		flag = role.matches("[\buser\bVIP\bsaller\badmin\b]");
		if(role.equals("user") || role.equals("vip") || role.equals("saller") || role.equals("admin")) {
			flag = true;
		}
		return flag;
	}
}
