# API Sistema de Lanchonete

## Introdução

<p>Este Projeto consiste em uma API Rest desenvolvida em Java juntamente com o Spring Framework, para atender os requisitos de um sistema de gerenciamento de uma lanchonete, com funcionalidades de cadastro de ingredientes, montagem de itens, e montagem e controle dos pedidos com estes itens.  
Os requisitos do cliente são apresentados no desafio de programação orientada a objetos encontrado neste link: https://www.computersciencemaster.com.br/exercicio-sistema-de-lanchonete/.
</p>

## Índice
<p><a href="#API-Sistema-de-Lanchonete">API Sistema de Lanchonete</a></p>
 <p><a href="##Introdução">Introdução</a></p>
 <p><a href="##Técnicas-e-Tecnologias-utilizadas">Técnicas e Tecnologias utilizadas</a></p>
 <p><a href="##Funcionalidades">Funcionalidades</a></p>
  <p><a href="###Requisitos-gerais-apresentado-pelo-cliente:">Requisitos gerais apresentado pelo cliente:</a></p>
  <p><a href="###Funcionalidades-estabelecidas-para-atender-a-demanda-do-cliente:">Requisitos gerais apresentado pelo cliente:</a></p>
 <p><a href="##Etapas:">Etapas:</a></p>
 <p><a href="##Status:-62/65">Status: 61/65</a></p>
 <p><a href="###Como-utilizar:">Como utilizar:</a></p>
  <p><a href="####Carregamento-do-projeto">Carregamento do projeto</a></p>
 <p><a href="#Na-operacionalização-do-sistema-obedecer-a-seguinte-sequência:">Na operacionalização do sistema obedecer a seguinte sequência:</a></p>
 <p><a href="#Um-melhor-detalhamento-do-uso-desta-API-é-apresentado-no-video-deste-link.">Um melhor detalhamento do uso desta API é apresentado no video deste link.</a></p>
 <p><a href="#Considerações:">Considerações:</a></p>

## ✔️Técnicas e Tecnologias utilizadas

* <p> Java </p> 
* <p>  Spring Framework</p> 
* <p> Spring Boot </p> 
* <p> Spring Boot Validation</p> 
* <p> Spring Data JPA</p> 
* <p> Maven</p> 
* <p> Lombok</p> 
* <p> Flyway</p> 
* <p> PostgresSQL</p> 
* <p> Postman </p> 
* <p> Programação Orientada a Objetos</p> 

## 📃Funcionalidades

### Requisitos gerais apresentado pelo cliente:
<p>A lanchonete possui 3 principais itens de venda: pizzas, lanches e salgadinhos. Inicialmente, o sistema será testado para controlar as vendas desses 3 itens apenas. 
 Todos os itens vendidos devem conter: preço de venda, data de validade e peso. 
O sistema da nossa lanchonete deverá criar um pedido, esse pedido será composto pelo nome do cliente, itens que foram consumidos e taxa de serviço. 
O sistema deve permitir gerar a nota fiscal para entregar ao cliente. 
O vendedor poderá inserir o valor recebido em dinheiro e o sistema calcula e mostra o troco do cliente na tela. 
Para o seu cliente, é imprescindível que o sistema tenha algumas funções. </br>
 O dono descreveu essas funções dizendo:  </br>
 “Gostaríamos de oferecer em nosso cardápio virtual, pizzas com diferentes recheios bordas e molhos. Também queremos oferecer opções para o cliente escolher qual o tipo de recheio, bordas recheadas ou não e o molho que vai ser usado.”   </br>
 “Os pedidos de lanches precisam conter algumas informações essenciais, são elas: tipo do pão, recheio e molhos obrigatoriamente.”   </br>
 “Os salgadinhos possuem grande saída, queremos controlar sua venda. Gostaríamos que os pedidos contivessem: o tipo (frito ou assado), massa e recheio.”  </p>

### Funcionalidades estabelecidas para atender a demanda do cliente:

<ol>
<li>  Cadastro dos ingredientes com os seguintes atributos: precoVenda, dataValidade, peso, nomeIgrediente.</li>
<li>  Cadastro do pedido inicial com status aberto, informando o nomeDoCliente.</li>
<li>  Com o pedido no status ABERTO é possível iniciar a montagem dos itens, podendo ser Lanche, Pizza ou salgadinho, com os diferentes tipos de ingredientes cadastrados na etapa 1. Caso algum item esteja com data de validade próxima a vencer o sistema informa. Após a montagem dos itens, é retornado a sua data de validade.</li> 
<li>  Após fechar a montagem dos itens do pedido, este assume o status de PROCESSANDO, no momento do pagamento é possível informar o valor pago, e se este for maior que o valor total do pedido, este assume o status de PAGOFINALIZADO. Caso seja menor ele retorna uma mensagem que o valor é insuficiente. </li>
</ol>

## Etapas:

