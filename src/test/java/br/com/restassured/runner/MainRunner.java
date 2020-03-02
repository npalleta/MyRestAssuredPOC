package br.com.restassured.runner;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import br.com.restassured.utils.UtilsClass;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
	features = "src\\test\\resources\\features",
	format =
	{
		"pretty"
	},
	glue =
	{
		"br.com.restassured.stepdefinition",
		"br.com.restassured.runner"
	},
	plugin =
	{
		"json:target/cucumber-reports/Cucumber.json",
		"junit:target/cucumber-reports/Cucumber.xml",
		"html:target/cucumber-reports"
	},
	tags =
	{ 
		"@google_books_api," +
		"@star_wars_api"
	},
	dryRun = false,
	monochrome = true,
	strict = false
)

public class MainRunner {
	
	@BeforeClass
	public static void before() {
		UtilsClass.deleteFileIfExists();
	}
	
	@AfterClass
	public static void after() {
	}
}