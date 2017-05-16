package main.gerenciador;

import java.util.ArrayList;
import java.util.List;

import main.model.Ocorrencia;

public class GerenciadorDeOcorrencias {
	
	private List<Ocorrencia> ocorrencias;
	
	public GerenciadorDeOcorrencias() {
		this.ocorrencias = new ArrayList<>();
	}

	public boolean cadastraOcorrencia(Ocorrencia ocorrencia) {
		return this.ocorrencias.add(ocorrencia);
	}

	public int getNumeroTotalDeOcorrencias() {
		return this.ocorrencias.size();
	}
	
}
