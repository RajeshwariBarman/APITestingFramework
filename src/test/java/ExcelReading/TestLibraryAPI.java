package ExcelReading;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestLibraryAPI {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		TestDataReading exceldata = new TestDataReading();
		ArrayList<String> data  = exceldata.getdata("Rest AddBook","RestAssured");
		//ArrayList<String> test_data = s1.getdata("Rest AddBook ","RestAssured");
		System.out.println(data);
		HashMap<String,Object> mp = new HashMap<String, Object>();
		mp.put("name", data.get(1));
		mp.put("isbn",data.get(2));
		mp.put("aisle", data.get(3));
		mp.put("author", data.get(4));
		
		RestAssured.baseURI = "http://216.10.245.166";
		String resp = given().log().all().header("Content-Type", "application/json")
				.body(mp)
				.when().post("Library/Addbook.php").then().assertThat().log().all().statusCode(200).extract().response()
				.asString();

		JsonPath js = new JsonPath(resp);
		String id = js.getString("ID");
		System.out.println(mp);
		System.out.println(id);

	}

}
