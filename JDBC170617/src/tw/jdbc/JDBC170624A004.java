package tw.jdbc;
//

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.ConnectionEventListener;

import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC170624A004 {

	public static void main(String[] args) {
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
		
		try(Connection conn = 
			DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop);)
		{
			Statement stmt =  conn.createStatement();
			
			
			//String sql = "SELECT * from gift where ProduceOrg = '龍崎區農會'";
			String keyword = "金棗";
			String sqlCond = "WHERE Name LIKE '%" + keyword + 
							 "%' or Feature LIKE '%" + keyword +
							 "%' or SalePlace LIKE '%" + keyword + "%'";
			String sqlCount =  "SELECT count(*) as num FROM gift " +sqlCond;
			System.out.println(sqlCount);
			
			ResultSet rsCount = stmt.executeQuery(sqlCount);
			if(!rsCount.next()) return;
			
			int total = rsCount.getInt("num");			
			int rpp=10;//每頁看幾筆 可自行輸入
			int page = 1;//看第幾頁可自行輸入
			
			int lastPage = (total%rpp==0)?(total/rpp):(total/rpp+1);
			page = (page<=lastPage)?page:lastPage;
			int start = (page-1)*rpp;
			
			String sql = "SELECT * from gift " + sqlCond + " limit " + start + ", " + rpp;
			System.out.println(sql);
			ResultSet rs =stmt.executeQuery(sql);
			int i = 0;
			while(rs.next()){
				String gid = rs.getString("gid");
				String name =  rs.getString("Name");
				System.out.println((++i) +" : " + gid + ":" + name);
			}
			
			
		
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	

}

