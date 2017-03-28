import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

public class SistemaBancarioTest {
	
	private SistemaBancario sistemaBancario;
	
	// Implicit Setup
	@Before
	public void criaSistemaBancario(){
		this.sistemaBancario = new SistemaBancario();
	}
	
	// Exercicio 2
	// Caso de Teste 1
	@Test
	public void criacaoDoBancoDoBrasil(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		// Exercise SUT and verify outcome
		assertThat(bancoDoBrasil.obterNome(), equalTo("Banco do Brasil"));
		assertThat(bancoDoBrasil.obterMoeda(), equalTo(Moeda.BRL));
	}
	
	// Caso de Teste 2
	@Test
	public void criacaoDaAgenciaCentro(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		// Exercise SUT and verify outcome
		assertThat(centro.obterIdentificador(), equalTo("001"));
		assertThat(centro.obterNome(), equalTo("Centro"));
		assertThat(centro.obterBanco(), equalTo(bancoDoBrasil));
	}
	
	// Caso de Teste 3
	@Test
	public void criacaoDaContaDaMaria(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		Conta contaDaMaria = criaContaDaMaria(centro);
		// Exercise SUT and verify outcome
		assertThat(contaDaMaria.obterIdentificador(), equalTo("0001-5"));
		assertThat(contaDaMaria.obterTitular(), equalTo("Maria"));
		assertThat(contaDaMaria.calcularSaldo().zero(), equalTo(true));
		assertThat(contaDaMaria.obterAgencia(), equalTo(centro));
	}
	
	// Caso de Teste 4
	@Test
	public void realizarOperacaoDeDeposito(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		Conta contaDaMaria = criaContaDaMaria(centro);
		// In-line Setup
		Dinheiro _10Reais = new Dinheiro(Moeda.BRL, 10, 00);
		Operacao operacao = this.sistemaBancario.depositar(contaDaMaria, _10Reais);
		// Exercise SUT and verify outcome
		assertThat(operacao.obterEstado(), equalTo(EstadosDeOperacao.SUCESSO));
		assertThat(contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala(), equalTo(Integer.valueOf(1000)));
	}
	
	// Caso de Teste 5
	@Test
	public void realizarSaqueDe6ReaisComSaldoDe10Reais(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		Conta contaDaMaria = criaContaDaMaria(centro);
		// In-line Setup
		Dinheiro _10Reais = new Dinheiro(Moeda.BRL, 10, 00);
		this.sistemaBancario.depositar(contaDaMaria, _10Reais);
		// Delegated Setup
		Operacao operacao = saqueDe6Reais(contaDaMaria);
		// Exercise SUT and verify outcome
		assertThat(operacao.obterEstado(), equalTo(EstadosDeOperacao.SUCESSO));
		assertThat(contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala(), equalTo(Integer.valueOf(400)));
	}
	
	// Caso de Teste 6
	@Test
	public void realizarSaqueDe6ReaisComSaldoDe4Reais(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		Conta contaDaMaria = criaContaDaMaria(centro);
		// In-line Setup
		Dinheiro _4Reais = new Dinheiro(Moeda.BRL, 4, 00);
		this.sistemaBancario.depositar(contaDaMaria, _4Reais);
		// Delegated Setup
		Operacao operacao = saqueDe6Reais(contaDaMaria);
		// Exercise SUT and verify outcome
		assertThat(operacao.obterEstado(), equalTo(EstadosDeOperacao.SALDO_INSUFICIENTE));
		assertThat(contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala(), equalTo(Integer.valueOf(400)));
	}
	
	// Exercicio 3
	@Test
	public void testCustomMatcherSuccess() throws Exception {
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		Conta contaDaMaria = criaContaDaMaria(centro);
		// Exercise SUT and verify outcome
		assertThat(contaDaMaria, new CombinadorDeConta("0001-5", "Maria", "Banco do Brasil", "Centro"));
	}
	
	@Test
	public void testCustomMatcherFailure() throws Exception {
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		Conta contaDaMaria = criaContaDaMaria(centro);
		// Exercise SUT and verify outcome
		assertThat(contaDaMaria, new CombinadorDeConta("0001-5", "Marua", "Banco do Brasil", "Trindade"));
	}
	
	// Métodos uteis
	public Banco criaBancoDoBrasil(){
		return sistemaBancario.criarBanco("Banco do Brasil", Moeda.BRL);
	}
	
	public Agencia criaAgenciaDoCentro(Banco banco){
		return banco.criarAgencia("Centro");
	}
	
	public Conta criaContaDaMaria(Agencia agencia){
		return agencia.criarConta("Maria");
	}
	
	public Operacao saqueDe6Reais(Conta conta){
		Dinheiro _6Reais = new Dinheiro(Moeda.BRL, 6, 00);
		return this.sistemaBancario.sacar(conta, _6Reais);
	}

}
