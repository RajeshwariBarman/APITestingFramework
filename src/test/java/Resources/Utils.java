package Resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.*;

public class Utils {

	static RequestSpecification req;

	public RequestSpecification requestSpecification() throws IOException {

		if (req == null) {
			PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
			req = new RequestSpecBuilder().setBaseUri(getglobalValue("baseUrl")).addQueryParam("key", "qaclick123")
					.setContentType("application/json").addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
			return req;
		}

		return req;

	}

	public static String getglobalValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Rajeshwari\\eclipse-workspace\\APIBddFramework\\src\\test\\java\\Resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);

	}

	public String getJsonPath(Response resp, String key) {
		String response = resp.asString();
		JsonPath js = new JsonPath(response);
		return js.get(key).toString();

	}
}
