# Desafio Sicoob

> MAINTAINER Tulio de Carvalho Matias <matiastulio@gmail.com>


## Introdução do Sistema
    Aplicativo que exibe os filmes mais populares, os que estão nos cinemas e os que estão para lançar, usando uma API de Cinema.

## Descrição do Desafio:
É necessário uma listagem para buscar os filmes e quando algum filme for selecionado, deverá exibir uma tela com detalhamento do filme selecionado.
Exibir uma lista contendo filmes mais populares. 
Cada filme apresentará uma imagem e o título do filme;
Ao clicar em um filme, será exibida uma página com detalhes do filme:
- Título
- Imagem
- Duração
- Gênero
- Sinopse

## Funcionalidades implementadas:

- Lista de filmes
    - `Tela que mostra uma lista de filmes obtidos do servidor `
    - `Existem 3 tipos de lista de filmes: Populares, Nos Cinemas e Filmes Futuros`
- Tela de Detalhe do Filme 
    - `Tela que mostra um único filme e o seu detalhamento`


## Frameworks Utilizados

- `Lombok: ` Elimina boilerplate code em classes POJO 
- `Butterknife: ` Elimina boilerplate code quando se está trabalhando com Views em Android
- `Okhttp: ` Facilita comunicação http
- `Gson: ` Facilita utilização de objetos JSON em classes java
- `Eventbus: ` Publish/Subscribe framework que funciona entre threads
- `Picasso: ` Facilita e extrai complexidade do download de imagens.
- `Room: ` Facilita a implementação de banco de dados SQLite 

## TODO
- Implementar a inserção de Filmes no Banco de dados Room
- Implementar a exclusão de Filmes no Banco de dados Room
- Implementar a tela `Sobre o App`
- Implementar transição entre Activities