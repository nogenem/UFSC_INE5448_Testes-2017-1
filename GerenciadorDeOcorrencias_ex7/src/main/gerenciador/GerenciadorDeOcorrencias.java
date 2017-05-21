package main.gerenciador;

import java.util.HashMap;

import main.model.Funcionario;
import main.model.Ocorrencia;
import main.model.Ocorrencia.Prioridade;
import main.model.Ocorrencia.Tipo;

public class GerenciadorDeOcorrencias {
	
	private HashMap<Long, Ocorrencia> ocorrencias;
	
	public GerenciadorDeOcorrencias() {
		this.ocorrencias = new HashMap<>();
	}

	public long cadastraOcorrencia(Prioridade prioridade, Tipo tipo, String resumo, Funcionario func) throws Exception {
		if(!funcionarioPodeSerResponsavelPorMaisOcorrencias(func))
			throw new Exception("Funcionario "+func.getNome()+" ja eh responsavel por 10 ocorrencias!");
		
		Ocorrencia ocorrencia = new Ocorrencia(prioridade, tipo, resumo);
		ocorrencia.setResponsavel(func);
		this.ocorrencias.put(ocorrencia.getUid(), ocorrencia);
		return ocorrencia.getUid();
	}

	private boolean funcionarioPodeSerResponsavelPorMaisOcorrencias(Funcionario funcionario) {
		int value = ocorrencias.values().stream().reduce(0, (sum, ocorrencia) -> { 
			return (ocorrencia.getResponsavel() == funcionario) ? sum + 1 : sum;
		}, (sum1, sum2) -> sum1 + sum2);
		return value < 10;
	}

	public int getNumeroTotalDeOcorrencias() {
		return this.ocorrencias.size();
	}
	
}
