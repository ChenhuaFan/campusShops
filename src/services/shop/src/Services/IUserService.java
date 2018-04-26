package Services;


public interface IUserService {
	
	//�û���½����
	public String[][] userLogin(String userName, String pw);
	
	//�û�ע�����
	public String[][] userRegister(String userName, String pw, String email, String phone, String gender);
	
	//�û������ֶβ���
	public int duplicateCheck(String key, String value);
	//�õ��û���ϸ��Ϣ
	public String[][] getUserDetails(int id);
	
	//�õ��û�������Ϣ
	public String[][] getUserBrief(int id);
	
	//�����û�������Ϣ
	public String[][] modifyEssentialInfo(int id, String email, String phone, String gender, String headPortrait);
	
	//����ID�����֤��Ϣ
	public String[][] getIdentifyByID(int id);
	
	//�����û���
	public String[][] modifyUserName(int id, String userName);
	
	//�����û�����
	public String[][] modifyUserPassword(int id, String pw);
	
	//�����û���ɫ
	public String[][] modifyUserRole(int id, String role);
	//ɾ���̼�
	public String[][] deleteUser(int id);
	
	//���ݽ�ɫ��ѯ�û�
	public String[][] queryUserByRole(String role, int index, int lim);


	public String[][] modifyUserName(int id, String name,  String type, String shopkeeper, String address,
			String phoneNumber,String picture);

	public String[][] getAllShop(int index, int lim);

	public String[][] getAllShop(int index, int lim, int id);

	String[][] shopAdd(String name, String type, String shopkeeper, String address, String phoneNumber, int rank,
			String picture, int isOpen);

	String[][] getIdentifyByID(String name);
}