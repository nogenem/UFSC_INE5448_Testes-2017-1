package main.model;

public class Projeto {
	
	private static long CURRENT_UID;
	
	private long uid;
	private String nome;
	
	public Projeto(String nome){
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
