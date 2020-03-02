# MyRestAssuredPOC

# Projeto de Estudo

- Este projeto tem como intuito ser uma estrutura para testes de API.
- A linguagem utilizada é o Java e o Framework Rest Assured.

## Estrutura

* br.com.restassured.api - Pacote com as funções de chamada para API.
* br.com.restassured.example - Exemplos genéricos com Rest Assured.
* br.com.restassured.model - Um model com Response e HTTPStatusCode, utilizando **static**.
* br.com.restassured.report - Relatório Cluecumber executado pelo CMD do Windows.
* br.com.restassured.runner - Contém as classes de execução - MainRunner e ExecutionClass.
* br.com.restassured.stepdefinition - Pacote que contém as classes que fazem a chamada para a suas respectivas classes de API.
* br.com.restassured.utils - Pacote com a classe que contém funções de ajuda.

## Utilização

```
@Test
   // Utilizando o UtilsClass temos:
	public void checkHttpStatusCodePeople(int httpStatusCode) {
	   // verifica o http status code da resposta do serviço.
		UtilsClass.assertHttpStatusCode(httpStatusCode);
	}

	@Test
	public void checkResponseBodyPeople(String value) {
	   // compara o valor retornado no serviço. Verifica o valor de uma chave do JSON.
		UtilsClass.compareValueResponseString(value);
	}

	@Test
	public void checkResponseBodyPeople(DataTable dataTable) {
	   // Faz a validação dos valores de uma lista retornada no serviço.
	   // Verifica se o JSON possui o mesmo valor da sua tabela em formato Gherkin,
	   // comparando os valores de ambas.
	   // na Assert, a função verifica a quantidade de linhas comparadas, que para retornar verdadeiro,
	   // deverá retornar o mesmo tamanho do JSON da resposta.
	   // Ex:
	   // | Nome   |
	   // | João   |
	   // | Maria  |
	   // | Carlos |
	   // JSON:
	   // {
	   //		Nome: [
	   	//					João,
	   	//					Maria,
	   	//					Carla
	   	//				]
	   // }
	   // Nesse caso o comparação retornará false, pois o nome Carlos não existe no JSON, logo
	   // o Assert retornará esperado [3] encontrado [4].
		UtilsClass.assertResponseList(UtilsClass.getDataTable(dataTable), UtilsClass.getResponse());
	}

	@Test
	public void checkResponseBodyPeople(DataTable dataTable, String key) {
		// A função faz o mesmo que a apresentada acima, a única diferença é que aqui ordenamos
		// alfabeticamente e também devemos passar como parâmetro o a chave da lista.
   		// Ex:
	   // | Nome   |
	   // | João   |
	   // | Maria  |
	   // | Carlos |
	   //  checkResponseBodyPeople(UtilsClass.getDataTable(dataTable), "Nome"));
	   // No contexto, o nome é passado via Gherkin.
		UtilsClass.assertResponseListReduced(UtilsClass.getDataTable(dataTable), UtilsClass.getIndexFromResponse(key));
	}
	
	Na classe BookAPIClass, existem exemplos mais simples de como implementar o Rest Assured.
	É usado como comparador a biblioteca Hamcrest Matchers.
	
	Na classe APIRegister, são registradas as classes que fazem a chamada de API. Dessa forma, não
	há necessidade de instânciar um novo objeto toda vez que queira utilizar as classes de chamada
	para as APIs, basta estender a classe APIRegister nas Classes de Step Definition e você terá acesso
	aos métodos públicos da classe.
	// Ex:
	// public class GetMethodClassStepDef extends APIRegister { ... }
```

## Links

###GITHUB

[https://github.com/pdrogf/APITestsJavaCucumber]
[https://github.com/angiejones/restassured-with-cucumber-demo]

###WEB SITES

[https://www.baeldung.com/cucumber-rest-api-testing]
[http://artofcode.info/2018/08/24/api-functional-tests-with-cucumber-and-rest-assured/]
[https://www.toolsqa.com/rest-assured-tutorial/]
