package main.gerenciador;

import java.util.ArrayList;
import java.util.List;

import main.model.Projeto;

public class GerenciadorDeProjetos {
	
	private List<Projeto> projetos;
	
	public GerenciadorDeProjetos() {
		this.projetos = new ArrayList<>();
	}

	public int getNumeroDeProjetos() {
		return this.projetos.size();
	}

	public boolean cadastraProjeto(Projeto projeto) {
		return this.projetos.add(projeto);
	}
	
}
