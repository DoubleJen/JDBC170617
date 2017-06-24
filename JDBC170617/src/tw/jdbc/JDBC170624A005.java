package tw.jdbc;
//異動免UPDATE

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

public class JDBC170624A005 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			DatabaseMetaData metadata = conn.getMetaData();//這次連線的相關資料將透過他取得
			boolean isOK = metadata.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);//同步異動
			System.out.println(isOK);
			
			Statement stmt =  conn.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			String sql = "SELECT * from member where id = 3";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			System.out.println(rs.getString("account"));
			
			rs.updateString("account", "Peter");
			rs.updateString("passwd", "Peter");
			rs.updateRow();//真正動作位置
			
			PreparedStatement pstmt=
					conn.prepareStatement("SELECT * from member", ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs2 = pstmt.executeQuery();
			while(rs2.next()){
				rs2.updateString("passwd", "1111");
				rs2.updateRow();
			}
			
			rs2.moveToInsertRow();//移動指標
			rs2.updateString("account", "Tony");
			rs2.updateString("passwd", "Tony");
			rs2.updateString("realname", "Tony Chang");
			rs2.insertRow();//插入

			
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	

}

