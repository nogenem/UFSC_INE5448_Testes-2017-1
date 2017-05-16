package main.gerenciador;

import java.util.ArrayList;
import java.util.List;

import main.model.Funcionario;
import main.model.Ocorrencia;

public class GerenciadorDeOcorrencias {
	
	private List<Ocorrencia> ocorrencias;
	
	public GerenciadorDeOcorrencias() {
		this.ocorrencias = new ArrayList<>();
	}

	public boolean cadastraOcorrencia(Ocorrencia ocorrencia, Funcionario funcionario) throws Exception {
		if(!funcionarioPodeSerResponsavelPorMaisOcorrencias(funcionario))
			throw new Exception("Funcionario "+funcionario.getNome()+" ja eh responsavel por 10 ocorrencias!");
		
		boolean retorno = ocorrencia.setResponsavel(funcionario);
		retorno &= this.ocorrencias.add(ocorrencia);
		return retorno; 
	}

	private boolean funcionarioPodeSerResponsavelPorMaisOcorrencias(Funcionario funcionario) {
		int value = ocorrencias.stream().reduce(0, (sum, ocorrencia) -> { 
			return (ocorrencia.getResponsavel() == funcionario) ? sum + 1 : sum;
		}, (sum1, sum2) -> sum1 + sum2);
		return value < 10;
	}

	public int getNumeroTotalDeOcorrencias() {
		return this.ocorrencias.size();
	}
	
}
