package br.com.restassured.runner;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class BeforeAfterClass {

	@Before
	public void before(Scenario scenario) {
	}

	@After
	public void after(Scenario scenario) {
	}
}
