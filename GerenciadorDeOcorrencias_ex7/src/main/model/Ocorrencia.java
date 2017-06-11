package main.model;

import main.exception.OcorrenciaException;

public class Ocorrencia {
	
	public enum Prioridade { ALTA, MEDIA, BAIXA };
	public enum Tipo { TAREFA, BUG, MELHORIA };
	public enum Estado { ABERTA, COMPLETADA };
	
	private static long CURRENT_UID;
	
	private long uid;
	private String resumo;
	private Prioridade prioridade;
	private Tipo tipo;
	private Estado estado;
	private Funcionario responsavel;
	
	public Ocorrencia(String resumo, Prioridade prioridade, Tipo tipo, Funcionario responsavel) {
		this.uid = ++CURRENT_UID;
		this.resumo = resumo;
		this.prioridade = prioridade;
		this.tipo = tipo;
		this.estado = Estado.ABERTA;
		this.responsavel = responsavel;
	}

	public long getUid() {
		return uid;
	}

	public String getResumo() {
		return resumo;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) throws OcorrenciaException {
		if(this.estado != Estado.ABERTA)
			throw new OcorrenciaException("Tentativa de modificar a prioridade da ocorrencia " +this+ 
					" enquanto a mesma não esta aberta!");
		this.prioridade = prioridade;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) throws OcorrenciaException {
		if(this.estado != Estado.ABERTA)
			throw new OcorrenciaException("Tentativa de modificar o responsavel da ocorrencia " +this+ 
					" enquanto a mesma não esta aberta!");
		this.responsavel = responsavel;
	}
	
	public static void zerarUID(){
		CURRENT_UID = 0;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Ocorrencia) {
			Ocorrencia ocorrencia = (Ocorrencia) obj;
			return this.uid == ocorrencia.uid;
		}
		return super.equals(obj);
	}
	
	@Override
	public String toString(){
		return "{"+uid+", "+resumo+"}";
	}
}
