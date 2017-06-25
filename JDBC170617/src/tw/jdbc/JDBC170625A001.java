package tw.jdbc;
//DB加入圖像

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.ConnectionEventListener;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC170625A001 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);
			FileInputStream in = new FileInputStream("./pic1/java.png");)
		{
			PreparedStatement pstmt=
					conn.prepareStatement("UPDATE member set img = ? WHERE id = 1");
			pstmt.setBinaryStream(1, in);
			pstmt.executeUpdate();


			
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	

}

