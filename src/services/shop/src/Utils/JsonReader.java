package Utils;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;
/*
 * 本程序处理前台发过来的json数据以及生成json返回给前台
 * */
public class JsonReader {
	public static JSONObject receivePost(HttpServletRequest request) throws IOException, UnsupportedEncodingException {  
		  
        // 读取请求内容  
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(),"utf-8"));  
        String line = null;  
        StringBuilder sb = new StringBuilder();  
        while ((line = br.readLine()) != null) {  
            sb.append(line);  
        }  
        //将json字符串转换为json对象  
        JSONObject json=JSONObject.fromObject(sb.toString());  
        return json;  
    }
}