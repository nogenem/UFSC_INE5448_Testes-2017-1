Feature: Dar Lance
	Dar lances

@DarLance
Scenario Outline: Dar lance 
Given O nome do produto para lance <nome>
	And o cpf comprador <cpfComprador>
	And o valor lance <valorLance>
When O lance for dado com <sucesso>
Then O lance deve <existir>

Examples:
	|nome|cpfComprador|valorLance|sucesso|existir|
	|"Produto 1"|"735.054.365-42"|"55.00"|"true"|"true"|
	|"Produto 1"|"735.054.365-42"|"49.00"|"false"|"true"|
	|"Produto 1"|"735.054.365-42"|"51.00"|"false"|"true"|