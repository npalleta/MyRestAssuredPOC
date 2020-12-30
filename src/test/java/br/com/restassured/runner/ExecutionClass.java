package br.com.restassured.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import br.com.restassured.report.ReportClass;

public class ExecutionClass {

	public static void main(String[] args) {
		JUnitCore jUnitCore = new JUnitCore();
		Result result = jUnitCore.run(MainRunner.class);
		System.out.println("Failure Count: " + result.getFailureCount());
		System.out.println("IgnoreCount: " + result.getIgnoreCount());
		System.out.println("Run Count: " + result.getRunCount());
		System.out.print("Run Time: " + result.getRunTime());

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		// Classe que contém a execução do relatório - Cluecumber
		// jUnitCore.run(ReportClass.class);
	}
}
