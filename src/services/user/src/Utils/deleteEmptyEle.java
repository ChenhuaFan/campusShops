package Utils;

import java.util.ArrayList;
/*
 * 本程序删除查询结果中值为null的二维数组
 * 
 * */
public class deleteEmptyEle {
	public String[][] deleteEle(String oldArr[][], int arrLen) {
		ArrayList<ArrayList<String>> listOut = new ArrayList<ArrayList<String>>();
		int inSize = 0;
        for (int i = 0; i <oldArr.length; i++) {
        	ArrayList<String> listIn = new ArrayList<String>();//初始化一个ArrayList集合
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
