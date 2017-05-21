package main.gerenciador;

import java.util.HashMap;

import main.model.Funcionario;

public class GerenciadorDeFuncionarios {
	
	private HashMap<Long, Funcionario> funcionarios;
	
	public GerenciadorDeFuncionarios() {
		this.funcionarios = new HashMap<>();
	}
	
	public int getNumeroDeFuncionarios() {
		return this.funcionarios.size();
	}

	public long cadastraFuncinario(String nome) {
		Funcionario func = new Funcionario(nome);
		this.funcionarios.put(func.getUid(), func);
		return func.getUid();
	}

	public Funcionario getFuncionario(long uidFunc) throws Exception {
		if(!this.funcionarios.containsKey(uidFunc))
			throw new Exception("Funcionario com uid " +uidFunc+ " não encontrado!");
		return this.funcionarios.get(uidFunc);
	}

}
