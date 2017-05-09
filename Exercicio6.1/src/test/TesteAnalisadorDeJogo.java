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
		List<Carta> parDeAs = new ArrayList<>();
		parDeAs.add(new Carta(Valor.ÁS, Naipe.ESPADAS));
		parDeAs.add(new Carta(Valor.ÁS, Naipe.COPAS));
		parDeAs.add(new Carta(Valor.CINCO, Naipe.PAUS));
		parDeAs.add(new Carta(Valor.SETE, Naipe.OURO));
		parDeAs.add(new Carta(Valor.NOVE, Naipe.ESPADAS));
		parDeAs.add(new Carta(Valor.VALETE, Naipe.COPAS));
		parDeAs.add(new Carta(Valor.REI, Naipe.PAUS));
		assertEquals(RankingDeMao.PAR.toString(), 
				AnalisadorDeJogos.fornecerInstância().fornecerJogo(parDeAs).toString());
	}

}
