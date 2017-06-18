package tw.jdbc;
//從Database撈出資料

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC170618A004 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
			//2. Build Connection
		try(Connection conn =DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop)){
			//3. SQL Statement
			String sql =  "SELECT * FROM gift";
			JSONWriter jw = new JSONStringer().array();

			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()){
				String Name = rs.getString("Name");
				String Feature = rs.getString("Feature");
				String gid = rs.getString("gid");

				jw.object();
				jw.key("gid").value(gid);
				jw.key("Name").value(Name);
				jw.key("Feature").value(Feature);
				jw.endObject();

				
				
			}
			jw.endArray();
			System.out.println(jw.toString());

		}catch(Exception e){
			System.out.println(e);
		}

	}
}

