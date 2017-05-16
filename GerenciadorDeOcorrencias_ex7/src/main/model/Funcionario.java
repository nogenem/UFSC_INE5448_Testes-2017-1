package main.model;

public class Funcionario {
	
	private static long CURRENT_ID = 0;
	
	private long uid;
	private String nome;
	
	public Funcionario(String nome) {
		this.uid = ++CURRENT_ID;
		this.nome = nome;
	}
	
	public String getNome() {
		return this.nome;
	}

	public long getUid() {
		return this.uid;
	}

}
