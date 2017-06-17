package tw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class JDBC170617A005 {

	public static void main(String[] args) {
//		String connectionUrl = 
//				"jdbc:sqlserver://localhost:1433;" +
//				"user=sa;password=sa;" +
//				"databaseName=Northwind;integratedSecurity=true;";
		String connectionUrl = 
				"jdbc:sqlserver://10.21.200.66:1433;" +
				"user=sa;password=sa;" +
				"databaseName=Northwind;";
		try{
			Connection conn =
					DriverManager.getConnection(
							connectionUrl);
			Statement stmt = conn.createStatement();
			ResultSet rs = 
				stmt.executeQuery("SELECT * FROM Products");
			while (rs.next()){
				String pname = rs.getString("ProductName");
				System.out.println(pname);
			}
			
			conn.close();
			System.out.println("OK3");
		}catch(Exception e){
			System.out.println(e);
		}
		
	}

}