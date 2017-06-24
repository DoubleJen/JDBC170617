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

public class JDBC170624A001 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			Statement stmt =  conn.createStatement();
			//新增資料
			String sql = "INSERT INTO member (account, passwd, realname)" +
			"VALUES('brad','123','Brad Chao')";
			stmt.executeUpdate(sql);
			
		
		}catch(Exception e){
			
		}
		
	}
}


