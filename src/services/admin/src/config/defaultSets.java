package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Properties;

import DataBase.Mysql;

public class defaultSets {
	
	private  String driverloads = null;
	private  String URL = null;
	private  String USER = null;
	private  String PASSWORD = null;
 

	
	public  String getDriverloads() {
		return driverloads;
	}


	public  String getURL() {
		return URL;
	}


	public  String getUSER() {
		return USER;
	}


	public  String getPASSWORD() {
		return PASSWORD;
	}


 

	private void init(){
		
		Properties properties = new Properties();
		try {
//			String path =  dbConn.class.getClassLoader().getResource("connDB.properties").toString();
		    String path =Mysql.class.getClassLoader().getResource("DB.properties").toString().substring(6);
		 
			path =URLDecoder.decode(path,"utf-8");
			InputStream inputStream =  
					new FileInputStream(path);

			properties.load(inputStream);
			driverloads = properties.getProperty("DRIVERLOAD");
			URL = properties.getProperty("URL");
			USER = properties.getProperty("USER");
			PASSWORD = properties.getProperty("PASSWORD");
			 
			
		} catch (FileNotFoundException e) {			 
			e.printStackTrace();
		} catch (IOException e) {			 
			e.printStackTrace();
		}
			 	
	}
	
	private defaultSets(){}
	private static defaultSets single=null;  
	    //   
    public static defaultSets getInstance() {  
         if (single == null) {    
             single = new defaultSets(); 
             single.init();
         }    
        return single;  
    }  
	
	public static void main(String[] args){
		
		System.out.println(defaultSets.getInstance().getDriverloads());
	}
}
