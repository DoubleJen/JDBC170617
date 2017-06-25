package tw.jdbc;
//批次

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;

public class GiftExample170625 {

	public static void main(String[] args) {
		long  start = System.currentTimeMillis();
		String strUrl = "http://data.coa.gov.tw/Service/OpenData/ODwsv/ODwsvAgriculturalProduce.aspx";
		String json = getJSONString(strUrl);
		
		Properties prop = new Properties();
		prop.setProperty("user", "root");
		prop.setProperty("password", "root");
			//2. Build Connection
		try(Connection conn =DriverManager.getConnection("jdbc:mysql://127.0.0.1/double", prop)){
			//3. SQL Statement
			String sql =  "INSERT INTO gift (gid, Name, Feature, SalePlace, ProduceOrg, SpecAndPrice, OrderUrl, ContactTel, Column1)" + 
						  " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			JSONArray root = new JSONArray(json);
			for(int i= 0; i<root.length(); i++){
				JSONObject row = root.getJSONObject(i);
				String gid = row.getString("ID");
				String Name = row.getString("Name");
				String Feature = row.getString("Feature");
				String SalePlace = row.getString("SalePlace");
				String ProduceOrg = row.getString("ProduceOrg");
				String SpecAndPrice = row.getString("SpecAndPrice");
				String OrderUrl = row.getString("OrderUrl");
				String ContactTel = row.getString("ContactTel");
				String Column1 = row.getString("Column1");
				
				//到進資料庫前, 可於此處進行資料檢查, ex正規化
				
				pstmt.setString(1, gid);
				pstmt.setString(2, Name);
				pstmt.setString(3, Feature);
				pstmt.setString(4, SalePlace);
				pstmt.setString(5, ProduceOrg);
				pstmt.setString(6, SpecAndPrice);
				pstmt.setString(7, OrderUrl);
				pstmt.setString(8, ContactTel);
				pstmt.setString(9, Column1);
				//批次
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
			
			System.out.println("OK");
			System.out.println(System.currentTimeMillis() - start);
		}catch(Exception e){
			System.out.println(e);
			
		}
		
	}
	
	private static String getJSONString(String strUrl){
		StringBuilder sb = new StringBuilder();
		try {
			URL url = new URL(strUrl);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
			conn.connect();
			BufferedReader reader = 
				new BufferedReader(
					new InputStreamReader(conn.getInputStream(),"UTF-8")) ;
			String line = null;
			while ( (line = reader.readLine()) != null){
				sb.append(line);
			}
			reader.close();
			System.out.println(sb);
		} catch (Exception e) {
			System.out.println(e);
		}
		return sb.toString();
	}
	

}
