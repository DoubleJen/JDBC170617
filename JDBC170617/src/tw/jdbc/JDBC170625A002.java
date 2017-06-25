package tw.jdbc;
//DB讀出圖像

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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

public class JDBC170625A002 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			PreparedStatement pstmt=
					conn.prepareStatement("SELECT * from member WHERE id =1");
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			//把串流讀取得到一個inputstream
			InputStream in = rs.getBinaryStream("img");
			
			//讀出來之後 存取到pic2下
			FileOutputStream out =  new FileOutputStream("./pic2/java2.png");
			byte[] buf =  new byte[4096]; int len;
			while((len = in.read(buf)) != -1){
				out.write(buf, 0, len);
			}		
			
			out.flush();
			out.close();
			
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	

}

