package main.model;

import java.util.HashSet;
import java.util.Set;

import main.exception.ProjetoException;

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
		boolean ocorrenciaAdicionada = this.getOcorrencias().add(ocorrencia);
		if(!ocorrenciaAdicionada)
			throw new ProjetoException("Ocorrencia " +ocorrencia+ " ja faz parte do projeto " +this+ "!");
		
		//Funcionários responsáveis por ocorrências pertencentes a um projeto
		//devem ser participantes deste projeto
		Funcionario responsavel = ocorrencia.getResponsavel();
		if(responsavel == null)
			throw new ProjetoException("Tentativa de adicionar ocorrencia " +ocorrencia+ " ao projeto " +this+ 
					" sem que a ocorrencia possua um responsavel!");
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
		boolean participanteAdicionado = this.getParticipantes().add(participante);
		if(!participanteAdicionado)
			throw new ProjetoException("Funcionario " +participante+ " ja participa do projeto " +this+ "!");
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
	
	@Override
	public String toString(){
		return "{"+uid+", "+nome+"}";
	}
}
