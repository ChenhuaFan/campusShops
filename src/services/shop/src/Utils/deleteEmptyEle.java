package Utils;

import java.util.ArrayList;
/*
 * ������ɾ����ѯ�����ֵΪnull�Ķ�ά����
 * 
 * */
public class deleteEmptyEle {
	public String[][] deleteEle(String oldArr[][], int arrLen) {
		ArrayList<ArrayList<String>> listOut = new ArrayList<ArrayList<String>>();
		int inSize = 0;
        for (int i = 0; i <oldArr.length; i++) {
        	ArrayList<String> listIn = new ArrayList<String>();//��ʼ��һ��ArrayList����
        	for(int j = 0; j < oldArr[i].length; j++) {
        		if (oldArr[i][j]  != null && !oldArr[i][j].equals("")) {
        			listIn.add(oldArr[i][j]);
        			inSize = listIn.size();
        		}
        	}
        	if(listIn.size() != 0) {
        		listOut.add(listIn);
        	}
        }
        int size = listOut.size()*inSize;
        if(size != 0) {
        	String newArr[][] = new String[size/arrLen][arrLen]; 
        	for(int i = 0; i < newArr.length; i++) {
        		for(int j = 0; j < newArr[i].length; j++) {
        			newArr[i][j] = oldArr[i][j];
        		}
        	}
        	return newArr;
        }
		return null;
		
	}
}