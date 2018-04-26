package Utils;

public class regexStr {
	
	///(.*?((select)|(from)|(count)|(delete)|(update)|(drop)|(truncate)).*?){2,}/i
	
	//�û����Ƿ�Ϸ�
	public boolean checkUserName(String str) {
		boolean flag = false;
		flag = str.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$");
		return flag;
	}
	//��֤��������
	public boolean checkEmail(String email) {
		boolean flag = false;
		flag = email.matches("^[A-Za-z\\d]+([-_.][A-Za-z\\d]+)*@([A-Za-z\\d]+[-.])+[A-Za-z\\d]{2,4}$");
		return flag;
	}
	
	//��֤�ֻ���
	public boolean checkPhone(String phone) {
		boolean flag = false;
		flag = phone.matches("^1([358][0-9]|4[579]|66|7[0135678]|9[89])[0-9]{8}$");
		return flag;
	}
	
	//��֤����
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
	
	//����Ա�
	public boolean checkGender(String gender) {
		boolean flag = false;
		flag = gender.matches("[��Ů]");
		return flag;
	}
	
	public boolean checkRole(String role) {
		boolean flag = false;
		flag = role.matches("[\buser\bVIP\bsaller\badmin\b]");
		if(role.equals("user") || role.equals("VIP") || role.equals("saller") || role.equals("admin")) {
			flag = true;
		}
		return flag;
	}
}