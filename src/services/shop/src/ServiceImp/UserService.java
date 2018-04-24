package ServiceImp;


import java.util.HashMap;
import java.util.Map;

import Dao.UserDao;
import Services.IUserService;
import Utils.deleteEmptyEle;
import Utils.regexStr;


public class UserService implements IUserService {

	//�û���½��֤
	@Override
	public String[][] userLogin(String userName, String pw) {
		//��������
		Map<String, String> userAndPw = null;
		UserDao ud = null;
		String userInfo[][] = null;
		
		regexStr reg = null;
		
		reg = new regexStr();
		//userName�ֶη�sqlע��
		if(reg.checkUserName(userName)) {
			String demandArr[] = {"userID","userName","role","headPortrait", "isActive", "isDelete"};
			String pw_sha256="";
			//�û�����sha256����


			//����Map
			userAndPw = new HashMap<String, String>();
			userAndPw.put("userName", userName);
			userAndPw.put("pw", pw_sha256);
			//����UserDao��
			ud = new UserDao();
			userInfo = ud.queryUser(userAndPw, demandArr, 1, 1);
			//isActive�ֶ�Ϊ0
			try {
				if(userInfo[0][4].equals("0")) {
					userInfo[0][0] = "freeze";
					return userInfo;
				} else if (userInfo[0][5].equals("1")) {//isDelete�ֶ�Ϊ1
					userInfo[0][0] = "deleted";
					return userInfo;
				} else {
					return userInfo;
				}
			} catch(NullPointerException e) {
				//δ��֤�ɹ�
				userInfo[0][0] = "wrong";
				return userInfo;
			}
		} else {
			return null;
		}
	}

	//�û�ע�����
	@Override
	public String[][] userRegister(String userName, String pw, String email, String phone, String gender) {
		//��������

		UserDao ud = null;
		UserService us = null;
		regexStr reg = null;
		//�洢�û�ע�����Ϣ
		Map<String, String> infoMap = null;
		//�û������Ϣ
		String userInfo[][] = null;
		String pw_sha256="";
		int count = 0;
		
		reg = new regexStr();
		//����û���������ֻ����Ƿ�Ϸ�
		if(!(reg.checkUserName(userName) && reg.checkEmail(email) && reg.checkPhone(phone))) {
			return null;
		} else {
			//�û����Ƿ����
			
			//ʵ�����������
			us = new UserService();
			//��userName�ֶβ���
			count = us.duplicateCheck("userName", userName);
			if(count > 0) {
				//���user�ֶ��ظ�,ֱ�ӷ���
				return userInfo;
			} else {
				//���û��������sha256����

				//����Ϣ����map��
				infoMap = new HashMap<String, String>();
				infoMap.put("userName", userName);
				infoMap.put("pw", pw_sha256);
				//email�ǿ��ж�
				if(email != "") {
					infoMap.put("email", email);
				}
				infoMap.put("phone", phone);
				infoMap.put("gender", gender);
				
				//����UserDao
				ud = new UserDao();
				ud.insertUser(infoMap);
				
				//ʵ�����Լ��Ķ���,����û������Ϣ
				userInfo = us.userLogin(userName, pw);
				return userInfo;
			}
			
		}
		
	}

	/*
	 * �û������ֶκϷ����жϺͲ���
	 * ���ֶθ�ʽ���Ϸ�������2
	 * �������ֶ��ظ�����1
	 * ��������0
	 * */
	@Override
	public int duplicateCheck(String key, String value) {
		//��������
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
		
		//����UserDao
		ud = new UserDao();
		count = ud.fieldRecheck(checkField);
		
		return count;
	}

	//�õ��û���ϸ��Ϣ
	@Override
	public String[][] getUserDetails(int id) {
		//��������
		Map<String, String> userIDMap = null;
		UserDao ud = null;
		String detailInfo[][] = null;
		
		//�洢�û�ID
		userIDMap = new HashMap<String, String>();
		userIDMap.put("userID", String.valueOf(id));
		String demandArr[] = {"userID", "userName", "email", "phone", "gender", "role", "headPortrait"};
		ud = new UserDao();
		detailInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
		
		return detailInfo;
	}

	//�õ��û�������Ϣ
	@Override
	public String[][] getUserBrief(int id) {
		//��������
		Map<String, String> userIDMap = null;
		UserDao ud = null;
		String briefInfo[][] = null;
		
		
		//�洢�û�ID
		userIDMap = new HashMap<String, String>();
		userIDMap.put("userID", String.valueOf(id));
		String demandArr[] = {"userID", "userName", "gender", "role", "headPortrait"};
		ud = new UserDao();
		briefInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
		
		return briefInfo;
		
	}

