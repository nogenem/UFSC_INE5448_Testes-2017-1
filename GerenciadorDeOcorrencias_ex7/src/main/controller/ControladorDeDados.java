package main.controller;

import java.util.HashMap;

import main.model.Funcionario;
import main.model.Projeto;

public class ControladorDeDados {
	
	private HashMap<Long, Funcionario> funcionarios;
	private HashMap<Long, Projeto> projetos;
	
	public ControladorDeDados(){
		this.funcionarios = new HashMap<>();
		this.projetos = new HashMap<>();
	}

	public long cadastraFuncionario(String nome) {
		Funcionario func = new Funcionario(nome);
		this.funcionarios.put(func.getUid(), func);
		return func.getUid();
	}

	public int getNumeroDeFuncionarios() {
		return this.funcionarios.size();
	}

	public long cadastraProjeto(String nome) {
		Projeto proj = new Projeto(nome);
		this.projetos.put(proj.getUid(), proj);
		return proj.getUid();
	}

	public int getNumeroDeProjetos() {
		return this.projetos.size();
	}

}
