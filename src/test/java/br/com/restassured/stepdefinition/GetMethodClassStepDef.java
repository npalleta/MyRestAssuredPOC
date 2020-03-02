package br.com.restassured.stepdefinition;

import br.com.restassured.api.APIRegister;
import br.com.restassured.utils.UtilsClass;
import cucumber.api.DataTable;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;

public class GetMethodClassStepDef extends APIRegister {

	@Dado("^que eu acesso a api people \"([^\"]*)\"$")
	public void que_eu_acesso_a_api_people(String id) {
		getMethodClass().getResponseBodyPeople(id);
	}

	@Quando("^o http status code da api people for (\\d+)$")
	public void o_http_status_code_da_api_people_for(int httpStatusCode) {
		getMethodClass().checkHttpStatusCodePeople(httpStatusCode);
	}

	@Então("^o parâmetro nome da personagem deverá ser \"([^\"]*)\"$")
	public void o_parâmetro_nome_da_personagem_deverá_ser(String nome) {
		UtilsClass.getIndexFromResponse("films");
		getMethodClass().checkResponseBodyPeople(nome);
	}

	@Então("^a altura deverá ser \"([^\"]*)\"$")
	public void a_altura_deverá_ser(String altura) {
		getMethodClass().checkResponseBodyPeople(altura);

	}

	@Então("^o peso deverá ser \"([^\"]*)\"$")
	public void o_peso_deverá_ser(String peso) {
		getMethodClass().checkResponseBodyPeople(peso);
	}

	@Então("^a cor de cabelo deverá ser \"([^\"]*)\"$")
	public void a_cor_de_cabelo_deverá_ser(String corCabelo) {
		getMethodClass().checkResponseBodyPeople(corCabelo);
	}

	@Então("^a cor de pele deverá ser \"([^\"]*)\"$")
	public void a_cor_de_pele_deverá_ser(String corPele) {
		getMethodClass().checkResponseBodyPeople(corPele);
	}

	@Então("^a cor do olho deverá ser \"([^\"]*)\"$")
	public void a_cor_do_olho_deverá_ser(String corOlho) {
		getMethodClass().checkResponseBodyPeople(corOlho);
	}

	@Então("^a data de nascimento deverá ser \"([^\"]*)\"$")
	public void a_data_de_nascimento_deverá_ser(String dataNasc) {
		getMethodClass().checkResponseBodyPeople(dataNasc);
	}

	@Então("^o gênero deverá ser \"([^\"]*)\"$")
	public void o_gênero_deverá_ser(String genero) {
		getMethodClass().checkResponseBodyPeople(genero);
	}

	@Então("^o planeta natal deverá ser apresentado no serviço \"([^\"]*)\"$")
	public void o_planeta_natal_deverá_ser_apresentado_no_serviço(String planetaNatal) {
		getMethodClass().checkResponseBodyPeople(planetaNatal);
	}

	@Então("^os filmes deverão ser apresentados nos serviços$")
	public void os_filmes_deverão_ser_apresentados_nos_serviços(DataTable urlFilmes) {
		System.out.print("\n");
		// getMethodClass().checkResponseBodyPeople(urlFilmes);
		getMethodClass().checkResponseBodyPeople(urlFilmes, UtilsClass.getFirstIndexDataTable(urlFilmes));
	}

	@Então("^as espécies deverão ser apresentados no serviço$")
	public void as_espécies_deverão_ser_apresentados_no_serviço(DataTable urlEspecies) {
		System.out.print("\n");
		getMethodClass().checkResponseBodyPeople(urlEspecies);
	}

	@Então("^os veículos deverão ser apresentados no serviço$")
	public void os_veículos_deverão_ser_apresentados_no_serviço(DataTable urlVeiculos) {
		System.out.print("\n");
		getMethodClass().checkResponseBodyPeople(urlVeiculos);
	}

	@Então("^as naves estelares deverão ser apresentados no serviço$")
	public void as_naves_estelares_deverão_ser_apresentados_no_serviço(DataTable urlNavesEstrelares) {
		System.out.print("\n");
		getMethodClass().checkResponseBodyPeople(urlNavesEstrelares);
	}

	@Então("^a data de criação deverá ser \"([^\"]*)\"$")
	public void a_data_de_criação_deverá_ser(String data_criacao) {
		System.out.print("\n");
		getMethodClass().checkResponseBodyPeople(data_criacao);
	}

	@Então("^a data de edição deverá ser \"([^\"]*)\"$")
	public void a_data_de_edição_deverá_ser(String data_edicao) {
		getMethodClass().checkResponseBodyPeople(data_edicao);
	}

	@Então("^o endereço de acesso contendo as informação deverá ser \"([^\"]*)\"$")
	public void o_endereço_de_acesso_contendo_as_informação_deverá_ser(String url) {
		getMethodClass().checkResponseBodyPeople(url);
	}
}