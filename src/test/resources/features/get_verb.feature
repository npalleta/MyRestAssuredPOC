# language: pt
@star_wars_api
Funcionalidade: Validar as apis sobre Star Wars
  
  - Eu gostaria de realizar o teste de apis sobre Star Wars,
  validando alguns de seus parâmetros.

  Esquema do Cenário: Validar a api people
    Dado que eu acesso a api people "<people>"
    Quando o http status code da api people for <status_code>
    Então o parâmetro nome da personagem deverá ser "<nome>"
    E a altura deverá ser "<altura>"
    E o peso deverá ser "<peso>"
    E a cor de cabelo deverá ser "<cor_cabelo>"
    E a cor de pele deverá ser "<cor_pele>"
    E a cor do olho deverá ser "<cor_olho>"
    E a data de nascimento deverá ser "<data_nascimento>"
    E o gênero deverá ser "<genero>"
    E o planeta natal deverá ser apresentado no serviço "<planeta_natal>"
    E os filmes deverão ser apresentados nos serviços
      | films                         |
      | http://swapi.dev/api/films/2/ |
      | http://swapi.dev/api/films/6/ |
      | http://swapi.dev/api/films/3/ |
      | http://swapi.dev/api/films/1/ |
    E as espécies deverão ser apresentados no serviço
      | species |
      |         |
    E os veículos deverão ser apresentados no serviço
      | vehicles                          |
      | http://swapi.dev/api/vehicles/14/ |
      | http://swapi.dev/api/vehicles/30/ |
    E as naves estelares deverão ser apresentados no serviço
      | starships                          |
      | http://swapi.dev/api/starships/12/ |
      | http://swapi.dev/api/starships/22/ |
    E a data de criação deverá ser "<data_criacao>"
    E a data de edição deverá ser "<data_edicao>"
    E o endereço de acesso contendo as informação deverá ser "<url>"

    Exemplos: 
      | people | status_code | nome           | altura | peso | cor_cabelo | cor_pele | cor_olho | data_nascimento | genero | planeta_natal                   | data_criacao                | data_edicao                 | url                            |
      |      1 |         200 | Luke Skywalker |    172 |   77 | blond      | fair     | blue     | 19BBY           | male   | http://swapi.dev/api/planets/1/ | 2014-12-09T13:50:51.644000Z | 2014-12-20T21:17:56.891000Z | http://swapi.dev/api/people/1/ |
