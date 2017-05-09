package br.ufsc.es.projetoPoquer.modelo.torneio.torneioDeUmaMesa.rodada.jogo;

public enum Valor {

	ÁS(1, "A"),
	DOIS(2, "2"), 
	TRÊS(3 , "3"), 
	QUATRO(4, "4"), 
	CINCO(5, "5"), 
	SEIS(6, "6"), 
	SETE(7, "7"), 
	OITO(8, "8"), 
	NOVE(9, "9"), 
	DEZ(10, "10"), 
	VALETE(11, "J"), 
	DAMA(12, "Q"), 
	REI(13, "K");
	
	private int valorNumérico;
	private String valorTextual;
	
	private static final int UM = 1;
	
	Valor(int valorNumérico, String valorTextual) {
		this.valorNumérico = valorNumérico;
		this.valorTextual = valorTextual;
	}
	
	public boolean éUmAMenosQue(Valor outroValor) {
		return (valorNumérico == outroValor.valorNumérico - UM); 
	}
	
	public boolean éMenorQue(Valor outroValor) {
		return valorNumérico < outroValor.valorNumérico;
	}
	
	public boolean éMaiorQue(Valor outroValor) {
		return valorNumérico > outroValor.valorNumérico;
	}
	
	@Override
	public String toString() {
		return valorTextual;
	}
}