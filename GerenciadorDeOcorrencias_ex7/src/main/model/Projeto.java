package main.model;

public class Projeto {
	
	private static long CURRENT_ID = 0;
	
	private long uid;
	private String nome;
	
	public Projeto(String nome) {
		this.uid = ++CURRENT_ID;
		this.nome = nome;
	}
	
	public long getUid(){
		return this.uid;
	}
	
	public String getNome(){
		return this.nome;
	}
}
