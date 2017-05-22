package main.model;

public class Funcionario {
	
	private static long CURRENT_UID = 0;
	
	private long uid;
	private String nome;
	
	public Funcionario(String nome){
		this.uid = ++CURRENT_UID;
		this.nome = nome;
	}

	public long getUid() {
		return uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
