package testes.gerenciador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import main.gerenciador.GerenciadorDeOcorrencias;
import main.model.Ocorrencia;

public class TesteGerenciadorOcorrencias {

	@Test
	public void cadastraNovaOcorrencia() {
		GerenciadorDeOcorrencias gerenciador = new GerenciadorDeOcorrencias();	
		Ocorrencia ocorrencia = new Ocorrencia("Bug encontrado na classe Avião");
		boolean retorno = gerenciador.cadastraOcorrencia(ocorrencia);
		
		assertTrue(retorno);
		assertEquals(1, gerenciador.getNumeroTotalDeOcorrencias());
	}

}
