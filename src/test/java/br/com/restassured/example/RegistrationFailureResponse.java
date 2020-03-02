package br.com.restassured.example;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RegistrationFailureResponse {

	@JsonProperty
	String FaultId;

	@JsonProperty
	String fault;
}
