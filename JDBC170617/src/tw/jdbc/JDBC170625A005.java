package tw.jdbc;
//commit

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.ConnectionEventListener;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC170625A005 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			conn.setAutoCommit(false);//關閉
			try{
				Statement stmt =  conn.createStatement();
				//新增資料前判斷帳號有無重複
				String account = "eric", passwd = "abcdef", realname = "Eric Ho";
				//System.out.println(account);
				if(!isDataRepeat(account,stmt)){
					String sql = "INSERT INTO member (account, passwd, realname)" +
								"VALUES('" + account + "','" + passwd + "','" + realname + "')";
					stmt.executeUpdate(sql);
					
					conn.commit();//打開
					
				}else{
					System.out.println("帳號重複了");
				}
			}catch(SQLException se){
				conn.rollback();
			}
		
		}catch(Exception e){
			
		}
		
	}
	static boolean isDataRepeat(String account, Statement stmt) throws Exception{
		//System.out.println("11");
		String sql = "SELECT count(*) as f1 from member where account = '" + account +"'";
		//System.out.println("12");
		ResultSet rs = stmt.executeQuery(sql);
		//System.out.println("0");
		if(rs.next()){
			int num = rs.getInt("f1");
			if(num > 0){
				//System.out.println("1");
				return true;
			}
			else{
				//System.out.println("2");
				return false;
			}
		}else{
			throw new Exception("SQL Error");
		}
	}
	
}