	//�����û�������Ϣ
	@Override
	public String[][] modifyEssentialInfo(int id, String email, String phone, String gender, String headPortrait) {
		//��������
		Map<String, String> updateMap = null;
		String userInfo[][] = null;
		UserDao ud = null;
		UserService us = new UserService();
		regexStr reg = new regexStr();
		
		updateMap = new HashMap<String, String>();
		//�Ƿ��ύ��email�ֶ�
		if(email != "") {
			if(us.duplicateCheck("email", email) == 0) {
				updateMap.put("email", email);
				
			}
		}
		//�Ƿ��ύ��phone�ֶ�
		if(phone != "") {
			if(us.duplicateCheck("phone", phone) == 0) {
				updateMap.put("phone", phone);
			}
		}
		//�Ƿ��ύ��gender�ֶ�
		if(gender != "") {
			if(reg.checkGender(gender)) {
				updateMap.put("gender", gender);
			}
		}
		//�Ƿ��ύ��headPortrait�ֶ�
		if(headPortrait != "") {
			updateMap.put("headPortrait", headPortrait);
		}
		//��ǰ̨���κ���Ϣ�޸�
		if(updateMap.isEmpty()) {
			
			return userInfo;
		} 
		ud = new UserDao();
		//���³ɹ�
		if(ud.updateInfo(updateMap, id) > 0) {
			userInfo = us.getIdentifyByID(id);
			return userInfo;
		}
		return userInfo;
	}

	//����ID�����֤��Ϣ
	@Override
	public String[][] getIdentifyByID(int id) {
		//��������
			Map<String, String> userIDMap = null;
			UserDao ud = null;
			String identifyInfo[][] = null;
			//�洢�û�ID
			userIDMap = new HashMap<String, String>();
			userIDMap.put("shopId", String.valueOf(id));
			String demandArr[] = {"shopId", "shopName", "shopType", "shopkeeper","shopAddress","shopPhoneNumber","shopRank","shopPicture","isOpen"};
			ud = new UserDao();
			identifyInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
			
			return identifyInfo;
	}
	@Override
	public String[][] getIdentifyByID(String name) {
		//��������
			Map<String, String> userIDMap = null;
			UserDao ud = null;
			String identifyInfo[][] = null;
			//�洢�û�ID
			userIDMap = new HashMap<String, String>();
			userIDMap.put("shopName", name);
			String demandArr[] = {"shopName", "shopType", "shopkeeper","shopAddress","shopPhoneNumber","shopRank","shopPicture","isOpen"};
			ud = new UserDao();
			identifyInfo = ud.queryUser(userIDMap, demandArr, 1, 1);
			
			return identifyInfo;
	}
	//�����û���
	@Override
	public String[][] modifyUserName(int id, String name,String type,String shopkeeper,String address,String phoneNumber,String picture) {
		//��������
		UserService us = null;
		UserDao ud = null;
		Map<String, String> userNameMap = null;
		String userInfo[][] = null;
		int result = 1, line;
		
		us = new UserService();
		
			userNameMap = new HashMap<String, String>();
			userNameMap.put("shopName", name);
			userNameMap.put("shopType", type);
			userNameMap.put("shopKeeper", shopkeeper);
			userNameMap.put("shopAddress", address);
			userNameMap.put("shopPhoneNumber", phoneNumber);
			userNameMap.put("shopPicture", picture);
			
			ud = new UserDao();
			//����userName
			line = ud.updateInfo(userNameMap, id);
			//���³ɹ��������û������Ϣ
			if(line == 1) {
				userInfo = us.getIdentifyByID(id);
				return userInfo;
			}
		
		return userInfo;
	}

	//�����û�����
	@Override
	public String[][] modifyUserPassword(int id, String pw) {
		//��������
		UserService us = null;
		UserDao ud = null;

		Map<String, String> pwMap = null;
		String userInfo[][] = null;
		int line;
		
		//���������sha256����


		pwMap = new HashMap<String, String>();
		pwMap.put("pw", pw);
		
		ud = new UserDao();
		//��������
		line = ud.updateInfo(pwMap, id);
		//���³ɹ��������û������Ϣ
		if(line == 1) {
			us = new UserService();
			userInfo = us.getIdentifyByID(id);
			return userInfo;
		}
		return userInfo;
	}

	////�����û���ɫ
	@Override
	public String[][] modifyUserRole(int id, String role) {
		//��������
		UserService us = null;
		UserDao ud = null;
		regexStr reg = null;
		Map<String, String> roleMap = null;
		String userInfo[][] = null;
		int line;
		
		//�ж�role�ֶ��Ƿ�Ϸ�
		reg = new regexStr();
		if(reg.checkRole(role)) {
			roleMap = new HashMap<String, String>();
			roleMap.put("role", role);
			ud = new UserDao();
			line = ud.updateInfo(roleMap, id);
			//���³ɹ������������Ϣ
			if(line == 1) {
				us = new UserService();
				userInfo = us.getIdentifyByID(id);
				return userInfo;
			}
		}
		return userInfo;
	}

