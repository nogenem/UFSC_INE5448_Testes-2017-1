package acceptanceTests.cucumberSteps;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class CadastrarProdutoTest {
	
	public String nome = "";
	public String descricao = "";
	public double lanceMinimo = 0.0;
	public String cpfLeiloador = "";
	public Date dataLimite;
	public boolean expectedResult = true;
	
	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@Before
	public void setup() throws Exception {
		fachada.limparMercado();
		fachada.cadastrarUsuario("Lucas Pereira", "Campus Universitario", "lucas@email.com", "735.054.365.42");
		fachada.cadastrarProduto("Produto 1", "Um produto...", 50.00, "735.054.365.42",
				new SimpleDateFormat("dd/MM/yyyy").parse("14/06/2017"));
	}
	
	@After
	public void tearDown() {
//		this.fachada.montarMercado();
		this.fachada.limparMercado();
	}
	
	@Given("^O nome do produto \"([^\"]*)\"$")
	public void o_nome_do_produto(String arg1) throws Throwable {
		this.nome = arg1;
	}
	
	@Given("^a descricao \"([^\"]*)\"$")
	public void a_descricao(String arg1) throws Throwable {
	    this.descricao = arg1;
	}

	@Given("^o lance minimo \"([^\"]*)\"$")
	public void o_lance_minimo(String arg1) throws Throwable {
		this.lanceMinimo = Double.parseDouble(arg1);
	}

	@Given("^o cpf leiloador \"([^\"]*)\"$")
	public void o_cpf_leiloador(String arg1) throws Throwable {
	    this.cpfLeiloador = arg1;
	}

	@Given("^a data limite \"([^\"]*)\"$")
	public void a_data_limite(String arg1) throws Throwable {
		this.dataLimite = new SimpleDateFormat("dd/MM/yyyy").parse(arg1);
	}
	
	@When("^O produto for cadastrado com \"([^\"]*)\"$")
	public void o_produto_for_cadastrado_com(String arg1) {
	    this.expectedResult = Boolean.parseBoolean(arg1);
	    boolean cadastrado = false;
	    try{
	    	fachada.cadastrarProduto(nome, descricao, lanceMinimo, cpfLeiloador, dataLimite);
	    	cadastrado = true;
	    }catch(Exception e){
	    	//e.printStackTrace();
	    	cadastrado = false;
	    }
	    Assert.assertEquals(this.expectedResult, cadastrado);
	}

	@Then("^O produto deve \"([^\"]*)\"$")
	public void o_produto_deve(String arg1) {
		this.expectedResult = Boolean.parseBoolean(arg1);
	    boolean existe = false;
	    try{
	    	existe = fachada.verificaSeOProdutoJaExiste(this.nome);
	    }catch(Exception e){
	    	//e.printStackTrace();
	    	existe = false;
	    }
	    Assert.assertEquals(this.expectedResult, existe);
	}
}
