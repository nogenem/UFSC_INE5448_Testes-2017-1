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

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Set<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}
	
	public boolean addOcorrencia(Ocorrencia ocorrencia){
		return this.ocorrencias.add(ocorrencia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Projeto other = (Projeto) obj;
		return uid == other.uid;
	}
	
}
