package main.gerenciador;

import java.util.ArrayList;
import java.util.List;

import main.model.Funcionario;

public class GerenciadorDeFuncionarios {
	
	private List<Funcionario> funcionarios;
	
	public GerenciadorDeFuncionarios() {
		this.funcionarios = new ArrayList<>();
	}
	
	public int getNumeroDeFuncionarios() {
		return this.funcionarios.size();
	}

	public boolean cadastraFuncinario(Funcionario funcionario) {
		return this.funcionarios.add(funcionario);
	}

}
