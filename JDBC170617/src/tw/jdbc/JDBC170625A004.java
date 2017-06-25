package tw.jdbc;
//DB 解序列化

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
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

public class JDBC170625A004 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			PreparedStatement pstmt=
					conn.prepareStatement("SELECT * from member WHERE id =4");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
	
			//解序列化
			ObjectInputStream in = new ObjectInputStream(rs.getBinaryStream("obj"));
			Student170625 s3 = (Student170625)in.readObject();
			System.out.println("read obj => " + s3.getId()+":" + s3.total() + ":"  + s3.avg());
			in.close();

			
			
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	

}

