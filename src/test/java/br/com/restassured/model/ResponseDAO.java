package br.com.restassured.model;

import java.io.Serializable;

public class ResponseDAO implements Serializable {

	private static final long serialVersionUID = 1L;
	private static String response;
	private static int httpStatusCode;

	public static void setResponse(String response) {
		ResponseDAO.response = response;
	}

	public static String getResponse() {
		return response;
	}

	public static void setHttpStatusCode(int httpStatusCode) {
		ResponseDAO.httpStatusCode = httpStatusCode;
	}

	public static int getHttpStatusCode() {
		return httpStatusCode;
	}
}
