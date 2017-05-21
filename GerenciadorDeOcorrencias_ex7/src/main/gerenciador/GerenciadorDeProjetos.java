package main.gerenciador;

import java.util.HashMap;

import main.model.Projeto;

public class GerenciadorDeProjetos {
	
	private HashMap<Long, Projeto> projetos;
	
	public GerenciadorDeProjetos() {
		this.projetos = new HashMap<>();
	}

	public int getNumeroDeProjetos() {
		return this.projetos.size();
	}

	public long cadastraProjeto(String nome) {
		Projeto projeto = new Projeto(nome);
		this.projetos.put(projeto.getUid(), projeto);
		return projeto.getUid();
	}
	
}
