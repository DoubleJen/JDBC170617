package tw.jdbc;

import org.json.JSONString;
import org.json.JSONStringer;
import org.json.JSONWriter;

public class JDBC170618A005 {

	public static void main(String[] args) {
		//JSON格式
		String json = new JSONStringer().object()
				.key("key1")
				.value("value1")
				.endObject()
				.toString();
		System.out.println(json);
		
		//JSON array格式
		String json2 = new JSONStringer().array()
				.object()
				.key("key1")
				.value("value1")
				.endObject()
				.object()
				.key("key2")
				.value("value2")
				.endObject()
				.endArray()
				.toString();
		System.out.println(json2);
		
		//陣列加迴圈
		JSONWriter jw = new JSONStringer().array();
		jw.object().key("key1").value("value1").endObject();
		jw.endArray();
		System.out.println(jw.toString());
		

	}

}
