package br.com.restassured.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;

import java.util.Arrays;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpStatus;
import org.junit.Test;

import br.com.restassured.utils.UtilsClass;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

/*
 * GOOGLE BOOKS APIs
 * 
 * https://developers.google.com/books/docs/v1/getting_started
 * 
 * Formato específico das APIs: https://www.googleapis.com/books/v1/{collectionName}/resourceID?parameters
 * 
 * Exemplos:
 * 
 * https://www.googleapis.com/books/v1/volumes
 * https://www.googleapis.com/books/v1/volumes/volumeId
 * https://www.googleapis.com/books/v1/mylibrary/bookshelves
 * https://www.googleapis.com/books/v1/mylibrary/bookshelves/shelf
 * https://www.googleapis.com/books/v1/mylibrary/bookshelves/shelf/volumes
 * 
 * Pesquisa usando o parâmetro quilting:
 * GET - https://www.googleapis.com/books/v1/volumes?q=quilting
 * 
 * Informação no volume s1gVAAAAYAAJ:
 * GET - https://www.googleapis.com/books/v1/volumes/s1gVAAAAYAAJ
 */

public class BookAPIClass {
	
	private static RequestSpecification request;
	
	private static Response response;

	private static ValidatableResponse json;

	private final String URLAPI = UtilsClass.getUrlFromJson("GoogleBooksAPIs").concat("volumes"), ISBN = "isbn:9781451648546";

	@Test
	public void setRequestBookAPI(String isbn) {
		request = given().param("q", "isbn:" + isbn);
	}

	@Test
	public void getResponseBookAPI() {
		response = request.when().get(URLAPI);
		System.out.println("<<-- RESPOSTA -->>: " + response.prettyPrint());
	}

	@Test
	public void getHttpStatusCodeBookAPI(int httpStatusCode) {
		json = response.then().statusCode(httpStatusCode);
	}

	@Test
	public void getResponseUsingEqualTo(Map<String, String> responseFields) {
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if (StringUtils.isNumeric(field.getValue())) {
				json.body(field.getKey(), equalTo(Integer.parseInt(field.getValue())));
			} else {
				json.body(field.getKey(), equalTo(field.getValue()));
			}
		}
	}

	@Test
	public void getResponseUsingInAnyOrder(Map<String, String> responseFields) {
		for (Map.Entry<String, String> field : responseFields.entrySet()) {
			if (StringUtils.isNumeric(field.getValue())) {
				json.body(field.getKey(), containsInAnyOrder(Integer.parseInt(field.getValue())));
			} else {
				json.body(field.getKey(), containsInAnyOrder(field.getValue()));
			}
		}
	}
	
	@Test
	public void getByISBN() {
		given().
		param("q", ISBN).
		when().
		get(URLAPI).
		then().
		statusCode(HttpStatus.SC_OK).
		body("totalItems", equalTo(1),
				"kind", equalTo("books#volumes"),
				"items.volumeInfo.title", containsInAnyOrder("Steve Jobs"),
				"items.volumeInfo.authors", containsInAnyOrder((Object) Arrays.asList("Walter Isaacson")),
				"items.volumeInfo.publisher", containsInAnyOrder("Simon and Schuster"),
				"items.volumeInfo.pageCount", containsInAnyOrder(630));
	}
}