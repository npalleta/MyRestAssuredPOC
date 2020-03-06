package br.com.restassured.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import br.com.restassured.model.ResponseDAO;
import cucumber.api.DataTable;

public class UtilsClass {

	private static JSONParser getJSONParser() {
		return new JSONParser();
	}

	private static JSONObject getJSONObjectFromReader(String file) {

		JSONObject jsonObject = null;

		try (Reader reader = new FileReader(file)) {
			jsonObject = (JSONObject) getJSONParser().parse(reader);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	public static String getUrlFromJson(String url) {

		JSONObject jsonObject = getJSONObjectFromReader("src/test/resources/json-folder/url_api.json");
		return url = (String) jsonObject.get(url);
	}

	@SuppressWarnings("unchecked")
	private static HashMap<String, Object> getDataMapFromResponse(String response) {

		HashMap<String, Object> result = null;

		try {
			result = new ObjectMapper().readValue(response, HashMap.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public static void printJsonObject() {

		try {
			JSONObject jsonObject = getJSONObjectFromReader("src/test/resources/json-folder/example.json");
			jsonObject.keySet().forEach(keyStr -> {
				Object keyvalue = jsonObject.get(keyStr);
				System.out.println("CHAVE: " + keyStr + " - VALOR: " + keyvalue);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public static void readJsonFileExample() {

		JSONObject jsonObject = getJSONObjectFromReader("src/test/resources/json-folder/example.json");

		System.out.println(jsonObject);

		long id = (Long) jsonObject.get("Id");
		System.out.println(id);

		String name = (String) jsonObject.get("Name");
		System.out.println(name);

		String author = (String) jsonObject.get("Author");
		System.out.println(author);

		JSONArray msg = (JSONArray) jsonObject.get("Company List");

		Iterator<String> iterator = msg.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}

	@SuppressWarnings("unchecked")
	public static List<String> getIndexFromResponse(String key) {

		JSONObject jsonObject = new JSONObject(getDataMapFromResponse(ResponseDAO.getResponse()));
		List<String> list = (List<String>) jsonObject.get(key);
		return list;
	}

	public static String getFirstIndexDataTable(DataTable dataTable) {

		List<String> strList = dataTable.asList(String.class);
		return strList.get(0);
	}

	public static void compareValueResponseString(String value) {

		Object ObjValue = new ArrayList<String>();

		int itr = 0;

		HashMap<String, Object> result = getDataMapFromResponse(ResponseDAO.getResponse());
		Set<String> entrySet = result.keySet();
		for (String key : entrySet) {
			ObjValue = result.get(key);
			String jsonString = new Gson().toJson(ObjValue);
			String[] values = jsonString.split(",");

			for (int i = 0; i < values.length; i++) {
				if (removeBracketsAndDoubleQuotes(values[i]).equals(value)) {
					System.out.println(String.format("Resultado | %s |", values[i]));
					itr += 1;
					/*
					 * Assert.assertTrue(removeBracketsAndDoubleQuotes(values[i]).equals(value),
					 * String.format("The value %s is not equal %s!", values[i], value)); break;
					 */
				}
			}
		}

		if (itr == 0)
			Assert.assertEquals(itr /* Actual Value */, 1 /* Expected value */,
					String.format("O valor comparado %s não existe na resposta do JSON!", value));
	}

	public static List<String> getDataTable(DataTable dataTable) {

		List<String> strList = null;

		int itr = 0;

		try {
			strList = dataTable.asList(String.class);
			for (String strData : strList) {
				itr += 1;
				System.out.println(String.format("Resultado (%d): %s.", itr, strData));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strList.subList(1, strList.size());
	}

	public static List<String> getResponse() {

		String[] strArr = null;
		List<String> linkedList = new LinkedList<>();

		HashMap<String, Object> result = getDataMapFromResponse(ResponseDAO.getResponse());
		List<Object> ObjList = result.entrySet().stream().map(v -> v.getValue()).collect(Collectors.toList());
		String jsonString = new Gson().toJson(ObjList);
		strArr = jsonString.split(",");

		for (String strValue : strArr) {
			linkedList.add(removeBracketsAndDoubleQuotes(strValue));
		}
		return linkedList;
	}

	public static void assertHttpStatusCode(int httpStatusCode) {
		Assert.assertEquals(ResponseDAO.getHttpStatusCode() /* Actual Value */, httpStatusCode /* Expected value */,
				String.format("O HTTP Status Code atual (%s) é diferente do esperado (%s)!",
						ResponseDAO.getHttpStatusCode(), httpStatusCode));
	}

	public static void assertResponseList(List<String> listA, List<String> listB) {

		int itr = 0, lstASize = listA.size();

		for (String strA : listA) {
			for (String strB : listB) {
				if (strA.equals(strB)) {
					itr += 1;
					System.out.println(
							String.format("--- VALIDADO --- Resultado Massa (%d): %s | Resultado JSON (%d): %s.", itr,
									strA, itr, strB));
				}
			}
		}
		Assert.assertEquals(itr /* Actual Value */, lstASize /* Expected value */, String.format(
				"A lista / massa do teste apresenta uma diferença ao ser comparada com a resposta do JSON! Massa: %s | JSON: %s",
				itr, lstASize));
	}

	public static void assertResponseListReduced(List<String> listA, List<String> listB) {

		listA = putInAlphabeticalOrder(listA);

		listB = putInAlphabeticalOrder(listB);

		for (int i = 0; i < listA.size(); i++) {
			Assert.assertEquals(listB.get(i) /* Actual Value */, listA.get(i) /* Expected value */, String
					.format("O valor atual (%s) não é o mesmo que o valor esperado (%s)!", listB.get(i), listA.get(i)));
		}
	}

	public void assertResponseListReducedUsingStream(List<String> listA, List<String> listB) {

		listA = putInAlphabeticalOrder(listA);

		listB = putInAlphabeticalOrder(listB);

		listB = listA.stream().filter(listB::contains).collect(Collectors.toList());
	}

	private static String removeBracketsAndDoubleQuotes(String strValue) {
		return strValue.replaceAll("[\\p{Ps}\\p{Pe}]", "").replaceAll("\"", "");
	}

	/**
	 * Execução no CMD do Windows.
	 */
	public static void cmdCommand(String... args) {

		if (args.length < 1) {
			System.out.println("USAGE: java cmd command line...");
			System.exit(1);
		}

		try {
			Runtime rt = Runtime.getRuntime();
			System.out.println("Command " + args[0] + " " + args[1] + " " + args[2]);
			Process proc = rt.exec(args[0] + " " + args[1] + " " + args[2]);
			StreamGobbler errorGobbler = new StreamGobbler(proc.getErrorStream(), "ERROR");
			StreamGobbler outputGobbler = new StreamGobbler(proc.getInputStream(), "OUTPUT");
			errorGobbler.start();
			outputGobbler.start();
			int exitVal = proc.waitFor();
			System.out.println("ExitValue: " + exitVal);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Verifica se existe determinado arquivo em um diretório.
	 */
	public static void checkFileExists(File file) {

		try {
			while (!file.exists()) {
				continue;
			}
			System.out.println(String.format("O %s foi criado / existe!", file.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Exclui um determinado arquivo existente no diretório.
	 */
	public static void deleteFileIfExists(File file) {

		try {
			if (file.delete())
				System.out.println(String.format("O %s foi deletado!", file.getName()));
			else
				System.out.println(String.format("A Exclusão do %s falhou!", file.getName()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Organiza lista em ordem alfabética
	 */
	public static List<String> putInAlphabeticalOrder(List<String> lstString) {

		String temp;

		String[] stockArr = new String[lstString.size()];

		stockArr = lstString.toArray(stockArr);

		for (int i = 0; i < stockArr.length; i++) {
			for (int j = i + 1; j < stockArr.length; j++) {
				if (stockArr[i].compareTo(stockArr[j]) > 0) {
					temp = stockArr[i];
					stockArr[i] = stockArr[j];
					stockArr[j] = temp;
				}
			}
		}
		return Arrays.asList(stockArr);
	}
}