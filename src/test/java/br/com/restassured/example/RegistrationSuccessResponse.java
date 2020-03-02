package br.com.restassured.example;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

@Test
public class RegistrationSuccessResponse {

	public String successCode;

	public String message;

	private final String baseURI = "http://restapi.demoqa.com/customer";
	private final String _baseURI = "http://dummy.restapiexample.com/api/v1";

	private final String userName = "ToolsQA";
	private final String password = "TestPassword";

	String getBaseURI() {
		return RestAssured.baseURI = this.baseURI;
	}

	String get_baseURI() {
		return RestAssured.baseURI = this._baseURI;
	}

	String getUserName() {
		return userName;
	}

	String getPassword() {
		return password;
	}

	@SuppressWarnings("unchecked")
	@Test
	public void registrationSuccessful() {

		getBaseURI();

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender");
		requestParams.put("LastName", "Singh");
		requestParams.put("UserName", "63userf2d3d2011");
		requestParams.put("Password", "password1");
		requestParams.put("Email", "ed26dff39@gmail.com");

		request.body(requestParams.toJSONString());
		Response response = request.post("/register");

		@SuppressWarnings("rawtypes")
		ResponseBody body = response.getBody();
		System.out.println(response.body().asString());

		if (response.statusCode() == 200) {
			RegistrationFailureResponse responseBody = body.as(RegistrationFailureResponse.class);
			Assert.assertEquals("User already exists", responseBody.FaultId);
			Assert.assertEquals("FAULT_USER_ALREADY_EXISTS", responseBody.fault);
		} else if (response.statusCode() == 201) {
			RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
			Assert.assertEquals("OPERATION_SUCCESS", responseBody.successCode);
			Assert.assertEquals("Operation completed successfully", responseBody.message);
		}
	}

	@Test
	public void authenticationBasics() {

		getBaseURI();

		PreemptiveBasicAuthScheme authScheme = new PreemptiveBasicAuthScheme();
		authScheme.setUserName(getUserName());
		authScheme.setPassword(getPassword());
		RestAssured.authentication = authScheme;

		RequestSpecification request = RestAssured.given();
		Response response = request.get();

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Status message " + response.body().asString());
	}

	@Test
	public void authBasics() {

		System.out.println("--- * ---ANOTHER AUTHENTICATION--- * ---");

		getBaseURI();

		RequestSpecification request = RestAssured.given().auth().basic(userName, password);
		Response response = request.get();

		System.out.println("Status code: " + response.getStatusCode());
		System.out.println("Status message " + response.body().asString());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void updateRecords() {

		get_baseURI();

		int empid = 15418;

		RequestSpecification request = RestAssured.given();

		JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Zion");
		requestParams.put("age", 23);
		requestParams.put("salary", 12000);

		request.body(requestParams.toJSONString());
		Response response = request.put("/update/" + empid);

		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);
	}

	@Test
	public void deleteEmpRecord() {

		int empid = 15418;

		get_baseURI();

		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");

		Response response = request.delete("/delete/" + empid);

		int statusCode = response.getStatusCode();
		System.out.println(response.asString());
		Assert.assertEquals(statusCode, 200);

		String jsonString = response.asString();
		Assert.assertEquals(jsonString.contains("Successfully! Deleted Records"), true);
	}
}