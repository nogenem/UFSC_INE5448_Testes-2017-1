package main.model;

import java.util.HashSet;
import java.util.Set;

public class Projeto {
	
	private static long CURRENT_UID;
	
	private long uid;
	private String nome;
	private Set<Ocorrencia> ocorrencias;
	
	public Projeto(String nome){
		this.uid = ++CURRENT_UID;
		this.nome = nome;
		this.ocorrencias = new HashSet<>();
	}

	public long getUid() {
		return uid;
	}

	public String getNome() {
		return nome;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	
	public boolean addOcorrencia(Ocorrencia ocorrencia){
		return this.ocorrencias.add(ocorrencia);
	}
	
	public static void zerarUID(){
		CURRENT_UID = 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Projeto) {
			Projeto proj = (Projeto) obj;
			return this.uid == proj.uid;
		}
		return super.equals(obj);
	}
	
}
