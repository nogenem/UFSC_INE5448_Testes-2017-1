package acceptanceTests.cucumberSteps;

import org.junit.Assert;

import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import modelo.FachadaMercadoLeilaoComSerializacao;

public class CadastrarUsuarioTest {

	public String nome = "";
	public String endereco = "";
	public String cpf = "";
	public String email = "";
	public Boolean expectedResult = true;

	FachadaMercadoLeilaoComSerializacao fachada = new FachadaMercadoLeilaoComSerializacao();

	@After
	public void tearDown() {
//		this.fachada.montarMercado();
		this.fachada.limparMercado();
	}

	@Given("^O nome de usuario \"([^\"]*)\"$")
	public void o_nome_de_usuario(String arg1) {
		this.nome = arg1;
	}

	@Given("^o enderco \"([^\"]*)\"$")
	public void o_enderco(String arg1) {
		this.endereco = arg1;
	}

	@Given("^e o CPF \"([^\"]*)\"$")
	public void e_o_CPF(String arg1) {
		this.cpf = arg1;
	}

	@Given("^e o e-mail \"([^\"]*)\"$")
	public void e_o_e_mail(String arg1) throws Throwable {
		this.email = arg1;
	}

	@When("^O usuario nao existir anteriormente$")
	public void o_usuario_nao_existir_anteriormente() {
		try {
			if (this.fachada.getUsuarioPor(this.cpf).getCpf().equals(this.cpf)) {
				// System.out.println("Usuário encontrado");
				this.expectedResult = false;
			} else {
				// System.out.println("Usuário não encontrado");
				this.expectedResult = true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			this.expectedResult = true;
			// e.printStackTrace();
		}
	}

	@Then("^O sistema deve cadastrar o usuario com sucesso$")
	public void o_sistema_deve_cadastrar_o_usuario_com_sucesso() {
		Boolean cadastro = false;
		try {
			this.fachada.cadastrarUsuario(this.nome, this.endereco, this.email, this.cpf);
			cadastro = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Assert.assertEquals(this.expectedResult, cadastro);

	}
}
