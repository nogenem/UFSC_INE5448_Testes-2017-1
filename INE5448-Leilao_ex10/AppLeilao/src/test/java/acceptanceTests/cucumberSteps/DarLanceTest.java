package acceptanceTests.cucumberSteps;

import java.text.SimpleDateFormat;

import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import interfaces.ILeiloavel;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class DarLanceTest {
	
	public String nome = "";
	public String cpfComprador = "";
	public double valorLance = 0.0;
	public boolean expectedResult = true;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@Before
	public void setup() throws Exception {
		fachada.limparMercado();
		fachada.cadastrarUsuario("Patricia", "UFSC", "patricia@email.com", "323.667.674-47");
		fachada.cadastrarUsuario("Lucas Pereira", "Campus Universitario", "lucas@email.com", "735.054.365-42");
		fachada.cadastrarProduto("Produto 1", "Um produto...", 50.00, "323.667.674-47",
				new SimpleDateFormat("dd/MM/yyyy").parse("14/06/2017"));
	}
	
	@After
	public void tearDown() {
//		this.fachada.montarMercado();
		this.fachada.limparMercado();
	}
	
	@Given("^O nome do produto para lance \"([^\"]*)\"$")
	public void o_nome_do_produto_para_lance(String arg1) throws Throwable {
		this.nome = arg1;
	}
	
	@Given("^o cpf comprador \"([^\"]*)\"$")
	public void o_cpf_comprador(String arg1) throws Throwable {
	    this.cpfComprador = arg1;
	}

	@Given("^o valor lance \"([^\"]*)\"$")
	public void o_valor_lance(String arg1) throws Throwable {
		this.valorLance = Double.parseDouble(arg1);
	}

	@When("^O lance for dado com \"([^\"]*)\"$")
	public void o_lance_for_dado_com(String arg1) throws Throwable {
		this.expectedResult = Boolean.parseBoolean(arg1);
		boolean deuLance = false;
		try{
			fachada.daLance(nome, cpfComprador, valorLance);
			deuLance = true;
		}catch(Exception e){
//			e.printStackTrace();
			deuLance = false;
		}
		Assert.assertEquals(this.expectedResult, deuLance);
	}

	@Then("^O lance deve \"([^\"]*)\"$")
	public void o_lance_deve(String arg1) throws Throwable {
		this.expectedResult = Boolean.parseBoolean(arg1);
		boolean existe = false;
		try{
			for(ILeiloavel p : fachada.getProdutosQueDeuLance(cpfComprador)){
				if(p.getNome().equals(nome)){
					existe = true;
					break;
				}
			}
			existe = false;
		}catch(Exception e){
//			e.printStackTrace();
			existe = false;
		}
		
		Assert.assertEquals(this.expectedResult, existe);
	}
}