- [x] Modelagem do diagrama de entidades e suas relações
 - [x] domain da API com:
   - [x] Ingredientes
  
     - [x] Ingrediente 

       - [x] LancheMolho
       - [x] LancheRecheio
       - [x] LancheTipoPao
       - [x] PizzaBorda
       - [x] PizzaMolho
       - [x] PizzaRecheio
       - [x] SalgadinhoMassa
       - [x] SalgadinhoRecheio
       - [x] SalgadinhoTipoPreparo
     - [ ] Verificação da validade dos ingrediente
     - [x] Item
       - [x] Item
       - [x] Lanche
       - [x] Pizza
       - [x] Salgadinho
     - [x] Calculo do peso total do item
     - [x] Calculo do valor total do item
     - [ ] Atribuição da validade do item
  
     - [x] Pedido
       - [x] Pedido
       - [x] StatusPedido

<p>Para cada uma das entidades itens da API foi implementado os seguintes componentes:</p>
        <p>Implementação da entidade</p>
        <p>Implementação do Repository</p>
        <p>Implementação do DTO Response</p>
        <p>Implementação do DTO Register</p>

- [x] controller 

  - [x] ingredientes
    - [x] LancheController
    - [x] PizzaController
    - [x] SalgadinhoController

  - [x] item
    - [x] MontarLancheController
    - [x] MontarPizzaController
    - [x] MontarSalgadinhoController
  - [x] pedido
    - [x] PedidoController
    - [x] listarPedidos
    - [x] listarPedidosPorId
    - [x] listarPedidoCompletoPorId
    - [x] listarPedidosAbertos
    - [x] listarPedidosProcessando
    - [x] listarPedidosPagoFinalizado
    - [x] cadastrarPedido
    - [x] atualizarPedido
    - [x] retornaCalculoTrocoPedido
    - [x] removerPedido

<p>Para cada um dos controllers de ingredientes e item foram implementadas os seguintes endpoints para as requisições HTTP compondo  CRUD:</p>
<p>Ex: Para o molho do lanche:</p>
<p>@RequestMapping("/ingrediente/lanches")  => Endereço URL básico:</p>
<p>@GetMapping("/molho") => Listar todos os molhos (com paginação).</p>
<p>@GetMapping("/molho/{id}") => Listar um molho especifico por seu id.</p>
<p>@PostMapping("/molho") => Cadastrar um molho com um JSON.</p>
<p>@PutMapping("/molho/{id}") => Atualizar um molho pelo id e com um JSON.</p>
<p>@DeleteMapping("/molho/{id}") => Deletar um molho por um id. </p>

- [x] services 

<p>Para cada um dos endpoints das controllers foram montados métodos dentro das services para acessar o banco de dados através dos Repositorys, e juntamente com os métodos das entidades, completar o CRUD e regras de negócio. </p>  

- [x] ingredientes
- [x] LancheService
- [x] PizzaService
- [x] SalgadinhoService

- [x] item
- [x] MontarLancheService
- [x] MontarPizzaService
- [x] MontarSalgadinhoService
- [x] pedidoService

- [x] implementação da configuração do banco de dados 
- [x] implementação do controle de versões do banco de dados com flyway
- [x] implementação do tratador de erros
- [x] implementação do itemJaExisteExeption
- [x] implementação do ValorPagoInsuficienteException
- [x] liberação do cors com o WebConfig 
- [ ] deploy no heroku 


## Status: 62/65

### Como utilizar:
   
   #### Carregamento do projeto
      <p>Neste momento para utilizar o sistema é necessário rodar o sistema offline dentro de alguma IDE, através do Spring Boot.</p>
      <p><strong>Etapas:</strong></p>
        <p>Download do projeto e descompactar </p>
        <p>Fazer a atualização das dependências com o Maven</p>
        <p>Fazer a configuração do banco de dados de sua preferência</p>
        <p>Criar o banco de dados </p>
        <p>Configurar a API a este banco de dados</p>
        <p>Rodar o projeto com a app.properties em spring.jpa.hibernate.ddl-auto=create</p>
        <p>Em seguida colocar spring.jpa.hibernate.ddl-auto=none</p>
        <p>Abrir a collection de endpoints com o software que gerencia requisições PostMan</p>

   #### Na operacionalização do sistema obedecer a seguinte sequência:
      <p>Cadastrar todos os ingredientes conforme os exemplos em JSON apresentados na collection de endpoints.</p>
      <p>Fazer a abertura de um novo pedido. </p>
      <p>Com o id do pedido e dos ingredientes, criar os itens deste pedido podendo ser:</p>
      <p>Lanche, Pizza ou Salgadinho. </p>
      <p>Mudar para status processando. </p>
      <p>E após o pedido estar pronto, realizar o pagamento através do endpoint RetornaCalculoTrocoPedido.</p>

   #### Um melhor detalhamento do uso desta API é apresentado no video deste link. 

## Considerações:
 <p>Neste sistema foi possível implementar o CRUD dos ingredientes, dos items e dos pedidos, bem como as regras de negócio estabelecidas pelos requisitos do cliente. </p>