	//���ݽ�ɫ��ѯ�û�
	@Override
	public String[][] queryUserByRole(String role, int index, int lim) {
		//��������
		UserService us = null;
		UserDao ud = null;
		regexStr reg = null;
		deleteEmptyEle dee = null;
		String userInfo[][] = null;
		Map<String, String> roleMap = null;
		boolean flag = false;
		
		//�ж�role�ֶθ�ʽ�Ƿ�Ϸ�
		reg = new regexStr();
		flag = reg.checkRole(role);
		if(!flag) {
			return null;
		} else {
			String demandArr[] = {"userID", "userName", "email", "phone", "gender", "role", "headPortrait"};
			roleMap = new HashMap<String, String>();
			roleMap.put("role", role);
			//��ѯrole���͵��û�
			ud = new UserDao();
			userInfo = ud.queryUser(roleMap, demandArr, index, lim);
			
			//ɾ��ֵΪnull�Ľ��
			dee = new deleteEmptyEle();
			userInfo = dee.deleteEle(userInfo, demandArr.length);
		}
		return userInfo;
	}
	//ɾ���û�
	@Override
	public String[][] deleteUser(int shopId) {
		//��������
		UserDao ud = null;
		Map<String, String> deleteMap = null;
		String userInfo[][] = null;
		int line;
		
		ud = new UserDao();
		deleteMap = new HashMap<String, String>();
		deleteMap.put("isDelete", "1");
		line = ud.updateInfo(deleteMap, shopId);
		if(line == 1) {
			String demandArr[] = {"isDelete"};
			Map<String, String> idMap = new HashMap<String, String>();
			idMap.put("shopId", String.valueOf(shopId));
			userInfo = ud.queryUser(idMap, demandArr, 1, 1);
			return userInfo;
		}
		return null;
	}
	//�޸�Ӫҵ״̬
	public String[][] changeStatus(int id) {
		//��������
		UserDao ud = null;
		Map<String, String> statusMap = null;
		String userInfo[][] = null;
		int line;
		
		//�ж�ID�Ƿ����
		String idFiled[] = {"shopId", String.valueOf(id)};
		ud = new UserDao();
		line = ud.fieldRecheck(idFiled);
		if(line == 0) {//�����ڵ����
			return userInfo;
		} else {//ID����,��ѯ�û���ǰstatus
			Map<String, String> idMap = new HashMap<String, String>();
			String demandArr[] = {"userID", "userName", "role", "isActive"};
			idMap.put("userID", String.valueOf(id));
			userInfo = ud.queryUser(idMap, demandArr, 1, 1);
			System.out.println(userInfo[0][3]);
			if(userInfo[0][3].equals("0")) {//�û���ǰΪδ����״̬
				statusMap = new HashMap<String, String>();
				statusMap.put("isActive", "1");
				line = ud.updateInfo(statusMap, id);
				if(line == 1) {
					userInfo[0][3] = "1";
					return userInfo;
				}
			} else if(userInfo[0][3].equals("1")) {//�û���ǰΪ����״̬
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

	@Override
	public String[][] modifyUserName(int id, String userName) {
		// TODO Auto-generated method stub
		return null;
	}
	//��������û���Ϣ
		@Override
		public String[][] getAllShop(int index, int lim) {
			//��������
			UserDao ud = null;
			deleteEmptyEle dee = null;
			String userInfo[][] = null;
			Map<String, String> nullMap = null;
			
			String demandArr[] = {"shopID", "shopName", "shopRank", "shopPicture", "isDelete", "isOpen"};
			ud = new UserDao();
			userInfo = ud.queryUser(nullMap, demandArr, index, lim);
			
			//ɾ��ֵΪnull�Ľ��
			dee = new deleteEmptyEle();
			userInfo = dee.deleteEle(userInfo, demandArr.length);
			
			return userInfo;
		}
		@Override
		public String[][] getAllShop(int index, int lim,int id) {
			//��������
			UserDao ud = null;
			deleteEmptyEle dee = null;
			String userInfo[][] = null;
			Map<String,Integer> shopIDMap = null;
			shopIDMap = new HashMap<String, Integer>();
			shopIDMap.put("shopId", id);
			String demandArr[] = {};
			ud = new UserDao();
			userInfo = ud.query(shopIDMap, demandArr, index, lim);
			
			//ɾ��ֵΪnull�Ľ��
			dee = new deleteEmptyEle();
			userInfo = dee.deleteEle(userInfo, demandArr.length);
			
			return userInfo;
		}
		@Override
		public String[][] shopAdd(String name,String type,String shopkeeper,String address,String phoneNumber,int rank,String picture,int isOpen) {
			//��������
			UserService us = null;
			UserDao ud = null;
			Map<String, String> userNameMap = null;
			String userInfo[][] = null;
			int result = 1, line;
			
			us = new UserService();
			
				userNameMap = new HashMap<String, String>();
				userNameMap.put("shopName", name);
				userNameMap.put("shopType", type);
				userNameMap.put("shopKeeper", shopkeeper);
				userNameMap.put("shopAddress", address);
				userNameMap.put("shopPhoneNumber", phoneNumber);
				userNameMap.put("shopPicture", picture);
				userNameMap.put("shopRank", String.valueOf(rank));
				userNameMap.put("isOpen", String.valueOf(isOpen));
				
				ud = new UserDao();
				//����userName
				line = ud.insertUser(userNameMap);
				//���³ɹ��������û������Ϣ
				if(line == 1) {
					userInfo = us.getIdentifyByID(name);
					return userInfo;
				}
			
			return userInfo;
		}
}