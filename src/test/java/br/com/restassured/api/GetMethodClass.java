package br.com.restassured.api;

import org.junit.Test;

import br.com.restassured.model.ResponseClass;
import br.com.restassured.utils.UtilsClass;
import cucumber.api.DataTable;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

public class GetMethodClass extends APIRegister {

	@SuppressWarnings("rawtypes")
	@Test
	public void getResponseBodyPeople(String id) {
		RestAssured.baseURI = UtilsClass.getUrlFromJson("Star_Wars_Url");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.get(String.format("/people/%s", id));
		ResponseBody body = response.getBody();
		String bodyAsString = body.asString();
		ResponseClass.setResponse(bodyAsString);
		ResponseClass.setHttpStatusCode(response.getStatusCode());
	}

	@Test
	public void checkHttpStatusCodePeople(int httpStatusCode) {
		UtilsClass.assertHttpStatusCode(httpStatusCode);
	}

	@Test
	public void checkResponseBodyPeople(String value) {
		UtilsClass.compareValueResponseString(value);
	}

	@Test
	public void checkResponseBodyPeople(DataTable dataTable) {
		UtilsClass.assertResponseList(UtilsClass.getDataTable(dataTable), UtilsClass.getResponse());
	}

	@Test
	public void checkResponseBodyPeople(DataTable dataTable, String key) {
		UtilsClass.assertResponseListReduced(UtilsClass.getDataTable(dataTable), UtilsClass.getIndexFromResponse(key));
	}
}