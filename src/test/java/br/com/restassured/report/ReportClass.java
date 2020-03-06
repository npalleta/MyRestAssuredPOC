package br.com.restassured.report;

import java.io.File;

import org.junit.Test;

import br.com.restassured.utils.UtilsClass;

public class ReportClass {

	@Test
	public void executeReportFromCMD() {
		UtilsClass.checkFileExists(new File("../MyRestAssuredPOC/target/cucumber-reports/Cucumber.json"));
		UtilsClass.cmdCommand("cmd.exe", "/c", "cd \"C:\\Users\\Niky.Lima\\Documents\\Projetos - Eclipse - NP\\MyRestAssuredPOC\" && report.bat");
		UtilsClass.cmdCommand("cmd.exe", "/c", "start chrome \"C:\\Users\\Niky.Lima\\Documents\\Projetos - Eclipse - NP\\MyRestAssuredPOC\\target\\cluecumber-report\\index.html");
	}
}
