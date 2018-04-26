package Dao;

import java.util.Map;

import Utils.sqlUtils;

public class UserDao {
	//��ѯ�û���Ϣ
	public String[][] queryUser(Map<String,String> offerMap, String demandArr[], int index, int lim) {
		//��������
		sqlUtils su = null;
		String userInfo[][] = null;
		//�������ݿ�
		su = new sqlUtils();
		su.connect();
		userInfo = su.select("shop", offerMap, demandArr, index, lim);
		su.close();
		
		return userInfo;
	}
	//��ѯ�û���Ϣ
		public String[][] query(Map<String,Integer> offerMap, String demandArr[], int index, int lim) {
			//��������
			sqlUtils su = null;
			String userInfo[][] = null;
			//�������ݿ�
			su = new sqlUtils();
			su.connect();
			userInfo = su.selectshop("shop", offerMap, demandArr, index, lim);
			su.close();
			
			return userInfo;
		}
	//����һ���û���Ϣ
	public int insertUser(Map<String,String> infoMap) {
		//��������
		sqlUtils su = null;
		int line;
		//�������ݿ�,����һ���¼�¼
		su = new sqlUtils();
		su.connect();
		line = su.insert("shop", infoMap);
		su.close();
		return line;
	}
	
	//����ֶ��Ƿ��ظ�
	public int fieldRecheck(String duplicateField[]) {
		//��������
		sqlUtils su = null;
		int count = 0;
		//����Ƿ����ظ��ֶ�
		su = new sqlUtils();
		su.connect();
		count = su.duplicateChecking("shop", duplicateField);
		su.close();
		//���ز鵽������
		return count;
	}
	
	//������Ϣ
	public int updateInfo(Map<String, String> updateMap, int id) {
		//��������
		sqlUtils su = null;
		int count = 0;
		su = new sqlUtils();
		su.connect();
		//����
		count = su.update("shop", updateMap, "shopId", id);
		su.close();
		//���µ�����
		return count;
		
	}
}