package model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

	//	DBに接続する
	private static final String URL = "jdbc:mysql://localhost:3306/customer_db";
	private static final String USER = "customer_user";
	private static final String PASSWORD = "customer_pass";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {

		Class.forName("com.mysql.cj.jdbc.Driver");
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

}
