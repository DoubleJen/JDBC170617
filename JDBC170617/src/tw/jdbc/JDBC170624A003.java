package tw.jdbc;
//從Database撈出資料

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.ConnectionEventListener;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC170624A003 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			Statement stmt =  conn.createStatement();
			//帳密驗證
			String account = "brad", passwd = "123";
			String[] result;
			
			if((result = checkMember(stmt, account, passwd)) !=  null){
				System.out.println("WELCOME" + result[3]);
			}else{
				System.out.println("Error Login");
			}
			
		
		}catch(Exception e){
			
		}
		
	}
	
	static Member checkMember(Statement stmt, String account, String passwd) throws Exception{
		String sql = "SELECT count(*)　as f1 from member where account = '" + account +
					"' and passwd = '" + passwd + "'";
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()){
			// right member
			Member member = new Member(rs.getString("id"), rs.getString("account"), rs.getString("realname"));
			return member;
		}else{
			return null;
		}
	}
}

class Member{
	String id, account, realname;
	
}




