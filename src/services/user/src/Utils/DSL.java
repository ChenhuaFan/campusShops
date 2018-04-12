package Utils;

import java.util.Map;

//防止SQL注入
public class DSL {
	public Map<String, String> defenseMap(Map<String, String> map) {
		
		return map;
		
	}
	
	
	public String[] defenseArr(String[] arr) {
		
		return arr;
		
	}
	
	
	public String regexStr(String str) {
		System.out.println(str.matches("^[a-zA-Z0-9_\\u4e00-\\u9fa5]+$"));
//		str.matches("^[a-zA-Z0-9_/u4e00-/u9fa5]+$");
		return str;
		
	}
	public static void main(String[] args) {
		DSL dsl = new DSL();
		dsl.regexStr("adbc潘凯‘");
	}
}
