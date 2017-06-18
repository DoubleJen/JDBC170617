package tw.jdbc;
//Autoclose
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC170618A002 {
	
	public static void main(String[] args){
		//1. Driver
		try{
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("OK");
		}catch(ClassNotFoundException ee){
			System.out.println(ee);
			System.exit(-1);
		}
		
		//==========================================================
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
			//2. Build Connection
		try(Connection conn =DriverManager.getConnection("jdbc:mysql://localhost/double", prop)){
			//3. SQL Statement
			Statement stmt = conn.createStatement();
			
			//4. query
			String sql = "SELECT * FROM cust";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()){
				String id = rs.getString("id");
				String cname = rs.getString("cname");
				String tel = rs.getString("tel");
				String birthday = rs.getString("birthday");
				System.out.println(id + ":" + cname + ":" + tel + ":" + birthday);
			
			}
		}//小誇號內再離開大跨號前會autoclose
		catch(Exception e){
			System.out.println(e);
		}
		
		
	}

}
