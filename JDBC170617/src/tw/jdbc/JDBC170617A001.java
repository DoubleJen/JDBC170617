package tw.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBC170617A001 {
	
	public static void main(String[] args){
		//1. Driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
				//forName		
				//"com.mysql.jdbc.Driver fr file:///C:/JDBC001/mysql-connector-java-5.1.42/docs/connector-j.html
			System.out.println("OK");
		}catch(ClassNotFoundException ee){
			System.out.println(ee);
			System.exit(0);
		}
		
		
		//2. Build Connection
		try{
		Connection conn =
				DriverManager.getConnection("jdbc:mysql://localhost/double?" +
											"user=root&password=root");
			//Connection --> java.sql
			//"jdbc:mysql://localhost/test?" + "user=root&password=root" fr file:///C:/JDBC001/mysql-connector-java-5.1.42/docs/connector-j.html
			//MySQL create one DB.
		System.out.println("OK");
		
		//3. SQL Statement
				Statement stmt = conn.createStatement();
				
		//4. query
				String sql = "INSERT INTO cust(cname, tel, birthday)" +
							 "VALUES('double','0932','1985-01-12')";
				boolean isQueryOK = stmt.execute(sql);
				if(isQueryOK){
					System.out.println("OK");
				}else{
					System.out.println("XX");
				}
		
		}catch(SQLException se){
			System.out.println(se);
			
		}
		
		
		
		
		
	}

}
