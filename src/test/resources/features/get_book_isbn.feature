# language: pt
@google_books_api
Funcionalidade: Verificar a API Get book usando um ISBN específico

  Cenário: Realizar uma chamada de um serviço que retorna informações de um livro pelo ISBN
    Dado que um livro possui o ISBN "9781451648546"
    Quando o usuário acessa o livro por ISBN
    Então o HTTP Status Code deverá ser 200
    E a resposta deverá comtemplar os dados abaixo
      | totalItems |             1 |
      | kind       | books#volumes |
    E deverá retornar os dados à seguir em alguma ordem
      | items.volumeInfo.title     | Steve Jobs         |
      | items.volumeInfo.publisher | Simon and Schuster |
      | items.volumeInfo.pageCount |                630 |
    E realizo uma verificação que deverá ser implícita
