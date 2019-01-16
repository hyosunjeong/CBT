package com.biz.cbt.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	private static Connection dbConn;
	
	static {
		String oracleDriver = "oracle.jdbc.driver.OracleDriver";
		
		String url ="jdbc:oracle:thin:@localhost:1521:xe";
		String user ="cbtUser";
		String password ="1234";
		
		
		try {
			Class.forName(oracleDriver);
			dbConn = DriverManager.getConnection(url, user, password);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	
	

}
