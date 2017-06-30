Feature: Cadastrar Produto
	Cadastrar produtos

@CadastrarProduto
Scenario Outline: Cadastrar produto 
Given O nome do produto <nome>
	And a descricao <descricao>
	And o lance minimo <lanceMinimo>
	And o cpf leiloador <cpfLeiloador>
	And a data limite <dataLimite>
When O produto for cadastrado com <sucesso>
Then O produto deve <existir>

Examples:
	|nome|descricao|lanceMinimo|cpfLeiloador|dataLimite|sucesso|existir|
	|"Produto 2"|"Um produto..."|"50.00"|"735.054.365.42"|"14/06/2017"|"true"|"true"|
	|"Produto 1"|"Um produto..."|"50.00"|"735.054.365.42"|"14/06/2017"|"false"|"true"|
	|"Produto 3"|"Um produto..."|"50.00"|"735.054.365.55"|"14/06/2017"|"false"|"false"|