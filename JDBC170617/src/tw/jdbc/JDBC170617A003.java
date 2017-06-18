package tw.jdbc;
//3.getConnection(String url, Properties info)
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC170617A003 {
	
	public static void main(String[] args){
		//1. Driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
				//forName		
				//"com.mysql.jdbc.Driver fr file:///C:/JDBC001/mysql-connector-java-5.1.42/docs/connector-j.html
			System.out.println("OK");
		}catch(ClassNotFoundException ee){
			System.out.println(ee);
			System.exit(-1);
		}
		
		
		//2. Build Connection
		try{
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		Connection conn =
				DriverManager.getConnection("jdbc:mysql://10.21.200.66/brad", prop);
			//Connection --> java.sql
			//"jdbc:mysql://localhost/test?" + "user=root&password=root" fr file:///C:/JDBC001/mysql-connector-java-5.1.42/docs/connector-j.html
			//MySQL create one DB.
		System.out.println("OK");
		
		//3. SQL Statement
				Statement stmt = conn.createStatement();
				
		//4. query
//				String sql = "INSERT INTO cust(cname, tel, birthday)" +
//							 "VALUES('Aya','0912','1985-01-12')";
//				boolean isQueryOK = stmt.execute(sql);
//				if(isQueryOK){
//					System.out.println("OK");
//				}else{
//					System.out.println("XX");
//				}
				
				String sql = "SELECT * FROM cust";
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()){
					String id = rs.getString("id");
					String cname = rs.getString("cname");
					String tel = rs.getString("tel");
					String birthday = rs.getString("birthday");
					System.out.println(id + ":" + cname + ":" + tel + ":" + birthday);
				}	
		}catch(SQLException se){
			System.out.println(se);
			
		}
		
		
		
		
		
	}

}
