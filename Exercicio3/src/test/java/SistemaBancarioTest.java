import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Banco;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
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
