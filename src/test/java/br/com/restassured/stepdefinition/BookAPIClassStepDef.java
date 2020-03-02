package br.com.restassured.stepdefinition;

import java.util.Map;

import br.com.restassured.api.APIRegister;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class BookAPIClassStepDef extends APIRegister {

	@Dado("^que um livro possui o ISBN \"([^\"]*)\"$")
	public void que_um_livro_possui_o_ISBN(String isbn) {
		bookAPIClass().setRequestBookAPI(isbn);
	}

	@Quando("^o usuário acessa o livro por ISBN$")
	public void o_usuário_acessa_o_livro_por_ISBN() {
		bookAPIClass().getResponseBookAPI();
	}

	@Então("^o HTTP Status Code deverá ser (\\d+)$")
	public void o_HTTP_Status_Code_deverá_ser(int httpStatusCode) {
		bookAPIClass().getHttpStatusCodeBookAPI(httpStatusCode);
	}

	@Então("^a resposta deverá comtemplar os dados abaixo$")
	public void a_resposta_deverá_comtemplar_os_dados_abaixo(Map<String, String> responseFields) {
		bookAPIClass().getResponseUsingEqualTo(responseFields);
	}
	
	@Então("^deverá retornar os dados à seguir em alguma ordem$")
	public void deverá_retornar_os_dados_à_seguir_em_alguma_ordem(Map<String, String> responseFields) {
		bookAPIClass().getResponseUsingInAnyOrder(responseFields);
	}

	@Então("^realizo uma verificação que deverá ser implícita$")
	public void realizo_uma_verificação_que_deverá_ser_implícita() {
		bookAPIClass().getByISBN();
	}
}