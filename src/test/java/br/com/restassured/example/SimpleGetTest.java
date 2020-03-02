package br.com.restassured.example;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class SimpleGetTest {

	private final String baseURI = "http://restapi.demoqa.com/utilities/weather/city";

	String getBaseURI() {
		return RestAssured.baseURI = this.baseURI;
	}

	@Test
	public void getWeatherDetails() {

		// RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";

		getBaseURI();

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		String contentType = response.header("Content-Type");
		System.out.println("Content-Type value: " + contentType);

		String serverType = response.header("Server");
		System.out.println("Server value: " + serverType);

		String acceptLanguage = response.header("Content-Encoding");
		System.out.println("Content-Encoding: " + acceptLanguage);

		Headers allHeaders = response.headers();

		for (Header header : allHeaders) {
			System.out.println("Key: " + header.getName() + " Value: " + header.getValue());
		}

		String responseBody = response.getBody().asString();

		System.out.println("Response Body is =>  " + responseBody);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode /* Actual Value */, 200 /* Expected value */, "Incorrect status code returned");

		String statusLine = response.getStatusLine();
		Assert.assertEquals(statusLine /* Actual Value */, "HTTP/1.1 200 OK" /* Expected Value */, "Incorrect status code returned");
	}

	@Test
	public void weatherMessageBody() {

		getBaseURI();

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.get("/Hyderabad");

		@SuppressWarnings("rawtypes")
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		Assert.assertEquals(bodyAsString.contains("Hyderabad") /* Expected Value */, true /* Actual Value */, "Response body does not contains Hyderabad");
	}

	@Test
	public void verifyCityInJsonResponse() {

		getBaseURI();

		RequestSpecification httpRequest = RestAssured.given();

		Response response = httpRequest.get("/Hyderabad");

		JsonPath jsonPathEvaluator = response.jsonPath();

		String city = jsonPathEvaluator.get("City");
		System.out.println("City received from Response " + city);
		Assert.assertEquals(city, "Hyderabad", "Incorrect city name received in the Response");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void registrationSuccessful() {

		RestAssured.baseURI = "http://restapi.demoqa.com/customer";

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();

		requestParams.put("FirstName", "Virender");
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "sdimpleuser2dd2011");
		requestParams.put("Password", "password1");
		requestParams.put("Email", "sample2ee26d9@gmail.com");

		request.body(requestParams.toJSONString());
		Response response = request.get("/register");
		int statusCode = response.getStatusCode();
		System.out.println("The status code recieved: " + statusCode);
		System.out.println("Response body: " + response.body().asString());
	}
}
