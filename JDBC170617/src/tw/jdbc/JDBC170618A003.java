package tw.jdbc;
//PreparedStatement, 可避免引碼攻擊
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBC170618A003 {
	
	public static void main(String[] args){

		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
			//2. Build Connection
		try(Connection conn =DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop)){
			//3. SQL Statement
			String sql =  "INSERT INTO cust(cname,tel,birthday) VALUES(?,?,?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);		
			pstmt.setString(1, "Peter");
			pstmt.setString(2, "321");
			pstmt.setString(3, "1998-05-09");
			pstmt.execute();
			
			//4. query
			
			
			
			
		}//小誇號內再離開大跨號前會autoclose
		catch(Exception e){
			System.out.println(e);
		}
		
		
	}

}
