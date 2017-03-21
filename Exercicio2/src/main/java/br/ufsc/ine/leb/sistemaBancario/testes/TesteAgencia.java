package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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

public class TesteAgencia {
	
	private SistemaBancario sistemaBancario;
	
	// Implicit Setup
	@Before
	public void criaSistemaBancario(){
		this.sistemaBancario = new SistemaBancario();
	}
	
	// Exemplo
	@Test
	public void caixaEconomicaTrindade() throws Exception {
		SistemaBancario sistemaBancario = new SistemaBancario();
		Banco caixaEconomica = sistemaBancario.criarBanco("Caixa Econômica", Moeda.BRL);
		Agencia caixaEconomicaTrindade = caixaEconomica.criarAgencia("Trindade");
		assertEquals("001", caixaEconomicaTrindade.obterIdentificador());
		assertEquals("Trindade", caixaEconomicaTrindade.obterNome());
		assertEquals(caixaEconomica, caixaEconomicaTrindade.obterBanco());
	}
	
	// Caso de Teste 1
	@Test
	public void criacaoDoBancoDoBrasil(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		// Exercise SUT and verify outcome
		assertEquals("Banco do Brasil", bancoDoBrasil.obterNome());
		assertEquals(Moeda.BRL, bancoDoBrasil.obterMoeda());
	}
	
	// Caso de Teste 2
	@Test
	public void criacaoDaAgenciaCentro(){
		// Implicit Setup
		// Delegated Setup
		Banco bancoDoBrasil = criaBancoDoBrasil();
		Agencia centro = criaAgenciaDoCentro(bancoDoBrasil);
		// Exercise SUT and verify outcome
		assertEquals("001", centro.obterIdentificador());
		assertEquals("Centro", centro.obterNome());
		assertEquals(bancoDoBrasil, centro.obterBanco());
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
		assertEquals("0001-5", contaDaMaria.obterIdentificador());
		assertEquals("Maria", contaDaMaria.obterTitular());
		assertTrue(contaDaMaria.calcularSaldo().zero());
		assertEquals(centro, contaDaMaria.obterAgencia());
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
		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(Integer.valueOf(1000), contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala());
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
		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(Integer.valueOf(400), contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala());
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
		assertEquals(EstadosDeOperacao.SALDO_INSUFICIENTE, operacao.obterEstado());
		assertEquals(Integer.valueOf(400), contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala());
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
