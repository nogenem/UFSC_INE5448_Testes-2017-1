package main.controller;

import java.util.HashMap;

import main.model.Funcionario;

public class ControladorDeDados {
	
	private HashMap<Long, Funcionario> funcionarios;
	
	public ControladorDeDados(){
		this.funcionarios = new HashMap<>();
	}

	public long cadastraFuncionario(String nome) {
		Funcionario func = new Funcionario(nome);
		this.funcionarios.put(func.getUid(), func);
		return func.getUid();
	}

	public int getNumeroDeFuncionarios() {
		return this.funcionarios.size();
	}

}
