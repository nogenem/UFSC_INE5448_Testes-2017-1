package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.AnalisadorDeJogos;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Carta;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Naipe;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.RankingDeMao;
import br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo.Valor;

public class TesteAnalisadorDeJogo {
	
	//https://pt.wikipedia.org/wiki/Tabela_de_jogadas_do_p%C3%B4quer
	
	@Test
	public void parDeAs() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.ESPADAS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.COPAS));
		cartas.add(new Carta(Valor.CINCO,	Naipe.PAUS));
		cartas.add(new Carta(Valor.SETE, 	Naipe.OURO));
		cartas.add(new Carta(Valor.NOVE, 	Naipe.ESPADAS));
		cartas.add(new Carta(Valor.VALETE, 	Naipe.COPAS));
		cartas.add(new Carta(Valor.REI, 	Naipe.PAUS));
		
		assertEquals(RankingDeMao.PAR, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void royalFlush() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.PAUS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.TRÊS,	Naipe.PAUS));
		cartas.add(new Carta(Valor.SEIS, 	Naipe.PAUS));
		
		assertEquals(RankingDeMao.ROYAL_FLUSH, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void royalStraightFlush() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.PAUS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.NOVE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.OITO, 	Naipe.PAUS));
		
		assertEquals(RankingDeMao.STRAIGHT_FLUSH, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void quadraDeAses() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.PAUS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.ESPADAS));
		cartas.add(new Carta(Valor.NOVE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.COPAS));
		
		assertEquals(RankingDeMao.QUADRA, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void fullHouse() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS,	 	Naipe.PAUS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.ESPADAS));
		cartas.add(new Carta(Valor.NOVE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.REI, 	Naipe.COPAS));
		
		assertEquals(RankingDeMao.FULL_HOUSE, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void flush() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.PAUS));
		cartas.add(new Carta(Valor.DOIS,	Naipe.PAUS));
		cartas.add(new Carta(Valor.QUATRO, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.SETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA,	Naipe.PAUS));
		cartas.add(new Carta(Valor.SEIS, 	Naipe.PAUS));
		
		assertEquals(RankingDeMao.FLUSH, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void straight() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA, 	Naipe.ESPADAS));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.NOVE,	Naipe.OURO));
		cartas.add(new Carta(Valor.OITO, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.SETE, 	Naipe.COPAS));
		
		assertEquals(RankingDeMao.STRAIGHT, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void trincaDeAses() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.PAUS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.ESPADAS));
		cartas.add(new Carta(Valor.NOVE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DOIS, 	Naipe.COPAS));
		
		assertEquals(RankingDeMao.TRINCA, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void doisPares() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.ÁS, 		Naipe.PAUS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.ÁS, 		Naipe.OURO));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.REI, 	Naipe.ESPADAS));
		cartas.add(new Carta(Valor.NOVE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DOIS, 	Naipe.COPAS));
		
		assertEquals(RankingDeMao.DOIS_PARES, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
	}
	
	@Test
	public void cartaMaisAlta() throws Exception {
		List<Carta> cartas = new ArrayList<>();
		cartas.add(new Carta(Valor.CINCO, 	Naipe.COPAS));
		cartas.add(new Carta(Valor.REI,		Naipe.PAUS));
		cartas.add(new Carta(Valor.DAMA, 	Naipe.ESPADAS));
		cartas.add(new Carta(Valor.VALETE,	Naipe.PAUS));
		cartas.add(new Carta(Valor.DEZ, 	Naipe.PAUS));
		cartas.add(new Carta(Valor.TRÊS,	Naipe.OURO));
		cartas.add(new Carta(Valor.SEIS, 	Naipe.PAUS));
		
		assertEquals(RankingDeMao.CARTA_ALTA, 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(cartas));
		
		assertEquals(5, cartas.size());
		Carta ultima = cartas.get(cartas.size()-1);
		assertTrue(ultima.éRei());
	}

}
