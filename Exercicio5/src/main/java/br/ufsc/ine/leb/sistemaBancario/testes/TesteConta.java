package br.ufsc.ine.leb.sistemaBancario.testes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import br.ufsc.ine.leb.projetos.estoria.Fixture;
import br.ufsc.ine.leb.projetos.estoria.FixtureSetup;
import br.ufsc.ine.leb.sistemaBancario.Agencia;
import br.ufsc.ine.leb.sistemaBancario.Conta;
import br.ufsc.ine.leb.sistemaBancario.Dinheiro;
import br.ufsc.ine.leb.sistemaBancario.EstadosDeOperacao;
import br.ufsc.ine.leb.sistemaBancario.Moeda;
import br.ufsc.ine.leb.sistemaBancario.Operacao;
import br.ufsc.ine.leb.sistemaBancario.SistemaBancario;

@FixtureSetup(TesteAgencia.class)
public class TesteConta {
	
	@Fixture private SistemaBancario sistemaBancario;
	@Fixture private Agencia bancoDoBrasilCentro;
	
	private Conta contaDaMaria;
	
	@Before
	public void configurar(){
		contaDaMaria = bancoDoBrasilCentro.criarConta("Maria");
	}
	
	// Caso de Teste 3
	@Test
	public void testContaDaMaria() {
		assertEquals("0001-5", contaDaMaria.obterIdentificador());
		assertEquals("Maria", contaDaMaria.obterTitular());
		assertTrue(contaDaMaria.calcularSaldo().zero());
		assertEquals(bancoDoBrasilCentro, contaDaMaria.obterAgencia());
	}
	
	// Caso de Teste 4
	@Test
	public void realizarOperacaoDeDeposito() {
		// In-line Setup
		Dinheiro _10Reais = new Dinheiro(Moeda.BRL, 10, 00);
		Operacao operacao = sistemaBancario.depositar(contaDaMaria, _10Reais);
		// Exercise SUT and verify outcome
		assertEquals(EstadosDeOperacao.SUCESSO, operacao.obterEstado());
		assertEquals(Integer.valueOf(1000), contaDaMaria.calcularSaldo().obterQuantia().obterQuantiaEmEscala());
	}
	
	// Caso de Teste 5
	@Test
	public void realizarSaqueDe6ReaisComSaldoDe10Reais() {
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
	public Operacao saqueDe6Reais(Conta conta){
		Dinheiro _6Reais = new Dinheiro(Moeda.BRL, 6, 00);
		return sistemaBancario.sacar(conta, _6Reais);
	}

}
