package main.controller;

import java.util.HashMap;

import main.model.Funcionario;
import main.model.Ocorrencia;
import main.model.Ocorrencia.Prioridade;
import main.model.Ocorrencia.Tipo;
import main.model.Projeto;

public class ControladorDeDados {
	
	private static int LIMITE_OCORRENCIAS_POR_FUNCIONARIO = 10;
	
	private HashMap<Long, Funcionario> funcionarios;
	private HashMap<Long, Projeto> projetos;
	private HashMap<Long, Ocorrencia> ocorrenciasAbertas;
	
	public ControladorDeDados(){
		this.funcionarios = new HashMap<>();
		this.projetos = new HashMap<>();
		this.ocorrenciasAbertas = new HashMap<>();
		
		Funcionario.zerarUID();
		Projeto.zerarUID();
		Ocorrencia.zerarUID();
	}
	
	// Funcionario
	public long cadastraFuncionario(String nome) {
		Funcionario func = new Funcionario(nome);
		this.funcionarios.put(func.getUid(), func);
		return func.getUid();
	}

	public int getNumeroDeFuncionarios() {
		return this.funcionarios.size();
	}
	
	public boolean funcionarioEstaCadastrado(long uidFunc) {
		return this.funcionarios.containsKey(uidFunc);
	}
	
	public Funcionario getFuncionario(long uidFunc) throws Exception {
		if(!this.funcionarioEstaCadastrado(uidFunc))
			throw new Exception("Funcionario com uid = " +uidFunc+ " não esta cadastrado!");
		return this.funcionarios.get(uidFunc);
	}
	
	// Projeto
	public long cadastraProjeto(String nome) {
		Projeto proj = new Projeto(nome);
		this.projetos.put(proj.getUid(), proj);
		return proj.getUid();
	}

	public int getNumeroDeProjetos() {
		return this.projetos.size();
	}
	
	public boolean projetoEstaCadastrado(long uidProj) {
		return this.projetos.containsKey(uidProj);
	}
	
	public Projeto getProjeto(long uidProj) throws Exception {
		if(!this.projetoEstaCadastrado(uidProj))
			throw new Exception("Projeto com uid = " +uidProj+ " não esta cadastrado!");
		return this.projetos.get(uidProj);
	}
	
	// Ocorrencia
	public long cadastraOcorrencia(String resumo, Prioridade prioridade, Tipo tipo, 
			long uidFunc, long uidProj) throws Exception {
		Funcionario responsavel = this.getFuncionario(uidFunc);
		Projeto projeto = this.getProjeto(uidProj);
		
		if(!funcionarioPodeSerReponsavelPorMaisOcorrencias(responsavel))
			throw new Exception("Funcionario " +responsavel+ " não pode ser responsavel por mais ocorrencias!");
		
		Ocorrencia ocorrencia = new Ocorrencia(resumo, 
				prioridade, tipo);
		ocorrencia.setResponsavel(responsavel);
		projeto.addOcorrencia(ocorrencia);
		this.ocorrenciasAbertas.put(ocorrencia.getUid(), ocorrencia);
		return ocorrencia.getUid();
	}
	
	public int getNumeroDeOcorrenciasAbertas(){
		return this.ocorrenciasAbertas.size();
	}
	
	public boolean ocorrenciaEstaAberta(long uidOcorrencia) {
		return this.ocorrenciasAbertas.containsKey(uidOcorrencia);
	}
	
	public boolean ocorrenciaEstaCadastrada(long uidOcorrencia) {
		try{ 
			this.getOcorrencia(uidOcorrencia);
			return true;
		}catch(Exception e){
			return false;
		}
	}
	
	public Ocorrencia getOcorrencia(long uidOcorrencia) throws Exception {
		if(this.ocorrenciaEstaAberta(uidOcorrencia))
			return this.ocorrenciasAbertas.get(uidOcorrencia);
		
		for(Projeto proj : this.projetos.values()){
			for(Ocorrencia ocorrencia : proj.getOcorrencias()){
				if(ocorrencia.getUid() == uidOcorrencia)
					return ocorrencia;
			}
		}
		
		throw new Exception("Ocorrencia com uid = " +uidOcorrencia+ " não esta cadastrada!");
	}
	
	public void concluirOcorrencia(long uidOcorrencia) throws Exception {
		Ocorrencia ocorrencia = this.getOcorrencia(uidOcorrencia);
		
		if(ocorrencia.getEstado() != Ocorrencia.Estado.ABERTA)
			throw new Exception("Tentativa de concluir a ocorrencia " +ocorrencia+ " que ja esta concluida!");
		ocorrencia.setEstado(Ocorrencia.Estado.COMPLETADA);
		this.ocorrenciasAbertas.remove(uidOcorrencia);
	}

	private boolean funcionarioPodeSerReponsavelPorMaisOcorrencias(Funcionario responsavel) {
		int sum = 0;
		for(Ocorrencia ocorrencia : this.ocorrenciasAbertas.values()){
			if(ocorrencia.getResponsavel().equals(responsavel))
				sum++;
		}
		return sum < LIMITE_OCORRENCIAS_POR_FUNCIONARIO;
	}

}
