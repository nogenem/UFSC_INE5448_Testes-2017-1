package main.model;

import java.util.HashSet;
import java.util.Set;

public class Projeto {
	
	private static long CURRENT_UID;
	
	private long uid;
	private String nome;
	private Set<Ocorrencia> ocorrencias;
	private Set<Funcionario> participantes;
	
	public Projeto(String nome){
		this.uid = ++CURRENT_UID;
		this.nome = nome;
		this.ocorrencias = new HashSet<>();
		this.participantes = new HashSet<>();
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
	
	public void addOcorrencia(Ocorrencia ocorrencia) throws Exception {
		boolean retorno = this.getOcorrencias().add(ocorrencia);
		if(!retorno)
			throw new Exception("Ocorrencia com uid = " +ocorrencia.getUid()+ 
					" ja faz parte do projeto com uid = " +this.uid+ "!");
		
		//Funcionários responsáveis por ocorrências pertencentes a um projeto
		//devem ser participantes deste projeto
		Funcionario responsavel = ocorrencia.getResponsavel();
		if(responsavel == null)
			throw new Exception("Tentativa de adicionar uma ocorrencia com uid = " +ocorrencia.getUid()+ 
					" ao projeto com uid = " +this.uid+ " sem que a ocorrencia possua um responsavel!");
		if(!this.getParticipantes().contains(responsavel))
			this.addParticipante(responsavel);
	}
	
	public int getNumeroDeOcorrencias() {
		return this.getOcorrencias().size();
	}
	
	public Set<Funcionario> getParticipantes(){
		return this.participantes;
	}
	
	public void addParticipante(Funcionario participante) throws Exception {
		boolean retorno = this.getParticipantes().add(participante);
		if(!retorno)
			throw new Exception("Funcionario com uid = " +participante.getUid()+ 
					" ja participa do projeto com uid = " +this.uid+ "!");
	}
	
	public int getNumeroDeParticipantes(){
		return this.getParticipantes().size();
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
