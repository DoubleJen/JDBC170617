package tw.jdbc;
//DB 序列化

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class JDBC170625A003 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			Student170625 s1 = new Student170625("0002", 100, 100, 40);
			System.out.println(s1.getId() + " : " + s1.total() + " : " + s1.avg());
			
			//setObject已幫我們序列化
			PreparedStatement pstmt2=
					conn.prepareStatement("UPDATE member set obj = ? WHERE id = 4");
			pstmt2.setObject(1, s1);
			pstmt2.executeUpdate();
			
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	

}

