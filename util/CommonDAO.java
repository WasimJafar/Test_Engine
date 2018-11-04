package com.xyz.testengine.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import java.util.ResourceBundle;
import java.util.ResourceBundle;
public interface CommonDAO {
	static Connection setConnection() throws ClassNotFoundException, SQLException {
		Class.forName("org.postgresql.Driver");
		
		ResourceBundle rb = ResourceBundle.getBundle("config");
		String url = rb.getString("url");
		String user = rb.getString("user");
		String password = rb.getString("password");
	
		

		return DriverManager.getConnection(url, user, password);
	}

}
